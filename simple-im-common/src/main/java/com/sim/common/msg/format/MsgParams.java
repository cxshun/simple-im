package com.sim.common.msg.format;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author xiaoshun.cxs
 * 2021/1/14
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class MsgParams<MsgObj> {

    /**
     * action value
     */
    private String action;
    /**
     * actual msg format
     */
    private MsgObj msg;

}
