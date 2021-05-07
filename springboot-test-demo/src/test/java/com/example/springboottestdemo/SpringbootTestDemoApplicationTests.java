package com.example.springboottestdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * 随机端口测试
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringbootTestDemoApplicationTests {


    @LocalServerPort
    private int port;

    private final String url = "http://localhost:";
    @Autowired
    private TestRestTemplate testTemplate;

    @Test
    void contextLoads() {

    }

    @Test
    void init() throws Exception {
        assertThat(this.testTemplate.getForObject(url + port + "/", String.class)).contains("hello world");
    }

    @Test
    void get() {
        System.out.println(this.testTemplate.getForEntity(url + port + "/get", Book.class).toString());
        assertThat(this.testTemplate.getForEntity(url + port + "/get", Book.class)).isNotNull();
    }

    @Test
    void post() {

        System.out.println(this.testTemplate
                .postForEntity(url + port + "/post2", new Book(1, "wo de", "3"), Book.class)
                .toString());
        assertThat(this.testTemplate.postForEntity(url + port + "/post2", new Book(1, "wo de", "3"), Book.class)).isNotNull();
    }
}
