package com.cmcorg.service.takeaway.address.delivery.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cmcorg.engine.web.model.model.dto.NotEmptyIdSet;
import com.cmcorg.engine.web.model.model.dto.NotNullId;
import com.cmcorg.service.takeaway.address.delivery.model.dto.TakeawayAddressDeliveryInsertOrUpdateDTO;
import com.cmcorg.service.takeaway.address.delivery.model.dto.TakeawayAddressDeliveryPageDTO;
import com.cmcorg.service.takeaway.address.delivery.model.entity.TakeawayAddressDeliveryDO;

public interface TakeawayAddressDeliveryService extends IService<TakeawayAddressDeliveryDO> {

    String insertOrUpdate(TakeawayAddressDeliveryInsertOrUpdateDTO dto);

    Page<TakeawayAddressDeliveryDO> myPage(TakeawayAddressDeliveryPageDTO dto);

    TakeawayAddressDeliveryDO infoById(NotNullId notNullId);

    String deleteByIdSet(NotEmptyIdSet notEmptyIdSet);

}
