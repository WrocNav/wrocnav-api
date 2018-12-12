package org.wrocnav.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StopType {
    TRAM(0),
    BUS(1),
    BUS_AND_TRAM(2);

    @JsonValue
    private final int value;

    StopType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
