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
import com.cmcorg.engine.web.auth.model.entity.BaseEntityNoId;
import com.cmcorg.engine.web.auth.model.vo.ApiResultVO;
import com.cmcorg.engine.web.auth.util.MyEntityUtil;
import com.cmcorg.engine.web.cache.util.MyCacheUtil;
import com.cmcorg.engine.web.model.model.dto.NotEmptyIdSet;
import com.cmcorg.engine.web.model.model.dto.NotNullId;
import com.cmcorg.service.takeaway.product.mapper.TakeawaySpuMapper;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpuInsertOrUpdateDTO;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpuPageDTO;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpuUserPageDTO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawayCategoryDO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawaySpuDO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawaySpuRefCategoryDO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawaySpuSpecDO;
import com.cmcorg.service.takeaway.product.model.enums.TakeawayRedisKeyEnum;
import com.cmcorg.service.takeaway.product.model.vo.TakeawaySpuInfoByIdVO;
import com.cmcorg.service.takeaway.product.model.vo.TakeawaySpuUserPageVO;
import com.cmcorg.service.takeaway.product.service.TakeawayCategoryService;
import com.cmcorg.service.takeaway.product.service.TakeawaySpuRefCategoryService;
import com.cmcorg.service.takeaway.product.service.TakeawaySpuService;
import com.cmcorg.service.takeaway.product.service.TakeawaySpuSpecService;
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
    @Resource
    TakeawayCategoryService takeawayCategoryService;

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
     * 用户检索商品
     */
    @Override
    public Page<TakeawaySpuUserPageVO> userPage(TakeawaySpuUserPageDTO dto) {

        // 查询所有关联了 spu的，分类 idSet以及 spuIdSet
        // 查询对应的分类
        // 查询对应的 spu
        // 组装

        List<TakeawaySpuRefCategoryDO> takeawaySpuRefCategoryDOList = MyCacheUtil
            .getListCache(TakeawayRedisKeyEnum.TAKEAWAY_SPU_REF_CATEGORY_CACHE, MyCacheUtil.getDefaultResultList(),
                () -> takeawaySpuRefCategoryService.lambdaQuery()
                    .select(TakeawaySpuRefCategoryDO::getSpuId, TakeawaySpuRefCategoryDO::getCategoryId).list());

        Set<Long> spuIdSet = new HashSet<>();
        Set<Long> categoryIdSet = new HashSet<>();
        Map<Long, Set<Long>> categoryRefSpuMap = MapUtil.newHashMap(takeawaySpuRefCategoryDOList.size());

        for (TakeawaySpuRefCategoryDO item : takeawaySpuRefCategoryDOList) {
            spuIdSet.add(item.getSpuId());
            categoryIdSet.add(item.getCategoryId());
            Set<Long> refSpuIdSet = categoryRefSpuMap.get(item.getCategoryId());
            if (refSpuIdSet == null) {
                refSpuIdSet = CollUtil.newHashSet();
                categoryRefSpuMap.put(item.getCategoryId(), refSpuIdSet);
            }
            refSpuIdSet.add(item.getSpuId());
        }

        List<TakeawayCategoryDO> takeawayCategoryDOList = MyCacheUtil
            .getListCache(TakeawayRedisKeyEnum.TAKEAWAY_CATEGORY_CACHE, MyCacheUtil.getDefaultResultList(),
                () -> takeawayCategoryService.lambdaQuery().select(BaseEntity::getId, TakeawayCategoryDO::getName)
                    .eq(BaseEntityNoId::getEnableFlag, true).in(BaseEntity::getId, categoryIdSet)
                    .eq(TakeawayCategoryDO::getScene, dto.getScene()).orderByDesc(TakeawayCategoryDO::getOrderNo)
                    .list());

        List<TakeawaySpuDO> takeawaySpuDOList = MyCacheUtil
            .getListCache(TakeawayRedisKeyEnum.TAKEAWAY_SPU_CACHE, MyCacheUtil.getDefaultResultList(),
                () -> lambdaQuery().select(BaseEntity::getId, TakeawaySpuDO::getName, TakeawaySpuDO::getMustFlag)
                    .eq(BaseEntityNoId::getEnableFlag, true).in(BaseEntity::getId, spuIdSet)
                    .eq(TakeawaySpuDO::getScene, dto.getScene()).orderByDesc(TakeawaySpuDO::getOrderNo).list());

        return null;
    }

}
