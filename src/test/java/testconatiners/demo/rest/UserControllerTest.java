package testconatiners.demo.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static testconatiners.demo.Utility.TestClassUtility.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.org.hamcrest.Matchers;
import testconatiners.demo.Utility.TestClassUtility;
import testconatiners.demo.model.UserEntity;
import testconatiners.demo.service.PersonService;
import testconatiners.demo.service.UserService;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    private static final String BASE_URL ="/api/v1/users";
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Create a user - /api/v1/users")
    void createUser() throws Exception {

        // given
        UserEntity userEntity = getUserEntity();
        String user = objectMapper.writeValueAsString(userEntity);
        //when
        when(userService.createUser(userEntity)).thenReturn(1l);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON).content(user))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println("response is "+response.getContentType());
    }
}