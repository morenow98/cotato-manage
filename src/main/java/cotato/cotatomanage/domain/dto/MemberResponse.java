package cotato.cotatomanage.domain.dto;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberResponse implements Serializable {

    private String name;
    private String period;
    private int age;
    private String part;
    private int ability;
}
