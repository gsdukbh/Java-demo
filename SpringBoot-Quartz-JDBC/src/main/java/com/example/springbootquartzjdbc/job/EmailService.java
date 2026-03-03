package com.example.springbootquartzjdbc.job;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 模拟发邮件
 *
 * @author JiaWei Lee by  03 3月 2026
 * @since 03 3月 2026
 */
@Component
public class EmailService {

  private static final Logger log = LoggerFactory.getLogger(EmailService.class);

  /**
   * 发送模拟邮件。
   */
  public void sendMail() {
    log.info("Executing Email Service Logic at {}", LocalDateTime.now());
  }
}
