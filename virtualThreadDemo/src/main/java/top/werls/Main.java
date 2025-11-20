package top.werls;

import java.util.concurrent.ThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Demo showing basic usage of virtual threads in Java 21.
 *
 * <p>Starts one virtual thread via a factory and submits two tasks to a virtual-thread-per-task executor.
 * Logs execution details instead of printing raw stack traces.</p>
 */
public class Main {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(final String[] args) {
    final Runnable printThread = () -> log.info("Current thread: {}", Thread.currentThread());

    // Create virtual and platform thread factories
    final ThreadFactory virtualThreadFactory = Thread.ofVirtual().factory();
    final ThreadFactory platformThreadFactory = Thread.ofPlatform().factory();

    final Thread virtualThread = virtualThreadFactory.newThread(printThread);
    final Thread platformThread = platformThreadFactory.newThread(printThread);

    virtualThread.start();
    platformThread.start();

    // 等待线程结束，使用日志记录异常
    try {
      virtualThread.join();
      platformThread.join();
    } catch (final InterruptedException e) {
      Thread.currentThread().interrupt();
      log.error("Interrupted while joining threads", e);
    }

    // 使用虚拟线程执行器提交多个任务
    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      final Future<String> f1 = executor.submit(() -> {
        log.info("Task1 start on {}", Thread.currentThread());
        Thread.sleep(100);
        return "Result-1";
      });
      final Future<String> f2 = executor.submit(() -> {
        log.info("Task2 start on {}", Thread.currentThread());
        Thread.sleep(50);
        return "Result-2";
      });

      try {
        final String r1 = f1.get();
        final String r2 = f2.get();
        log.info("Results: {}, {}", r1, r2);
      } catch (final InterruptedException ie) {
        Thread.currentThread().interrupt();
        log.error("Interrupted while waiting for task results", ie);
      } catch (final ExecutionException ee) {
        log.error("Task execution failed", ee.getCause());
      }
    }

  }
}