package cotato.cotatomanage.domain;

import cotato.cotatomanage.domain.dto.OrderByPartResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

@Getter
@Slf4j
public class MemberRepository {
    private List<Member> members = new ArrayList<>();
    @Getter
    private static MemberRepository instance = new MemberRepository();

    public void addMember(Member member) {
        members.add(member);
    }

    public void calculateAbility(int currentPeriod){
        members.forEach(member -> member.updateAbility(currentPeriod));
        log.info("calculateAbility finished");
    }

    public List<Member> orderAllMembers() {
        return printMemberWithOrder(members);
    }

    public List<OrderByPartResponse> orderByPart() {
        PriorityQueue<OrderByPartResponse> list = new PriorityQueue<>();
        for (Part part : Part.values()) {
            OrderByPartResponse data = makeOrderByPartResponse(part);
            if (data!=null) {
                list.add(data);
            }
        }
        List<OrderByPartResponse> orderedList = new ArrayList<>();
        while (!list.isEmpty()){
            OrderByPartResponse tmp = list.poll();
            log.info(tmp.getPart().name());
            orderedList.add(tmp);
        }
        return orderedList;
    }

    public List<Member> orderAllMembersByPart(String part) {
        Part enumPart = Part.valueOf(part);
        List<Member> members = findAllMembersByPart(enumPart);
        return printMemberWithOrder(members);
    }

    private List<Member> printMemberWithOrder(List<Member> members) {
        PriorityQueue<Member> list = new PriorityQueue<>();
        list.addAll(members);
        List<Member> orderedList = new ArrayList<>();
        while (!list.isEmpty()) {
            Member tmp = list.poll();
            orderedList.add(tmp);
        }
        return orderedList;
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
