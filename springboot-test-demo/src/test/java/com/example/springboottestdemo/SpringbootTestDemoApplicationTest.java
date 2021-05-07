package com.example.springboottestdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest
//@SpringBootTest
//@AutoConfigureMockMvc
class SpringbootTestDemoApplicationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void init() throws Exception {
        this.mvc
                .perform(MockMvcRequestBuilders.get("/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsString("Hello, World")));
    }

    @Test
    void get() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/get", Book.class))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void post() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.post("/post")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content("sc")
                .param("id", "1")
                .param("name", "ni go")
                .param("author", "ni go"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}