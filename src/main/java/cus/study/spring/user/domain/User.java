package cus.study.spring.user.domain;

import cus.study.spring.common.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String name;
    private String address;

    public User(String email, String name, String address) {
        this.email = email;
        this.name = name;
        this.address = address;
    }

    public void updateAddress(String address) {
        this.address = address;
    }
}
