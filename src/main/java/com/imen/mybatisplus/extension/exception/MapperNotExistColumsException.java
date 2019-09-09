package com.imen.mybatisplus.extension.exception;

/**
 * @author wfee
 */
public class MapperNotExistColumsException extends DynamicQueryException {
    public MapperNotExistColumsException(String message) {
        super(message);
    }
}
