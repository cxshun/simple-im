package com.sim.common.msg.format.spec.user;

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

}
