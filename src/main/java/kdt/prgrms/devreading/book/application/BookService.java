package kdt.prgrms.devreading.book.application;

import kdt.prgrms.devreading.book.domain.Book;
import kdt.prgrms.devreading.book.dto.BookRequest;
import kdt.prgrms.devreading.book.dto.BookResponse;
import kdt.prgrms.devreading.book.domain.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponse insert(BookRequest bookRequest) {
        Book book = bookRequest.createBook();
        bookRepository.insert(book);
        return new BookResponse(book);
    }

    public void update(long bookId, BookRequest bookRequest) {
        Book book = bookRequest.createBook();
        book.injectBookId(bookId);
        bookRepository.update(book);
    }

    public BookResponse findById(long bookId) {
        Book book = bookRepository.findById(bookId);
        return new BookResponse(book);
    }

    public List<BookResponse> findAll() {
        List<Book> books = bookRepository.findALl();
        return booksToResponse(books);
    }

//    public List<BookResponse> findOnePage() {
//        return new ArrayList<>();
//    }

    public void delete(long bookId) {
        bookRepository.delete(bookId); //casadate
    }

    private List<BookResponse> booksToResponse(List<Book> books) {
        return books.stream()
                .map(book -> new BookResponse(book))
                .collect(Collectors.toList());
    }
}
