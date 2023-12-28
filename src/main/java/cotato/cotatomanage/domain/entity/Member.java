package cotato.cotatomanage.domain.entity;

import cotato.cotatomanage.domain.Part;
import java.time.LocalDate;
import java.time.Month;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Member implements Comparable<Member> {

    private static final int AGE_LIMIT = 27;
    private static final int MONTH_BONUS = 10;
    private static final int PERIOD_BONUS = 2;

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

    public int calculateEachAbility(int currentPeriod) {
        Month month = LocalDate.now().getMonth();
        return ability + calculateByMonth(month) + calculateByPeriod(currentPeriod);
    }

    private int calculateByPeriod(int currentPeriod) {
        int bonusPeriod = (int) (currentPeriod - period);
        return bonusPeriod * PERIOD_BONUS;
    }

    private int calculateByMonth(Month month) {
        if (isOverAge(age)) {
            return 0;
        }
        if (isBonusMonth(month)) {
            return MONTH_BONUS;
        }
        return 0;
    }

    private boolean isBonusMonth(Month month) {
        return part.getAdditionalAbility().contains(month);
    }

    private boolean isOverAge(int age) {
        return age >= AGE_LIMIT;
    }

    @Override
    public int compareTo(Member o) {
        if (this.ability > o.ability) {
            return -1;
        }
        if (this.ability < o.ability) {
            return 1;
        }
        return compareByPeriod(this, o);
    }

    private int compareByPeriod(Member member, Member o) {
        if (member.period > o.period) {
            return -1;
        }
        if (member.period < o.period) {
            return 1;
        }
        return compareByName(member, o);
    }

    private int compareByName(Member member, Member o) {
        if (member.name.compareTo(o.name) > 0) {
            return 1;
        }
        return -1;
    }
}
