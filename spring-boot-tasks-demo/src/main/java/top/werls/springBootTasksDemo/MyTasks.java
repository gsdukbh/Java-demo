package top.werls.springBootTasksDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author leejiawei
 */
@Component

public class MyTasks {
    private static final Logger log=LoggerFactory.getLogger(MyTasks.class);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");
    @Scheduled(cron = "*/10 * * * * *")
    public void tasks1(){
        log.info("now {}",DATE_FORMAT.format(new Date()));
    }
}
