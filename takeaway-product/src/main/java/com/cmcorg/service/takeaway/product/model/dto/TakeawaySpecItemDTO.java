package com.cmcorg.service.takeaway.product.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TakeawaySpecItemDTO {

    @Schema(description = "规格类型名称")
    private String typeName;

    @Schema(description = "规格名称")
    private String name;

}
