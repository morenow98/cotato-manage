package cotato.cotatomanage.domain.enums;

import cotato.cotatomanage.domain.Member;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public enum Event {

    PRODUCT_MANAGER_EVENT(Part.PRODUCT_MANAGER, List.of(1, 5, 9), 10),
    DESIGNER_EVENT(Part.DESIGNER, List.of(2, 6, 10), 10),
    FRONT_END_EVENT(Part.FRONT_END, List.of(3, 7, 11), 10),
    BACK_END_EVENT(Part.BACK_END, List.of(4, 8, 12), 10),
    ;

    private final Part part;
    private final List<Integer> months;
    private final int ability;

    Event(Part part, List<Integer> months, int ability) {
        this.part = part;
        this.months = months;
        this.ability = ability;
    }

    public static Event fromPart(Part part) {
        return Arrays.stream(values())
                .filter(event -> event.part.equals(part))
                .findAny()
                .orElseThrow();
    }

    public static int getIncreasingAbility(Member member, LocalDate now) {
        int nowMonth = now.getMonthValue();
        Event nowEvent = fromPart(member.getPart());
        if (nowEvent.months.contains(nowMonth)) {
            return nowEvent.ability;
        }
        return 0;
    }
}
