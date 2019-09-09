package com.imen.mybatisplus.extension.exception;

/**
 * @author wfee
 */
public class DynamicQueryException extends RuntimeException{
    public DynamicQueryException(String message)
    {
        super(message);
    }
}
