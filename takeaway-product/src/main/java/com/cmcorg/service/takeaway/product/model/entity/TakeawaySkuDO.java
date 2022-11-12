package com.cmcorg.service.takeaway.product.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cmcorg.engine.web.auth.model.entity.BaseEntity;
import com.cmcorg.engine.web.model.generate.model.annotation.RequestClass;
import com.cmcorg.engine.web.model.generate.model.annotation.RequestField;
import com.cmcorg.engine.web.model.generate.model.constant.WebModelConstant;
import com.cmcorg.service.takeaway.product.model.enums.TakeawaySceneEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@RequestClass(tableIgnoreFields = WebModelConstant.TABLE_IGNORE_FIELDS_TWO)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "takeaway_sku")
@Data
@Schema(description = "主表：商品 SKU")
public class TakeawaySkuDO extends BaseEntity {

    @RequestField(formTitle = "关联SPU")
    @Schema(description = "SPU 主键 id（外键）")
    private Long spuId;

    @RequestField(formTitle = "选择规格")
    @Schema(description = "规格 json对象集合字符串，例如：[{}]")
    private String spuSpecJsonListStr;

    @RequestField(hideInSearchFlag = true)
    @Schema(description = "价格")
    private BigDecimal price;

    @RequestField(formTitle = "最低购买", formTooltip = "最低购买的数量", hideInSearchFlag = true)
    @Schema(description = "最低购买数量")
    private Long minBuyNumber;

    @RequestField(formTitle = "最高购买", formTooltip = "最高购买的数量", hideInSearchFlag = true)
    @Schema(description = "最高购买数量")
    private Long maxBuyNumber;

    @RequestField(formTitle = "优惠价格", formTooltip = "可以实现第一个为 0.01元", hideInSearchFlag = true)
    @Schema(description = "优惠价格")
    private BigDecimal discountPrice;

    @RequestField(formTitle = "优惠个数", formTooltip = "可以实现第一个为 0.01元", hideInSearchFlag = true)
    @Schema(description = "优惠个数")
    private Integer discountNumber;

    @RequestField(hideInSearchFlag = true)
    @Schema(description = "打包价格")
    private BigDecimal packagePrice;

    @RequestField(formTitle = "场景")
    @Schema(description = "场景：1 堂食 2 外卖")
    private TakeawaySceneEnum scene;

    @RequestField(formTitle = "备货时长", formTooltip = "单位：秒", hideInSearchFlag = true)
    @Schema(description = "备货时长（秒）")
    private Integer prepareS;

}
