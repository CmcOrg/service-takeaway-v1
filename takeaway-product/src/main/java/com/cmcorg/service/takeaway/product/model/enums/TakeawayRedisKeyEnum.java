package com.cmcorg.service.takeaway.product.model.enums;

import com.cmcorg.engine.web.redisson.model.interfaces.IRedisKey;

/**
 * redis中 key的枚举类
 * 备注：如果是 redisson的锁 key，一定要备注：锁什么，例如：锁【用户主键 id】
 * 备注：【PRE_】开头，表示 key后面还要跟字符串
 * 备注：【_CACHE】结尾，表示 key后面不用跟字符串
 */
public enum TakeawayRedisKeyEnum implements IRedisKey {

    // 【PRE_】开头 ↓

    // 【_CACHE】结尾 ↓
    TAKEAWAY_SPU_USER_PRODUCT_DINNER_CACHE, // 用户获取：堂食商品，缓存

    TAKEAWAY_SPU_USER_PRODUCT_TAKEAWAY_CACHE, // 用户获取：外卖商品，缓存

    TAKEAWAY_SPU_ID_SKU_LIST_MAP_CACHE, // spuId下的 skuList，缓存

    TAKEAWAY_SPU_MAP_CACHE, // spu表 map缓存

    TAKEAWAY_CATEGORY_CACHE, // 分类表，缓存

    TAKEAWAY_SPU_REF_CATEGORY_CACHE, // spu，分类 关联表，缓存

    // 其他 ↓

    ;

}
