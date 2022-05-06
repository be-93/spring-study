package cus.study.spring.user.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    private Long id;
    private String email;
    private String name;
    private String address;

    public UserDto(String email, String name, String address) {
        this.email = email;
        this.name = name;
        this.address = address;
    }
}
