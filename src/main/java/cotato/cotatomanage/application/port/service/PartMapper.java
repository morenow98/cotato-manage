package cotato.cotatomanage.application.port.service;

import cotato.cotatomanage.domain.Part;
import cotato.cotatomanage.domain.PartType;
import cotato.cotatomanage.domain.User;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PartMapper {

    public List<Part> getPartInfo(List<User> users) {
        Map<PartType, List<User>> usersByPartType = users.stream()
                .collect(Collectors.groupingBy(User::getPartType));

        return usersByPartType.entrySet().stream()
                .map(entry -> calculatePartStatistics(entry.getKey(), entry.getValue()))
                .filter(part -> part.getCount() > 0) // 동아리원이 없는 파트는 제외
                .sorted(Comparator
                        .comparing(Part::getAbility).reversed()
                        .thenComparing(Part::getCount)
                        .thenComparingInt(part -> part.getPartType().getOrder()))
                .toList();
    }

    private Part calculatePartStatistics(PartType partType, List<User> partUsers) {
        int totalAbility = partUsers.stream().mapToInt(User::getAbility).sum();
        int count = partUsers.size();

        int averageAbility = count > 0 ? totalAbility / count : 0;

        return Part.of(partType, averageAbility, count);
    }
}
