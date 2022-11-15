package com.cmcorg.service.takeaway.rider.model.dto;

import com.cmcorg.engine.web.auth.model.dto.MyPageDTO;
import com.cmcorg.engine.web.model.generate.model.annotation.RequestField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TakeawayRiderPageDTO extends MyPageDTO {

    @RequestField(formTitle = "姓名")
    @Schema(description = "骑手姓名")
    private String name;

    @RequestField(formTitle = "身份证号")
    @Schema(description = "骑手身份证号码")
    private String idNumber;

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
