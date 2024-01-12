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
        if (this.ability > o.ability) {
            return -1;
        }
        if (this.ability < o.ability) {
            return 1;
        }
        return compareByCount(this, o);
    }

    private int compareByCount(PartResponse partResponse, PartResponse o) {
        if (partResponse.count < o.ability) {
            return -1;
        }
        if (partResponse.count > o.ability) {
            return 1;
        }
        return 0;
    }
}
