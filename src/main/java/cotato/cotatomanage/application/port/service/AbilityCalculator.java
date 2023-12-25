package cotato.cotatomanage.application.port.service;

import cotato.cotatomanage.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.time.LocalDate.now;

@Component
class AbilityCalculator {

    public List<User> setUserAbilityAll(int period, List<User> users) {
        return users.stream().map(user -> user.updateAbility(period, now()))
                .toList();
    }
}
