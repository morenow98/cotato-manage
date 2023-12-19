package cotato.cotatomanage.dto.response;

import cotato.cotatomanage.domain.Member;
import cotato.cotatomanage.domain.enums.Part;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberResponse implements Comparable<MemberResponse> {

    private String name;
    private int period;
    private int age;
    private Part part;
    private int ability;

    public String getPeriod() {
        return period + Member.LITERAL_PERIOD;
    }

    public String getPart() {
        return part.getValue();
    }

    public static MemberResponse of(Member member, int currentPeriod) {
        return MemberResponse.builder()
                .name(member.getName())
                .period(member.getPeriod())
                .age(member.getAge())
                .part(member.getPart())
                .ability(member.calculateAbility(currentPeriod, LocalDate.now()))
                .build();
    }

    @Override
    public int compareTo(MemberResponse o) {
        if (this.ability != o.ability) {
            return o.ability - this.ability;
        }
        if (this.age != o.age) {
            return o.age - this.age;
        }
        if (this.period != o.period) {
            return this.period - o.period;
        }
        return this.name.compareTo(o.name);
    }
}
