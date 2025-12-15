package top.werls.springmcpserverdemo.service;

import io.modelcontextprotocol.server.McpSyncServerExchange;
import io.modelcontextprotocol.spec.McpSchema.CreateMessageRequest;
import io.modelcontextprotocol.spec.McpSchema.CreateMessageResult;
import io.modelcontextprotocol.spec.McpSchema.LoggingLevel;
import io.modelcontextprotocol.spec.McpSchema.LoggingMessageNotification;
import io.modelcontextprotocol.spec.McpSchema.ProgressNotification;
import io.modelcontextprotocol.spec.McpSchema.Role;
import io.modelcontextprotocol.spec.McpSchema.SamplingMessage;
import io.modelcontextprotocol.spec.McpSchema.TextContent;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springaicommunity.mcp.annotation.McpProgressToken;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 * 天气
 *
 * @author JiaWei Lee by 15 12月 2025
 * @since 15 12月 2025
 * @version 1
 */
@Service
public class WeatherService {

  public record WeatherResponse(Current current) {
    public record Current(LocalDateTime time, int interval, double temperature_2m) {}
  }

  @McpTool(description = "Get the temperature (in celsius) for a specific location")
  public String getTemperature(
      McpSyncServerExchange exchange, // (1)
      @McpToolParam(description = "The location latitude") double latitude,
      @McpToolParam(description = "The location longitude") double longitude,
      @McpProgressToken String progressToken) { // (2)

    exchange.loggingNotification(LoggingMessageNotification.builder() // (3)
        .level(LoggingLevel.DEBUG)
        .data("Call getTemperature Tool with latitude: " + latitude + " and longitude: " + longitude)
        .meta(Map.of()) // non null meta as a workaround for bug: ...
        .build());

    WeatherResponse weatherResponse = RestClient.create()
        .get()
        .uri("https://api.open-meteo.com/v1/forecast?latitude={latitude}&longitude={longitude}&current=temperature_2m",
            latitude, longitude)
        .retrieve()
        .body(WeatherResponse.class);


    String epicPoem = "MCP Client doesn't provide sampling capability.";

    if (exchange.getClientCapabilities().sampling() != null) {
      // 50% progress
      exchange.progressNotification(new ProgressNotification(progressToken, 0.5, 1.0, "Start sampling"));	// (4)

      String samplingMessage = """
					For a weather forecast (temperature is in Celsius): %s.
					At location with latitude: %s and longitude: %s.
					Please write an epic poem about this forecast using a Shakespearean style.
					""".formatted(weatherResponse.current().temperature_2m(), latitude, longitude);

      CreateMessageResult samplingResponse = exchange.createMessage(CreateMessageRequest.builder()
          .systemPrompt("You are a poet!")
          .messages(List.of(new SamplingMessage(Role.USER, new TextContent(samplingMessage))))
          .build()); // (5)

      epicPoem = ((TextContent) samplingResponse.content()).text();
    }

    // 100% progress
    exchange.progressNotification(new ProgressNotification(progressToken, 1.0, 1.0, "Task completed"));

    return """
			Weather Poem: %s			
			about the weather: %s°C at location: (%s, %s)		
			""".formatted(epicPoem, weatherResponse.current().temperature_2m(), latitude, longitude);
  }
}