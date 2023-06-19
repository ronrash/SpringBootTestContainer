package testconatiners.demo.rest;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import testconatiners.demo.model.PersonEntity;
import testconatiners.demo.service.PersonService;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    private PersonService personService;

    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity savePerson(@RequestBody @Valid PersonEntity personEntity) {
        Long personId = personService.createPerson(personEntity);

        URI location = getLocation(personId);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{personId}")
    public ResponseEntity updatePerson(@PathVariable Long personId, @RequestBody PersonEntity personEntity) {
        Long updatePersonId = personService.updatePerson(personId, personEntity);
        URI location = getLocation(updatePersonId);
        return ResponseEntity.ok(location);
    }

    @PatchMapping("/{identityCard}")
    public ResponseEntity partialUpdate(@PathVariable String identityCard, @RequestBody PersonEntity personEntity) {
        Long updatePersonId = personService.partialUpdate(identityCard, personEntity);
        URI location = getLocation(updatePersonId);
        return ResponseEntity.ok(location);
    }

    @GetMapping
    public ResponseEntity<List<PersonEntity>> getAllPersons() {
        return ResponseEntity.ok(personService.getAllPerons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonEntity> getPersonById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getById(id));
    }

    @GetMapping("/age")
    public ResponseEntity<List<PersonEntity>> getPersonByage(@RequestParam int age) {
        return ResponseEntity.ok(personService.getByAge(age));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removePersonById(@PathVariable Long id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private URI getLocation(Long personId) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{personId}")
                .buildAndExpand(personId)
                .toUri();
    }

}
