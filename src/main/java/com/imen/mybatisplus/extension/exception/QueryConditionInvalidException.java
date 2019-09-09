package com.imen.mybatisplus.extension.exception;

/**
 * @author wfee
 */
public class QueryConditionInvalidException extends DynamicQueryException {
    public QueryConditionInvalidException(String message) {
        super(message);
    }
}
