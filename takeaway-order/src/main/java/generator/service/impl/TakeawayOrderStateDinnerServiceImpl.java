package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.entity.TakeawayOrderStateDinnerDO;
import generator.mapper.TakeawayOrderStateDinnerMapper;
import generator.service.TakeawayOrderStateDinnerService;
import org.springframework.stereotype.Service;

@Service
public class TakeawayOrderStateDinnerServiceImpl
    extends ServiceImpl<TakeawayOrderStateDinnerMapper, TakeawayOrderStateDinnerDO>
    implements TakeawayOrderStateDinnerService {

}
