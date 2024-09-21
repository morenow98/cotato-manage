package cotato.cotatomanage.service;

import cotato.cotatomanage.domain.Part;
import cotato.cotatomanage.domain.dto.JoinMemberRequest;
import cotato.cotatomanage.domain.dto.MemberResponse;
import cotato.cotatomanage.domain.dto.PartResponse;
import cotato.cotatomanage.domain.entity.Member;
import cotato.cotatomanage.repository.MemberRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private static final String PERIOD_END = "기";
    private static final String NONE = "";

    private final MemberRepository memberRepository;
    private final ValidateService validateService;

    public void createMember(JoinMemberRequest request) {
        int period = convertPeriodNumber(request.getPeriod());
        Part part = Part.getPart(request.getPart());
        validateService.checkAge(request.getAge());

        Member createdMember = Member.builder()
                .name(request.getName())
                .period(period)
                .age(request.getAge())
                .part(part)
                .ability(request.getAge())
                .build();
        memberRepository.save(createdMember);
        log.info("동아리원 등록 완료: {}", request.getName());
    }

    private int convertPeriodNumber(String period) {
        return Integer.parseInt(period.replace(PERIOD_END, NONE));
    }

    public List<MemberResponse> getAllMember(int period) {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(member -> buildMemberResponse(member, period))
                .sorted(MemberResponse::compareTo)
                .collect(Collectors.toList());
    }

    private MemberResponse buildMemberResponse(Member member, int period) {
        return MemberResponse.builder()
                .name(member.getName())
                .period(member.getPeriod() + PERIOD_END)
                .age(member.getAge())
                .part(member.getPart().getKey())
                .ability(member.calculateEachAbility(period))
                .build();
    }

    public List<MemberResponse> getPartMembers(String part, int period) {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .filter(member -> Part.getPart(part).equals(member.getPart()))
                .map(member -> buildMemberResponse(member, period))
                .collect(Collectors.toList());
    }

    public List<PartResponse> getAllPart(int period) {
        return Arrays.stream(Part.values())
                .map(part -> getPartResponse(part, period)).filter(Objects::nonNull)
                .sorted(PartResponse::compareTo)
                .toList();
    }

    private PartResponse getPartResponse(Part part, int period) {
        List<Member> partMember = memberRepository.findByPart(part);
        partMember.forEach(member -> member.calculateEachAbility(period));
        if (partMember.isEmpty()) {
            return null;
        }

        int average = calculateAbilityAverage(partMember);
        return PartResponse.builder()
                .part(part.getKey())
                .ability(average)
                .count(partMember.size())
                .build();
    }

    private int calculateAbilityAverage(List<Member> partMember) {
        return (int) partMember.stream()
                .mapToInt(Member::getAbility)
                .average()
                .orElse(0);
    }
}
