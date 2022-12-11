package kdt.prgrms.devreading.book.presentation;

import kdt.prgrms.devreading.book.dto.BookRequest;
import kdt.prgrms.devreading.book.dto.BookResponse;
import kdt.prgrms.devreading.book.application.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;

    }

    @GetMapping("/api/books/{id}")
    public ResponseEntity<BookResponse> findBook(@PathVariable("id") long bookId) {
        BookResponse bookResponse = bookService.findById(bookId);
        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping("/api/books/all")
    public ResponseEntity<List<BookResponse>> findAll() {
        List<BookResponse> bookResponses = bookService.findAll();
        return ResponseEntity.ok(bookResponses);
    }

    @PostMapping("/api/newBook")
    public ResponseEntity<BookResponse> createBook(MultipartHttpServletRequest request) throws IOException {
        BookRequest bookRequest = new BookRequest(
                request.getParameter("title"),
                request.getParameter("summary"),
                Integer.parseInt(request.getParameter("price")),
                Integer.parseInt(request.getParameter("devCourse")),
                Integer.parseInt(request.getParameter("junior")),
                Integer.parseInt(request.getParameter("middle")));

        MultipartFile multipartFile = request.getFile("file");

        BookResponse bookResponse = bookService.insert(bookRequest);


        multipartFile.transferTo(new File("/users/kimkiwoong/file/" + bookResponse.getBookId() + ".png"));

        log.info("bookResponse:" + bookResponse);
        return ResponseEntity.created(URI.create("/api/books/" + bookResponse.getBookId())).body(bookResponse);
    }

    @PatchMapping("/api/books/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable("id") long id, HttpEntity<BookRequest> httpEntity) {
        BookRequest bookRequest = httpEntity.getBody();
        bookService.update(id, bookRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/images/{fileName}")
    public Resource downloadImage(@PathVariable("fileName") String fileName) {
        try {
            return new UrlResource("file:/users/kimkiwoong/file/" + fileName);
        } catch (MalformedURLException e) {
            throw new RuntimeException("기웅아 런타임 에러 만들어라 -이미지 파일 찾는 에러");
        }
    }

    //    @PostMapping("/api/newBook")
//    public ResponseEntity<BookResponse> createBook(HttpEntity<BookRequest> httpEntity) {
//        BookRequest bookRequest = httpEntity.getBody();
//        BookResponse bookResponse = bookService.insert(bookRequest);
//        log.info("bookResponse:" + bookResponse);
//        return ResponseEntity.created(URI.create("/api/books/" + bookResponse.getBookId())).body(bookResponse);
//    }

}
