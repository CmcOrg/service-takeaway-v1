package com.cmcorg.service.takeaway.product.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cmcorg.engine.web.auth.model.entity.BaseEntity;
import com.cmcorg.engine.web.model.generate.model.annotation.RequestClass;
import com.cmcorg.engine.web.model.generate.model.annotation.RequestField;
import com.cmcorg.engine.web.model.generate.model.constant.WebModelConstant;
import com.cmcorg.service.takeaway.product.model.enums.TakeawaySceneEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@RequestClass(tableIgnoreFields = WebModelConstant.TABLE_IGNORE_FIELDS_TWO)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "takeaway_category")
@Data
@Schema(description = "主表：商品分类")
public class TakeawayCategoryDO extends BaseEntity {

    @Schema(description = "分类名称")
    private String name;

    @RequestField(formTitle = "场景")
    @Schema(description = "场景：1 堂食 2 外卖")
    private TakeawaySceneEnum scene;

    @RequestField(formTitle = "排序号", hideInSearchFlag = true)
    @Schema(description = "排序号（值越大越前面，默认为 0）")
    private Integer orderNo;

    @TableField(exist = false)
    @Schema(description = "分类关联的 spu集合")
    private List<TakeawaySpuDO> takeawaySpuDOList;

}
