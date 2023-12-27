package cotato.cotatomanage.repository;

import cotato.cotatomanage.domain.Member;
import cotato.cotatomanage.domain.enums.Part;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class ManageRepository {

    private final List<Member> members = new ArrayList<>();

    public void save(Member member) {
        members.add(member);
    }

    public List<Member> findAll() {
        return members;
    }

    public List<Member> findByPart(Part part) {
        return members.stream()
                .filter(member -> member.getPart() == part)
                .collect(Collectors.toList());
    }
}
