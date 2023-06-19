package testconatiners.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import testconatiners.demo.exception.PersonNotFoundException;
import testconatiners.demo.model.PersonEntity;
import testconatiners.demo.repository.PersonRepository;
import testconatiners.demo.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Long createPerson(final PersonEntity personEntity) {
        // make sure we throw appropriate exception for
        Optional<PersonEntity> optionalPerson = personRepository.findByIdentityCard(personEntity.getIdentityCard());
        if (optionalPerson.isPresent()) {
            throw new PersonNotFoundException("person with same identity card exist ");
        }
        PersonEntity entity = personRepository.save(personEntity);

        return entity.getPersonId();
    }

    @Override
    public Long updatePerson(final Long personId, final PersonEntity personEntity) {
        PersonEntity actualEntity = personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException("Person does not exist"));

        actualEntity.setPersonId(actualEntity.getPersonId());
        actualEntity.setAge(personEntity.getAge());
        actualEntity.setName(personEntity.getName());
        actualEntity.setIdentityCard(personEntity.getIdentityCard());

        return personRepository.save(actualEntity).getPersonId();

    }

    @Override
    public Long partialUpdate(final String identityCard, final PersonEntity personEntity) {
        PersonEntity actualEntity = personRepository.findByIdentityCard(identityCard)
                .orElseThrow(() -> new PersonNotFoundException("Person does not exist"));
        actualEntity.setIdentityCard(personEntity.getIdentityCard());

        return personRepository.save(actualEntity).getPersonId();

    }

    @Override
    public List<PersonEntity> getAllPerons() {
        return personRepository.findAll();
    }


    public List<PersonEntity> getAllPersonCapitalNames() {
        List<PersonEntity> personEntities = personRepository.findAll();
        personEntities.stream()
                .forEach(personEntity -> personEntity.setName(personEntity.getName().toUpperCase()));
        return personEntities;
    }

    @Override
    public List<PersonEntity> getByAge(final int age) {
        return personRepository.findByAge(age).orElse(new ArrayList<>());
    }

    @Override
    public PersonEntity getById(final Long personId) {
        return personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException("Person does not exist"));
    }

    @Override
    public void deletePerson(final Long personId) {
        personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException("Person does not exist"));
        personRepository.deleteById(personId);
    }

}
