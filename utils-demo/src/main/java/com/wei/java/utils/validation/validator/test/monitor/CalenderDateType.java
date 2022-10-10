package com.wei.java.utils.validation.validator.test.monitor;

public enum CalenderDateType {
    ALL("all"),
    DAY("day"),
    TUYA_WORK("tuyaWork"),
    WORK("work"),
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private String code;

    private CalenderDateType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
