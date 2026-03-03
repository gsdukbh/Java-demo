package com.example.springbootquartzjdbc.job;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测试
 *
 * @author JiaWei Lee by  03 3月 2026
 * @since 03 3月 2026
 */
@Service
public class JobService {

  @Autowired
  private Scheduler scheduler;

  /**
   * 动态添加一个 Cron 任务
   */
  public void addJob(String jobName, String group, String cronExp) throws SchedulerException {
    JobDetail jobDetail = JobBuilder.newJob(SampleJob.class)
        .withIdentity(jobName, group)
        .storeDurably()
        .build();

    CronTrigger trigger = TriggerBuilder.newTrigger()
        .withIdentity(jobName + "_trigger", group)
        .withSchedule(CronScheduleBuilder.cronSchedule(cronExp))
        .build();

    scheduler.scheduleJob(jobDetail, trigger);
  }

  /**
   * 暂停任务
   */
  public void pauseJob(String jobName, String group) throws SchedulerException {
    scheduler.pauseJob(JobKey.jobKey(jobName, group));
  }
}