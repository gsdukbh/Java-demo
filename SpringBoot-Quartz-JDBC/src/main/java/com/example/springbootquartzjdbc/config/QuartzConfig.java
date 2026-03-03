package com.example.springbootquartzjdbc.config;

import com.example.springbootquartzjdbc.job.SampleJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Quartz配置
 *
 * @author JiaWei Lee by  03 3月 2026
 * @since 03 3月 2026
 */
@Configuration
public class QuartzConfig {
  // 1. 定义 JobDetail
  @Bean
  public JobDetail sampleJobDetail() {
    return JobBuilder.newJob(SampleJob.class)
        .withIdentity("sampleJob", "group1")
        .usingJobData("info", "Hello Quartz") // 传递参数
        .storeDurably() // 即使没有 Trigger 关联，任务也保留在数据库中
        .build();
  }

  // 2. 定义 Trigger (触发器)
  @Bean
  public Trigger sampleJobTrigger() {
    // 定义调度规则：每隔 5 秒执行一次
    SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
        .withIntervalInSeconds(5)
        .repeatForever();

    return TriggerBuilder.newTrigger()
        .forJob(sampleJobDetail()) // 关联上面的 JobDetail
        .withIdentity("sampleTrigger", "group1")
        .withSchedule(scheduleBuilder)
        .build();
  }
}
