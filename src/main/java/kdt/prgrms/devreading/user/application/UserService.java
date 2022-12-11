package kdt.prgrms.devreading.user.application;

import kdt.prgrms.devreading.user.domain.User;
import kdt.prgrms.devreading.user.dto.LoginRequest;
import kdt.prgrms.devreading.user.dto.UserRequest;
import kdt.prgrms.devreading.user.dto.UserResponse;
import kdt.prgrms.devreading.user.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse insert(UserRequest userRequest) {
        User user = userRequest.createUser();
        userRepository.insert(user);
        return new UserResponse(user);
    }

    public Optional<User> findById(long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user;
    }

    public Optional<User> login(LoginRequest loginRequest) {
        Optional<User> user = userRepository.login(loginRequest.getEmail(), loginRequest.getPassword());
        log.info("###userService, Optional.empty(): ", user.isEmpty());
        return user;
    }

}
