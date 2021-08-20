package dev.nurlan.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumBookingType {

    RESERVATION(0), REGISTRATION(1), EXIT(2);

    private int value;

    private static final Map<Integer, EnumBookingType> VALUES = new HashMap<>();

    static {
        for (EnumBookingType type : EnumBookingType.values()) {
            VALUES.put(type.value, type);
        }
    }

    EnumBookingType(int enumValue) {
        this.value = enumValue;
    }

    public Integer getValue() {
        return value;
    }

    public static EnumBookingType getEnum(Integer value) {
        if (value == null)
            return null;

        return VALUES.get(value.intValue());
    }
}
