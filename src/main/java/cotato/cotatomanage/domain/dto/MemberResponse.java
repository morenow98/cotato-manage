package cotato.cotatomanage.domain.dto;

import java.util.Comparator;

public class MemberResponse implements Comparable<MemberResponse> {

    private String name;
    private String period;
    private int ability;

    @Override
    public int compareTo(MemberResponse o) {
        return Comparator
                .comparing(MemberResponse::getAbility, Comparator.reverseOrder())
                .thenComparing(MemberResponse::getPeriod, Comparator.reverseOrder())
                .thenComparing(MemberResponse::getName)
                .compare(this, o);
    }

    public int getAbility() {
        return ability;
    }

    public String getPeriod() {
        return period;
    }

    public String getName() {
        return name;
    }
}

