import com.api.controller.MessageController;
import com.api.domain.Message;
import com.api.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(MessageController.class)
@ActiveProfiles("test")
public class MessageControllerTest {

    /*
     * We can @Autowire MockMvc because the WebApplicationContext provides an
     * instance/bean for us
     */
    @Autowired
    MockMvc mockMvc;

    /*
     * Jackson mapper for Object -> JSON conversion
     */
    @Autowired
    ObjectMapper mapper;

    /*
     * We use @MockBean because the WebApplicationContext does not provide
     * any @Component, @Service or @Repository beans instance/bean of this service
     * in its context. It only loads the beans solely required for testing the
     * controller.
     */
    @MockBean
    MessageService messageService;

//    @Test
//    public void get_allMessages_returnsOkWithListOfMessages() throws Exception {
//
//        Iterable<Message> messageList = new Iterable<>();
//        Message message1 = new Message("AD23E5R98EFT3SL00", "kak", false);
//        Message message2 = new Message("O90DEPADE564W4W83", "Volkswagen", false);
//        messageList.(message1);
//        messageList.add(message2);
//
//        // Mocking out the message service
//        Mockito.when(messageService.getAllMessages()).thenReturn(messageList);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/messages").contentType(MediaType.APPLICATION_JSON));
////                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
////                .andExpect(jsonPath("$[0].vin", is("AD23E5R98EFT3SL00"))).andExpect(jsonPath("$[0].make", is("Ford")))
////                .andExpect(jsonPath("$[1].vin", is("O90DEPADE564W4W83")))
////                .andExpect(jsonPath("$[1].make", is("Volkswagen")));
//    }

    @Test
    public void post_createsNewMessage_andReturnsObjWith201() throws Exception {
        Message message = new Message(0, "kak", false);

        Mockito.when(messageService.createMessage(message.getMessageText())).thenReturn(message);

        // Build post request with message object payload
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/message")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(message));

//        mockMvc.perform(builder).andExpect(status().isCreated()).andExpect(jsonPath("$.Id", is(1)))
//                .andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(message)));
    }

//    @Test
//    public void post_submitsInvalidMessage_WithEmptyMake_Returns400() throws Exception {
//        // Create new message with empty 'make' field
//        Message message = new Message("AD23E5R98EFT3SL00", "", "Firebird", 1982, false);
//
//        String messageJsonString = this.mapper.writeValueAsString(message);
//
//        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/demo/create/message/")
//                .contentType(MediaType.APPLICATION_JSON).content(messageJsonString)).andExpect(status().isBadRequest());
//
//        // @Valid annotation in controller will cause exception to be thrown
//        assertEquals(MethodArgumentNotValidException.class,
//                resultActions.andReturn().getResolvedException().getClass());
//        assertTrue(resultActions.andReturn().getResolvedException().getMessage().contains("'make' field was empty"));
//    }
//
//    @Test
//    public void put_updatesAndReturnsUpdatedObjWith202() throws Exception {
//        Message message = new Message("AD23E5R98EFT3SL00", "Ford", "Fiesta", 2016, false);
//
//        Mockito.when(messageService.updateMessage("AD23E5R98EFT3SL00", message)).thenReturn(message);
//
//        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
//                .put("/demo/update/message/AD23E5R98EFT3SL00", message).contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
//                .content(this.mapper.writeValueAsBytes(message));
//
//        mockMvc.perform(builder).andExpect(status().isAccepted()).andExpect(jsonPath("$.vin", is("AD23E5R98EFT3SL00")))
//                .andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(message)));
//    }
//
//    @Test
//    public void delete_deleteMessage_Returns204Status() throws Exception {
//        String messageVin = "AD23E5R98EFT3SL00";
//
//        MessageService serviceSpy = Mockito.spy(messageService);
//        Mockito.doNothing().when(serviceSpy).deleteMessage(messageVin);
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/demo/messages/AD23E5R98EFT3SL00")
//                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
//
//        verify(messageService, times(1)).deleteMessage(messageVin);
//    }
//
//    @Test
//    public void get_messageByVin_ThrowsMessageNotFoundException() throws Exception {
//
//        // Return an empty Optional object since we didn't find the vin
//        Mockito.when(messageService.getMessageByVin("AD23E5R98EFT3SL00")).thenReturn(Optional.empty());
//
//        ResultActions resultActions = mockMvc.perform(
//                MockMvcRequestBuilders.get("/demo/messages/AD23E5R98EFT3SL00").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//
//        assertEquals(MessageNotFoundException.class, resultActions.andReturn().getResolvedException().getClass());
//        assertTrue(resultActions.andReturn().getResolvedException().getMessage()
//                .contains("Message with VIN (" + "AD23E5R98EFT3SL00" + ") not found!"));
//    }
}