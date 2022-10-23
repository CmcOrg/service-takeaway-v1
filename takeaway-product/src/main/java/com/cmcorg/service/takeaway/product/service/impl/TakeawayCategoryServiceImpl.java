package com.cmcorg.service.takeaway.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmcorg.service.takeaway.product.mapper.TakeawayCategoryMapper;
import com.cmcorg.service.takeaway.product.model.entity.TakeawayCategoryDO;
import com.cmcorg.service.takeaway.product.service.TakeawayCategoryService;
import org.springframework.stereotype.Service;

@Service
public class TakeawayCategoryServiceImpl extends ServiceImpl<TakeawayCategoryMapper, TakeawayCategoryDO>
    implements TakeawayCategoryService {

}
