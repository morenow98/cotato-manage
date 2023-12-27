package cotato.cotatomanage.service;

import cotato.cotatomanage.domain.Member;
import cotato.cotatomanage.domain.enums.Part;
import cotato.cotatomanage.dto.request.MemberRegistrationRequest;
import cotato.cotatomanage.dto.response.MemberResponse;
import cotato.cotatomanage.dto.response.PartResponse;
import cotato.cotatomanage.repository.ManageRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManageService {

    private final ManageRepository manageRepository;

    public void registerMember(MemberRegistrationRequest memberRegistrationRequest) {
        Member member = memberRegistrationRequest.toEntity();
        manageRepository.save(member);
    }

    private Optional<PartResponse> createPartResponse(Part part, int currentPeriod) {
        List<Member> members = manageRepository.findByPart(part);
        OptionalDouble averageAbility = members.stream()
                .mapToInt(member -> member.calculateAbility(currentPeriod, LocalDate.now()))
                .average();

        if (averageAbility.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(PartResponse.builder()
                .part(part)
                .ability((int) averageAbility.getAsDouble())
                .count(members.size())
                .build());
    }

    public List<PartResponse> getParts(int currentPeriod) {
        List<PartResponse> partResponses = new ArrayList<>(Arrays.stream(Part.values())
                .map(part -> createPartResponse(part, currentPeriod))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList());

        Collections.sort(partResponses);

        return partResponses;
    }

    public List<MemberResponse> getMembers(int currentPeriod, Optional<String> part) {
        if (part.isPresent()) {
            List<Member> members = manageRepository.findByPart(Part.fromValue(part.get()));
            return getMembers(currentPeriod, members);
        }
        List<Member> members = manageRepository.findAll();
        return getMembers(currentPeriod, members);
    }

    private List<MemberResponse> getMembers(int currentPeriod, List<Member> members) {
        List<MemberResponse> memberResponses = new ArrayList<>(members.stream()
                .map(member -> MemberResponse.of(member, currentPeriod))
                .toList());

        Collections.sort((memberResponses));

        return memberResponses;
    }
}
