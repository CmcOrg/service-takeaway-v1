package com.cmcorg.service.takeaway.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmcorg.service.takeaway.order.mapper.TakeawayOrderStateDinnerMapper;
import com.cmcorg.service.takeaway.order.model.entity.TakeawayOrderStateDinnerDO;
import com.cmcorg.service.takeaway.order.service.TakeawayOrderStateDinnerService;
import org.springframework.stereotype.Service;

@Service
public class TakeawayOrderStateDinnerServiceImpl
    extends ServiceImpl<TakeawayOrderStateDinnerMapper, TakeawayOrderStateDinnerDO>
    implements TakeawayOrderStateDinnerService {

}
