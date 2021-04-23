package top.werls.springbootrabbitmqdemo2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author leejiawei
 */
@Component
@Slf4j
@RabbitListener(queues = "order.cancel")
public class CancelOrderReceiver {

    @RabbitHandler
    public void handle(String orderId){
        log.info("cancel order id :{}",orderId);
    }

}
