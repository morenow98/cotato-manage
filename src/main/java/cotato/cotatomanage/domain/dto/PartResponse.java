package cotato.cotatomanage.domain.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PartResponse implements Comparable<PartResponse> {

    private String part;
    private int ability;
    private int count;

    @Override
    public int compareTo(PartResponse o) {
        if(o.ability > this.ability)
            return 1;
        else if (o.ability < this.ability)
            return -1;
        else return compareByCount(o);
    }

    private int compareByCount(PartResponse o) {
        if(o.count > this.count)
            return -1;
        else if (o.count < this.count)
            return 1;
        return 0;
    }
}
