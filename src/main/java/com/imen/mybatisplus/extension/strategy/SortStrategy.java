package com.imen.mybatisplus.extension.strategy;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imen.mybatisplus.extension.config.QueryCondition;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wfee
 */
public class SortStrategy extends QueryStrategyAdapter {
    public SortStrategy(QueryCondition queryCondition, QueryWrapper<?> queryWrapper, String column, Object[] values) {
        super(queryCondition, queryWrapper, column, values);
    }

    @Override
    protected Object[] getMapperColumns() {
        return getValues();
    }

    @Override
    protected Method getMethod() throws NoSuchMethodException {
        return getQueryWrapper().getClass().getMethod(getQueryCondition().getName(), Object[].class);
    }

    @Override
    protected boolean invokeMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        getMethod().invoke(getQueryWrapper(), (Object) getValues());
        return true;
    }
}