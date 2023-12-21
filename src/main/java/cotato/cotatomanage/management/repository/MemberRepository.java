package cotato.cotatomanage.management.repository;


import cotato.cotatomanage.management.domain.Member;
import cotato.cotatomanage.management.domain.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findAllByPart(Part part);
}
