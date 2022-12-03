package com.cmcorg.service.takeaway.product.configuration;

import com.cmcorg.engine.web.cache.util.CanalKafkaHandlerUtil;
import com.cmcorg.service.takeaway.product.model.enums.TakeawayCanalKafkaHandlerKeyEnum;
import org.springframework.stereotype.Component;

@Component
public class TakeawayCanalKafkaRedisKeyConfiguration {

    public TakeawayCanalKafkaRedisKeyConfiguration(CanalKafkaHandlerUtil canalKafkaHandlerUtil) {
        canalKafkaHandlerUtil.putCanalKafkaHandlerMap(TakeawayCanalKafkaHandlerKeyEnum.values());
    }

}
