package cotato.cotatomanage.repository;


import cotato.cotatomanage.domain.Part;
import cotato.cotatomanage.domain.entity.Member;
import java.util.List;

public interface MemberRepository {

    void save(Member member);

    List<Member> findAll();

    List<Member> findByPart(Part part);
}
