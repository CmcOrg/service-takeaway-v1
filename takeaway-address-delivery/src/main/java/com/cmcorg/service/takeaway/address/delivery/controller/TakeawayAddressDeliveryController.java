package com.cmcorg.service.takeaway.address.delivery.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cmcorg.engine.web.auth.model.vo.ApiResultVO;
import com.cmcorg.engine.web.model.generate.model.annotation.WebPage;
import com.cmcorg.engine.web.model.generate.model.enums.PageTypeEnum;
import com.cmcorg.engine.web.model.model.dto.NotEmptyIdSet;
import com.cmcorg.engine.web.model.model.dto.NotNullId;
import com.cmcorg.service.takeaway.address.delivery.model.dto.TakeawayAddressDeliveryInsertOrUpdateDTO;
import com.cmcorg.service.takeaway.address.delivery.model.dto.TakeawayAddressDeliveryPageDTO;
import com.cmcorg.service.takeaway.address.delivery.model.entity.TakeawayAddressDeliveryDO;
import com.cmcorg.service.takeaway.address.delivery.service.TakeawayAddressDeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@WebPage(type = PageTypeEnum.ADMIN, title = "收货地址")
@RequestMapping("/takeaway/address/delivery")
@RestController
@Tag(name = "收货地址-管理")
public class TakeawayAddressDeliveryController {

    @Resource
    TakeawayAddressDeliveryService baseService;

    @Operation(summary = "新增/修改")
    @PostMapping("/insertOrUpdate")
    public ApiResultVO<String> insertOrUpdate(@RequestBody @Valid TakeawayAddressDeliveryInsertOrUpdateDTO dto) {
        return ApiResultVO.ok(baseService.insertOrUpdate(dto));
    }

    @Operation(summary = "分页排序查询")
    @PostMapping("/page")
    public ApiResultVO<Page<TakeawayAddressDeliveryDO>> myPage(@RequestBody @Valid TakeawayAddressDeliveryPageDTO dto) {
        return ApiResultVO.ok(baseService.myPage(dto));
    }

    @Operation(summary = "通过主键id，查看详情")
    @PostMapping("/infoById")
    public ApiResultVO<TakeawayAddressDeliveryDO> infoById(@RequestBody @Valid NotNullId notNullId) {
        return ApiResultVO.ok(baseService.infoById(notNullId));
    }

    @Operation(summary = "批量删除")
    @PostMapping("/deleteByIdSet")
    public ApiResultVO<String> deleteByIdSet(@RequestBody @Valid NotEmptyIdSet notEmptyIdSet) {
        return ApiResultVO.ok(baseService.deleteByIdSet(notEmptyIdSet));
    }

}
