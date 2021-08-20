package dev.nurlan.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumRoomStatus {

    EMPTY(0), FULL(1), RESERVED(2);

    private int value;

    private static final Map<Integer, EnumRoomStatus> VALUES = new HashMap<>();

    static {
        for (EnumRoomStatus type : EnumRoomStatus.values()) {
            VALUES.put(type.value, type);
        }
    }

    EnumRoomStatus(int enumValue) {
        this.value = enumValue;
    }

    public Integer getValue() {
        return value;
    }

    public static EnumRoomStatus getEnum(Integer value) {
        if (value == null)
            return null;

        return VALUES.get(value.intValue());
    }
}
