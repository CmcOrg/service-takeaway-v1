package com.cmcorg.service.takeaway.address.delivery.model.dto;

import com.cmcorg.engine.web.model.generate.model.annotation.RequestField;
import com.cmcorg.engine.web.model.model.constant.BaseRegexConstant;
import com.cmcorg.engine.web.model.model.dto.BaseInsertOrUpdateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@EqualsAndHashCode(callSuper = true)
@Data
public class TakeawayAddressDeliveryInsertOrUpdateDTO extends BaseInsertOrUpdateDTO {

    @NotBlank
    @RequestField(formTitle = "地图取点")
    @Schema(description = "地图取点（json）")
    private String mapJson;

    @NotBlank
    @Schema(description = "门牌号")
    private String houseNumber;

    @NotBlank
    @RequestField(formTitle = "收货人")
    @Schema(description = "收货人名称")
    private String name;

    @Pattern(regexp = BaseRegexConstant.PHONE)
    @NotBlank
    @RequestField(formTitle = "联系电话")
    @Schema(description = "收货人联系电话")
    private String phone;

}
