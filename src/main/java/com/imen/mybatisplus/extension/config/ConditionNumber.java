package com.imen.mybatisplus.extension.config;

/**
 * @author wfee
 */
public enum ConditionNumber {
    ZERO(0),
    ONE(1),
    TOW(2),
    THREE(3),
    MULTIPLE;

    private int value;

    public int getValue() {
        return this.value;
    }

    private ConditionNumber() {
    }

    public void setValue(int value) {
        this.value = value;
    }

    private ConditionNumber(int value) {
        this.value = value;
    }
}
