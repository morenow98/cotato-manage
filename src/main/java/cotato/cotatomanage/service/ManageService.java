package cotato.cotatomanage.service;

import cotato.cotatomanage.entity.Member;
import cotato.cotatomanage.entity.Part;
import cotato.cotatomanage.repository.MemberRepository;
import cotato.cotatomanage.dto.AddMemberRequest;
import cotato.cotatomanage.dto.OrderByPartResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Queue;

@Service
@Slf4j
public class ManageService {
    MemberRepository memberRepository = MemberRepository.getInstance();
    public void addMember(AddMemberRequest request) {
        Member member = Member.builder()
                .age(request.getAge())
                .part(request.getPart())
                .name(request.getName())
                .period(request.getPeriod())
                .build();
        memberRepository.addMember(member);
        memberRepository.getMembers()
                .forEach(member1 -> log.info(member1.getName() + " " + member1.getPart().name() + " " + member1.getAbility()));
    }

    public List<OrderByPartResponse> printByPart(int period) {
        log.info("printByPart Start");
        memberRepository.calculateAbility(period);
        return memberRepository.orderByPart();
    }

    public List<Member> printAllMembers(int period) {
        log.info("printAllMembers Start");
        memberRepository.calculateAbility(period);
        return memberRepository.orderAllMembers();
    }

    public List<Member> printAllMembersByPart(int period, String part) {
        log.info("printAllMembersByPart Start");
        memberRepository.calculateAbility(period);
        return memberRepository.orderAllMembersByPart(part);
    }
}
