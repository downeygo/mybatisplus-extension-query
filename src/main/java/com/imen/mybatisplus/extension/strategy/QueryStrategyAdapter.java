package com.imen.mybatisplus.extension.strategy;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imen.mybatisplus.extension.config.ConditionNumber;
import com.imen.mybatisplus.extension.config.QueryCondition;
import com.imen.mybatisplus.extension.exception.InvokeMethodException;
import com.imen.mybatisplus.extension.exception.MapperNotExistColumsException;
import com.imen.mybatisplus.extension.exception.QueryMethodNotFoundException;
import com.imen.mybatisplus.extension.exception.QueryValuesException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wfee
 */
public abstract class QueryStrategyAdapter implements QueryStrategy {
    private QueryCondition queryCondition;
    private String column;
    private Object[] values;
    private QueryWrapper<?> queryWrapper;

    public QueryStrategyAdapter(QueryCondition queryCondition, QueryWrapper<?> queryWrapper, String column, Object[] values) {
        this.queryCondition = queryCondition;
        this.queryWrapper = queryWrapper;
        this.column = column;
        this.values = values;
    }

    public QueryCondition getQueryCondition() {
        return queryCondition;
    }

    public String getColumn() {
        return column;
    }

    public Object[] getValues() {
        return values;
    }

    public QueryWrapper<?> getQueryWrapper() {
        return queryWrapper;
    }

    @Override
    public QueryWrapper<?> initQueryWrapper(Class<?> clazz) {
        if (!isExistColumns(clazz, getMapperColumns())) {
            throw new MapperNotExistColumsException("property " + Arrays.toString(getMapperColumns()) + " at least one is not exist in mapper " + clazz);
        }
        if (!validateValues()) {
            throw new QueryValuesException("condition " + queryCondition.getName() + " need " + queryCondition.getNumber().getValue() + " query value");
        }
        if (getQueryMethod() == null) {
            throw new QueryMethodNotFoundException("condition " + queryCondition.getName() + " is not allowed");
        }
        if (!invokeQueryMethod()) {
            throw new InvokeMethodException("invoke " + queryCondition.getName() + " failed");
        }
        return queryWrapper;
    }

    protected abstract Object[] getMapperColumns();

    protected abstract Method getMethod() throws NoSuchMethodException;

    protected abstract boolean invokeMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    private boolean isExistColumns(Class<?> clazz, Object[] column) {
        return getAllColumns(clazz).containsAll(Arrays.asList(column));
    }

    private List<String> getAllColumns(Class<?> clazz) {
        ArrayList<String> columnList = new ArrayList();
        while (clazz != null) {
            List<String> columns = Arrays.stream(clazz.getDeclaredFields())
                    .map(x -> x.getName())
                    .collect(Collectors.toList());
            columnList.addAll(columns);
            clazz = clazz.getSuperclass();
        }
        return columnList;
    }

    private boolean validateValues() {
        return queryCondition.getNumber().equals(ConditionNumber.MULTIPLE) || queryCondition.getNumber().getValue() == values.length;
    }

    private Method getQueryMethod() {
        try {
            return getMethod();
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private boolean invokeQueryMethod() {
        try {
            return invokeMethod();
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return false;
        }
    }
}