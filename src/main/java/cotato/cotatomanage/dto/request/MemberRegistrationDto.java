package cotato.cotatomanage.dto.request;

import cotato.cotatomanage.domain.Member;
import cotato.cotatomanage.domain.enums.Part;
import lombok.Getter;

@Getter
public class MemberRegistrationDto {

    private String name;
    private String period;
    private int age;
    private String part;

    private int convertPeriod(String period) {
        return Integer.parseInt(
                period.replace("기", "")
        );
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .period(convertPeriod(period))
                .age(age)
                .part(Part.fromValue(part))
                .build();
    }
}
