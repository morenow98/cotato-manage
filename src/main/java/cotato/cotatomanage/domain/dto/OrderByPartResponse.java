package cotato.cotatomanage.domain.dto;

import cotato.cotatomanage.domain.Part;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class OrderByPartResponse implements Comparable<OrderByPartResponse>{
    private Part part;
    private int ability;
    private int count;

    @Override
    public int compareTo(OrderByPartResponse o) {
        log.info(this.part.name() + " " + this.ability +" "+o.part.name() + " "+ o.ability);
        if (ability > o.ability) return -1; //내림차순
        else if (ability < o.ability) return 1;
        else return compareByCount(this, o);
    }

    private int compareByCount(OrderByPartResponse t, OrderByPartResponse o) {
        if(t.count>o.count) return 1; //오름차순
        else if(t.count < o.count) return -1;
        else return compareByPart(t, o);
    }

    private int compareByPart(OrderByPartResponse t, OrderByPartResponse o) {
        if (t.part.getOrder() > o.part.getOrder()) return 1; //오름차순
        else return -1;
    }

    @Builder
    public OrderByPartResponse(Part part,int ability, int count) {
        this.part = part;
        this.ability = ability;
        this.count = count;
    }
}
