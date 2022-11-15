package com.cmcorg.service.takeaway.rider.model.dto;

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
public class TakeawayRiderInsertOrUpdateDTO extends BaseInsertOrUpdateDTO {

    @Pattern(regexp = BaseRegexConstant.CHINESE_STR)
    @NotBlank
    @RequestField(formTitle = "姓名")
    @Schema(description = "骑手姓名")
    private String name;

    @Pattern(regexp = BaseRegexConstant.ID_NUMBER)
    @NotBlank
    @RequestField(formTitle = "身份证号")
    @Schema(description = "骑手身份证号码")
    private String idNumber;

    @Pattern(regexp = BaseRegexConstant.PHONE)
    @NotBlank
    @RequestField(formTitle = "联系电话")
    @Schema(description = "骑手联系电话")
    private String phone;

    @RequestField(formTitle = "是否开工")
    @Schema(description = "是否开工")
    private Boolean workFlag;

    @Schema(description = "是否启用")
    private Boolean enableFlag;

    @Schema(description = "备注")
    private String remark;

}
