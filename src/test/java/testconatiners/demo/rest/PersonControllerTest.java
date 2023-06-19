package testconatiners.demo.rest;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static testconatiners.demo.Utility.TestClassUtility.getPersonEntity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import testconatiners.demo.model.PersonEntity;
import testconatiners.demo.service.PersonService;


@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @MockBean
    PersonService personService;

    @Autowired
    MockMvc mockMvc;

    private static final String BASE_URL = "/api/v1/persons";
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Create a user - /api/v1/persons")
    void createUser() throws Exception {

        // given
        PersonEntity personEntity = getPersonEntity();
        String user = objectMapper.writeValueAsString(personEntity);
        //when
        when(personService.createPerson(personEntity)).thenReturn(1l);
        MvcResult mvcResult = mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON).content(user))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println("response is " + response.getContentType());
    }

    @Test
    @DisplayName("Update  a user - /api/v1/persons/{personId}")
    void getAUser() throws Exception {

        // given
        PersonEntity personEntity = getPersonEntity();
        String user = objectMapper.writeValueAsString(personEntity);
        String pathVariable = "/" + 1;

        //when
        when(personService.updatePerson(1L, personEntity)).thenReturn(1L);
        MvcResult mvcResult = mockMvc.perform(put(BASE_URL + pathVariable)
                        .contentType(MediaType.APPLICATION_JSON).content(user))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println("response is " + response.getContentAsString());
    }

    @Test
    @DisplayName("Get  a user - /api/v1/persons/{id}")
    void getAUserById() throws Exception {

        // given
        PersonEntity personEntity = getPersonEntity();
        String user = objectMapper.writeValueAsString(personEntity);
        String pathVariable = "/" + 1L;

        //when
        PersonEntity personEntity1 = getPersonEntity(20,"rondev","passport");
        when(personService.getById(1L)).thenReturn(personEntity1);
        MvcResult mvcResult = mockMvc.perform(get(BASE_URL + pathVariable))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        System.out.println("response is " + response.getContentAsString());
        //JsonAssert
        JSONAssert.assertEquals(user, response.getContentAsString(), false);
        System.out.println("response is " + response.getContentAsString());
    }
}