package com.cmcorg.service.takeaway.order.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@TableName(value = "takeaway_order_state_takeaway_rider")
@Data
@Schema(description = "子表：订单外卖骑手状态，父表：订单")
public class TakeawayOrderStateTakeawayRiderDO {

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "订单主键 id（外键）（分表键）")
    private Long orderId;

    @Schema(description = "状态：1 无 2 待骑手结单 3 骑手已接单 4 骑手已到店 5 骑手已拿到商品（配送中） 6 骑手申请取消订单 7 商家同意骑手取消 8 商家不同意骑手取消 9 订单配送完成")
    private Integer state;

    @Schema(description = "骑手主键 id（外键）")
    private Long riderId;

    @Schema(description = "待骑手结单开始时间")
    private Date searchRiderTime;

    @Schema(description = "骑手接单时间")
    private Date riderOrderTime;

    @Schema(description = "骑手到店时间")
    private Date riderArrivalTime;

    @Schema(description = "骑手开始配送时间")
    private Date riderBeginTime;

    @Schema(description = "骑手申请取消订单时间")
    private Date riderCancelTime;

    @Schema(description = "骑手申请取消订单的理由")
    private String riderCancelReason;

    @Schema(description = "商家同意骑手申请取消订单的理由")
    private String sellerAgreeReason;

    @Schema(description = "商家不同意骑手申请取消订单的理由")
    private String sellerRejectReason;

    @Schema(description = "商家处理骑手申请取消订单的时间")
    private Date sellerHandleTime;

    @Schema(description = "骑手订单配送完成时间")
    private Date riderCompleteTime;

    @Schema(description = "骑手姓名")
    private String name;

    @Schema(description = "骑手身份证号码")
    private String idNumber;

    @Schema(description = "骑手联系电话")
    private String phone;

}
