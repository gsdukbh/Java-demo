package top.werls.springbootrabbitmqdemo2;

import lombok.Getter;

/**
 * @author leejiawei
 */
@Getter
public enum QueueEnum {
    /**
     * 通知队列
     */
    QUEUE_NOTICE("order.direct", "order.cancel", "order.cancel"),
    /**
     * 超时队列
     */
    QUEUE_TTL_NOTICE("order.direct.ttl", "order.cancel.ttl", "order.cancel.ttl");
    /**
     * 交换名称
     */
    private final String exchange;
    /**
     * 队列名称
     */
    private final String name;
    /**
     * 路由键
     */
    private final String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }

}
