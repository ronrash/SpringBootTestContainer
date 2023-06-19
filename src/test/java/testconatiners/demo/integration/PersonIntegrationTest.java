package testconatiners.demo.integration;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import testconatiners.demo.Utility.TestClassUtility;
import testconatiners.demo.model.PersonEntity;
import testconatiners.demo.repository.PersonRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonIntegrationTest extends BaseIntegrationTest{

    @Autowired
    PersonRepository personRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Get all persons - ")
    void testGetAllPersons() throws Exception {
        //given
        List<PersonEntity> personList = TestClassUtility.getPersonList();

        personRepository.saveAll(personList);
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/persons")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response);
    }
}
