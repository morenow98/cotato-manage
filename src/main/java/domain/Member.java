package domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.junit.Before;

import java.time.LocalDate;



@Data
@NoArgsConstructor
@Getter
public class Member {
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 10;
    private static final int MIN_AGE_SIZE = 22;
    private static final int MAX_AGE_SIZE = 31;
    private static final int MIN_ABILITY_SIZE = 0;
    private static final int CURRENT_PERIOD = 9;

    private Integer id;

    @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH, message = "이름은 2글자 이상, 10글자 이하여야 합니다.")
    private String name;

    private String period;

    @Min(value = MIN_AGE_SIZE, message = "나이는 22살 이상이어야 합니다.")
    @Max(value = MAX_AGE_SIZE, message = "나이는 30살 이하이어야 합니다.")
    private int age;

    private Part part;

    private int ability;

    @Builder
    public Member(String name, String period, int age, String part){
        this.name = name;
        this.period = period;
        this.age = age;
        this.part = Part.valueOf(part);
        this.ability = setAbility();
    }

    public int setAbility() {
        int intPeriod = Integer.parseInt(period.split("기")[0]);
        int periodAbility = (CURRENT_PERIOD - intPeriod) * 2;
        int ability = MIN_ABILITY_SIZE;

        if (age < 27) {
            ability = getBuffPart(String.valueOf(part));
        }

        return age + periodAbility + ability;
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
        if (buffPart.equals(part)) return 10;
        else return 0;
    }
}
