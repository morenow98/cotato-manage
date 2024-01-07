package cotato.cotatomanage.domain;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Part {
    
    FRONTEND("프론트엔드"),
    BACKEND("백엔드"),
    DESIGNER("디자이너"),
    PRODUCT_MANAGER("기획자")
    ;

    private final String key;

    Part(String key){
        this.key = key;
    }

    public static Part getPart(String key){
        return Arrays.stream(Part.values())
                .filter(p->p.getKey().equals(key))
                .findAny()
                .orElseThrow(()->new IllegalArgumentException());
    }
}
