package com.cmcorg.service.takeaway.product.model.dto;

import com.cmcorg.engine.web.model.generate.model.annotation.RequestField;
import com.cmcorg.engine.web.model.model.dto.BaseInsertOrUpdateDTO;
import com.cmcorg.service.takeaway.product.model.enums.TakeawaySceneEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class TakeawaySpuInsertOrUpdateDTO extends BaseInsertOrUpdateDTO {

    @NotBlank
    @RequestField(formTitle = "SPU名称", formTooltip = "例如：招牌芋圆奶茶")
    @Schema(description = "SPU名称")
    private String name;

    @NotNull
    @RequestField(formTitle = "场景")
    @Schema(description = "场景：1 堂食 2 外卖")
    private TakeawaySceneEnum scene;

    @RequestField(formTitle = "是否必选", formTooltip = "是否：不选无法下单")
    @Schema(description = "是否必选，即：不选无法下单")
    private Boolean mustFlag;

    @RequestField(formTitle = "排序号", hideInSearchFlag = true)
    @Schema(description = "排序号（值越大越前面，默认为 0）")
    private Integer orderNo;

    @Schema(description = "是否启用")
    private Boolean enableFlag;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "商品分类主键 idSet")
    private Set<Long> categoryIdSet;

    @NotBlank
    @Schema(description = "规格 json对象集合字符串")
    private String specJsonListStr;

}
