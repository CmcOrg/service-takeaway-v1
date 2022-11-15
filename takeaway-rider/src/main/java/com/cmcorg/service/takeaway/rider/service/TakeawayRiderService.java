package com.cmcorg.service.takeaway.rider.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cmcorg.engine.web.model.model.dto.NotEmptyIdSet;
import com.cmcorg.engine.web.model.model.dto.NotNullId;
import com.cmcorg.service.takeaway.rider.model.dto.TakeawayRiderInsertOrUpdateDTO;
import com.cmcorg.service.takeaway.rider.model.dto.TakeawayRiderPageDTO;
import com.cmcorg.service.takeaway.rider.model.entity.TakeawayRiderDO;

public interface TakeawayRiderService extends IService<TakeawayRiderDO> {

    String insertOrUpdate(TakeawayRiderInsertOrUpdateDTO dto);

    Page<TakeawayRiderDO> myPage(TakeawayRiderPageDTO dto);

    TakeawayRiderDO infoById(NotNullId notNullId);

    String deleteByIdSet(NotEmptyIdSet notEmptyIdSet);

}
