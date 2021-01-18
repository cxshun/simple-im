package com.sim.common.msg.format.spec.group;

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
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class GroupMsg extends MsgSpec {

    /**
     * group name
     */
    private String groupName;

    @Override
    public MsgSpec parse(String message) {
        String[] strs = message.split(" ");
        return new GroupMsg().setGroupName(strs[1]);
    }
}
