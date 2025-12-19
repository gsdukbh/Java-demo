package top.werls.springmcpserverdemo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

/**
 * @author JiaWei Lee by 16 12月 2025
 * @since 16 12月 2025
 * @version
 */
@Configuration
public class TimeToolsConfig {

  // 定义请求参数的数据结构（Record 是极好的选择）
  public record TimeRequest(String format) {}

  @Bean
  @Description("获取服务器当前的日期和时间。参数 format 是可选的日期格式字符串（例如 'yyyy-MM-dd'）")
  public Function<TimeRequest, String> getCurrentTime() {
    return request -> {
      String formatPattern = (request.format() != null && !request.format().isEmpty())
          ? request.format()
          : "yyyy-MM-dd HH:mm:ss";

      try {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(formatPattern));
      } catch (Exception e) {
        return "无效的时间格式: " + e.getMessage();
      }
    };
  }
}
