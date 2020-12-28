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
 * 12/21/2020
 **/
@TableName("group")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Group {

    /**
     * primary id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * group name
     */
    private String name;
    /**
     * member count
     */
    private Integer memberCount;
    /**
     * create time
     */
    private Date createTime;
    /**
     * update time
     */
    private Date updateTime;
    /**
     * is delete or not, 1-for deleted, 0-available
     */
    @TableLogic
    private Integer isDeleted;

}
