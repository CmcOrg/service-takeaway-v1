package com.cmcorg.service.takeaway.product.model.dto;

import com.cmcorg.engine.web.auth.model.dto.MyPageDTO;
import com.cmcorg.engine.web.model.generate.model.annotation.RequestField;
import com.cmcorg.service.takeaway.product.model.enums.TakeawaySceneEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class TakeawaySpuUserPageDTO extends MyPageDTO {

    @NotNull
    @RequestField(formTitle = "场景")
    @Schema(description = "场景：1 堂食 2 外卖")
    private TakeawaySceneEnum scene;

}
