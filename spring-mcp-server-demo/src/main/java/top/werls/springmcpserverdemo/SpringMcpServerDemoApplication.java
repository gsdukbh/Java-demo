package top.werls.springmcpserverdemo;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import top.werls.springmcpserverdemo.service.WeatherService;


@SpringBootApplication
public class SpringMcpServerDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringMcpServerDemoApplication.class, args);
  }

  @Bean
  public ToolCallbackProvider weatherTools(WeatherService weatherService) {
    return MethodToolCallbackProvider.builder().toolObjects(weatherService).build();
  }

  public record TextInput(String input) {
  }

  @Bean
  public ToolCallback toUpperCase() {
    return FunctionToolCallback.builder("toUpperCase", (TextInput input) -> input.input().toUpperCase())
        .inputType(TextInput.class)
        .description("Put the text to upper case")
        .build();
  }
}
