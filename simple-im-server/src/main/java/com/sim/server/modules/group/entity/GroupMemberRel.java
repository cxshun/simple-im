package com.sim.server.modules.group.entity;

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
 * @author xiaoshun.cxs
 * 12/22/2020
 **/
@TableName("group_member_rel")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class GroupMemberRel {

    /**
     * primary id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * group id
     */
    private Long groupId;
    /**
     * user id
     */
    private Long uid;
    /**
     * create time
     */
    private Date createTime;
    /**
     * update time
     */
    private Date updateTime;
    /**
     * whether delete or not, 1-for deleted, 0-available
     */
    @TableLogic
    private Integer isDeleted;

}
