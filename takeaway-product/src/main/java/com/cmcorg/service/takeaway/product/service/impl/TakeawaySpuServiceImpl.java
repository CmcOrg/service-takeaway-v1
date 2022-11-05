package com.cmcorg.service.takeaway.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmcorg.engine.web.auth.exception.BaseBizCodeEnum;
import com.cmcorg.engine.web.auth.model.entity.BaseEntity;
import com.cmcorg.engine.web.auth.model.vo.ApiResultVO;
import com.cmcorg.engine.web.auth.util.MyEntityUtil;
import com.cmcorg.engine.web.model.model.dto.NotEmptyIdSet;
import com.cmcorg.engine.web.model.model.dto.NotNullId;
import com.cmcorg.service.takeaway.product.mapper.TakeawaySpuMapper;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpuInsertOrUpdateDTO;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpuPageDTO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawaySpuDO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawaySpuRefCategoryDO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawaySpuSpecDO;
import com.cmcorg.service.takeaway.product.model.vo.TakeawaySpuInfoByIdVO;
import com.cmcorg.service.takeaway.product.service.TakeawaySpuRefCategoryService;
import com.cmcorg.service.takeaway.product.service.TakeawaySpuService;
import com.cmcorg.service.takeaway.product.service.TakeawaySpuSpecService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

        List<TakeawaySpuSpecDO> takeawaySpuSpecDOInsertList = new ArrayList<>();
        for (String item : dto.getSpecJsonListStrSet()) {
            if (StrUtil.isBlank(item)) {
                continue;
            }
            TakeawaySpuSpecDO takeawaySpuSpecDO = new TakeawaySpuSpecDO();
            takeawaySpuSpecDO.setSpuId(takeawaySpuDO.getId());
            takeawaySpuSpecDO.setSpecJsonListStr(item);
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

        return lambdaQuery().like(StrUtil.isNotBlank(dto.getName()), TakeawaySpuDO::getName, dto.getName())
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

        Set<String> specJsonListStrSet =
            takeawaySpuSpecService.lambdaQuery().select(TakeawaySpuSpecDO::getSpecJsonListStr)
                .eq(TakeawaySpuSpecDO::getSpuId, notNullId.getId()).list().stream()
                .map(TakeawaySpuSpecDO::getSpecJsonListStr).collect(Collectors.toSet());

        takeawaySpuInfoByIdVO.setCategoryIdSet(categoryIdSet);
        takeawaySpuInfoByIdVO.setSpecJsonListStrSet(specJsonListStrSet);

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

}
