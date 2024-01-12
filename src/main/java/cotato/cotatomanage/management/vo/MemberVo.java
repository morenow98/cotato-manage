package cotato.cotatomanage.management.vo;

import cotato.cotatomanage.management.domain.Member;
import cotato.cotatomanage.management.domain.Part;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberVo {

    private String name;
    private int period;
    private int age;
    private Part part;
    private int ability;

    @Builder
    private MemberVo(String name, int period, int age, Part part, int ability) {
        this.name = name;
        this.period = period;
        this.age = age;
        this.part = part;
        this.ability = ability;
    }

    public static MemberVo of(Member member, int periodNow) {
        return MemberVo.builder()
                .name(member.getName())
                .period(member.getPeriod())
                .age(member.getAge())
                .part(member.getPart())
                .ability(member.getAbility(periodNow))
                .build();
    }
}