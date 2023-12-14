package cotato.cotatomanage.domain;

import cotato.cotatomanage.domain.enums.Part;
import lombok.Builder;

public class Member {

    private static int memberNumber;

    private int id;
    private String name;
    private int period;
    private int age;
    private Part part;

    @Builder
    public Member(String name, int period, int age, Part part) {
        this.id = memberNumber++;
        this.name = name;
        this.period = period;
        this.age = age;
        this.part = part;
    }

}
