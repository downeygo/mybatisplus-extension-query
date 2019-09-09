package com.imen.mybatisplus.extension.strategy;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author wfee
 */
public interface QueryStrategy {
    QueryWrapper<?> initQueryWrapper(Class<?> var1);
}
