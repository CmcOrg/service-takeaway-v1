package com.cmcorg.service.takeaway.product.model.dto;

import com.cmcorg.engine.web.auth.model.dto.MyPageDTO;
import com.cmcorg.service.takeaway.product.model.enums.TakeawaySceneEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TakeawaySpuPageDTO extends MyPageDTO {

    @Schema(description = "SPU名称")
    private String name;

    @Schema(description = "场景：1 堂食 2 外卖")
    private TakeawaySceneEnum scene;

    @Schema(description = "是否必选，即：不选无法下单")
    private Boolean mustFlag;

    @Schema(description = "排序号（值越大越前面，默认为 0）")
    private Integer orderNo;

    @Schema(description = "是否启用")
    private Boolean enableFlag;

    @Schema(description = "备注")
    private String remark;

}
