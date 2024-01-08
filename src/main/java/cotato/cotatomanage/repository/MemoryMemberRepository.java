package cotato.cotatomanage.repository;


import cotato.cotatomanage.domain.Part;
import cotato.cotatomanage.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MemoryMemberRepository {

    private final List<Member> memberRepository = new ArrayList<>();

    public void save(Member member){
        memberRepository.add(member);
    }

    public List<Member> findByPart(Part part){
        return memberRepository.stream()
                .filter(m -> m.getPart() == part)
                .collect(Collectors.toList());
    }

    public List<Member> findAll(){
        return memberRepository.stream()
                .collect(Collectors.toList());
    }
}
