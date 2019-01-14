package my.utils.rabbitmq.manager.message;

import java.util.UUID;

/**
 * @author lpwang
 * @Title GenretaUUID
 * @Package my.utils.rabbitmq.manager.message
 * @Description:
 * @date 2018-11-23 16:04
 */
public class GenretaUUID {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        }
    }

}
