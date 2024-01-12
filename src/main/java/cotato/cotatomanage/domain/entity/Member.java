package cotato.cotatomanage.domain.entity;

import cotato.cotatomanage.domain.Part;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Arrays;

@Getter
@Slf4j
public class Member implements Comparable<Member> {
    private static final int MIN_AGE = 22;
    private static final int MAX_AGE = 30;
    private static final int CURRENT_PERIOD = 9;

    private String name;
    private String period;
    private int age;
    private Part part;
    private int ability;

    @Builder
    public Member(String name, String period, int age, String part) {
        validate(name, part, age);
        this.name = name;
        this.period = period;
        this.age = age;
        this.part = Part.valueOf(part);
        this.ability = setAbility();
    }

    private int setAbility() {
        int intPeriod = Integer.parseInt(period.split("기")[0]);
        int periodAbility = (CURRENT_PERIOD - intPeriod) * 2;
        int partAbility = age < 27 ? getBuffPart(part.name()) : 0;
        return age + periodAbility + partAbility;
    }

    public void updateAbility(int currentPeriod) {
        int intPeriod = Integer.parseInt(period.split("기")[0]);
        int periodAbility = (currentPeriod - intPeriod) * 2;
        int partAbility = age < 27 ? getBuffPart(part.name()) : 0;
        this.ability = age + periodAbility + partAbility;
    }

    private int getBuffPart(String part) {
        int month = LocalDate.now().getMonthValue();
        String buffPart;
        switch (month) {
            case 1, 5, 9 -> buffPart = "기획";
            case 2, 6, 10 -> buffPart = "디자이너";
            case 3, 7, 11 -> buffPart = "프론트엔드";
            default -> buffPart = "백엔드";
        }
        return buffPart.equals(part) ? 10 : 0;
    }

    private void validate(String name, String part, int age) {
        if (name.isBlank() || name.length() < 3 || name.length() > 10) {
            throw new IllegalArgumentException("이름은 3자에서 10자 사이여야 합니다.");
        }
        if (age > MAX_AGE || age < MIN_AGE) {
            throw new IllegalArgumentException("나이는 " + MIN_AGE + "에서 " + MAX_AGE + "사이여야 합니다.");
        }
        if (Arrays.stream(Part.values()).noneMatch(v -> v.name().equals(part))) {
            throw new IllegalArgumentException("유효하지 않은 파트입니다.");
        }
    }

    @Override
    public int compareTo(Member o) {
        int abilityComparison = Integer.compare(o.getAbility(), this.ability);

        if (abilityComparison != 0) {
            return abilityComparison;
        }

        int ageComparison = Integer.compare(this.age, o.age);

        if (ageComparison != 0) {
            return ageComparison;
        }

        int periodComparison = this.period.compareTo(o.period);

        if (periodComparison != 0) {
            return periodComparison;
        }

        return this.name.compareTo(o.name);
    }
}
