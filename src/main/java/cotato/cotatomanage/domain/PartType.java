package cotato.cotatomanage.domain;

import lombok.Getter;

@Getter
public enum PartType {
    DESIGN("디자이너", 1), PM("기획자", 2), FRONT("프론트엔드", 3), BACK("백엔드", 4);

    private final String description;
    private final int order;

    PartType(String description, int order) {
        this.description = description;
        this.order = order;
    }

    public static PartType fromDescription(String description) {
        for (PartType partType : PartType.values()) {
            if (partType.getDescription().equals(description)) {
                return partType;
            }
        }
        throw new IllegalArgumentException("Invalid PartType description: " + description);
    }
}

