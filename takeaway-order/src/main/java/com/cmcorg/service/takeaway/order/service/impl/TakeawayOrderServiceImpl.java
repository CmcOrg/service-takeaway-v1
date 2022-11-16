package com.cmcorg.service.takeaway.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmcorg.service.takeaway.order.mapper.TakeawayOrderMapper;
import com.cmcorg.service.takeaway.order.model.entity.TakeawayOrderDO;
import com.cmcorg.service.takeaway.order.service.TakeawayOrderService;
import org.springframework.stereotype.Service;

@Service
public class TakeawayOrderServiceImpl extends ServiceImpl<TakeawayOrderMapper, TakeawayOrderDO>
    implements TakeawayOrderService {

}
