package cotato.cotatomanage.service;

import cotato.cotatomanage.domain.Member;
import cotato.cotatomanage.domain.enums.Part;
import cotato.cotatomanage.dto.request.MemberRegistrationRequest;
import cotato.cotatomanage.dto.response.PartResponse;
import cotato.cotatomanage.repository.ManageRepository;
import java.time.LocalDate;
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
        List<PartResponse> partResponses = new java.util.ArrayList<>(Arrays.stream(Part.values())
                .map(part -> createPartResponse(part, currentPeriod))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList());

        Collections.sort(partResponses);

        return partResponses;
    }
}
