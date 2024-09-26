package cotato.cotatomanage.management.domain;

import cotato.cotatomanage.management.dto.request.RegisterRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    private static final int MAX_AGE = 27;
    private static final int PERIOD_INCREASING_ABILITY = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    private int period;

    @NotNull
    private int age;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Part part;

    // 생성자
    @Builder
    private Member(
            String name,
            int period,
            int age,
            Part part) {

        this.name = name;
        this.period = period;
        this.age = age;
        this.part = part;
    }

    public static Member of(RegisterRequest registerRequest) {
        return Member.builder()
                .name(registerRequest.getName())
                .period(registerRequest.getPeriod())
                .age(registerRequest.getAge())
                .part(registerRequest.getPart())
                .build();
    }

    private int calcAbilityByPeriod(int periodNow) {
        return PERIOD_INCREASING_ABILITY * (periodNow - this.getPeriod());
    }

    public int calcAbility(int periodNow) {
        int ability = this.getAge() + calcAbilityByPeriod(periodNow);

        if (this.getAge() <= MAX_AGE) {
            return ability + PartMonth.calcAbilityByMonth(this);
        }
        return ability;
    }
}
