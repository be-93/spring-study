package cus.study.spring.user.ui;

import cus.study.spring.user.application.UserService;
import cus.study.spring.user.domain.User;
import cus.study.spring.user.dto.UserDto;
import cus.study.spring.user.dto.UserRequest;
import cus.study.spring.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userEmail}")
    public ResponseEntity<UserResponse> findOneUser(@PathVariable final String userEmail) {
        User user = userService.findOneUser(userEmail);
        return ResponseEntity.ok(UserResponse.of(user));
    }

    @GetMapping("/redis/{userEmail}")
    public ResponseEntity<UserResponse> findOneRedisUser(@PathVariable final String userEmail) {
        User user = userService.findOneRedisUser(userEmail);
        return ResponseEntity.ok(UserResponse.of(user));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody final UserRequest userRequest) {
        User user = userService.createUser(userRequest);
        URI uri = URI.create("/user/" + user.getId());
        return ResponseEntity.created(uri).body(UserResponse.of(user));
    }

    @PutMapping("/{userEmail}")
    public ResponseEntity<UserResponse> updateUserAddress(@PathVariable final String userEmail,
                                                          @RequestBody final UserRequest userRequest) {
        User user = userService.updateAddress(userEmail, userRequest);
        return ResponseEntity.ok(UserResponse.of(user));
    }

    @PutMapping("/dto/{userEmail}")
    public ResponseEntity<UserDto> updateUserAddress(@PathVariable final String userEmail,
                                                          @RequestBody final UserDto userDto) {
        UserDto user = userService.updateAddress(userEmail, userDto);
        return ResponseEntity.ok(user);
    }
}
