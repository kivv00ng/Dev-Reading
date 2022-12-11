package kdt.prgrms.devreading.user.domain;

import java.util.Optional;

public interface UserRepository {
    User insert(User user);

    void update(User user);

    Optional<User> findById(long userId);

    void delete(long userId);

    Optional<User> login(String email, String password);
}
