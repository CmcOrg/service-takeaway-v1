package com.cmcorg.service.takeaway.rider.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cmcorg.engine.web.auth.model.entity.BaseEntity;
import com.cmcorg.engine.web.model.generate.model.annotation.RequestClass;
import com.cmcorg.engine.web.model.generate.model.annotation.RequestField;
import com.cmcorg.engine.web.model.generate.model.constant.WebModelConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@RequestClass(tableIgnoreFields = WebModelConstant.TABLE_IGNORE_FIELDS_TWO)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "takeaway_rider")
@Data
@Schema(description = "主表：骑手")
public class TakeawayRiderDO extends BaseEntity {

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

}
