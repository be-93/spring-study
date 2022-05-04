package cus.study.spring.user.mapper;

import cus.study.spring.common.mapstruct.GenericMapper;
import cus.study.spring.user.domain.User;
import cus.study.spring.user.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<UserDto, User> {
}
