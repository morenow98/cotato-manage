package cotato.cotatomanage.repository;


import cotato.cotatomanage.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemoryMemberRepository {

    private final List<Member> memberRepository = new ArrayList<>();

    public void save(Member member){
        memberRepository.add(member);
    }
}
