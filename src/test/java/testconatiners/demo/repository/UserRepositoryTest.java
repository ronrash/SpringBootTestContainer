package testconatiners.demo.repository;

import java.util.List;

import org.junit.Test;
import testconatiners.demo.model.UserEntity;

public class UserRepositoryTest extends BaseRepository{


    @Test
    void testFindAll()
    {
        List<UserEntity> userEntities = userRepository.findAll();

        System.out.println("user entitites"+userEntities);
    }


}
