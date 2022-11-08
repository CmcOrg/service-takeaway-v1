package com.cmcorg.service.takeaway.product.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cmcorg.engine.web.auth.model.entity.BaseEntity;
import com.cmcorg.engine.web.model.generate.model.annotation.RequestClass;
import com.cmcorg.engine.web.model.generate.model.annotation.RequestField;
import com.cmcorg.engine.web.model.generate.model.constant.WebModelConstant;
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

    @RequestField(formTitle = "SPU")
    @Schema(description = "SPU 主键 id（外键）")
    private Long spuId;

    @RequestField(formTitle = "SPU规格")
    @Schema(description = "SPU 规格主键 id（外键）")
    private Long spuSpecId;

    @Schema(description = "价格")
    private BigDecimal price;

    @RequestField(formTitle = "最低购买", formTooltip = "最低购买的数量")
    @Schema(description = "最低购买数量")
    private Long minBuyNumber;

    @RequestField(formTitle = "最高购买", formTooltip = "最高购买的数量")
    @Schema(description = "最高购买数量")
    private Long maxBuyNumber;

    @Schema(description = "优惠价格")
    private BigDecimal discountPrice;

    @Schema(description = "优惠个数")
    private Integer discountNumber;

    @Schema(description = "打包价格")
    private BigDecimal packagePrice;

    @RequestField(formTitle = "场景")
    @Schema(description = "场景：1 堂食 2 外卖")
    private Byte scene;

    @RequestField(formTitle = "备货时长", formTooltip = "单位：秒")
    @Schema(description = "备货时长（秒）")
    private Integer prepareS;

}
