package com.cmcorg.service.takeaway.product.model.dto;

import com.cmcorg.engine.web.model.generate.model.annotation.RequestField;
import com.cmcorg.engine.web.model.model.dto.BaseInsertOrUpdateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
public class TakeawaySpecInsertOrUpdateDTO extends BaseInsertOrUpdateDTO {

    @NotBlank
    @RequestField(formTitle = "类型名称", formTooltip = "例如：颜色")
    @Schema(description = "规格类型名称，例如：颜色")
    private String typeName;

    @NotBlank
    @RequestField(formTitle = "规格名称", formTooltip = "例如：蓝色")
    @Schema(description = "规格名称，例如：蓝色")
    private String name;

    @RequestField(formTitle = "排序号", hideInSearchFlag = true)
    @Schema(description = "排序号（值越大越前面，默认为 0）")
    private Integer orderNo;

    @Schema(description = "是否启用")
    private Boolean enableFlag;

    @Schema(description = "备注")
    private String remark;

}
