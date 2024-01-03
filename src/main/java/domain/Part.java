package domain;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public enum Part {
    PM(0),
    Designer(1),
    BackEnd(2),
    FrontEnd(3);

    private final int value;

    Part(int value) {
        this.value = value;
    }

}
