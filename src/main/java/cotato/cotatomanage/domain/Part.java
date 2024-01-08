package cotato.cotatomanage.domain;

import lombok.Getter;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

@Getter
public enum Part {

    DESIGNER("디자이너", List.of(Month.FEBRUARY, Month.JUNE, Month.OCTOBER)),
    PRODUCT_MANAGER("기획자", List.of(Month.JANUARY, Month.MARCH, Month.SEPTEMBER)),
    BACKEND("백엔드", List.of(Month.APRIL, Month.AUGUST,Month.DECEMBER)),
    FRONTEND("프론트엔드", List.of(Month.MARCH, Month.JULY, Month.NOVEMBER))
    ;

    private final String key;
    private final List<Month> monthList;

    Part(String key, List<Month> monthList){
        this.key = key;
        this.monthList = monthList;
    }

    public static Part getPart(String key){
        return Arrays.stream(Part.values())
                .filter(p->p.getKey().equals(key))
                .findAny()
                .orElseThrow(()->new IllegalArgumentException());
    }
}
