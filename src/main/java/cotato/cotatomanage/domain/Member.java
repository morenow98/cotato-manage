package cotato.cotatomanage.domain;

import cotato.cotatomanage.domain.enums.Event;
import cotato.cotatomanage.domain.enums.Part;
import java.time.LocalDate;
import lombok.Builder;

public class Member {

    private static int memberNumber;
    private static final int INELIGIBLE_MIN_AGE_FOR_EVENT = 27;

    private final int id;
    private String name;
    private int period;
    private int age;
    private Part part;

    @Builder
    public Member(String name, int period, int age, Part part) {
        this.id = ++memberNumber;
        this.name = name;
        this.period = period;
        this.age = age;
        this.part = part;
    }

    public int calculateAbility(int currentPeriod, LocalDate now) {
        int ability = this.age;
        ability += currentPeriod - this.period;
        if (age < INELIGIBLE_MIN_AGE_FOR_EVENT) {
            ability += Event.getIncreasingAbility(this, now);
        }
        return ability;
    }
}
