package cotato.cotatomanage.management.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum Part {
    DESIGNER("디자이너"),
    PRODUCT("기획"),
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    @JsonValue private final String part;

    @JsonCreator
    public static Part parsing(String inputValue) {
        return Stream.of(Part.values())
                .filter(part -> part.getPart().equals(inputValue))
                .findFirst()
                .orElse(null);
    }
}
