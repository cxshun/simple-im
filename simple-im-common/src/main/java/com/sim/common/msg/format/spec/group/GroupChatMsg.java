package com.sim.common.msg.format.spec.group;

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
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class GroupChatMsg extends GroupMsg{

    /**
     * chatting message
     */
    private String msg;

}
