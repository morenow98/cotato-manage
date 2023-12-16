package cotato.cotatomanage.repository;

import cotato.cotatomanage.domain.Member;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ManageRepository {

    private final List<Member> members = new ArrayList<>();

    public void save(Member member) {
        members.add(member);
    }
}
