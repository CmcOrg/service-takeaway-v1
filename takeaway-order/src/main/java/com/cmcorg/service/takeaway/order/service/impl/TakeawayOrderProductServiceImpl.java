package com.cmcorg.service.takeaway.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmcorg.service.takeaway.order.mapper.TakeawayOrderProductMapper;
import com.cmcorg.service.takeaway.order.model.entity.TakeawayOrderProductDO;
import com.cmcorg.service.takeaway.order.service.TakeawayOrderProductService;
import org.springframework.stereotype.Service;

@Service
public class TakeawayOrderProductServiceImpl extends ServiceImpl<TakeawayOrderProductMapper, TakeawayOrderProductDO>
    implements TakeawayOrderProductService {

}
