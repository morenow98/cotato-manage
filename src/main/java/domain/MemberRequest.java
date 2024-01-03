package domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberRequest {
    private String name;
    private String period;
    private int age;
    private String part;
}
