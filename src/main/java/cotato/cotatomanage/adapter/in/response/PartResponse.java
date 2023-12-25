package cotato.cotatomanage.adapter.in.response;

import cotato.cotatomanage.domain.Part;

public record PartResponse(
        String part,
        int ability,
        int count
) {

    public static PartResponse from(Part part) {
        return new PartResponse(part.getPartType().getDescription(), part.getAbility(), part.getCount());
    }
}
