package com.cmcorg.service.takeaway.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmcorg.engine.web.auth.exception.BaseBizCodeEnum;
import com.cmcorg.engine.web.auth.model.entity.BaseEntity;
import com.cmcorg.engine.web.auth.model.vo.ApiResultVO;
import com.cmcorg.engine.web.auth.util.MyEntityUtil;
import com.cmcorg.engine.web.model.model.dto.NotEmptyIdSet;
import com.cmcorg.engine.web.model.model.dto.NotNullId;
import com.cmcorg.service.takeaway.product.mapper.TakeawaySkuMapper;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySkuInsertOrUpdateDTO;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySkuPageDTO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawaySkuDO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawaySpuDO;
import com.cmcorg.service.takeaway.product.service.TakeawaySkuService;
import com.cmcorg.service.takeaway.product.service.TakeawaySpuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class TakeawaySkuServiceImpl extends ServiceImpl<TakeawaySkuMapper, TakeawaySkuDO>
    implements TakeawaySkuService {

    @Resource
    TakeawaySpuService takeawaySpuService;

    /**
     * 新增/修改
     */
    @Override
    public String insertOrUpdate(TakeawaySkuInsertOrUpdateDTO dto) {

        TakeawaySpuDO takeawaySpuDO =
            takeawaySpuService.lambdaQuery().select(TakeawaySpuDO::getScene).eq(BaseEntity::getId, dto.getSpuId())
                .one();

        if (takeawaySpuDO == null) {
            ApiResultVO.error("操作失败：spu不存在");
        }

        TakeawaySkuDO takeawaySkuDO = new TakeawaySkuDO();
        takeawaySkuDO.setSpuId(dto.getSpuId());
        takeawaySkuDO.setSpuSpecJsonListStr(dto.getSpuSpecJsonListStr());
        takeawaySkuDO.setPrice(dto.getPrice());
        takeawaySkuDO.setMinBuyNumber(MyEntityUtil.getNotNullLong(dto.getMinBuyNumber(), -1L));
        takeawaySkuDO.setMaxBuyNumber(MyEntityUtil.getNotNullLong(dto.getMaxBuyNumber(), -1L));
        takeawaySkuDO.setDiscountPrice(MyEntityUtil.getNotNullBigDecimal(dto.getDiscountPrice(), BigDecimal.ZERO));
        takeawaySkuDO.setDiscountNumber(MyEntityUtil.getNotNullInt(dto.getDiscountNumber(), -1));
        takeawaySkuDO.setPackagePrice(MyEntityUtil.getNotNullBigDecimal(dto.getPackagePrice(), BigDecimal.ZERO));
        takeawaySkuDO.setScene(takeawaySpuDO.getScene());
        takeawaySkuDO.setPrepareS(MyEntityUtil.getNotNullInt(dto.getPrepareS(), -1));
        takeawaySkuDO.setId(dto.getId());
        takeawaySkuDO.setEnableFlag(dto.getEnableFlag());
        takeawaySkuDO.setDelFlag(false);
        takeawaySkuDO.setRemark(MyEntityUtil.getNotNullStr(dto.getRemark()));

        saveOrUpdate(takeawaySkuDO);

        return BaseBizCodeEnum.OK;
    }

    /**
     * 分页排序查询
     */
    @Override
    public Page<TakeawaySkuDO> myPage(TakeawaySkuPageDTO dto) {

        return lambdaQuery().eq(dto.getSpuId() != null, TakeawaySkuDO::getSpuId, dto.getSpuId())
            .eq(dto.getScene() != null, TakeawaySkuDO::getScene, dto.getScene())
            .like(StrUtil.isNotBlank(dto.getRemark()), BaseEntity::getRemark, dto.getRemark())
            .eq(dto.getEnableFlag() != null, BaseEntity::getEnableFlag, dto.getEnableFlag())
            .orderByDesc(TakeawaySkuDO::getUpdateTime).page(dto.getPage(true));
    }

    /**
     * 通过主键id，查看详情
     */
    @Override
    public TakeawaySkuDO infoById(NotNullId notNullId) {
        return getById(notNullId.getId());
    }

    /**
     * 批量删除
     */
    @Override
    @Transactional
    public String deleteByIdSet(NotEmptyIdSet notEmptyIdSet) {

        removeByIds(notEmptyIdSet.getIdSet());

        return BaseBizCodeEnum.OK;
    }

}
