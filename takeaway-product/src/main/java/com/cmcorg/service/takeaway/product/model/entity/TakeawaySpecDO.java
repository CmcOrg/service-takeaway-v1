package com.cmcorg.service.takeaway.product.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cmcorg.engine.web.auth.model.entity.BaseEntity;
import com.cmcorg.engine.web.model.generate.model.annotation.RequestClass;
import com.cmcorg.engine.web.model.generate.model.annotation.RequestField;
import com.cmcorg.engine.web.model.generate.model.constant.WebModelConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@RequestClass(tableIgnoreFields = WebModelConstant.TABLE_IGNORE_FIELDS_TWO)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "takeaway_spec")
@Data
@Schema(description = "主表：商品规格")
public class TakeawaySpecDO extends BaseEntity {

    @RequestField(formTitle = "类型名称", formTooltip = "例如：颜色")
    @Schema(description = "规格类型名称，例如：颜色")
    private String typeName;

    @RequestField(formTitle = "规格名称", formTooltip = "例如：蓝色")
    @Schema(description = "规格名称，例如：蓝色")
    private String name;

    @RequestField(formTitle = "排序号", hideInSearchFlag = true)
    @Schema(description = "排序号（值越大越前面，默认为 0）")
    private Integer orderNo;

}
