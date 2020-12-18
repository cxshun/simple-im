package com.sim.common.exception;

import com.sim.common.utils.StringUtils;

/**
 * @author xiaoshun.cxs
 * 2020/12/10
 **/
public class BizException extends Exception{

    private String message;
    public BizException(MessageCode messageCode, Object... args) {
        this.message = StringUtils.format(messageCode.getDesc(), args);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
