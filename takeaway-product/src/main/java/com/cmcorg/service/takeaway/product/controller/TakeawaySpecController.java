package com.cmcorg.service.takeaway.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cmcorg.engine.web.auth.model.vo.ApiResultVO;
import com.cmcorg.engine.web.model.generate.model.annotation.WebPage;
import com.cmcorg.engine.web.model.generate.model.enums.PageTypeEnum;
import com.cmcorg.engine.web.model.model.dto.NotEmptyIdSet;
import com.cmcorg.engine.web.model.model.dto.NotNullId;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpecInsertOrUpdateDTO;
import com.cmcorg.service.takeaway.product.model.dto.TakeawaySpecPageDTO;
import com.cmcorg.service.takeaway.product.model.entity.TakeawaySpecDO;
import com.cmcorg.service.takeaway.product.service.TakeawaySpecService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@WebPage(type = PageTypeEnum.ADMIN, title = "商品规格")
@RequestMapping("/takeaway/spec")
@RestController
@Tag(name = "商品规格-管理")
public class TakeawaySpecController {

    @Resource
    TakeawaySpecService baseService;

    @Operation(summary = "新增/修改")
    @PostMapping("/insertOrUpdate")
    @PreAuthorize("hasAuthority('takeawaySpec:insertOrUpdate')")
    public ApiResultVO<String> insertOrUpdate(@RequestBody @Valid TakeawaySpecInsertOrUpdateDTO dto) {
        return ApiResultVO.ok(baseService.insertOrUpdate(dto));
    }

    @Operation(summary = "分页排序查询")
    @PostMapping("/page")
    @PreAuthorize("hasAuthority('takeawaySpec:page')")
    public ApiResultVO<Page<TakeawaySpecDO>> myPage(@RequestBody @Valid TakeawaySpecPageDTO dto) {
        return ApiResultVO.ok(baseService.myPage(dto));
    }

    @Operation(summary = "通过主键id，查看详情")
    @PostMapping("/infoById")
    @PreAuthorize("hasAuthority('takeawaySpec:infoById')")
    public ApiResultVO<TakeawaySpecDO> infoById(@RequestBody @Valid NotNullId notNullId) {
        return ApiResultVO.ok(baseService.infoById(notNullId));
    }

    @Operation(summary = "批量删除")
    @PostMapping("/deleteByIdSet")
    @PreAuthorize("hasAuthority('takeawaySpec:deleteByIdSet')")
    public ApiResultVO<String> deleteByIdSet(@RequestBody @Valid NotEmptyIdSet notEmptyIdSet) {
        return ApiResultVO.ok(baseService.deleteByIdSet(notEmptyIdSet));
    }

}
