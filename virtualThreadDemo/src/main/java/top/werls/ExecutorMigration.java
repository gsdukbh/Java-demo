package top.werls;

import java.util.concurrent.Executors;

/**
 * 1
 *
 * @author JiaWei Lee
 * @version 1
 * @since on   20 11月 2025
 */
public class ExecutorMigration {
  public static void main(String[] args) {

    // 传统方式: 固定大小线程池 (资源受限)
    // var executor = Executors.newFixedThreadPool(100);

    // Java 21 方式: 为每个任务创建一个新的虚拟线程
    // 不需要"池化"虚拟线程，因为它们极其廉价！
    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

      for (int i = 0; i < 10_000; i++) {
        int taskId = i;
        executor.submit(() -> {
          // 模拟 IO 操作 (HTTP 请求, DB 查询)
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
      }
      // try-with-resources 会自动等待所有任务完成 (Structured Concurrency 雏形)
    }
  }
}
