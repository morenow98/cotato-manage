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

//    private Integer ability;

    @Builder
    public Member(String name, Integer period, Integer age, Part part, Integer ability){
        this.name = name;
        this.period = period;
        this.age = age;
        this.part = part;
        this.ability = ability;
    }
}
