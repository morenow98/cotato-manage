package cotato.cotatomanage.domain.dto;

import cotato.cotatomanage.domain.Part;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class PartResponse implements Comparable<PartResponse> {
    private Part part;
    private int ability;
    private int count;

    @Override
    public int compareTo(PartResponse other) {
        log.info(this.part.name() + " " + this.ability + " " + other.part.name() + " " + other.ability);

        int abilityComparision = Integer.compare(other.ability, this.ability);
        if(abilityComparision != 0){
            return abilityComparision;
        }

        int countComparison = Integer.compare(this.count, other.count);
        if (countComparison != 0) {
            return countComparison;
        }

        return Integer.compare(this.part.getOrder(), other.part.getOrder());
    }
}
