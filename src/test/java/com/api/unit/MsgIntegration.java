package com.api.unit;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.api.domain.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.springframework.boot
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;
import com.api.PalindromeMessageApplication;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PalindromeMessageApplication.class)
@WebAppConfiguration
public class MsgIntegration {
    ObjectMapper objectMapper = new ObjectMapper();
    @Inject
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenA_PalindromeMessage_whenGet_thenStatus200_and_isPalindrome_true()
            throws Exception {
        mockMvc.perform(get("/messages/{messageId}", 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.isPalindrome").value(true));
    }

    @Test
    public void createMessageAPI() throws Exception
    {
        ResultActions perform = mockMvc.perform(post("/messages/{messageText}", "aba")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.isPalindrome").value(true));
    }

}