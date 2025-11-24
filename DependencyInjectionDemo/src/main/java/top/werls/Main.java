package top.werls;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import top.werls.mo.AutoInject;
import top.werls.mo.Component;
import top.werls.mo.UserController;
import top.werls.mo.UserService;


/**
 *
 *
 * @author JiaWei Lee
 * @since on 24 11月 2025
 * @version
 */
public class Main {
  // 缓存池：存放已经创建好的 Bean (类类型 -> 实例对象)
  private Map<Class<?>, Object> beanMap = new HashMap<>();

  public Main() throws Exception {
    scanAndInstantiate();
    injectDependencies();
  }

  // 阶段一：扫描并实例化 (为了简化，这里直接硬编码了类列表，实际是扫描包)
  private void scanAndInstantiate() throws Exception {
    Class<?>[] classes = {UserService.class, UserController.class};

    for (Class<?> clazz : classes) {
      // 如果类上有 @Component 注解
      if (clazz.isAnnotationPresent(Component.class)) {
        // 反射创建实例
        Object instance = clazz.getDeclaredConstructor().newInstance();
        beanMap.put(clazz, instance);
      }
    }
  }

  // 阶段二：依赖注入
  private void injectDependencies() throws Exception {
    for (Object bean : beanMap.values()) {
      // 遍历该对象的所有字段
      for (Field field : bean.getClass().getDeclaredFields()) {
        // 如果字段上有 @AutoInject 注解
        if (field.isAnnotationPresent(AutoInject.class)) {
          // 1. 获取字段类型 (例如 UserService.class)
          Class<?> fieldType = field.getType();

          // 2. 从容器中找到对应的 Bean
          Object dependency = beanMap.get(fieldType);

          if (dependency != null) {
            // 3. 暴力反射：允许访问私有字段
            field.setAccessible(true);
            // 4. 赋值：将 dependency 注入到 bean 的 field 中
            field.set(bean, dependency);
          }
        }
      }
    }
  }

  public Object getBean(Class<?> clazz) {
    return beanMap.get(clazz);
  }

  // 测试入口
  public static void main(String[] args) throws Exception {
    Main container = new Main();

    // 获取 Controller 并调用，验证 Service 是否被注入
    UserController controller = (UserController) container.getBean(UserController.class);
    controller.handleRequest(); // 输出: Hello from UserService!
  }
}
