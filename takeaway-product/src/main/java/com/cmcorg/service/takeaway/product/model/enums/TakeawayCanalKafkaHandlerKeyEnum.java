package com.cmcorg.service.takeaway.product.model.enums;

import cn.hutool.core.collection.CollUtil;
import com.cmcorg.engine.web.cache.model.interfaces.ICanalKafkaHandlerKey;
import com.cmcorg.engine.web.redisson.model.interfaces.IRedisKey;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Schema(description = "takeaway canal的消息枚举")
public enum TakeawayCanalKafkaHandlerKeyEnum implements ICanalKafkaHandlerKey {

    // sku表
    TAKEAWAY_SKU("takeaway_sku", CollUtil.newHashSet(TakeawayRedisKeyEnum.TAKEAWAY_SPU_USER_PRODUCT_DINNER_CACHE,
        TakeawayRedisKeyEnum.TAKEAWAY_SPU_USER_PRODUCT_TAKEAWAY_CACHE,
        TakeawayRedisKeyEnum.TAKEAWAY_SPU_ID_SKU_LIST_MAP_CACHE)),

    // spu表
    TAKEAWAY_SPU("takeaway_spu", CollUtil.newHashSet(TakeawayRedisKeyEnum.TAKEAWAY_SPU_USER_PRODUCT_DINNER_CACHE,
        TakeawayRedisKeyEnum.TAKEAWAY_SPU_USER_PRODUCT_TAKEAWAY_CACHE,
        TakeawayRedisKeyEnum.TAKEAWAY_SPU_ID_SKU_LIST_MAP_CACHE, TakeawayRedisKeyEnum.TAKEAWAY_SPU_MAP_CACHE,
        TakeawayRedisKeyEnum.TAKEAWAY_SPU_REF_CATEGORY_CACHE)),

    // 分类表
    TAKEAWAY_SPEC("takeaway_spec", CollUtil.newHashSet(TakeawayRedisKeyEnum.TAKEAWAY_SPU_USER_PRODUCT_DINNER_CACHE,
        TakeawayRedisKeyEnum.TAKEAWAY_SPU_USER_PRODUCT_TAKEAWAY_CACHE, TakeawayRedisKeyEnum.TAKEAWAY_CATEGORY_CACHE,
        TakeawayRedisKeyEnum.TAKEAWAY_SPU_REF_CATEGORY_CACHE)),

    // spu，分类 关联表
    TAKEAWAY_SPU_REF_CATEGORY("takeaway_spu_ref_category",
        CollUtil.unionDistinct(TAKEAWAY_SPU.getDeleteRedisKeyEnumSet(), TAKEAWAY_SPEC.getDeleteRedisKeyEnumSet())),

    ;

    private String name;
    private Set<Enum<? extends IRedisKey>> deleteRedisKeyEnumSet;

}
