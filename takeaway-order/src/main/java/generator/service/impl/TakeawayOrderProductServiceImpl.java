package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.entity.TakeawayOrderProductDO;
import generator.mapper.TakeawayOrderProductMapper;
import generator.service.TakeawayOrderProductService;
import org.springframework.stereotype.Service;

@Service
public class TakeawayOrderProductServiceImpl extends ServiceImpl<TakeawayOrderProductMapper, TakeawayOrderProductDO>
    implements TakeawayOrderProductService {

}
