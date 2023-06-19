package testconatiners.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testconatiners.demo.model.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity,Long> {
    Optional<List<PersonEntity>> findByAge(int age);

    Optional<PersonEntity> findByIdentityCard(String identityCard);
}
