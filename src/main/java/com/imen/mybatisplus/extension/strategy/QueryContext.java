package com.imen.mybatisplus.extension.strategy;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imen.mybatisplus.extension.config.QueryCondition;
import com.imen.mybatisplus.extension.config.QueryStrategyFactory;

/**
 * @author wfee
 */
public class QueryContext {
    private static QueryStrategy queryStrategy;

    public static QueryWrapper<?> initQueryWrapper(QueryWrapper<?> queryWrapper, Class<?> clazz, QueryCondition queryCondition, String column, Object[] values) {
        queryStrategy = QueryStrategyFactory.newInstance(queryCondition, queryWrapper, column, values);
        return queryStrategy.initQueryWrapper(clazz);
    }
}