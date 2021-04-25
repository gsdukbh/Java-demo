package top.werls.springbootrabbitmqdemo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author leejiawei
 */
@SpringBootApplication
@EnableScheduling
public class SpringbootRabbitmqDemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitmqDemo2Application.class, args);
    }

    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Scheduled(fixedRate = 1000*3)
    public void test() {
        long delayTimes = 2 * 1000;
        cancelOrderSender.sendMessage("100", delayTimes);
        Order order = new Order();
        order.setId(12L);
        order.setName("nme");
        cancelOrderSender.sendMessages(order,delayTimes);
    }


}
