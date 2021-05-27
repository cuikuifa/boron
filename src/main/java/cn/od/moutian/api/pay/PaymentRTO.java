package cn.od.moutian.api.pay;

import java.math.BigDecimal;

/**
 * Created by jacky on 16/9/9.
 * 上海渠道方支付结果RTO
 */
public class PaymentRTO {

    private Integer itemNum;
    private Integer itemStatus;
    private BigDecimal itemSuccAmount;
    private String itemResponseCode;
    private String itemResponseMsg;

    public PaymentRTO() {
    }

    @Override
    public String toString() {
        return "PaymentRTO{" +
                "itemNum=" + itemNum +
                ", itemStatus=" + itemStatus +
                ", itemSuccAmount=" + itemSuccAmount +
                ", itemResponseCode='" + itemResponseCode + '\'' +
                ", itemResponseMsg='" + itemResponseMsg + '\'' +
                '}';
    }

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    public Integer getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(Integer itemStatus) {
        this.itemStatus = itemStatus;
    }

    public BigDecimal getItemSuccAmount() {
        return itemSuccAmount;
    }

    public void setItemSuccAmount(BigDecimal itemSuccAmount) {
        this.itemSuccAmount = itemSuccAmount;
    }

    public String getItemResponseCode() {
        return itemResponseCode;
    }

    public void setItemResponseCode(String itemResponseCode) {
        this.itemResponseCode = itemResponseCode;
    }

    public String getItemResponseMsg() {
        return itemResponseMsg;
    }

    public void setItemResponseMsg(String itemResponseMsg) {
        this.itemResponseMsg = itemResponseMsg;
    }
}
