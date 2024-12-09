package com.team14.clientProject.loginPage;

import com.team14.clientProject.homePage.HomePageRepositoryImpl;
import com.team14.clientProject.homePage.HomePageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.sql.DataSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class LoginPageControllerTest {
    @MockBean
    private HomePageService homePageService;
    @MockBean
    private HomePageRepositoryImpl homePageRepositoryImpl;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataSource dataSource;

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void loginPageShouldShow() throws Exception {
        MvcResult result = mockMvc
                .perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }


}
