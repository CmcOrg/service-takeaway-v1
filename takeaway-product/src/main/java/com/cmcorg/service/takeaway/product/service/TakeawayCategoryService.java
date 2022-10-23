package com.cmcorg.service.takeaway.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cmcorg.engine.web.model.model.dto.NotEmptyIdSet;
import com.cmcorg.engine.web.model.model.dto.NotNullId;
import com.cmcorg.service.takeaway.product.model.dto.TakeawayCategoryInsertOrUpdateDTO;
import com.cmcorg.service.takeaway.product.model.dto.TakeawayCategoryPageDTO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawayCategoryDO;

public interface TakeawayCategoryService extends IService<TakeawayCategoryDO> {

    String insertOrUpdate(TakeawayCategoryInsertOrUpdateDTO dto);

    Page<TakeawayCategoryDO> myPage(TakeawayCategoryPageDTO dto);

    TakeawayCategoryDO infoById(NotNullId notNullId);

    String deleteByIdSet(NotEmptyIdSet notEmptyIdSet);

}
