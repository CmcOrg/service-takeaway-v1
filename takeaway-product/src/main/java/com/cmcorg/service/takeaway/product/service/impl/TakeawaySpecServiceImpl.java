package com.cmcorg.service.takeaway.product.service.impl;

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
import com.cmcorg.service.takeaway.product.mapper.TakeawaySpecMapper;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpecInsertOrUpdateDTO;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpecPageDTO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawaySpecDO;
import com.cmcorg.service.takeaway.product.service.TakeawaySpecService;
import org.springframework.stereotype.Service;

@Service
public class TakeawaySpecServiceImpl extends ServiceImpl<TakeawaySpecMapper, TakeawaySpecDO>
    implements TakeawaySpecService {

    /**
     * 新增/修改
     */
    @Override
    public String insertOrUpdate(TakeawaySpecInsertOrUpdateDTO dto) {

        // typeName + name 不能重复
        boolean exists =
            lambdaQuery().eq(TakeawaySpecDO::getTypeName, dto.getTypeName()).eq(TakeawaySpecDO::getName, dto.getName())
                .ne(dto.getId() != null, BaseEntity::getId, dto.getId()).exists();

        if (exists) {
            ApiResultVO.error("操作失败：类型名称 + 规格名称，不能重复");
        }

        TakeawaySpecDO takeawaySpecDO = new TakeawaySpecDO();
        takeawaySpecDO.setTypeName(dto.getTypeName());
        takeawaySpecDO.setName(dto.getName());
        takeawaySpecDO.setOrderNo(MyEntityUtil.getNotNullOrderNo(dto.getOrderNo()));
        takeawaySpecDO.setId(dto.getId());
        takeawaySpecDO.setEnableFlag(BooleanUtil.isTrue(dto.getEnableFlag()));
        takeawaySpecDO.setDelFlag(false);
        takeawaySpecDO.setRemark(MyEntityUtil.getNotNullStr(dto.getRemark()));

        saveOrUpdate(takeawaySpecDO);

        return BaseBizCodeEnum.OK;
    }

    /**
     * 分页排序查询
     */
    @Override
    public Page<TakeawaySpecDO> myPage(TakeawaySpecPageDTO dto) {

        return lambdaQuery().like(StrUtil.isNotBlank(dto.getTypeName()), TakeawaySpecDO::getTypeName, dto.getTypeName())
            .like(StrUtil.isNotBlank(dto.getName()), TakeawaySpecDO::getName, dto.getName())
            .like(StrUtil.isNotBlank(dto.getRemark()), BaseEntity::getRemark, dto.getRemark())
            .eq(dto.getEnableFlag() != null, BaseEntity::getEnableFlag, dto.getEnableFlag())
            .orderByDesc(TakeawaySpecDO::getOrderNo).page(dto.getPage(true));
    }

    /**
     * 通过主键id，查看详情
     */
    @Override
    public TakeawaySpecDO infoById(NotNullId notNullId) {
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
