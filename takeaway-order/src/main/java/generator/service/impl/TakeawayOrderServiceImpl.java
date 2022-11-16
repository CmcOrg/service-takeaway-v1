package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.entity.TakeawayOrderDO;
import generator.mapper.TakeawayOrderMapper;
import generator.service.TakeawayOrderService;
import org.springframework.stereotype.Service;

@Service
public class TakeawayOrderServiceImpl extends ServiceImpl<TakeawayOrderMapper, TakeawayOrderDO>
    implements TakeawayOrderService {

}
