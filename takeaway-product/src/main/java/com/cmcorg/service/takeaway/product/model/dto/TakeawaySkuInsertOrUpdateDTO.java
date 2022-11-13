package com.cmcorg.service.takeaway.product.model.dto;

import com.cmcorg.engine.web.model.generate.model.annotation.RequestField;
import com.cmcorg.engine.web.model.model.dto.BaseInsertOrUpdateDTO;
import com.cmcorg.service.takeaway.product.model.enums.TakeawaySceneEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class TakeawaySkuInsertOrUpdateDTO extends BaseInsertOrUpdateDTO {

    @Min(1)
    @NotNull
    @RequestField(formTitle = "关联SPU")
    @Schema(description = "SPU 主键 id（外键）")
    private Long spuId;

    @NotBlank
    @RequestField(formTitle = "规格参数")
    @Schema(description = "规格 json对象集合字符串，例如：[{}]")
    private String spuSpecJsonListStr;

    @NotNull
    @Schema(description = "价格")
    private BigDecimal price;

    @RequestField(formTitle = "最低购买", formTooltip = "单次最低购买的数量")
    @Schema(description = "最低购买数量")
    private Long minBuyNumber;

    @RequestField(formTitle = "最高购买", formTooltip = "单次最高购买的数量")
    @Schema(description = "最高购买数量")
    private Long maxBuyNumber;

    @RequestField(formTitle = "优惠价格", formTooltip = "可以实现第一个为 0.01元")
    @Schema(description = "优惠价格")
    private BigDecimal discountPrice;

    @RequestField(formTitle = "优惠个数", formTooltip = "可以实现第一个为 0.01元")
    @Schema(description = "优惠个数")
    private Integer discountNumber;

    @Schema(description = "打包价格")
    private BigDecimal packagePrice;

    @NotNull
    @RequestField(formTitle = "场景")
    @Schema(description = "场景：1 堂食 2 外卖")
    private TakeawaySceneEnum scene;

    @RequestField(formTitle = "备货时长", formTooltip = "单位：秒")
    @Schema(description = "备货时长（秒）")
    private Integer prepareS;

    @Schema(description = "是否启用")
    private Boolean enableFlag;

    @Schema(description = "备注")
    private String remark;

}
