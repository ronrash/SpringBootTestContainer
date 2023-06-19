package testconatiners.demo.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import testconatiners.demo.model.PersonEntity;
import testconatiners.demo.model.UserEntity;


@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class BaseRepository {

    @Container
    protected PostgreSQLContainer postgreSQLContainer =
            new PostgreSQLContainer("postgres")
                    .withDatabaseName("postgres-test")
                    .withUsername("postgres")
                    .withPassword("postgres");

    @Autowired
    protected PersonRepository personRepository;

    @Autowired
    protected UserRepository userRepository;

    @BeforeEach
    void setup()
    {
        intializeTestData();
    }

    @AfterEach
    void tearDown()
    {
        deleteData();
    }

    private void deleteData() {

        personRepository.deleteAll();
    }

    private void intializeTestData() {

        PersonEntity jack = PersonEntity.builder()
                .name("Jack")
                .age(30)
                .identityCard("1234")
                .build();
        PersonEntity john = PersonEntity.builder()
                .name("John")
                .age(31)
                .identityCard("889")
                .build();
        PersonEntity mary = PersonEntity.builder()
                .name("Mary")
                .age(32)
                .identityCard("223")
                .build();

        UserEntity user = UserEntity.builder()
                .name("rohit")
                .age(30)
                .identityCard("no card")
                .build();

        personRepository.saveAll(Arrays.asList(jack,john,mary));

        List<PersonEntity> all = personRepository.findAll();
        System.out.println("all persons"+all);


    userRepository.save(user);
        List<UserEntity> userEntities = userRepository.findAll();
        System.out.println("all users"+userEntities);
    }
}
