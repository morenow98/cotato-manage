package cotato.cotatomanage.adapter.in.request;

import cotato.cotatomanage.application.port.in.UserRegisterCommand;
import cotato.cotatomanage.domain.PartType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @Size(min = 2, max = 10)
        String name,
        String period,
        @Min(22)
        @Max(30)
        int age,
        String part
) {

    public UserRegisterCommand toCommand() {
        return new UserRegisterCommand(name, extractNumericPeriod(), age, PartType.fromDescription(part));
    }

    private int extractNumericPeriod() {
        if (period.endsWith("기")) {
            return Integer.parseInt(period.substring(0, period.length() - 1));
        } else {
            throw new IllegalArgumentException("Invalid period format: " + period);
        }
    }

}

