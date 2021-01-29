package com.sim.common.msg.format.spec.user;

import com.sim.common.msg.format.spec.MsgSpec;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Accessors(chain = true)
public class SingleChatMsg extends UserMsg{

    /**
     * chatting msg
     */
    private String msg;

    @Override
    public MsgSpec parse(String message) {
        String[] strs = message.split(" ");
        return new SingleChatMsg().setMsg(strs[2]).setLoginId(strs[1]);
    }
}
