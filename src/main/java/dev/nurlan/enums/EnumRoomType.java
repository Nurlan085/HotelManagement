package dev.nurlan.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumRoomType {

    ECONOM(1), COMFORT(2), VIP(3);

    private int value;

    private static final Map<Integer, EnumRoomType> VALUES = new HashMap<>();

    static {
        for (EnumRoomType type : EnumRoomType.values()) {
            VALUES.put(type.value, type);
        }
    }

    EnumRoomType(int enumValue) {
        this.value = enumValue;
    }

    public Integer getValue() {
        return value;
    }

    public static EnumRoomType getEnum(Integer value) {
        if (value == null)
            return null;

        return VALUES.get(value.intValue());
    }
}
