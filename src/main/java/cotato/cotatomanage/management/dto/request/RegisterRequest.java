package cotato.cotatomanage.management.dto.request;

import cotato.cotatomanage.management.domain.Part;
import lombok.Getter;

@Getter
public class RegisterRequest {

    private String name;

    private int period;

    private int age;

    private Part part;
}