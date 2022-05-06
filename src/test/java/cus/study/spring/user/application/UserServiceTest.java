package cus.study.spring.user.application;

import cus.study.spring.user.domain.User;
import cus.study.spring.user.dto.UserDto;
import cus.study.spring.user.mapper.UserMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void mapstructTest(){
        // given
        User user = new User("A", "A name", "A address");
        UserDto userDto = new UserDto("B", "B name", "B address");

        // when
        userMapper.updateFromDto(userDto, user);

        // then
        assertAll(
                () -> assertThat(user.getName()).isEqualTo(userDto.getName()),
                () -> assertThat(user.getEmail()).isEqualTo(userDto.getEmail()),
                () -> assertThat(user.getAddress()).isEqualTo(userDto.getAddress())
        );
    }
}
