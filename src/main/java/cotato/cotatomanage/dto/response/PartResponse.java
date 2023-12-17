package cotato.cotatomanage.dto.response;

import cotato.cotatomanage.domain.enums.Part;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PartResponse implements Comparable<PartResponse> {

    private final Part part;
    private final int ability;
    private final int count;

    public String getPart() {
        return part.getValue();
    }

    @Override
    public int compareTo(PartResponse o) {
        if (this.ability != o.ability) {
            return o.ability - this.ability;
        }
        if (this.count != o.count) {
            return this.count - o.count;
        }
        return this.part.getOrder() - o.part.getOrder();
    }
}