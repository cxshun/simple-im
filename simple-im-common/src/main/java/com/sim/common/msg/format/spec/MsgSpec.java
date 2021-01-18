package com.sim.common.msg.format.spec;

/**
 * @author xiaoshun.cxs
 * 2021/1/18
 **/
public abstract class MsgSpec {

    /**
     * parse message to msgSpec from specify message
     * @param message message
     * @return msgSpec
     */
    public abstract MsgSpec parse(String message);

}
