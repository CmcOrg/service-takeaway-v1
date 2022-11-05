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
import com.cmcorg.service.takeaway.product.mapper.TakeawayCategoryMapper;
import com.cmcorg.service.takeaway.product.model.dto.TakeawayCategoryInsertOrUpdateDTO;
import com.cmcorg.service.takeaway.product.model.dto.TakeawayCategoryPageDTO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawayCategoryDO;
import com.cmcorg.service.takeaway.product.service.TakeawayCategoryService;
import org.springframework.stereotype.Service;

@Service
public class TakeawayCategoryServiceImpl extends ServiceImpl<TakeawayCategoryMapper, TakeawayCategoryDO>
    implements TakeawayCategoryService {

    /**
     * 新增/修改
     */
    @Override
    public String insertOrUpdate(TakeawayCategoryInsertOrUpdateDTO dto) {

        // 分类名，不能重复
        boolean exists = lambdaQuery().eq(TakeawayCategoryDO::getName, dto.getName())
            .eq(TakeawayCategoryDO::getScene, dto.getScene()).ne(dto.getId() != null, BaseEntity::getId, dto.getId())
            .exists();

        if (exists) {
            ApiResultVO.error("操作失败：分类名，不能重复");
        }

        TakeawayCategoryDO takeawayCategoryDO = new TakeawayCategoryDO();
        takeawayCategoryDO.setName(dto.getName());
        takeawayCategoryDO.setScene(dto.getScene());
        takeawayCategoryDO.setOrderNo(MyEntityUtil.getNotNullOrderNo(dto.getOrderNo()));
        takeawayCategoryDO.setId(dto.getId());
        takeawayCategoryDO.setEnableFlag(BooleanUtil.isTrue(dto.getEnableFlag()));
        takeawayCategoryDO.setDelFlag(false);
        takeawayCategoryDO.setRemark(MyEntityUtil.getNotNullStr(dto.getRemark()));

        saveOrUpdate(takeawayCategoryDO);

        return BaseBizCodeEnum.OK;
    }

    /**
     * 分页排序查询
     */
    @Override
    public Page<TakeawayCategoryDO> myPage(TakeawayCategoryPageDTO dto) {

        return lambdaQuery().like(StrUtil.isNotBlank(dto.getName()), TakeawayCategoryDO::getName, dto.getName())
            .eq(dto.getScene() != null, TakeawayCategoryDO::getScene, dto.getScene())
            .eq(dto.getEnableFlag() != null, BaseEntity::getEnableFlag, dto.getEnableFlag())
            .orderByDesc(TakeawayCategoryDO::getOrderNo).page(dto.getPage(true));
    }

    /**
     * 通过主键id，查看详情
     */
    @Override
    public TakeawayCategoryDO infoById(NotNullId notNullId) {
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
