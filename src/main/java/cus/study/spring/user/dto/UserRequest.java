package cus.study.spring.user.dto;

import cus.study.spring.user.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {
    private String email;
    private String name;
    private String address;

    public UserRequest(String email, String name, String address) {
        this.email = email;
        this.name = name;
        this.address = address;
    }

    public User toUser() {
        return new User(email, name, address);
    }
}
