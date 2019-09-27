package com.imen.mybatisplus.extension.strategy;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author wfee
 */
public interface QueryStrategy {
    /**
     * 初始化QueryWrapper
     *
     * @param var1
     * @return
     */
    QueryWrapper<?> initQueryWrapper(Class<?> var1);
}
