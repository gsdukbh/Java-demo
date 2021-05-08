package top.werls.springbootvalidfromdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@WebMvcTest
class SpringbootValidFromDemoApplicationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void post1() throws Exception{
        mvc.perform(post("/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("id","182")
                .param("name",""))
                .andDo(print());
    }

    @Test
    void post2() throws Exception{
        mvc.perform(post("/post")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("id","182"))
                .andDo(print());
    }

}