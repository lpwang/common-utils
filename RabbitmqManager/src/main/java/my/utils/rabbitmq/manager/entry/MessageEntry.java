package my.utils.rabbitmq.manager.entry;

/**
 * @author lpwang
 * @Title MessageEntry
 * @Package my.utils.rabbitmq.manager.entry
 * @Description: 消息实体
 * @date 2018-07-04 14:08
 */
public class MessageEntry {

    private properties properties;
    private String routing_key = "pay-monitor-queue";
    private String payload;
    private String payload_encoding = "string";

    public MessageEntry.properties getProperties() {
        return properties;
    }

    public void setProperties(MessageEntry.properties properties) {
        this.properties = properties;
    }

    public String getRouting_key() {
        return routing_key;
    }

    public void setRouting_key(String routing_key) {
        this.routing_key = routing_key;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getPayload_encoding() {
        return payload_encoding;
    }

    public void setPayload_encoding(String payload_encoding) {
        this.payload_encoding = payload_encoding;
    }

    public static class properties {
        private String content_type;

        public String getContent_type() {
            return content_type;
        }

        public void setContent_type(String content_type) {
            this.content_type = content_type;
        }
    }
}
