package com.cmcorg.service.takeaway.address.delivery.model.dto;

import com.cmcorg.engine.web.auth.model.dto.MyPageDTO;
import com.cmcorg.engine.web.model.generate.model.annotation.RequestField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TakeawayAddressDeliveryPageDTO extends MyPageDTO {

    @RequestField(formTitle = "收货人")
    @Schema(description = "收货人名称")
    private String name;

    @RequestField(formTitle = "联系电话")
    @Schema(description = "收货人联系电话")
    private String phone;

}
