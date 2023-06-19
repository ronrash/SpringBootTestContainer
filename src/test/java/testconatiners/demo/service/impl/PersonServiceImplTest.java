package testconatiners.demo.service.impl;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import testconatiners.demo.Utility.TestClassUtility;
import testconatiners.demo.model.PersonEntity;
import testconatiners.demo.repository.PersonRepository;
import testconatiners.demo.service.PersonService;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {


    @InjectMocks
    PersonServiceImpl personService;

    @Mock
    PersonRepository personRepository;

    @Test
    @DisplayName("Test should return all names with upper letters ")
    void getAllPersons() {

        // given
        PersonEntity personEntity = TestClassUtility.getPersonEntity();
        // when
        when(personRepository.findAll()).thenReturn(Arrays.asList(personEntity));
        List<PersonEntity> allPersonCapitalNames = personService.getAllPersonCapitalNames();
        // then
        personService.getAllPersonCapitalNames();
        assertThat(allPersonCapitalNames.get(0).getName()).isEqualTo("ROHIT");
    }
}