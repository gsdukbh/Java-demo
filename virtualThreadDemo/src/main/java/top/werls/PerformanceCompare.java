package top.werls;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * demo
 *
 * @author JiaWei Lee
 * @version 1
 * @since on   20 11月 2025
 */
public class PerformanceCompare {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    // 尝试创建 10 万个线程
    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      for (int i = 0; i < 100_000; i++) {
        executor.submit(() -> {
          Thread.sleep(1000); // 模拟 1秒 IO 延迟
          return 0;
        });
      }
    } // 等待所有 10万个任务完成

    long end = System.currentTimeMillis();
    System.out.println("耗时: " + (end - start) + "ms");
  }
}
