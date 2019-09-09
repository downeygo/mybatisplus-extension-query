package com.imen.mybatisplus.extension.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imen.mybatisplus.extension.exception.DynamicQueryException;
import com.imen.mybatisplus.extension.strategy.*;

/**
 * @author wfee
 */
public class QueryStrategyFactory {
    public static QueryStrategy newInstance(QueryCondition queryCondition, QueryWrapper<?> queryWrapper, String column, Object[] values) {
        if (queryCondition.getType().equals(ConditionType.SORT)) {
            return new SortStrategy(queryCondition, queryWrapper, column, values);
        } else if (queryCondition.getType().equals(ConditionType.WHERE) && queryCondition.getNumber().equals(ConditionNumber.ZERO)) {
            return new WhereZeroParamStrategy(queryCondition, queryWrapper, column, values);
        } else if (queryCondition.getType().equals(ConditionType.WHERE) && queryCondition.getNumber().equals(ConditionNumber.ONE)) {
            return new WhereOneParamStrategy(queryCondition, queryWrapper, column, values);
        } else if (queryCondition.getType().equals(ConditionType.WHERE) && queryCondition.getNumber().equals(ConditionNumber.TOW)) {
            return new WhereTowParamStrategy(queryCondition, queryWrapper, column, values);
        } else if (queryCondition.getType().equals(ConditionType.WHERE) && queryCondition.getNumber().equals(ConditionNumber.MULTIPLE)) {
            return new WhereMultipleParamStrategy(queryCondition, queryWrapper, column, values);
        } else {
            throw new DynamicQueryException("can not find query strategy");
        }
    }
}
