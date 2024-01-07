package cotato.cotatomanage.domain.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PartResponse {

    private String part;
    private Integer ability;
    private Integer count;
}
