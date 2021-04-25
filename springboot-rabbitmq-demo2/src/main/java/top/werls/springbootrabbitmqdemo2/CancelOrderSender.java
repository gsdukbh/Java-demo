package top.werls.springbootrabbitmqdemo2;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author leejiawei
 */
@Component
@Slf4j
public class CancelOrderSender {

    @Autowired
    private AmqpTemplate amqpTemplate;


    public  void sendMessage(String orderId,final long delayTimes){
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_TTL_NOTICE.getExchange(),
                QueueEnum.QUEUE_TTL_NOTICE.getRouteKey(),
                orderId, message -> {
                    //给消息设置延迟 毫秒值
                    message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                    return message;
                });
        log.info("send delay message orderId:{}",orderId);
    }
    public  void  sendMessages(Order order,long delayTimes){
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_ORDER.getExchange(),
                QueueEnum.QUEUE_ORDER.getRouteKey()
              ,order);
        log.info("send {}" ,order);
    }

}
