package com.cmcorg.service.takeaway.rider.service.impl;

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
import com.cmcorg.service.takeaway.rider.mapper.TakeawayRiderMapper;
import com.cmcorg.service.takeaway.rider.model.dto.TakeawayRiderInsertOrUpdateDTO;
import com.cmcorg.service.takeaway.rider.model.dto.TakeawayRiderPageDTO;
import com.cmcorg.service.takeaway.rider.model.entity.TakeawayRiderDO;
import com.cmcorg.service.takeaway.rider.service.TakeawayRiderService;
import org.springframework.stereotype.Service;

@Service
public class TakeawayRiderServiceImpl extends ServiceImpl<TakeawayRiderMapper, TakeawayRiderDO>
    implements TakeawayRiderService {

    /**
     * 新增/修改
     */
    @Override
    public String insertOrUpdate(TakeawayRiderInsertOrUpdateDTO dto) {

        // 身份证号，不能重复
        boolean exists = lambdaQuery().eq(TakeawayRiderDO::getIdNumber, dto.getIdNumber())
            .ne(dto.getId() != null, BaseEntity::getId, dto.getId()).exists();

        if (exists) {
            ApiResultVO.error("操作失败：身份证号，不能重复");
        }

        TakeawayRiderDO takeawayRiderDO = new TakeawayRiderDO();
        takeawayRiderDO.setName(dto.getName());
        takeawayRiderDO.setIdNumber(dto.getIdNumber());
        takeawayRiderDO.setPhone(dto.getPhone());
        takeawayRiderDO.setWorkFlag(BooleanUtil.isTrue(dto.getWorkFlag()));
        takeawayRiderDO.setId(dto.getId());
        takeawayRiderDO.setEnableFlag(BooleanUtil.isTrue(dto.getEnableFlag()));
        takeawayRiderDO.setDelFlag(false);
        takeawayRiderDO.setRemark(MyEntityUtil.getNotNullStr(dto.getRemark()));

        saveOrUpdate(takeawayRiderDO);

        return BaseBizCodeEnum.OK;
    }

    /**
     * 分页排序查询
     */
    @Override
    public Page<TakeawayRiderDO> myPage(TakeawayRiderPageDTO dto) {

        return lambdaQuery().like(StrUtil.isNotBlank(dto.getName()), TakeawayRiderDO::getName, dto.getName())
            .like(StrUtil.isNotBlank(dto.getIdNumber()), TakeawayRiderDO::getIdNumber, dto.getIdNumber())
            .like(StrUtil.isNotBlank(dto.getPhone()), TakeawayRiderDO::getPhone, dto.getPhone())
            .eq(dto.getWorkFlag() != null, TakeawayRiderDO::getWorkFlag, dto.getWorkFlag())
            .like(StrUtil.isNotBlank(dto.getRemark()), BaseEntity::getRemark, dto.getRemark())
            .eq(dto.getEnableFlag() != null, BaseEntity::getEnableFlag, dto.getEnableFlag())
            .orderByDesc(TakeawayRiderDO::getUpdateTime).page(dto.getPage(true));

    }

    /**
     * 通过主键id，查看详情
     */
    @Override
    public TakeawayRiderDO infoById(NotNullId notNullId) {
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
