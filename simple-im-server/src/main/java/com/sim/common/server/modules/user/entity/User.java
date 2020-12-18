package com.sim.common.server.modules.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * user info
 * @author xiaoshun.cxs
 * 2020/12/9
 **/
@TableName("t_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * loginId
     */
    private String loginId;
    /**
     * login password currently not encrypted
     */
    private String password;

    /**
     * create time
     */
    private Date createTime;

}
