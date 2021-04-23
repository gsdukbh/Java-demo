package top.werls.springbootrabbitmqdemo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootRabbitmqDemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitmqDemo2Application.class, args);
    }

    @Autowired
    private CancelOrderSender cancelOrderSender;

    @GetMapping("/")
    public String test() {
        //
        long delayTimes = 30 * 1000;
        cancelOrderSender.sendMessage("100", delayTimes);
        return "OK";
    }


}
