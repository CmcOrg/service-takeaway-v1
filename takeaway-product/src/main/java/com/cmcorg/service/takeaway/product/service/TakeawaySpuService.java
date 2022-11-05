package com.cmcorg.service.takeaway.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cmcorg.engine.web.model.model.dto.NotEmptyIdSet;
import com.cmcorg.engine.web.model.model.dto.NotNullId;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpuInsertOrUpdateDTO;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpuPageDTO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawaySpuDO;
import com.cmcorg.service.takeaway.product.model.vo.TakeawaySpuInfoByIdVO;

public interface TakeawaySpuService extends IService<TakeawaySpuDO> {

    String insertOrUpdate(TakeawaySpuInsertOrUpdateDTO dto);

    Page<TakeawaySpuDO> myPage(TakeawaySpuPageDTO dto);

    TakeawaySpuInfoByIdVO infoById(NotNullId notNullId);

    String deleteByIdSet(NotEmptyIdSet notEmptyIdSet);

}
