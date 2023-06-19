package testconatiners.demo.integration;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import testconatiners.demo.Utility.TestClassUtility;
import testconatiners.demo.model.PersonEntity;
import testconatiners.demo.repository.PersonRepository;
import testconatiners.demo.repository.UserRepository;
import org.springframework.context.ApplicationContextInitializer;


public  class BaseIntegrationTest {


    @Autowired
    protected PersonRepository personRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    MockMvc mockMvc;

    protected static final String PERSON_URL ="/api/v1/persons";
    protected static final String USERS_URL ="/api/v1/users";

    @Container
    protected static PostgreSQLContainer postgreSQLContainer =
            new PostgreSQLContainer("postgres")
                    .withDatabaseName("postgres-test")
                    .withUsername("postgres")
                    .withPassword("postgres");

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static  void beforeAll()
    {
        postgreSQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        postgreSQLContainer.stop();
    }
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(applicationContext.getEnvironment());
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(applicationContext,
                    "spring.flyway.enabled=false"); // Disable Flyway migrations for testing
        }
    }

    @Test
    void testCreateUser() throws Exception {
        // Prepare the user payload
        Map<String, Object> userPayload = new HashMap<>();
        userPayload.put("username", "john_doe");
        userPayload.put("email", "john.doe@example.com");

        PersonEntity personEntity = TestClassUtility.getPersonEntity();
        String user  = objectMapper.writeValueAsString(personEntity);

        // Send a POST request to create a new user
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(user))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        // Verify the response
        assertThat(result.getResponse().getStatus()).isEqualTo(201);
        assertThat(result.getResponse().getHeader("Location")).isNotBlank();
    }
}
