package com.imen.mybatisplus.extension.config;

/**
 * @author wfee
 */
public enum ConditionType {
    WHERE("where"),
    SORT("sort");

    private String value;

    private ConditionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
