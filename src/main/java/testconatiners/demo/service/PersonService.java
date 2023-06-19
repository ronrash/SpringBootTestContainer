package testconatiners.demo.service;

import java.util.List;

import testconatiners.demo.model.PersonEntity;

public interface PersonService {


    //create update partilaupdate, getAllDetail getDetailById , delete
    Long createPerson(PersonEntity personEntity);

    Long updatePerson(Long personId,PersonEntity personEntity);

    Long partialUpdate(String identityCard,PersonEntity personEntity);

    List<PersonEntity> getAllPerons();

    List<PersonEntity> getByAge(int age);

    PersonEntity getById(Long personId);

    void deletePerson(Long personId);
}
