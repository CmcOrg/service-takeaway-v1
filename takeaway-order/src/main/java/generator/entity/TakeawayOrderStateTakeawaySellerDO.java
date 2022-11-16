package generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@TableName(value = "takeaway_order_state_takeaway_seller")
@Data
@Schema(description = "子表：订单外卖商家状态，父表：订单")
public class TakeawayOrderStateTakeawaySellerDO {

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "订单主键 id（外键）（分表键）")
    private Long orderId;

    @Schema(description = "状态：1 草稿 2 买家待付款（不能修改订单信息） 3 买家付款完成 4 买家付款取消 5 买家付款超时 10 商家备货中 11 商家取消订单 12 买家申请取消订单 13 商家同意买家取消 14商家不同意买家取消 15 商家出货完成")
    private Integer state;

    @Schema(description = "买家待付款生成时间")
    private Date buyerSubmitTime;

    @Schema(description = "买家付款完成时间")
    private Date buyerPayCompleteTime;

    @Schema(description = "买家付款取消时间")
    private Date buyerPayCancelTime;

    @Schema(description = "商家取消订单的理由")
    private String sellerCancelReason;

    @Schema(description = "买家申请取消订单的理由")
    private String buyerCancelReason;

    @Schema(description = "买家申请取消订单的退款金额")
    private BigDecimal buyerCancelPrice;

    @Schema(description = "商家同意买家申请取消订单的理由")
    private String sellerAgreeReason;

    @Schema(description = "商家不同意买家申请取消订单的理由")
    private String sellerRejectReason;

    @Schema(description = "商家备货开始时间")
    private Date sellerPrepareTime;

    @Schema(description = "商家取消订单时间")
    private Date sellerCancelTime;

    @Schema(description = "买家申请取消订单的时间")
    private Date buyerCancelTime;

    @Schema(description = "商家处理买家申请取消订单的时间")
    private Date sellerHandleTime;

    @Schema(description = "商家出货完成时间")
    private Date sellerCompleteTime;

}
