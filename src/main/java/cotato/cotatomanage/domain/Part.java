package cotato.cotatomanage.domain;

import java.time.Month;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
public enum Part {

    PRODUCT_MANAGER("기획", List.of(Month.JANUARY, Month.MAY, Month.SEPTEMBER)),
    DESIGN("디자이너", List.of(Month.FEBRUARY, Month.JUNE, Month.OCTOBER)),
    FRONTEND("프론트엔드", List.of(Month.MARCH, Month.JULY, Month.NOVEMBER)),
    BACKEND("백엔드", List.of(Month.APRIL, Month.AUGUST, Month.DECEMBER));

    private final String key;
    private final List<Month> additionalAbility;

    Part(String key, List<Month> additionalAbility) {
        this.key = key;
        this.additionalAbility = additionalAbility;
    }

    public static Part getPart(String key) {
        return Arrays.stream(Part.values())
                .filter(m -> m.getKey().equals(key))
                .findAny()
                .orElseThrow();
    }
}
