package cotato.cotatomanage.adapter.out.persistence;

import cotato.cotatomanage.adapter.out.persistence.model.UserEntity;
import cotato.cotatomanage.application.port.out.UserRepository;
import cotato.cotatomanage.domain.PartType;
import cotato.cotatomanage.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final AtomicLong id = new AtomicLong(0L);
    private static final List<UserEntity> userEntities = new ArrayList<>();

    @Override
    public void saveUser(User user) {
        userEntities.add(UserEntity.fromUser(id.incrementAndGet(), user));
    }

    @Override
    public List<User> findAll() {
        return userEntities.stream().map(UserEntity::toUser)
                .toList();
    }

    @Override
    public List<User> findByPartType(PartType partType) {
        return userEntities.stream().filter(user -> user.getPartType().equals(partType))
                .map(UserEntity::toUser)
                .toList();
    }

}
