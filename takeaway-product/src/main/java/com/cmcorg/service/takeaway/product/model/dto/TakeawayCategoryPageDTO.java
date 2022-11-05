package com.cmcorg.service.takeaway.product.model.dto;

import com.cmcorg.engine.web.auth.model.dto.MyPageDTO;
import com.cmcorg.service.takeaway.product.model.enums.TakeawaySceneEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TakeawayCategoryPageDTO extends MyPageDTO {

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "场景：1 堂食 2 外卖")
    private TakeawaySceneEnum scene;

    @Schema(description = "是否启用")
    private Boolean enableFlag;

}
