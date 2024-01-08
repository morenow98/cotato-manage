package cotato.cotatomanage.domain.dto;


import lombok.Builder;
import lombok.Data;

import java.util.Comparator;

@Data
@Builder
public class MemberResponse implements Comparable<MemberResponse> {

    private String name;
    private String period;
    private int age;
    private String part;
    private int ability;

    @Override
    public int compareTo(MemberResponse o) {
        if(o.getAbility() > this.getAbility())
            return 1;
        else if (o.getAbility() < this.getAbility())
            return -1;
        else return compareByAge(o);
        }

    private int compareByAge(MemberResponse o) {
        if(o.getAge() > this.getAge())
            return 1;
        else if (o.getAge() < this.getAge())
            return -1;
        else return compareByPeriod(o);
    }

    private int compareByPeriod(MemberResponse o) {
        if(this.period.compareTo(o.getPeriod()) >0)
            return -1;
        else if (this.period.compareTo(o.getPeriod())<0) {
            return 1;
        }
        else return compareByName(o);
    }

    private int compareByName(MemberResponse o) {
        if(this.name.compareTo(o.getName()) >0)
            return 1;
        else return -1;
    }
}

