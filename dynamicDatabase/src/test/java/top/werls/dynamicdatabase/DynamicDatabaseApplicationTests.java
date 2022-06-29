package top.werls.dynamicdatabase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.werls.dynamicdatabase.entity.SysUser;
import top.werls.dynamicdatabase.service.SysUserService;

@SpringBootTest
class DynamicDatabaseApplicationTests {

    @Autowired
    SysUserService service;

    @Test
    void contextLoads() {
        var user = new SysUser();
        user.setUsername("test");
        user.setUsername("test");
        service.add(user);

        var users = service.get();

        users.forEach(System.out::println);
    }

}
