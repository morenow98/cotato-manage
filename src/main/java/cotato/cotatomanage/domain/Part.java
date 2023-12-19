package cotato.cotatomanage.domain;

import lombok.Getter;

@Getter
public enum Part {
    디자이너(1),
    기획(2),
    백엔드(3),
    프론트엔드(4);

    private final int order;

    Part(int order){ this.order=order;}
}
