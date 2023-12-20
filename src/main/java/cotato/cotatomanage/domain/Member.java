package cotato.cotatomanage.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {

    private String name;
    private int period;
    private int age;
    private Part part;
    private Ability ability;

    @Builder
    public Member(String name, int period, int age, Part part) {
        this.name = name;
        this.period = period;
        this.age = age;
        this.part = part;
    }
}
