package com.cmcorg.service.takeaway.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cmcorg.engine.web.model.model.dto.NotEmptyIdSet;
import com.cmcorg.engine.web.model.model.dto.NotNullId;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpecInsertOrUpdateDTO;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpecPageDTO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawaySpecDO;

public interface TakeawaySpecService extends IService<TakeawaySpecDO> {

    String insertOrUpdate(TakeawaySpecInsertOrUpdateDTO dto);

    Page<TakeawaySpecDO> myPage(TakeawaySpecPageDTO dto);

    TakeawaySpecDO infoById(NotNullId notNullId);

    String deleteByIdSet(NotEmptyIdSet notEmptyIdSet);

}
