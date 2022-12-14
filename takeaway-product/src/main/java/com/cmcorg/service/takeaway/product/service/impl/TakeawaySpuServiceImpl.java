package com.cmcorg.service.takeaway.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmcorg.engine.web.auth.exception.BaseBizCodeEnum;
import com.cmcorg.engine.web.auth.model.entity.BaseEntity;
import com.cmcorg.engine.web.auth.model.vo.ApiResultVO;
import com.cmcorg.engine.web.auth.util.MyEntityUtil;
import com.cmcorg.engine.web.cache.util.MyCacheUtil;
import com.cmcorg.engine.web.model.model.dto.NotEmptyIdSet;
import com.cmcorg.engine.web.model.model.dto.NotNullId;
import com.cmcorg.service.takeaway.product.mapper.TakeawaySpuMapper;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpecItemDTO;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpuInsertOrUpdateDTO;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpuPageDTO;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpuUserProductDTO;
import com.cmcorg.service.takeaway.product.model.entity.*;
import com.cmcorg.service.takeaway.product.model.enums.TakeawayRedisKeyEnum;
import com.cmcorg.service.takeaway.product.model.enums.TakeawaySceneEnum;
import com.cmcorg.service.takeaway.product.model.vo.TakeawaySpuInfoByIdVO;
import com.cmcorg.service.takeaway.product.service.TakeawaySpuRefCategoryService;
import com.cmcorg.service.takeaway.product.service.TakeawaySpuService;
import com.cmcorg.service.takeaway.product.service.TakeawaySpuSpecService;
import com.cmcorg.service.takeaway.product.util.TakeawayCacheHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TakeawaySpuServiceImpl extends ServiceImpl<TakeawaySpuMapper, TakeawaySpuDO>
    implements TakeawaySpuService {

    @Resource
    TakeawaySpuRefCategoryService takeawaySpuRefCategoryService;
    @Resource
    TakeawaySpuSpecService takeawaySpuSpecService;

    /**
     * 新增/修改
     */
    @Override
    @Transactional
    public String insertOrUpdate(TakeawaySpuInsertOrUpdateDTO dto) {

        TakeawaySpuDO takeawaySpuDO = new TakeawaySpuDO();
        takeawaySpuDO.setName(dto.getName());
        takeawaySpuDO.setScene(dto.getScene());
        takeawaySpuDO.setMustFlag(BooleanUtil.isTrue(dto.getMustFlag()));
        takeawaySpuDO.setOrderNo(MyEntityUtil.getNotNullOrderNo(dto.getOrderNo()));
        takeawaySpuDO.setId(dto.getId());
        takeawaySpuDO.setEnableFlag(BooleanUtil.isTrue(dto.getEnableFlag()));
        takeawaySpuDO.setDelFlag(false);
        takeawaySpuDO.setRemark(MyEntityUtil.getNotNullStr(dto.getRemark()));

        saveOrUpdate(takeawaySpuDO);

        if (dto.getId() != null) {
            deleteByIdSetSub(CollUtil.newHashSet(dto.getId())); // 先删除 子表数据
        }

        insertOrUpdateSub(takeawaySpuDO, dto); // 再新增 子表数据

        return BaseBizCodeEnum.OK;
    }

    /**
     * 新增 子表数据
     */
    private void insertOrUpdateSub(TakeawaySpuDO takeawaySpuDO, TakeawaySpuInsertOrUpdateDTO dto) {

        if (CollUtil.isNotEmpty(dto.getCategoryIdSet())) {
            List<TakeawaySpuRefCategoryDO> takeawaySpuRefCategoryDOInsertList = new ArrayList<>();
            for (Long item : dto.getCategoryIdSet()) {
                TakeawaySpuRefCategoryDO takeawaySpuRefCategoryDO = new TakeawaySpuRefCategoryDO();
                takeawaySpuRefCategoryDO.setSpuId(takeawaySpuDO.getId());
                takeawaySpuRefCategoryDO.setCategoryId(item);
                takeawaySpuRefCategoryDOInsertList.add(takeawaySpuRefCategoryDO);
            }
            takeawaySpuRefCategoryService.saveBatch(takeawaySpuRefCategoryDOInsertList);
        }

        JSONArray specJsonList = JSONUtil.parseArray(dto.getSpecJsonListStr());

        List<TakeawaySpuSpecDO> takeawaySpuSpecDOInsertList = new ArrayList<>();
        for (Object item : specJsonList) { // 备注：item是一个：JSONArray
            JSONArray jsonArray = (JSONArray)item;
            if (jsonArray.size() == 0) {
                continue;
            }
            TakeawaySpuSpecDO takeawaySpuSpecDO = new TakeawaySpuSpecDO();
            takeawaySpuSpecDO.setSpuId(takeawaySpuDO.getId());
            takeawaySpuSpecDO.setSpecJsonListStr(jsonArray.toString());
            takeawaySpuSpecDOInsertList.add(takeawaySpuSpecDO);
        }
        if (takeawaySpuSpecDOInsertList.size() == 0) {
            ApiResultVO.error("操作失败：规格不能为空");
        }
        takeawaySpuSpecService.saveBatch(takeawaySpuSpecDOInsertList);

    }

    /**
     * 删除 子表数据
     */
    private void deleteByIdSetSub(Set<Long> spuIdSet) {

        takeawaySpuRefCategoryService.lambdaUpdate().in(TakeawaySpuRefCategoryDO::getSpuId, spuIdSet).remove();

        takeawaySpuSpecService.lambdaUpdate().in(TakeawaySpuSpecDO::getSpuId, spuIdSet).remove();

    }

    /**
     * 分页排序查询
     */
    @Override
    public Page<TakeawaySpuDO> myPage(TakeawaySpuPageDTO dto) {

        return lambdaQuery().eq(dto.getId() != null, BaseEntity::getId, dto.getId())
            .like(StrUtil.isNotBlank(dto.getName()), TakeawaySpuDO::getName, dto.getName())
            .eq(dto.getScene() != null, TakeawaySpuDO::getScene, dto.getScene())
            .eq(dto.getMustFlag() != null, TakeawaySpuDO::getMustFlag, dto.getMustFlag())
            .like(StrUtil.isNotBlank(dto.getRemark()), BaseEntity::getRemark, dto.getRemark())
            .eq(dto.getEnableFlag() != null, BaseEntity::getEnableFlag, dto.getEnableFlag())
            .orderByDesc(TakeawaySpuDO::getOrderNo).page(dto.getPage(true));
    }

    /**
     * 通过主键id，查看详情
     */
    @Override
    public TakeawaySpuInfoByIdVO infoById(NotNullId notNullId) {

        TakeawaySpuInfoByIdVO takeawaySpuInfoByIdVO =
            BeanUtil.copyProperties(getById(notNullId.getId()), TakeawaySpuInfoByIdVO.class);

        if (takeawaySpuInfoByIdVO == null) {
            return null;
        }

        Set<Long> categoryIdSet =
            takeawaySpuRefCategoryService.lambdaQuery().select(TakeawaySpuRefCategoryDO::getCategoryId)
                .eq(TakeawaySpuRefCategoryDO::getSpuId, notNullId.getId()).list().stream()
                .map(TakeawaySpuRefCategoryDO::getCategoryId).collect(Collectors.toSet());

        List<JSONArray> specJsonList =
            takeawaySpuSpecService.lambdaQuery().select(TakeawaySpuSpecDO::getSpecJsonListStr)
                .eq(TakeawaySpuSpecDO::getSpuId, notNullId.getId()).orderByDesc(TakeawaySpuSpecDO::getSpecJsonListStr)
                .list().stream().map(it -> JSONUtil.parseArray(it.getSpecJsonListStr())).collect(Collectors.toList());

        takeawaySpuInfoByIdVO.setCategoryIdSet(categoryIdSet);
        takeawaySpuInfoByIdVO.setSpecJsonListStr(JSONUtil.toJsonStr(specJsonList));

        return takeawaySpuInfoByIdVO;
    }

    /**
     * 批量删除
     */
    @Override
    @Transactional
    public String deleteByIdSet(NotEmptyIdSet notEmptyIdSet) {

        removeByIds(notEmptyIdSet.getIdSet());

        // 删除：子表数据
        deleteByIdSetSub(notEmptyIdSet.getIdSet());

        return BaseBizCodeEnum.OK;
    }

    /**
     * 用户获取商品
     */
    @Override
    public List<TakeawayCategoryDO> userProduct(TakeawaySpuUserProductDTO dto) {

        // 查询对应的分类
        // 查询对应的 spu
        // 查询所有关联了 spu的，分类 idSet以及 spuIdSet
        // 组装

        TakeawayRedisKeyEnum takeawayRedisKeyEnum;

        if (dto.getScene().equals(TakeawaySceneEnum.DINNER)) {
            takeawayRedisKeyEnum = TakeawayRedisKeyEnum.TAKEAWAY_SPU_USER_PRODUCT_DINNER_CACHE;
        } else {
            takeawayRedisKeyEnum = TakeawayRedisKeyEnum.TAKEAWAY_SPU_USER_PRODUCT_TAKEAWAY_CACHE;
        }

        return MyCacheUtil.getListCache(takeawayRedisKeyEnum, MyCacheUtil.getDefaultResultList(), () -> {

            List<TakeawayCategoryDO> takeawayCategoryDOList =
                TakeawayCacheHelper.getCategoryList().stream().filter(it -> it.getScene().equals(dto.getScene()))
                    .collect(Collectors.toList());

            Set<Long> categoryIdSet =
                takeawayCategoryDOList.stream().map(BaseEntity::getId).collect(Collectors.toSet());

            List<TakeawaySpuRefCategoryDO> takeawaySpuRefCategoryDOList =
                TakeawayCacheHelper.getSpuRefCategoryList().stream()
                    .filter(it -> categoryIdSet.contains(it.getCategoryId())).collect(Collectors.toList());

            Set<Long> spuIdSet = new HashSet<>();
            Map<Long, Set<Long>> categoryIdRefSpuIdSetMap = MapUtil.newHashMap(categoryIdSet.size());
            Map<Long, Set<Long>> spuIdRefCategoryIdSetMap = MapUtil.newHashMap();

            for (TakeawaySpuRefCategoryDO item : takeawaySpuRefCategoryDOList) {
                spuIdSet.add(item.getSpuId());
                categoryIdSet.add(item.getCategoryId());
                Set<Long> refSpuIdSet =
                    categoryIdRefSpuIdSetMap.computeIfAbsent(item.getCategoryId(), k -> new HashSet<>());
                refSpuIdSet.add(item.getSpuId());
                Set<Long> refCategoryIdSet =
                    spuIdRefCategoryIdSetMap.computeIfAbsent(item.getSpuId(), k -> new HashSet<>());
                refCategoryIdSet.add(item.getCategoryId());
            }

            Map<Long, TakeawaySpuDO> spuMap =
                TakeawayCacheHelper.getSpuList().stream().filter(it -> spuIdSet.contains(it.getId()))
                    .collect(Collectors.toMap(BaseEntity::getId, it -> it));

            Map<Long, List<TakeawaySkuDO>> spuIdSkuListMap =
                TakeawayCacheHelper.getSkuList().stream().filter(it -> spuIdSet.contains(it.getSpuId())).peek(it -> {

                    // 设置：规格集合
                    it.setSpuSpecJsonList(JSONUtil.parseArray(it.getSpuSpecJsonListStr()).stream()
                        .map(subIt -> BeanUtil.toBean(subIt, TakeawaySpecItemDTO.class)).collect(Collectors.toList()));

                    // 设置：关联的规格主键 idSet
                    it.setSpecIdSet(spuIdRefCategoryIdSetMap.get(it.getSpuId()));

                    // 设置：spu名称
                    TakeawaySpuDO takeawaySpuDO = spuMap.get(it.getSpuId());
                    if (takeawaySpuDO != null) {
                        it.setSpuName(takeawaySpuDO.getName());
                    }

                }).sorted(Comparator.comparing(TakeawaySkuDO::getSpuSpecJsonListStr)).collect(
                    Collectors.groupingBy(TakeawaySkuDO::getSpuId, Collectors.mapping(it -> it, Collectors.toList())));

            // 组装数据
            for (TakeawayCategoryDO item : takeawayCategoryDOList) {
                Set<Long> refSpuIdSet = categoryIdRefSpuIdSetMap.get(item.getId());
                if (CollUtil.isNotEmpty(refSpuIdSet)) {
                    List<TakeawaySpuDO> refTakeawaySpuDOList = new ArrayList<>(refSpuIdSet.size());
                    for (Long subItem : refSpuIdSet) {
                        TakeawaySpuDO takeawaySpuDO = spuMap.get(subItem);
                        if (takeawaySpuDO != null) {
                            takeawaySpuDO.setTakeawaySkuDOList(spuIdSkuListMap.get(takeawaySpuDO.getId())); // 设置：sku集合
                            refTakeawaySpuDOList.add(takeawaySpuDO);
                        }
                    }
                    item.setTakeawaySpuDOList(refTakeawaySpuDOList);
                }
            }

            return takeawayCategoryDOList;

        });

    }

}
