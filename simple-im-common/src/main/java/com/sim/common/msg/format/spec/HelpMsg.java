package com.sim.common.msg.format.spec;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author xiaoshun.cxs
 * 2021/1/14
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class HelpMsg extends MsgSpec {
    @Override
    public MsgSpec parse(String message) {
        return new HelpMsg();
    }
}
