package top.werls.springmcpserverdemo.service;

import org.springaicommunity.mcp.annotation.McpResource;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

/**
 * 1
 *
 * @author JiaWei Lee by 15 12月 2025
 * @since 15 12月 2025
 * @version
 */
@Component
public class CalculatorTools {



  @McpTool(name = "add", description = "Add two numbers together")
  public int add(
      @McpToolParam(description = "First number", required = true) int a,
      @McpToolParam(description = "Second number", required = true) int b) {
    return a + b;
  }

  @McpResource(uri = "config://{key}", name = "Configuration")
  public String getConfig(String key) {
    return "configData.get(key)" +key;
  }
}
