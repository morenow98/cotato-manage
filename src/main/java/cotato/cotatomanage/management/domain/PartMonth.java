package cotato.cotatomanage.management.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static cotato.cotatomanage.management.domain.Part.*;

@Getter
@RequiredArgsConstructor
public enum PartMonth {

    PRODUCT_MONTH(PRODUCT, List.of(1, 5, 9), 10),
    DESIGNER_MONTH(DESIGNER, List.of(2, 6, 10), 10),
    FRONTEND_MONTH(FRONTEND, List.of(3, 7, 11), 10),
    BACKEND_MONTH(BACKEND, List.of(4, 8, 12), 10);

    private Part part;
    private List<Integer> months;
    private int number;

    PartMonth(Part part, List<Integer> months, int number) {
        this.part = part;
        this.months = months;
        this.number = number;
    }

    public static PartMonth partParsing(Part part) {
        return Stream.of(PartMonth.values())
                .filter(partMonth -> partMonth.getPart().equals(part))
                .findFirst()
                .orElse(null);
    }

    public static int calcAbilityByMonth(Member member) {
        PartMonth partMonth = partParsing(member.getPart());
        int monthNow = LocalDate.now().getDayOfMonth();
        if (partMonth.months.contains(monthNow)) {
            return partMonth.getNumber();
        }
        return 0;
    }
}
