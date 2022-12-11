package kdt.prgrms.devreading.book.domain;

import java.util.List;

public interface BookRepository {
    Book insert(Book book);

    void update(Book book);

    Book findById(long bookId);

    List<Book> findALl();

    List<Book> findOnePage(int pageNumber);

    void delete(long bookId);
}
