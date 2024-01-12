package cotato.cotatomanage.management.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static cotato.cotatomanage.management.domain.Part.*;

@Getter
@RequiredArgsConstructor
public enum Month {

    PRODUCT_MONTH(PRODUCT, List.of(1, 5, 9), 10),
    DESIGNER_MONTH(DESIGNER, List.of(2, 6, 10), 10),
    FRONTEND_MONTH(FRONTEND, List.of(3, 7, 11), 10),
    BACKEND_MONTH(BACKEND, List.of(4, 8, 12), 10);

    private Part part;
    private List<Integer> months;
    private int number;

    Month(Part part, List<Integer> months, int number) {
        this.part = part;
        this.months = months;
        this.number = number;
    }

    public static Month partParsing(Part part) {
        return Stream.of(Month.values())
                .filter(partMonth -> partMonth.getPart().equals(part))
                .findFirst()
                .orElse(null);
    }

    public static int ability(Member member) {
        Month partMonth = partParsing(member.getPart());
        int monthNow = LocalDate.now().getDayOfMonth();
        if (partMonth.months.contains(monthNow)) {
            return partMonth.getNumber();
        }
        return 0;
    }
}