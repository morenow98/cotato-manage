package cotato.cotatomanage.management.vo;

import cotato.cotatomanage.management.domain.Member;
import cotato.cotatomanage.management.domain.Part;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PartInfoVo {
    private Part part;
    private int ability;
    private int count;

    @Builder
    private PartInfoVo(Part part, int ability, int count) {
        this.part = part;
        this.ability = ability;
        this.count = getCount();
    }

    public static PartInfoVo of(Part part, List<Member> memberList, int periodNow) {
        return PartInfoVo.builder()
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
            sum += member.calcAbility(periodNow);
        }
        return (int) sum / memberList.size();
    }
}
