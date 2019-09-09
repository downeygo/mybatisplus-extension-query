package com.imen.mybatisplus.extension.exception;

/**
 * @author wfee
 */
public class QueryMethodNotFoundException extends DynamicQueryException {
    public QueryMethodNotFoundException(String message) {
        super(message);
    }
}
