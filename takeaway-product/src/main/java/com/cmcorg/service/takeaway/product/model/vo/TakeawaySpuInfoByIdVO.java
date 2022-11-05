package com.cmcorg.service.takeaway.product.model.vo;

import com.cmcorg.service.takeaway.product.model.entity.TakeawaySpuDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class TakeawaySpuInfoByIdVO extends TakeawaySpuDO {

    @Schema(description = "商品分类主键 idSet")
    private Set<Long> categoryIdSet;

    @Schema(description = "规格 json对象集合字符串 set")
    private Set<String> specJsonListStrSet;

}
