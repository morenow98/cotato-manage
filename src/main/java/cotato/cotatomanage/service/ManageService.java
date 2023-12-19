package cotato.cotatomanage.service;

import cotato.cotatomanage.domain.Member;
import cotato.cotatomanage.domain.MemberRepository;
import cotato.cotatomanage.domain.dto.AddMemberRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
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
}
