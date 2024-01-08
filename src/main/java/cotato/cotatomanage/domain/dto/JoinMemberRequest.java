package cotato.cotatomanage.domain.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class JoinMemberRequest {

    private String name;
    private String period;
    private int age;
    private String part;

}
