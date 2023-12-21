package cotato.cotatomanage.domain.entity;

import cotato.cotatomanage.domain.Part;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {

    private String name;
    private int period;
    private int age;
    private Part part;
    private int ability;

    @Builder
    public Member(String name, int period, int age, Part part, int ability) {
        this.name = name;
        this.period = period;
        this.age = age;
        this.part = part;
        this.ability = ability;
    }
}
