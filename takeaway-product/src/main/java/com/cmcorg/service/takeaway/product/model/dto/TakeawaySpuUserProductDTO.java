package com.cmcorg.service.takeaway.product.model.dto;

import com.cmcorg.engine.web.model.generate.model.annotation.RequestField;
import com.cmcorg.service.takeaway.product.model.enums.TakeawaySceneEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TakeawaySpuUserProductDTO {

    @NotNull
    @RequestField(formTitle = "场景")
    @Schema(description = "场景：1 堂食 2 外卖")
    private TakeawaySceneEnum scene;

}
