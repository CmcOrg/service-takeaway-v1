package com.cmcorg.service.takeaway.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cmcorg.engine.web.model.model.dto.NotEmptyIdSet;
import com.cmcorg.engine.web.model.model.dto.NotNullId;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpuInsertOrUpdateDTO;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpuPageDTO;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpuUserProductDTO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawayCategoryDO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawaySpuDO;
import com.cmcorg.service.takeaway.product.model.vo.TakeawaySpuInfoByIdVO;

import java.util.List;

public interface TakeawaySpuService extends IService<TakeawaySpuDO> {

    String insertOrUpdate(TakeawaySpuInsertOrUpdateDTO dto);

    Page<TakeawaySpuDO> myPage(TakeawaySpuPageDTO dto);

    TakeawaySpuInfoByIdVO infoById(NotNullId notNullId);

    String deleteByIdSet(NotEmptyIdSet notEmptyIdSet);

    List<TakeawayCategoryDO> userProduct(TakeawaySpuUserProductDTO dto);

}
