package testconatiners.demo.service.impl;

import java.util.Objects;

import testconatiners.demo.exception.UserNotFoundException;
import testconatiners.demo.model.UserEntity;
import testconatiners.demo.repository.UserRepository;
import testconatiners.demo.service.UserService;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long createUser(final UserEntity userEntity) {
        UserEntity user_already_exist = userRepository
                .findByIdentityCard(userEntity.getIdentityCard());
        if (Objects.nonNull(user_already_exist)) {
            throw new UserNotFoundException("user already exist");
        }
        return userEntity.getUserId();
    }
}
