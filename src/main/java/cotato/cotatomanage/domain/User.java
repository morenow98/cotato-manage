package cotato.cotatomanage.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private Long id;
    private String name;
    private int period;
    private int age;
    private PartType partType;
    private Integer ability;

    public static User of(Long id, String name, int period, int age, PartType partType) {
        return new User(id, name, period, age, partType, null);
    }

    public static User of(String name, int period, int age, PartType partType) {
        return of(null, name, period, age, partType);
    }

    public User updateAbility(int inputPeriod, LocalDate currentDate) {
        if (this.period > inputPeriod) {
            throw new IllegalArgumentException("유저보다 낮은 기수를 입력하였습니다.");
        }

        if (age >= 27) {
            ability = (inputPeriod - period) * 2;
        } else {
            ability = calculateUpdatedAbility(currentDate) + (inputPeriod - period) * 2;
        }

        return this;
    }

    private int calculateUpdatedAbility(LocalDate currentDate) {
        int month = currentDate.getMonthValue();

        return switch (this.partType) {
            case PM -> (month == 1 || month == 5 || month == 9) ? this.age + 10 : this.age;
            case DESIGN -> (month == 2 || month == 6 || month == 10) ? this.age + 10 : this.age;
            case FRONT -> (month == 3 || month == 7 || month == 11) ? this.age + 10 : this.age;
            case BACK -> (month == 4 || month == 8 || month == 12) ? this.age + 10 : this.age;
        };
    }
}
