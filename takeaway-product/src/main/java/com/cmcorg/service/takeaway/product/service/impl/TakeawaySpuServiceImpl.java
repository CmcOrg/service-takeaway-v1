package com.cmcorg.service.takeaway.product.service.impl;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmcorg.engine.web.auth.exception.BaseBizCodeEnum;
import com.cmcorg.engine.web.auth.model.entity.BaseEntity;
import com.cmcorg.engine.web.auth.util.MyEntityUtil;
import com.cmcorg.engine.web.model.model.dto.NotEmptyIdSet;
import com.cmcorg.engine.web.model.model.dto.NotNullId;
import com.cmcorg.service.takeaway.product.mapper.TakeawaySpuMapper;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpuInsertOrUpdateDTO;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpuPageDTO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawaySpuDO;
import com.cmcorg.service.takeaway.product.service.TakeawaySpuService;
import org.springframework.stereotype.Service;

@Service
public class TakeawaySpuServiceImpl extends ServiceImpl<TakeawaySpuMapper, TakeawaySpuDO>
    implements TakeawaySpuService {

    /**
     * 新增/修改
     */
    @Override
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

        return BaseBizCodeEnum.OK;
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
    public TakeawaySpuDO infoById(NotNullId notNullId) {
        return getById(notNullId.getId());
    }

    /**
     * 批量删除
     */
    @Override
    public String deleteByIdSet(NotEmptyIdSet notEmptyIdSet) {

        removeByIds(notEmptyIdSet.getIdSet());

        return BaseBizCodeEnum.OK;
    }

}
