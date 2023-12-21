package cotato.cotatomanage.application.port.service;

import cotato.cotatomanage.application.port.in.UserRegisterCommand;
import cotato.cotatomanage.application.port.out.UserRepository;
import cotato.cotatomanage.domain.Part;
import cotato.cotatomanage.domain.PartType;
import cotato.cotatomanage.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PartMapper partMapper;
    private final AbilityCalculator abilityCalculator;


    public void userRegister(UserRegisterCommand command) {
        userRepository.saveUser(command.toUser());
    }

    public List<User> searchUsersInfo(int period) {
        return abilityCalculator.setUserAbilityAll(period, userRepository.findAll()).stream()
                .sorted(Comparator
                        .comparing(User::getAbility).reversed()
                        .thenComparing(User::getAge).reversed()
                        .thenComparing(User::getPeriod)
                        .thenComparing(User::getName))
                .toList();
    }

    public List<Part> searchPartInfo(int period) {
        List<User> users = abilityCalculator.setUserAbilityAll(period, userRepository.findAll());

        return partMapper.getPartInfo(users);
    }

    public List<User> searchUsersOfPartInfo(int period, PartType partType) {
        return abilityCalculator.setUserAbilityAll(period, userRepository.findByPartType(partType)).stream()
                .sorted(Comparator
                        .comparing(User::getAbility).reversed()
                        .thenComparing(User::getAge).reversed()
                        .thenComparing(User::getPeriod)
                        .thenComparing(User::getName))
                .toList();
    }

}
