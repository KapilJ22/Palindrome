package com.api.integration;

import com.api.controller.MessageController;
//import com.api.domain.Message;
import com.api.repository.MessageRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
////import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
////import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
////import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.boot.autoconfigure.*;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)

//@RunWith(SpringRunner.class)
//@SpringBootTest(
//        SpringBootTest.WebEnvironment.MOCK,
//        classes = Application.class)
@WebMvcTest(MessageController.class)
@AutoConfigureMockMvc
//@AutoConfigureTestDatabase
//@TestPropertySource(
//        locations = "classpath:application-integrationtest.properties")
public class MessageIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private MessageRepository repository;

    @Test
    public void givenEmployees_whenGetEmployees_thenStatus200()
            throws Exception {

      //  createTestEmployee("bob");

        mvc.perform(get("/api/messages/{messageId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }
}