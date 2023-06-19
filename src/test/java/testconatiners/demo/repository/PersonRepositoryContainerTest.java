package testconatiners.demo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import testconatiners.demo.model.PersonEntity;

class PersonRepositoryContainerTest extends BaseRepository{

//    @Test
//    void savePersonTest(){
//        //given
//        PersonEntity jack = PersonEntity.builder()
//                .name("Jack")
//                .age(30)
//                .identityCard("1234")
//                .build();
//        //when
//        PersonEntity savedPerson = personRepository.save(jack);
//
//        // then
//
//        Assertions.assertThat(savedPerson).usingRecursiveComparison().ignoringFields("personId").isEqualTo(jack);
//    }

    @Test
    void findAll(){
        //given
        List<PersonEntity> all = personRepository.findAll();

        System.out.println("all persons"+all);
        // then

    }
}
