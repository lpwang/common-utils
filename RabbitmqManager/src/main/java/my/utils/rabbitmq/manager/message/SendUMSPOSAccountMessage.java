package my.utils.rabbitmq.manager.message;

import com.alibaba.fastjson.JSONObject;
import my.utils.rabbitmq.manager.entry.MessageEntry;
import my.utils.rabbitmq.manager.http.HttpFactory;
import okhttp3.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * @author lpwang
 * @Title SendUMSPOSAccountMessage
 * @Package my.utils.rabbitmq.manager.message
 * @Description:
 * @date 2018-11-10 17:10
 */
public class SendUMSPOSAccountMessage {

    public static final String URL = "http://10.70.93.75:15672/api/exchanges/%2f/payment-to-account/publish/";

    public static final String PAY_POS = "{\"called\":\"ASYNC\",\"className\":\"PosSwipeCommand\",\"command\":\"payPos\",\"content\":\"{'bankOfficeCode':'04081000','bankOfficeName':'中国银行','batchNo':'000001','cardClass':'','cardNo':'6214180700200001814','cardType':'','channelCode':'UMSPAY01','channelNo':'CH08D183122100001','command':'payPos','merchantNo':'898111958140360','noticeSign':'00','payAmount':10,'payNo':'PAYMD183122100001','payTime':'20181107204800','payType':'00','refNo':'1107ref01','refundFlag':false,'sendAccount':true,'sendSign':'00','settleDate':'20181107','status':'01','termId':'89DH6568','termNo':'transNum02','voucherNo':'v1107'}\",\"from\":\"pay-payment\",\"methodName\":\"handle\",\"time\":\"20181108211822\",\"to\":\"pay-account\"}";
    public static final String PAY_DIVISION = "{\"called\":\"ASYNC\",\"className\":\"DivisionCommand\",\"command\":\"batchDivideOrder\",\"content\":\"[{\\\"fee\\\":\\\"6\\\",\\\"batchNo\\\":\\\"BAT20181025\\\",\\\"busitypeCode\\\":\\\"02\\\",\\\"channelCode\\\":\\\"UMSPAY01\\\",\\\"channelNo\\\":\\\"CH9D182981902997\\\",\\\"command\\\":\\\"batchDivideOrder\\\",\\\"divisionRule\\\":\\\"{\\\\\\\"divisionNum\\\\\\\":2,\\\\\\\"rule\\\\\\\":[{\\\\\\\"seq\\\\\\\":1,\\\\\\\"divisionID\\\\\\\":\\\\\\\"divisiondaping5\\\\\\\",\\\\\\\"accID\\\\\\\":\\\\\\\"xinfee\\\\\\\",\\\\\\\"divisionRate\\\\\\\":0,\\\\\\\"divisionAmount\\\\\\\":4,\\\\\\\"feeFlag\\\\\\\":1,\\\\\\\"feeRate\\\\\\\":0,\\\\\\\"fee\\\\\\\":0,\\\\\\\"remark\\\\\\\":\\\\\\\"xinfee\\\\\\\\u5206\\\\\\\\u8d26\\\\\\\"},{\\\\\\\"seq\\\\\\\":2,\\\\\\\"divisionID\\\\\\\":\\\\\\\"divisiondaping6\\\\\\\",\\\\\\\"accID\\\\\\\":\\\\\\\"xinrepay\\\\\\\",\\\\\\\"divisionRate\\\\\\\":0,\\\\\\\"divisionAmount\\\\\\\":6,\\\\\\\"feeFlag\\\\\\\":0,\\\\\\\"feeRate\\\\\\\":0,\\\\\\\"fee\\\\\\\":0,\\\\\\\"remark\\\\\\\":\\\\\\\"xinrepay\\\\\\\\u5206\\\\\\\\u8d26\\\\\\\"}]}\\\",\\\"merchantNo\\\":\\\"898111958140360\\\",\\\"payNo\\\":\\\"PAYMD182981902997\\\",\\\"refNo\\\":\\\"1107ref01\\\",\\\"responseTime\\\":\\\"20181108193600\\\",\\\"sendAccount\\\":true,\\\"status\\\":\\\"01\\\",\\\"subBusitypeCode\\\":\\\"54\\\",\\\"tradeRequestNo\\\":\\\"TRD201810252997\\\",\\\"type\\\":\\\"01\\\",\\\"voucherNo\\\":\\\"v1107\\\"}]\",\"from\":\"pay-payment\",\"methodName\":\"handle\",\"time\":\"20181108215020\",\"to\":\"pay-account\"}";
    public static final String PAY_WITHDRAW = "{\n" +
            "    \"called\": \"async\",\n" +
            "    \"className\": \"WithdrawCommand\",\n" +
            "    \"command\": \"payWithdraw\",\n" +
            "    \"content\": \"{'amount':2,'batchNo':'batch_no1106','busitypeCode':'02','channelCode':'UMSPAY01','channelNo':'channel_no1106','channelReturnNo':'channel_return_no1106','command':'payWithdraw','isRefund':false,'orderNo':'order_no1106','payNo':'pay_no1106','subBusitypeCode':'54','tradeRequestNo':'trade_request_no1106','type':'T00','virtualAccountName':'xinrepay'}\",\n" +
            "    \"from\": \"PAY-JOB\",\n" +
            "    \"methodName\": \"handle\",\n" +
            "    \"time\": \"20181106222104\",\n" +
            "    \"to\": \"PAY-ACCOUNT\"\n" +
            "}";
    public static final String PAY_TRANSFER = "{\n" +
            "    \"called\": \"ASYNC\",\n" +
            "    \"className\": \"VirtualAccTransferCommand\",\n" +
            "    \"command\": \"virtualAccTransfer\",\n" +
            "    \"content\": \"{'accountIdIn':'UMS2PUB008','accountIdOut':'UMS2PUB025','accountKeyIn':'xinfee','accountKeyOut':'xinrepay','amount':1,'busiTypeCode':'02','channelCode':'UMSPAY01','channelNo':'CH10M183141000002','channelRequestTime':'20181110100029','channelReturnNo':'','command':'virtualAccTransfer','orderNo':'406701074176201','refNo':null,'payNo':'PAYMM183141000009','remark':'','responseCode':'000000','responseMessage':'交易成功','responseTime':'20181110100029','sendAccount':true,'status':'01','subBusiTypeCode':'54','tradeRequestNo':'ctjAT572085702095037'}\",\n" +
            "    \"from\": \"pay-payment\",\n" +
            "    \"methodName\": \"handle\",\n" +
            "    \"time\": \"20181110100029\",\n" +
            "    \"to\": \"pay-account\"\n" +
            "}";

    public static void main(String[] args) throws Exception {
        OkHttpClient client = HttpFactory.getNewInstants();

        ArrayList<String> list = new ArrayList<String>();
        list.add(SendUMSPOSAccountMessage.PAY_POS);
        list.add(SendUMSPOSAccountMessage.PAY_DIVISION);
        list.add(SendUMSPOSAccountMessage.PAY_TRANSFER);
        list.add(SendUMSPOSAccountMessage.PAY_WITHDRAW);

        int count = 0;
        while (true) {
            Random random = new Random();
            int index = random.nextInt(4);
            String message = list.get(index);

            MessageEntry messageEntry = new MessageEntry();
            MessageEntry.properties properties = new MessageEntry.properties();
            properties.setContent_type("application/json");
            messageEntry.setProperties(properties);
            messageEntry.setPayload(message);

            String jsonString = JSONObject.toJSONString(messageEntry);

            RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"), jsonString);

            Request request = new Request.Builder()
                    .url(URL)
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();
            System.out.println(++count+" == "+response.body().string());

            Thread.sleep(100);
        }
    }

}
