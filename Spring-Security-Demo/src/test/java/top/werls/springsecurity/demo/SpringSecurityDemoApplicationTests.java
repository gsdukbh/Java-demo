package top.werls.springsecurity.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

//@SpringBootTest
@WebMvcTest
class SpringSecurityDemoApplicationTests {

    @Autowired
    private WebApplicationContext webContext;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setupMockMvc() {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(webContext)
                .apply(springSecurity())
                .build();
    }
    @Test
    @WithUserDetails()
    public  void contextLoads() throws Exception{
       this.mvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(MockMvcResultHandlers.print());
    }

}
