package cotato.cotatomanage.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MemberRepository {
    private List<Member> members = new ArrayList<>();
    @Getter
    private static MemberRepository instance = new MemberRepository();

    public void addMember(Member member) {
        members.add(member);
    }
}
