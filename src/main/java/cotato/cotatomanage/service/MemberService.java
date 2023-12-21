package cotato.cotatomanage.service;

import cotato.cotatomanage.domain.Part;
import cotato.cotatomanage.domain.dto.JoinMemberRequest;
import cotato.cotatomanage.domain.dto.MemberResponse;
import cotato.cotatomanage.domain.entity.Member;
import cotato.cotatomanage.repository.MemberRepository;
import java.util.List;
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

    public List<MemberResponse> getAllMember() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .sorted(Member::compareTo)
                .map(this::buildResponse)
                .collect(Collectors.toList());
    }

    private MemberResponse buildResponse(Member member) {
        return MemberResponse.builder()
                .name(member.getName())
                .period(member.getPeriod() + PERIOD_END)
                .age(member.getAge())
                .part(member.getPart().getKey())
                .ability(member.getAbility())
                .build();
    }
}
