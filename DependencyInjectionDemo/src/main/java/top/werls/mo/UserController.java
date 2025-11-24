package top.werls.mo;

/**
 * @author JiaWei Lee
 * @since on 24 11月 2025
 * @version
 */
@Component
public class UserController {
  // 需要注入的依赖
  @AutoInject
  private UserService userService;

  public void handleRequest() {
    userService.sayHello();
  }
}
