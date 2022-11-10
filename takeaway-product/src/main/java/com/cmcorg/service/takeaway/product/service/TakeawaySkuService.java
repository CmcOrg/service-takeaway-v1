package com.cmcorg.service.takeaway.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cmcorg.engine.web.model.model.dto.NotEmptyIdSet;
import com.cmcorg.engine.web.model.model.dto.NotNullId;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySkuInsertOrUpdateDTO;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySkuPageDTO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawaySkuDO;

public interface TakeawaySkuService extends IService<TakeawaySkuDO> {

    String insertOrUpdate(TakeawaySkuInsertOrUpdateDTO dto);

    Page<TakeawaySkuDO> myPage(TakeawaySkuPageDTO dto);

    TakeawaySkuDO infoById(NotNullId notNullId);

    String deleteByIdSet(NotEmptyIdSet notEmptyIdSet);

}
