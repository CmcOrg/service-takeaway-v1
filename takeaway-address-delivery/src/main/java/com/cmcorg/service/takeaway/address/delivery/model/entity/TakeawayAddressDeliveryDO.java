package com.cmcorg.service.takeaway.address.delivery.model.entity;

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
@TableName(value = "takeaway_address_delivery")
@Data
@Schema(description = "主表：收货地址")
public class TakeawayAddressDeliveryDO extends BaseEntity {

    @RequestField(formTitle = "用户主键 id")
    @Schema(description = "用户主键 id（外键）")
    private Long userId;

    @RequestField(formTitle = "地图取点")
    @Schema(description = "地图取点（json）")
    private String mapJson;

    @Schema(description = "门牌号")
    private String houseNumber;

    @RequestField(formTitle = "收货人")
    @Schema(description = "收货人名称")
    private String name;

    @RequestField(formTitle = "联系电话")
    @Schema(description = "收货人联系电话")
    private String phone;

}
