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
    TAKEAWAY_SPU_CACHE, // spu表，缓存

    TAKEAWAY_CATEGORY_CACHE, // 分类表，缓存

    TAKEAWAY_SPU_REF_CATEGORY_CACHE, // spu，分类 关联表，缓存

    // 其他 ↓

    ;

}
