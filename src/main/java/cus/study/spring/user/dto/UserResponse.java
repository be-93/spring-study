package cus.study.spring.user.dto;

import cus.study.spring.user.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private String address;

    public UserResponse(Long id, String email, String name, String address) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
    }

    public static UserResponse of(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getName(), user.getAddress());
    }
}
