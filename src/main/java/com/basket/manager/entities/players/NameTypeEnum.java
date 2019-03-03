package com.basket.manager.entities.players;

import java.util.stream.Stream;

public enum NameTypeEnum {
    LAST_NAME("last_name"),
    FIRST_NAME("first_name");

    private final String value;

    NameTypeEnum(String value) {
        this.value = value;
    }

    public static NameTypeEnum getEnumFrom(String value) {
        return Stream.of(values())
                .filter(e -> e.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }


    public String getValue() {
        return value;
    }
}
