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
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LoginMsg extends UserMsg{

    /**
     * user's login password
     */
    private String password;

    @Override
    public MsgSpec parse(String message) {
        String[] strs = message.split(" ");
        String[] loginIdAndPassword = strs[1].split(":");
        return new LoginMsg().setPassword(loginIdAndPassword[0]).setLoginId(loginIdAndPassword[1]);
    }
}
