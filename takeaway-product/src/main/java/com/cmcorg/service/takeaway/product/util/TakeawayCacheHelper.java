package com.cmcorg.service.takeaway.product.util;

import com.cmcorg.engine.web.auth.model.entity.BaseEntity;
import com.cmcorg.engine.web.auth.model.entity.BaseEntityNoId;
import com.cmcorg.engine.web.cache.util.MyCacheUtil;
import com.cmcorg.service.takeaway.product.model.entity.TakeawayCategoryDO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawaySkuDO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawaySpuDO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawaySpuRefCategoryDO;
import com.cmcorg.service.takeaway.product.model.enums.TakeawayRedisKeyEnum;
import com.cmcorg.service.takeaway.product.service.TakeawayCategoryService;
import com.cmcorg.service.takeaway.product.service.TakeawaySkuService;
import com.cmcorg.service.takeaway.product.service.TakeawaySpuRefCategoryService;
import com.cmcorg.service.takeaway.product.service.TakeawaySpuService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TakeawayCacheHelper {

    private static TakeawaySpuService takeawaySpuService;
    private static TakeawaySpuRefCategoryService takeawaySpuRefCategoryService;
    private static TakeawayCategoryService takeawayCategoryService;
    private static TakeawaySkuService takeawaySkuService;

    public TakeawayCacheHelper(TakeawaySpuService takeawaySpuService,
        TakeawaySpuRefCategoryService takeawaySpuRefCategoryService, TakeawayCategoryService takeawayCategoryService,
        TakeawaySkuService takeawaySkuService) {
        TakeawayCacheHelper.takeawaySpuService = takeawaySpuService;
        TakeawayCacheHelper.takeawaySpuRefCategoryService = takeawaySpuRefCategoryService;
        TakeawayCacheHelper.takeawayCategoryService = takeawayCategoryService;
        TakeawayCacheHelper.takeawaySkuService = takeawaySkuService;
    }

    /**
     * 获取：分类表，数据
     */
    @NotNull
    public static List<TakeawayCategoryDO> getCategoryList() {
        return MyCacheUtil
            .getListCache(TakeawayRedisKeyEnum.TAKEAWAY_CATEGORY_LIST_CACHE, MyCacheUtil.getDefaultResultList(),
                () -> takeawayCategoryService.lambdaQuery()
                    .select(BaseEntity::getId, TakeawayCategoryDO::getName, TakeawayCategoryDO::getScene)
                    .eq(BaseEntityNoId::getEnableFlag, true).orderByDesc(TakeawayCategoryDO::getOrderNo).list();)
    }

    /**
     * 获取：spu，分类 关联表，数据
     */
    @NotNull
    public static List<TakeawaySpuRefCategoryDO> getSpuRefCategoryList() {
        return MyCacheUtil
            .getListCache(TakeawayRedisKeyEnum.TAKEAWAY_SPU_REF_CATEGORY_LIST_CACHE, MyCacheUtil.getDefaultResultList(),
                () -> takeawaySpuRefCategoryService.lambdaQuery()
                    .select(TakeawaySpuRefCategoryDO::getSpuId, TakeawaySpuRefCategoryDO::getCategoryId).list());
    }

    /**
     * 获取：spu表，数据
     */
    @NotNull
    public static List<TakeawaySpuDO> getSpuList() {
        return MyCacheUtil
            .getListCache(TakeawayRedisKeyEnum.TAKEAWAY_SPU_LIST_CACHE, MyCacheUtil.getDefaultResultList(),
                () -> takeawaySpuService.lambdaQuery()
                    .select(BaseEntity::getId, TakeawaySpuDO::getName, TakeawaySpuDO::getMustFlag)
                    .eq(BaseEntityNoId::getEnableFlag, true).orderByDesc(TakeawaySpuDO::getOrderNo).list());
    }

    /**
     * 获取：sku表，数据
     */
    @NotNull
    public static List<TakeawaySkuDO> getSkuList() {
        return MyCacheUtil
            .getListCache(TakeawayRedisKeyEnum.TAKEAWAY_SKU_LIST_CACHE, MyCacheUtil.getDefaultResultList(),
                () -> takeawaySkuService.lambdaQuery().eq(BaseEntityNoId::getEnableFlag, true)
                    .orderByAsc(TakeawaySkuDO::getPrice).list());
    }

}
