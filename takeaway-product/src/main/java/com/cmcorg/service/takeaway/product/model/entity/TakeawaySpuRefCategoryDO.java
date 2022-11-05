package com.cmcorg.service.takeaway.product.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@TableName(value = "takeaway_spu_ref_category")
@Data
@Schema(description = "关联表：商品 SPU，商品分类")
public class TakeawaySpuRefCategoryDO {

    @TableId
    @Schema(description = "商品 SPU主键 id")
    private Long spuId;

    @Schema(description = "商品分类主键 id")
    private Long categoryId;

}
