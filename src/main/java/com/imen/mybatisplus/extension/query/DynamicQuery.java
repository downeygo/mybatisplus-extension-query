package com.imen.mybatisplus.extension.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.imen.mybatisplus.extension.config.QueryCondition;
import com.imen.mybatisplus.extension.exception.QueryConditionInvalidException;
import com.imen.mybatisplus.extension.exception.QueryKeyInvalidException;
import com.imen.mybatisplus.extension.strategy.QueryContext;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author wfee
 */
public class DynamicQuery {
    private String column;
    public static final String SIZE = "size";
    public static final String CURRENT = "current";

    public QueryWrapper<?> getQueryWrapper(Class<?> clazz, Map<String, Object> param) {
        QueryWrapper<?> queryWrapper = Wrappers.query();
        param.entrySet().stream().forEach(x -> {
            if (!x.getKey().equals(SIZE) && !x.getKey().equals(CURRENT)) {
                QueryCondition queryCondition = determineQueryCondition(x.getKey());
                QueryContext.initQueryWrapper(queryWrapper, clazz, queryCondition, column, convertQueryValues(x.getValue()));
            }

        });
        return queryWrapper;
    }

    private QueryCondition determineQueryCondition(String key) {
        String[] split = key.split("_");
        String name = null;
        if (split.length == 1) {
            if (QueryCondition.isSort(split[0])) {
                column = "";
                name = split[0];
            } else {
                column = split[0];
                name = QueryCondition.EQ.getName();
            }
        } else {
            if (split.length != 2) {
                throw new QueryKeyInvalidException("query key " + key + " is invalid");
            }
            column = split[0];
            name = split[1];
        }

        QueryCondition queryCondition = QueryCondition.getQueryConditionByName(name);
        if (queryCondition == null) {
            throw new QueryConditionInvalidException("query condition " + name + " is invalid");
        }
        return queryCondition;

    }

    private Object[] convertQueryValues(Object values) {
        try {
            return ((ArrayList) values).toArray();
        } catch (ClassCastException e) {
            return new Object[]{values};
        }
    }
}
