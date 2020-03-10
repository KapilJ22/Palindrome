package com.api.integration;

import com.api.PalindromeMessageApplication;
import com.api.domain.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

//import org.springframework.boot


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PalindromeMessageApplication.class)
@WebAppConfiguration
public class MsgIntegrationTest {
    ObjectMapper objectMapper = new ObjectMapper();
    @Inject
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenA_PalindromeMessage_whenGet_thenStatus200_and_isPalindrome_true()
            throws Exception {
        mockMvc.perform(get("/v1/messages/{messageId}", 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.isPalindrome").value(true));
    }

    @Test
    public void givenA_PalindromeMessage_whenPost_thenStatus201_and_isPalindrome_true() throws Exception {
        Message message = new Message(0, "aba", false);
        // Build post request with message object payload
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/v1/messages")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(message));
        mockMvc.perform(builder).andExpect(status().isCreated()).andExpect(jsonPath("$.isPalindrome").value(true));

    }


    @Test
    public void givenA_NonPalindromeMessage_whenPost_thenStatus201_and_isPalindrome_false() throws Exception {
        Message message = new Message(0, "abad", false);
        // Build post request with message object payload
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/v1/messages")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(message));
        mockMvc.perform(builder).andExpect(status().isCreated()).andExpect(jsonPath("$.isPalindrome").value(false));

    }

}