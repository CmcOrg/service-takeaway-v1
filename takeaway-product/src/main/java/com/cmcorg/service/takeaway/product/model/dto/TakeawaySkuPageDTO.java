package com.cmcorg.service.takeaway.product.model.dto;

import com.cmcorg.engine.web.auth.model.dto.MyPageDTO;
import com.cmcorg.service.takeaway.product.model.enums.TakeawaySceneEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TakeawaySkuPageDTO extends MyPageDTO {

    @Schema(description = "SPU 主键 id（外键）")
    private Long spuId;

    @Schema(description = "场景：1 堂食 2 外卖")
    private TakeawaySceneEnum scene;

    @Schema(description = "规格 json对象集合字符串，例如：[{}]")
    private String spuSpecJsonListStr;

    @Schema(description = "是否启用")
    private Boolean enableFlag;

    @Schema(description = "备注")
    private String remark;

}
