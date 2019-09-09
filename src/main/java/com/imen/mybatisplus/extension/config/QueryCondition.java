package com.imen.mybatisplus.extension.config;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wfee
 */
public enum QueryCondition {
    IS_NULL("isNull", ConditionType.WHERE, ConditionNumber.ZERO),
    IS_NOT_NUll("isNotNull", ConditionType.WHERE, ConditionNumber.ZERO),
    EQ("eq", ConditionType.WHERE, ConditionNumber.ONE),
    NE("ne", ConditionType.WHERE, ConditionNumber.ONE),
    GT("gt", ConditionType.WHERE, ConditionNumber.ONE),
    GE("ge", ConditionType.WHERE, ConditionNumber.ONE),
    LT("lt", ConditionType.WHERE, ConditionNumber.ONE),
    LE("le", ConditionType.WHERE, ConditionNumber.ONE),
    LIKE("like", ConditionType.WHERE, ConditionNumber.ONE),
    NOT_LIKE("notLike", ConditionType.WHERE, ConditionNumber.ONE),
    LIKE_LEFT("likeLeft", ConditionType.WHERE, ConditionNumber.ONE),
    LIKE_RIGHT("likeRight", ConditionType.WHERE, ConditionNumber.ONE),
    BETWEEN("between", ConditionType.WHERE, ConditionNumber.TOW),
    NOT_BETWEEN("notBetween", ConditionType.WHERE, ConditionNumber.TOW),
    ORDER_BY_ASC("orderByAsc", ConditionType.SORT, ConditionNumber.MULTIPLE),
    ORDER_BY_DESC("orderByDesc", ConditionType.SORT, ConditionNumber.MULTIPLE),
    IN("in", ConditionType.WHERE, ConditionNumber.MULTIPLE),
    NOT_IN("notIn", ConditionType.WHERE, ConditionNumber.MULTIPLE);

    private String name;
    private ConditionNumber number;
    private ConditionType type;

    private QueryCondition(String name, ConditionType type, ConditionNumber number) {
        this.name = name;
        this.type = type;
        this.number = number;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConditionNumber getNumber() {
        return this.number;
    }

    public void setNumber(ConditionNumber number) {
        this.number = number;
    }

    public ConditionType getType() {
        return this.type;
    }

    public void setType(ConditionType type) {
        this.type = type;
    }

    public static QueryCondition getQueryConditionByName(String name) {
        List<QueryCondition> conditionList = (List) Arrays.stream(values()).filter((x) -> {
            return x.getName().equals(name);
        }).collect(Collectors.toList());
        return conditionList.size() > 0 ? (QueryCondition)conditionList.get(0) : null;
    }

    public static boolean isSort(String condition) {
        long count = EnumSet.allOf(QueryCondition.class).stream().filter((x) -> {
            return x.getType().getValue().equals(ConditionType.SORT.getValue()) && condition.equals(x.getName());
        }).count();
        return count > 0L;
    }
}
