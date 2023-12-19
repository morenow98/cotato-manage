package cotato.cotatomanage.domain;

import cotato.cotatomanage.domain.dto.OrderByPartResponse;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

@Getter
public class MemberRepository {
    private List<Member> members = new ArrayList<>();
    @Getter
    private static MemberRepository instance = new MemberRepository();

    public void addMember(Member member) {
        members.add(member);
    }

    public void calculateAbility(int currentPeriod){
        members.forEach(member -> member.updateAbility(currentPeriod));
    }

    public Queue<OrderByPartResponse> orderByPart() {
        PriorityQueue<OrderByPartResponse> list = new PriorityQueue<>();
        for (Part part : Part.values()) {
            OrderByPartResponse data = makeOrderByPartResponse(part);
            if (data!=null) {
                list.add(data);
            }
        }
        return list;
    }

    private OrderByPartResponse makeOrderByPartResponse(Part part) {
        List<Member> findMembersByPart = findAllMembersByPart(part);
        int count = findMembersByPart.size();
        if(count==0){
            return null;
        }
        int average = (findMembersByPart.stream().mapToInt(Member::getAbility).sum())/count;

        return OrderByPartResponse.builder()
                .part(part)
                .ability(average)
                .count(count)
                .build();
    }

    private List<Member> findAllMembersByPart(Part part) {
        return members.stream().filter(member -> member.getPart().equals(part)).toList();
    }
}
