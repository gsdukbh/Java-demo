package top.werls.springboot.multimod.common;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jiawei Lee
 * @version TODO
 * @since on  2022/6/30
 */
@SpringBootTest(classes = UtilsTest.class)
class UtilsTest {

    @Test
    void demo() {
        System.out.println(Utils.demo());
    }
}