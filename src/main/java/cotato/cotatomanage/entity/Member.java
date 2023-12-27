package cotato.cotatomanage.entity;

import jakarta.persistence.*;
import lombok.*;
import cotato.cotatomanage.repository.MemberRepository;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDate;
import java.util.Arrays;
@Entity
@Getter
@ToString // 모든 필드 출력
@AllArgsConstructor // 모든 필드 매개변수 갖는 생성자
@NoArgsConstructor // 매개변수 없는 기본 생성자

public class Member {
    //@Size(min = 2, max = 10)
    //private String name;

    //@Size(min = 22, max = 30)
    //private int age;

    //private String part;

    //@Min(0)
    //private int capacity;
    private static final int MIN_AGE = 22;
    private static final int MAX_AGE = 30;
    private static final int CURRENT_PERIOD = 9;

    private String name;
    private String period;
    private int age;
    private Part part;
    private int ability;

    @Builder
    public Member(String name, String period, int age, String part){
        validation(name,part,age);
        this.name = name;
        this.period = period;
        this.age = age;
        this.part = Part.valueOf(part);
        this.ability = setAbility(period, age,part);
    }

    private int setAbility(String period, int age, String part) {
        int intPeriod = Integer.parseInt(period.split("기")[0]);
        int periodAbility = (CURRENT_PERIOD - intPeriod)*2;
        int partAbility = 0;

        if (age < 27) {
            partAbility = getBuffPart(part);
        }

        return age+periodAbility+partAbility;
    }

    public void updateAbility(int currentPeriod) {
        int intPeriod = Integer.parseInt(period.split("기")[0]);
        int periodAbility = (currentPeriod - intPeriod)*2;
        int partAbility = 0;

        if (age < 27) {
            partAbility = getBuffPart(this.part.name());
        }
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
        if (buffPart.equals(part)) return 10;
        else return 0;
    }

    void validation(String name, String part, int age){
        if (name.isBlank()||name.length() < 3 || name.length() > 10) {
            throw new IllegalArgumentException("이름은 3자에서 10자 사이여야 합니다.");
        }
        if (age > MAX_AGE || age < MIN_AGE) {
            throw new IllegalArgumentException("나이는 ");
        }
        if (Arrays.stream(Part.values()).noneMatch(v -> v.name().equals(part))) {
            throw new IllegalArgumentException("유효하지 않은 파트입니다.");
        }
    }

    //@Override
    public int compareTo(Member o) {
        if (this.ability > o.getAbility()) return -1;
        else if (this.ability < o.getAbility()) return 1;
        else return compareByAge(this, o);
    }

    private int compareByAge(Member t, Member o) {
        if (t.age > o.age) return -1;
        if (t.age < o.age) return 1;
        else return compareByPeriod(t, o);
    }

    private int compareByPeriod(Member t, Member o) {
        if (t.period.compareTo(o.period) > 0) return 1;
        if (t.period.compareTo(o.period) < 0) return -1;
        else return compareByName(t, o);
    }

    private int compareByName(Member t, Member o) {
        if (t.name.compareTo(o.name) > 0) return 1;
        else return -1;
    }

}
