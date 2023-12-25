package cotato.cotatomanage.adapter.in.response;

import cotato.cotatomanage.domain.User;

public record UserResponse(
        String name,
        int period,
        int age,
        String part,
        Integer ability
) {

    public static UserResponse from(User user) {
        return new UserResponse(user.getName(), user.getPeriod(), user.getAge(), user.getPartType().getDescription(), user.getAbility());
    }

}
