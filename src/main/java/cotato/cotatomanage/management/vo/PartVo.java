package cotato.cotatomanage.management.vo;

import cotato.cotatomanage.management.domain.Member;
import cotato.cotatomanage.management.domain.Part;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PartVo {
    private Part part;
    private int ability;
    private int count;

    @Builder
    private PartVo(Part part, int ability, int count) {
        this.part = part;
        this.ability = ability;
        this.count = getCount();
    }

    public static PartVo of(Part part, List<Member> memberList, int periodNow) {
        return PartVo.builder()
                .part(part)
                .ability(calcAbilityStats(memberList, periodNow))
                .count(memberList.size())
                .build();
    }

    private static int calcAbilityStats(List<Member> memberList, int periodNow) {
        if (memberList == null || memberList.isEmpty()) {
            return 0;
        }

        int sum = 0;
        for (Member member : memberList) {
            sum += member.getAbility(periodNow);
        }
        return (int) sum / memberList.size();
    }
}