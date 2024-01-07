package cotato.cotatomanage.domain.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberResponse {

    private String name;
    private String period;
    private int age;
    private String part;
    private int ability;
}
