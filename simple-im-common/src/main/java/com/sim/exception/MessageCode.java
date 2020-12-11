package com.sim.exception;

import lombok.Getter;

/**
 * @author xiaoshun.cxs
 * 2020/12/10
 **/
@Getter
public enum MessageCode {

    /**
     * message code
     */
    SUCCESS(200, "success"),
    BIZ_ERROR(400, "something error happened"),
    CUSTOM_ERROR(400, "{}"),
    INTERNAL_ERROR(500, "internal error");
    private final int code;
    private final String desc;
    MessageCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
