package cus.study.spring.user.application;

import cus.study.spring.user.domain.User;
import cus.study.spring.user.domain.UserRepository;
import cus.study.spring.user.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public User findOneUser(final String userEmail) {
        return userRepository.findUserByEmail(userEmail)
                .orElseThrow();
    }

    public User createUser(final UserRequest userRequest) {
        return userRepository.save(userRequest.toUser());
    }

    public User updateAddress(final String userEmail, final UserRequest userRequest) {
        final User user = userRepository.findUserByEmail(userEmail)
                .orElseThrow();

        user.updateAddress(userRequest.getAddress());

        return user;
    }
}
