package com.cmcorg.service.takeaway.product.model.dto;

import com.cmcorg.engine.web.model.generate.model.annotation.RequestField;
import com.cmcorg.engine.web.model.model.dto.BaseInsertOrUpdateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class TakeawaySkuInsertOrUpdateDTO extends BaseInsertOrUpdateDTO {

    @Min(1)
    @NotNull
    @RequestField(formTitle = "关联SPU")
    @Schema(description = "SPU 主键 id（外键）")
    private Long spuId;

    @NotEmpty
    @RequestField(formTitle = "规格参数")
    @Schema(description = "规格 json对象集合字符串，例如：[{}]，set")
    private Set<String> spuSpecJsonListStrSet;

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

    @RequestField(formTitle = "备货时长", formTooltip = "单位：秒")
    @Schema(description = "备货时长（秒）")
    private Integer prepareS;

    @RequestField(formTitle = "库存", formTooltip = "-1 表示：无限制", hideInSearchFlag = true)
    @Schema(description = "库存")
    private Long number;

    @Schema(description = "是否启用")
    private Boolean enableFlag;

    @Schema(description = "备注")
    private String remark;

}
