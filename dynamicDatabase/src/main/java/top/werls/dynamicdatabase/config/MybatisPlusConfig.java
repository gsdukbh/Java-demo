package top.werls.dynamicdatabase.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jiawei Lee
 * @version TODO
 * @since on  2022/6/28
 */
@Configuration
@MapperScan("top.werls.dynamicdatabase.**.mapper*")
public class MybatisPlusConfig {
}
