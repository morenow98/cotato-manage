package cotato.cotatomanage.adapter.out.persistence.model;

import cotato.cotatomanage.domain.PartType;
import cotato.cotatomanage.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserEntity {
    private Long id;
    private String name;
    private int period;
    private int age;
    private PartType partType;

    public static UserEntity fromUser(Long id, User user) {
        return new UserEntity(id, user.getName(), user.getPeriod(), user.getAge(), user.getPartType());
    }

    public User toUser() {
        return User.of(id, name, period, age, partType);
    }
}
