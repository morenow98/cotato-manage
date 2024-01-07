package cotato.cotatomanage.domain.entity;

import cotato.cotatomanage.domain.Part;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {

    private String name;
    private Integer period;
    private Integer age;
    private Part part;

//    private Integer ability;

    @Builder
    public Member(String name, Integer period, Integer age, Part part){
        this.name = name;
        this.period = period;
        this.age = age;
        this.part = part;
    }
}
