package testconatiners.demo.rest;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import testconatiners.demo.model.UserEntity;
import testconatiners.demo.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid UserEntity userEntity) {
        Long userId = userService.createUser(userEntity);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/userId}")
                .buildAndExpand(userId).toUri();
        return ResponseEntity.created(location).build();
    }
}
