package kdt.prgrms.devreading.book.presentation;

import kdt.prgrms.devreading.user.domain.User;
import kdt.prgrms.devreading.book.application.BookService;
import kdt.prgrms.devreading.user.application.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
@Controller
public class BookController {

    private final BookService bookService;
    private final UserService userService;

    public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("/")
    String home(@CookieValue(value = "userId", required = false) Long userId, HttpServletResponse response) {
        log.info("@@@@@@@@ home cookie userId: " + userId);
        if (userId == null) {
            return "newHome";
        }

        Optional<User> user = userService.findById(userId);
        if (user.isEmpty()) {
            return "newHome";
        }

        return "loginHome";
    }

    @GetMapping("/books/{id}")
    String detail(@CookieValue(value = "userId", required = false) Long userId, @PathVariable("id") long id) {
        log.info("@@@@@@@@ detail cookie userId: " + userId);

        bookService.findById(id); //있나없나 확인하기!

        return "detail";
    }

    @GetMapping("/newBook")
    String insert(@CookieValue(value = "userId", required = false) Long userId) {
        log.info("@@@@@@@@ insert cookie userId: " + userId);

        return "insert";
    }

    @GetMapping("/login")
    String loginForm(@CookieValue(value = "userId", required = false) Long userId) {
        log.info("@@@@@@@@ loginForm cookie userId: " + userId);
        return "loginForm";
    }

    @GetMapping("/newUser")
    String joinForm() {
        return "joinForm";
    }
}
