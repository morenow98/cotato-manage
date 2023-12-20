package cotato.cotatomanage.service;

import cotato.cotatomanage.domain.Member;
import cotato.cotatomanage.domain.Part;
import cotato.cotatomanage.domain.dto.JoinMemberRequest;
import cotato.cotatomanage.repository.MemberRepository;
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
        Part part = Part.valueOf(request.getPart());
        validateService.checkAge(request.getAge());

        Member createdMember = Member.builder()
                .name(request.getName())
                .period(period)
                .age(request.getAge())
                .part(part)
                .build();
        memberRepository.save(createdMember);
        log.info("동아리원 등록 완료: {}", request.getName());
    }

    private int convertPeriodNumber(String period) {
        return Integer.parseInt(period.replace(PERIOD_END, NONE));
    }
}
