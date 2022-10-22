package com.cmcorg.service.takeaway.product.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 主表：商品规格
 *
 * @TableName mall_spec
 */
@TableName(value = "mall_spec")
@Data
public class TakeawaySpecDO implements Serializable {
    /**
     *
     */
    @TableId
    private Long id;

    /**
     *
     */
    private Long createId;

    /**
     *
     */
    private LocalDateTime createTime;

    /**
     *
     */
    private Long updateId;

    /**
     *
     */
    private LocalDateTime updateTime;

    /**
     * 启用/禁用
     */
    private Boolean enableFlag;

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 是否逻辑删除
     */
    private Boolean delFlag;

    /**
     * 描述/备注
     */
    private String remark;

    /**
     * 规格类型名称，例如：颜色
     */
    private String typeName;

    /**
     * 规格名称，例如：蓝色
     */
    private String name;

    /**
     * 排序号（值越大越前面，默认为 0）
     */
    private Integer orderNo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
