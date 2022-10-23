package com.cmcorg.service.takeaway.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        return null;
    }

    /**
     * 分页排序查询
     */
    @Override
    public Page<TakeawayCategoryDO> myPage(TakeawayCategoryPageDTO dto) {
        return null;
    }

    /**
     * 通过主键id，查看详情
     */
    @Override
    public TakeawayCategoryDO infoById(NotNullId notNullId) {
        return null;
    }

    /**
     * 批量删除
     */
    @Override
    public String deleteByIdSet(NotEmptyIdSet notEmptyIdSet) {
        return null;
    }
}
