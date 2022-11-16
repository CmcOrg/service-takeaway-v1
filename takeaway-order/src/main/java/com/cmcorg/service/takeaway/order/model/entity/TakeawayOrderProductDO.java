package com.cmcorg.service.takeaway.order.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cmcorg.service.takeaway.product.model.enums.TakeawaySceneEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@TableName(value = "takeaway_order_product")
@Data
@Schema(description = "子表：订单商品，父表：订单")
public class TakeawayOrderProductDO {

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "订单主键id（外键）（分表键）")
    private Long orderId;

    @Schema(description = "商品主键id（外键）")
    private Long productId;

    @Schema(description = "原始总价（不可修改）")
    private BigDecimal originPrice;

    @Schema(description = "商品 SKU主键 id")
    private Long skuId;

    @Schema(description = "SPU 主键 id（外键）")
    private Long spuId;

    @Schema(description = "SPU 规格主键 id（外键）")
    private Long spuSpecId;

    @Schema(description = "价格")
    private BigDecimal price;

    @Schema(description = "最低购买数量")
    private Long minBuyNumber;

    @Schema(description = "最高购买数量")
    private Long maxBuyNumber;

    @Schema(description = "优惠价格")
    private BigDecimal discountPrice;

    @Schema(description = "优惠个数")
    private Long discountNumber;

    @Schema(description = "打包价格")
    private BigDecimal packagePrice;

    @Schema(description = "场景：1 外卖 2 堂食")
    private TakeawaySceneEnum scene;

    @Schema(description = "备货时长（秒）")
    private Long prepareS;

}
