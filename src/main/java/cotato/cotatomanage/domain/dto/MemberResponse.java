package cotato.cotatomanage.domain.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberResponse implements Comparable<MemberResponse> {

    private String name;
    private String period;
    private int age;
    private String part;
    private int ability;

    @Override
    public int compareTo(MemberResponse o) {
        if (this.ability > o.ability) {
            return -1;
        }
        if (this.ability < o.ability) {
            return 1;
        }
        return compareByPeriod(this, o);
    }

    private int compareByPeriod(MemberResponse member, MemberResponse o) {
        if (member.period.compareTo(o.period) > 0) {
            return -1;
        }
        if (member.period.compareTo(o.period) < 0) {
            return 1;
        }
        return compareByName(member, o);
    }

    private int compareByName(MemberResponse member, MemberResponse o) {
        if (member.name.compareTo(o.name) > 0) {
            return 1;
        }
        return -1;
    }
}
