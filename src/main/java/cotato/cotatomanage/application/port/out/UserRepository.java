package cotato.cotatomanage.application.port.out;

import cotato.cotatomanage.domain.PartType;
import cotato.cotatomanage.domain.User;

import java.util.List;

public interface UserRepository {

    void saveUser(User user);

    List<User> findAll();

    List<User> findByPartType(PartType partType);
}
