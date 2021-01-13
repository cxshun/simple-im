package com.sim.server.modules.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
@TableName("`user`")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {

    /**
     * primary
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * loginId
     */
    private String loginId;
    /**
     * channelId related to the current user
     */
    private String sessionId;
    /**
     * login password currently not encrypted
     */
    private String password;
    /**
     * create time
     */
    private Date createTime;

    /**
     * update time
     */
    private Date updateTime;

    /**
     * whether delete or not, 1-deleted,0-available
     */
    @TableLogic
    private Integer isDeleted;

}
