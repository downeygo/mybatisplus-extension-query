package com.imen.mybatisplus.extension.config;

import com.imen.mybatisplus.extension.query.DynamicQuery;

/**
 * @author wfee
 */
public class DynamicQueryFactory {
    private DynamicQueryFactory() {
    }

    public static DynamicQuery newInstance() {
        return new DynamicQuery();
    }
}
