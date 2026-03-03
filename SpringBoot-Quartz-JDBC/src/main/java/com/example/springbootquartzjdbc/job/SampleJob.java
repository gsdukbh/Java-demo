package com.example.springbootquartzjdbc.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 测试任务
 *
 * @author JiaWei Lee by  03 3月 2026
 * @since 03 3月 2026
 */
public class SampleJob extends QuartzJobBean {

  private static final Logger log = LoggerFactory.getLogger(SampleJob.class);

  // 直接注入 Service，这是 Spring Boot 整合 Quartz 的最大优势
  private final EmailService emailService;

  public SampleJob(final EmailService emailService) {
    this.emailService = emailService;
  }

  @Override
  protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
    // 获取传递的参数
    final String info = context.getJobDetail().getJobDataMap().getString("info");

    log.info("--- 任务开始: {} ---", info);
    emailService.sendMail();
    log.info("--- 任务结束 ---");
  }
}
