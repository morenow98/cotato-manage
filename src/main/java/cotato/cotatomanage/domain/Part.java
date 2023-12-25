package cotato.cotatomanage.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Part {
    private PartType partType;
    private int ability;
    private int count;


    public static Part of(PartType partType, int ability, int count) {
        return new Part(partType, ability, count);
    }

}
