package top.werls.springbootrabbitmqdemo2;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author leejiawei
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 订单消息实际消费队列所绑定的交换机
     */
    @Bean
    DirectExchange orderDirect() {
        return ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_NOTICE.getExchange())
                .durable(true)
                .build();
    }
    /**
     * 订单延迟队列队列所绑定的交换机
     */
    @Bean
    DirectExchange orderTtlDirect() {
        return ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_TTL_NOTICE.getExchange())
                .durable(true)
                .build();
    }
    /**
     * 订单实际消费队列
     */
    @Bean
    public Queue orderQueue() {
        return new Queue(QueueEnum.QUEUE_NOTICE.getName());
    }


    @Bean
    public Queue orderDirectQueue(){
        return new Queue(QueueEnum.QUEUE_ORDER.getExchange());
    }

    /**
     * 订单延迟队列（死信队列）
     */
    @Bean
    public Queue orderTtlQueue() {
        return QueueBuilder
                .durable(QueueEnum.QUEUE_TTL_NOTICE.getName())
                .withArgument("x-dead-letter-exchange", QueueEnum.QUEUE_NOTICE.getExchange())
                //到期后转发的交换机
                .withArgument("x-dead-letter-routing-key", QueueEnum.QUEUE_NOTICE.getRouteKey())
                //到期后转发的路由键
                .build();
    }

    /**
     * 将订单队列绑定到router key
     */
    @Bean
    Binding orderBinding(DirectExchange orderDirect, Queue orderQueue){
        return BindingBuilder
                .bind(orderQueue)
                .to(orderDirect)
                .with(QueueEnum.QUEUE_NOTICE.getRouteKey());
    }

    @Bean
    Binding orderBindingD(DirectExchange orderDirect, Queue orderDirectQueue){
        return BindingBuilder
                .bind(orderDirectQueue)
                .to(orderDirect)
                .with(QueueEnum.QUEUE_ORDER.getRouteKey());
    }

    /**
     * 将订单延迟队列绑定到交换机
     */
    @Bean
    Binding orderTtlBinding(DirectExchange orderTtlDirect,Queue orderTtlQueue){
        return BindingBuilder
                .bind(orderTtlQueue)
                .to(orderTtlDirect)
                .with(QueueEnum.QUEUE_TTL_NOTICE.getRouteKey());
    }
}
