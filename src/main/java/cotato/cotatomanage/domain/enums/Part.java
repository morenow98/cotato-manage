package cotato.cotatomanage.domain.enums;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum Part {

    PRODUCT_MANAGER("기획"),
    DESIGNER("디자이너"),
    FRONT_END("프론트엔드"),
    BACK_END("백엔드"),
    ;

    private final String value;

    Part(String value) {
        this.value = value;
    }

    public static Part fromValue(String value) {
        return Arrays.stream(values())
                .filter(part -> part.value.equals(value))
                .findAny()
                .orElseThrow();
    }

    public int getOrder() {
        return switch (this) {
            case DESIGNER -> 1;
            case PRODUCT_MANAGER -> 2;
            case BACK_END -> 3;
            case FRONT_END -> 4;
        };
    }
}
