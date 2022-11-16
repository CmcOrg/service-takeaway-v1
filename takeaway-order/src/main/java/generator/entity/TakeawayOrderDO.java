package generator.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cmcorg.engine.web.auth.model.entity.BaseEntity;
import com.cmcorg.engine.web.model.generate.model.annotation.RequestClass;
import com.cmcorg.engine.web.model.generate.model.constant.WebModelConstant;
import com.cmcorg.service.takeaway.product.model.enums.TakeawaySceneEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@RequestClass(tableIgnoreFields = WebModelConstant.TABLE_IGNORE_FIELDS_TWO)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "takeaway_order")
@Data
@Schema(description = "主表：订单")
public class TakeawayOrderDO extends BaseEntity {

    @Schema(description = "场景：1 堂食 2 外卖")
    private TakeawaySceneEnum scene;

    @Schema(description = "派送费")
    private BigDecimal takeawayPrice;

    @Schema(description = "原始总价（不可修改）")
    private BigDecimal originPrice;

    @Schema(description = "最终总价（可以修改）")
    private BigDecimal finalPrice;

    @Schema(description = "买家主键 id（外键）")
    private Long buyerId;

    @Schema(description = "买家收货地址，地图取点（json）")
    private String buyerMapJson;

    @Schema(description = "买家收货地址，门牌号")
    private String buyerHouseNumber;

    @Schema(description = "买家名称")
    private String buyerName;

    @Schema(description = "买家联系电话")
    private String buyerPhone;

    @Schema(description = "买家，描述/备注")
    private String buyerRemark;

    @Schema(description = "商家，描述/备注")
    private String sellerRemark;

    @Schema(description = "是否作废（商家用）")
    private Boolean invalidFlag;

    @Schema(description = "预计出货时间")
    private Date planSellerCompleteTime;

}
