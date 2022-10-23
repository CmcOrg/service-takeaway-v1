package com.cmcorg.service.takeaway.product.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Schema(description = "外卖场景")
public enum TakeawaySceneEnum {

    DINNER((byte)1, ""), //
    TAKEAWAY((byte)2, ""), //

    ;

    @EnumValue
    @JsonValue
    private byte code;
    private String codeDescription; // code 说明

}
