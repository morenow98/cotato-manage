package cotato.cotatomanage.domain.enums;

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

}
