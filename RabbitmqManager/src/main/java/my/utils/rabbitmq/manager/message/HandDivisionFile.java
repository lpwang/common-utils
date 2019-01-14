package my.utils.rabbitmq.manager.message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author lpwang
 * @Title HandDivisionFile
 * @Package my.utils.rabbitmq.manager.message
 * @Description:
 * @date 2018-12-09 20:23
 */
public class HandDivisionFile {
    public static void main(String[] args) throws Exception {
        ArrayList<AccountWithdrawReqEntry> accountWithdrawReqEntries = Lists.newArrayList();
        List<String> lines = Files.readLines(new File("/home/lpwang/桌面/2"), Charset.forName("UTF-8"));
        for (String line : lines) {
            MessageEntry messageEntry = JSONObject.parseObject(line, MessageEntry.class);
            String content = messageEntry.getContent();
            List<DivisionEntry> DivisionEntries = JSONArray.parseArray(content, DivisionEntry.class);
            for (DivisionEntry divisionEntry :DivisionEntries) {
                String divisionRule = divisionEntry.getDivisionRule();
                DivisionRuleEntry divisionRuleEntry = JSONObject.parseObject(divisionRule, DivisionRuleEntry.class);
                List<DivisionRuleEntry.Rule> rules = divisionRuleEntry.getRule();
                for (DivisionRuleEntry.Rule rule : rules) {
                    AccountWithdrawReqEntry accountWithdrawReqEntry = new AccountWithdrawReqEntry();
                    accountWithdrawReqEntry.setBusitypeCode(divisionEntry.getBusitypeCode());
                    accountWithdrawReqEntry.setSubBusitypeCode(divisionEntry.getSubBusitypeCode());
                    accountWithdrawReqEntry.setDivisionId(rule.getDivisionID());
                    accountWithdrawReqEntry.setVirtualAccountName(rule.getAccID());
                    accountWithdrawReqEntry.setRefNo(divisionEntry.getRefNo());
                    accountWithdrawReqEntry.setVoucherNo(divisionEntry.getVoucherNo());
                    accountWithdrawReqEntries.add(accountWithdrawReqEntry);
                }
            }
        }
        System.out.println(JSONObject.toJSONString(accountWithdrawReqEntries));
    }
}

class MessageEntry {
    private String command;
    private String from;
    private String to;
    private String time;
    private String called;
    private String content;
    private String className;
    private String methodName;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCalled() {
        return called;
    }

    public void setCalled(String called) {
        this.called = called;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}

class DivisionEntry {
    private String tradeRequestNo;
    private String payNo;
    private String channelNo;
    private String channelReturnNo;
    private String batchNo;
    private String refNo;
    private String voucherNo;
    private String settleDate;
    private String orderNo;
    private String busitypeCode;
    private String subBusitypeCode;
    private String channelCode;
    private String merchantNo;
    private String divisionRule;
    private String type;
    private String command;
    private Long fee;

    public String getTradeRequestNo() {
        return tradeRequestNo;
    }

    public void setTradeRequestNo(String tradeRequestNo) {
        this.tradeRequestNo = tradeRequestNo;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getChannelReturnNo() {
        return channelReturnNo;
    }

    public void setChannelReturnNo(String channelReturnNo) {
        this.channelReturnNo = channelReturnNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBusitypeCode() {
        return busitypeCode;
    }

    public void setBusitypeCode(String busitypeCode) {
        this.busitypeCode = busitypeCode;
    }

    public String getSubBusitypeCode() {
        return subBusitypeCode;
    }

    public void setSubBusitypeCode(String subBusitypeCode) {
        this.subBusitypeCode = subBusitypeCode;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getDivisionRule() {
        return divisionRule;
    }

    public void setDivisionRule(String divisionRule) {
        this.divisionRule = divisionRule;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }
}

class DivisionRuleEntry {

    public static class Rule {
        private String seq;
        private String divisionID;
        private String accID;
        private String divisionRate;
        private String divisionAmount;
        private String feeFlag;
        private String feeRate;
        private String fee;
        private String remark;

        public String getSeq() {
            return seq;
        }

        public void setSeq(String seq) {
            this.seq = seq;
        }

        public String getDivisionID() {
            return divisionID;
        }

        public void setDivisionID(String divisionID) {
            this.divisionID = divisionID;
        }

        public String getAccID() {
            return accID;
        }

        public void setAccID(String accID) {
            this.accID = accID;
        }

        public String getDivisionRate() {
            return divisionRate;
        }

        public void setDivisionRate(String divisionRate) {
            this.divisionRate = divisionRate;
        }

        public String getDivisionAmount() {
            return divisionAmount;
        }

        public void setDivisionAmount(String divisionAmount) {
            this.divisionAmount = divisionAmount;
        }

        public String getFeeFlag() {
            return feeFlag;
        }

        public void setFeeFlag(String feeFlag) {
            this.feeFlag = feeFlag;
        }

        public String getFeeRate() {
            return feeRate;
        }

        public void setFeeRate(String feeRate) {
            this.feeRate = feeRate;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

    private String divisionNum;
    private List<Rule> rule;

    public String getDivisionNum() {
        return divisionNum;
    }

    public void setDivisionNum(String divisionNum) {
        this.divisionNum = divisionNum;
    }

    public List<Rule> getRule() {
        return rule;
    }

    public void setRule(List<Rule> rule) {
        this.rule = rule;
    }
}

class AccountWithdrawReqEntry {

    private String busitypeCode;
    private String subBusitypeCode;
    private String virtualAccountName;
    private String divisionId;
    private String refNo;
    private String voucherNo;

    public String getBusitypeCode() {
        return busitypeCode;
    }

    public void setBusitypeCode(String busitypeCode) {
        this.busitypeCode = busitypeCode;
    }

    public String getSubBusitypeCode() {
        return subBusitypeCode;
    }

    public void setSubBusitypeCode(String subBusitypeCode) {
        this.subBusitypeCode = subBusitypeCode;
    }

    public String getVirtualAccountName() {
        return virtualAccountName;
    }

    public void setVirtualAccountName(String virtualAccountName) {
        this.virtualAccountName = virtualAccountName;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }
}