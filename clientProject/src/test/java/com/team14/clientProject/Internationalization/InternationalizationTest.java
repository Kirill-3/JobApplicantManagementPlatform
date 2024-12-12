package com.team14.clientProject.Internationalization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StatisticsPageTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageSource messageSource;

    @BeforeEach
    public void setUp() {
        given(messageSource.getMessage("statistics.overview.title", null, Locale.ENGLISH))
                .willReturn("Statistics Overview");
        given(messageSource.getMessage("statistics.overview.title", null, new Locale("cy")))
                .willReturn("Trosolwg o Ystadegau");
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testDefaultLanguageDisplay() throws Exception {
        mockMvc.perform(get("/statistics"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("title", "Statistics Overview"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void shouldRenderStatisticsPageInWelsh() throws Exception {
        // Perform a GET request to the /statistics page with Welsh language parameter
        MvcResult result = mockMvc.perform(get("/statistics").param("lang", "cy"))
                .andDo(print())
                .andExpect(status().isOk()) // Expecting a 200 OK status
                .andReturn();

        // Get the content of the response
        String content = result.getResponse().getContentAsString();

        // Assertions to verify Welsh content
        assertTrue(content.contains("<select name=\"lang\" class=\"form-select\" onchange=\"this.form.submit()\">"),
                "The language selector should be present.");
        assertTrue(content.contains("<option value=\"cy\" selected=\"selected\">Cymraeg</option>"),
                "Welsh should be selected in the language dropdown.");
        assertTrue(content.contains("<a href=\"/event-statistics\">??statistics.page.eventRecruitment_cy??</a>"),
                "The page should contain the placeholder or proper Welsh translation for event recruitment.");
    }
}
