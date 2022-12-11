package kdt.prgrms.devreading.user.dataAccess;

import kdt.prgrms.devreading.user.domain.User;
import kdt.prgrms.devreading.user.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class JdbcTemplateUserRepository implements UserRepository {

    private static final int NOT_AFFECT_RESULT = 0;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public User insert(User user) {
        String sql = "insert into User(email, password, user_name, phoneNumber, authority) values(:email, :password, :userName, :phoneNumber, :authority)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("email", user.getEmail())
                .addValue("password", user.getEmail())
                .addValue("userName", user.getUserName())
                .addValue("phoneNumber", user.getPhoneNumber())
                .addValue("authority", user.getAuthority());

        jdbcTemplate.update(sql, param, keyHolder, new String[]{"user_Id"});
        long userId = keyHolder.getKey().longValue();
        user.injectUserId(userId);

        return user;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public Optional<User> findById(long userId) {
        String sql = "select user_id, email, password, user_name, phoneNumber, authority from user where user_id = :userId";
        Map<String, Object> param = Map.of("userId", userId);

        User user = jdbcTemplate.queryForObject(sql, param, userRowMapper());

        return Optional.of(user);
    }

    @Override
    public void delete(long userId) {

    }

    @Override
    public Optional<User> login(String email, String password) {
        String sql = "select user_id, email, password, user_name, phoneNumber, authority from user where email = :email and password = :password";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("email", email)
                .addValue("password", password);

        User user = jdbcTemplate.queryForObject(sql, param, userRowMapper());

        log.info("####UserRepo, user={}", user.toString());
        return Optional.of(user);
    }

    private RowMapper<User> userRowMapper() {
        return ((rs, rowNum) -> {
            long userId = rs.getLong("user_id");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String name = rs.getString("user_name");

            String phoneNumber = rs.getString("phoneNumber");
            String authority = rs.getString("authority");

            return new User(userId, email, password, name, phoneNumber, authority);
        });
    }
}
