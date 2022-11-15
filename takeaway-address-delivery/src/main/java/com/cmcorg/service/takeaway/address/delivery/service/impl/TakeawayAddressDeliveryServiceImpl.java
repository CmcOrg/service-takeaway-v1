package com.cmcorg.service.takeaway.address.delivery.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmcorg.engine.web.auth.exception.BaseBizCodeEnum;
import com.cmcorg.engine.web.auth.model.entity.BaseEntity;
import com.cmcorg.engine.web.auth.util.AuthUserUtil;
import com.cmcorg.engine.web.model.model.dto.NotEmptyIdSet;
import com.cmcorg.engine.web.model.model.dto.NotNullId;
import com.cmcorg.service.takeaway.address.delivery.mapper.TakeawayAddressDeliveryMapper;
import com.cmcorg.service.takeaway.address.delivery.model.dto.TakeawayAddressDeliveryInsertOrUpdateDTO;
import com.cmcorg.service.takeaway.address.delivery.model.dto.TakeawayAddressDeliveryPageDTO;
import com.cmcorg.service.takeaway.address.delivery.model.entity.TakeawayAddressDeliveryDO;
import com.cmcorg.service.takeaway.address.delivery.service.TakeawayAddressDeliveryService;
import org.springframework.stereotype.Service;

@Service
public class TakeawayAddressDeliveryServiceImpl
    extends ServiceImpl<TakeawayAddressDeliveryMapper, TakeawayAddressDeliveryDO>
    implements TakeawayAddressDeliveryService {

    /**
     * 新增/修改
     */
    @Override
    public String insertOrUpdate(TakeawayAddressDeliveryInsertOrUpdateDTO dto) {

        Long currentUserId = AuthUserUtil.getCurrentUserId();

        TakeawayAddressDeliveryDO takeawayAddressDeliveryDO = new TakeawayAddressDeliveryDO();
        takeawayAddressDeliveryDO.setUserId(currentUserId);
        takeawayAddressDeliveryDO.setMapJson(dto.getMapJson());
        takeawayAddressDeliveryDO.setHouseNumber(dto.getHouseNumber());
        takeawayAddressDeliveryDO.setName(dto.getName());
        takeawayAddressDeliveryDO.setPhone(dto.getPhone());
        takeawayAddressDeliveryDO.setId(dto.getId());
        takeawayAddressDeliveryDO.setEnableFlag(true);
        takeawayAddressDeliveryDO.setDelFlag(false);
        takeawayAddressDeliveryDO.setRemark("");

        saveOrUpdate(takeawayAddressDeliveryDO);

        return BaseBizCodeEnum.OK;
    }

    /**
     * 分页排序查询
     */
    @Override
    public Page<TakeawayAddressDeliveryDO> myPage(TakeawayAddressDeliveryPageDTO dto) {

        Long currentUserId = AuthUserUtil.getCurrentUserId();

        return lambdaQuery().eq(TakeawayAddressDeliveryDO::getUserId, currentUserId)
            .like(StrUtil.isNotBlank(dto.getName()), TakeawayAddressDeliveryDO::getName, dto.getName())
            .like(StrUtil.isNotBlank(dto.getPhone()), TakeawayAddressDeliveryDO::getPhone, dto.getPhone())
            .orderByDesc(TakeawayAddressDeliveryDO::getCreateTime).page(dto.getPage(true));

    }

    /**
     * 通过主键id，查看详情
     */
    @Override
    public TakeawayAddressDeliveryDO infoById(NotNullId notNullId) {

        Long currentUserId = AuthUserUtil.getCurrentUserId();

        return lambdaQuery().eq(TakeawayAddressDeliveryDO::getUserId, currentUserId)
            .eq(BaseEntity::getId, notNullId.getId()).one();
    }

    /**
     * 批量删除
     */
    @Override
    public String deleteByIdSet(NotEmptyIdSet notEmptyIdSet) {

        Long currentUserId = AuthUserUtil.getCurrentUserId();

        lambdaUpdate().eq(TakeawayAddressDeliveryDO::getUserId, currentUserId)
            .in(BaseEntity::getId, notEmptyIdSet.getIdSet()).remove();

        return BaseBizCodeEnum.OK;
    }

}
