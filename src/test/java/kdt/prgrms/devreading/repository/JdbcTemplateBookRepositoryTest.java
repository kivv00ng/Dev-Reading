package kdt.prgrms.devreading.repository;

import kdt.prgrms.devreading.book.domain.BookRepository;
import kdt.prgrms.devreading.book.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JdbcTemplateBookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void insert() {
        Book book = new Book("도메인주도", "가나다라마바사", 30000, 1, 2, 3);
        book = bookRepository.insert(book);

        Assertions.assertThat(bookRepository.findById(book.getBookId())).usingRecursiveComparison().isEqualTo(book);

    }

}