package cotato.cotatomanage.application.port.in;

import cotato.cotatomanage.domain.PartType;
import cotato.cotatomanage.domain.User;

public record UserRegisterCommand(
        String name,
        Integer period,
        int age,
        PartType partType
) {

    public User toUser() {
        return User.of(name, period, age, partType);
    }
}
