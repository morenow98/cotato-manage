package cotato.cotatomanage.domain;

import lombok.Getter;

@Getter
public enum Part {

    PRODUCT_MANAGER("기획"),
    DESIGN("디자이너"),
    FRONTEND("프론트엔드"),
    BACKEND("백엔드");

    private final String key;

    Part(String key) {
        this.key = key;
    }

    public static Part getPart(String key) {
        return Part.valueOf(key);
    }
}
