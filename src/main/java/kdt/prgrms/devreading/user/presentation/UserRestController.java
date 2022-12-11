package kdt.prgrms.devreading.user.presentation;

import kdt.prgrms.devreading.user.domain.User;
import kdt.prgrms.devreading.user.dto.LoginRequest;
import kdt.prgrms.devreading.user.dto.UserRequest;
import kdt.prgrms.devreading.user.dto.UserResponse;
import kdt.prgrms.devreading.user.application.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.Optional;

@Slf4j
@RestController
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/newUser")
    public ResponseEntity<UserResponse> createUser(HttpEntity<UserRequest> httpEntity) {
        UserRequest userRequest = httpEntity.getBody();
        log.info("###userRequest=>{}", userRequest);

        UserResponse userResponse = userService.insert(userRequest);
        log.info("###userResponse =>{}", userResponse);
        return ResponseEntity.created(URI.create("/api/user/" + userResponse.getUserId())).body(userResponse);
    }

    @PostMapping("/api/login")
    public ResponseEntity<Void> login(HttpEntity<LoginRequest> httpEntity, HttpServletRequest request, HttpServletResponse response) {
        LoginRequest loginRequest = httpEntity.getBody();
        Optional<User> user = userService.login(loginRequest);

        log.info("#### userisEmpty(): ", user.isEmpty());

        if (user.isEmpty()) {
            ResponseEntity.badRequest().build();
        }
        //쿠키에 시간정보 주지않으면 브라우저 종료시 종료됨.
        Cookie cookie = new Cookie("userId", String.valueOf(user.get().getUserId()));
        cookie.setHttpOnly(false);
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);

        if (httpSession != null) {
            httpSession.invalidate();
        }

        return ResponseEntity.ok().build();

    }

}
