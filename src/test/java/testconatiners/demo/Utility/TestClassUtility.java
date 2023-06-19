package testconatiners.demo.Utility;

import java.util.Arrays;
import java.util.List;

import testconatiners.demo.model.PersonEntity;
import testconatiners.demo.model.UserEntity;

public class TestClassUtility {


    public static PersonEntity getPersonEntity() {
        return PersonEntity.builder()
                .identityCard("123")
                .age(28)
                .name("rohit")
                .build();
    }

    public static UserEntity getUserEntity() {
        return UserEntity.builder()
                .identityCard("123")
                .age(28)
                .name("rohit")
                .build();
    }

    public static List<UserEntity> getUserList() {
        UserEntity rohit = UserEntity.builder()
                .identityCard("123")
                .age(28)
                .name("rohit")
                .build();
        UserEntity rohan = UserEntity.builder()
                .identityCard("23")
                .age(22)
                .name("rohan")
                .build();
        return Arrays.asList(rohan, rohit);

    }
    public static List<PersonEntity> getPersonList() {
        PersonEntity rohit = PersonEntity.builder()
                .identityCard("123")
                .age(28)
                .name("rohit")
                .build();
        PersonEntity rohan = PersonEntity.builder()
                .identityCard("23")
                .age(22)
                .name("rohan")
                .build();
        return Arrays.asList(rohan, rohit);

    }

    public static PersonEntity getPersonEntity(int age,String name,String identityCard) {
        return PersonEntity.builder()
                .identityCard(identityCard)
                .age(age)
                .name(name)
                .build();
    }
}
