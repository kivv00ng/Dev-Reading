package kdt.prgrms.devreading.book.dto;

import kdt.prgrms.devreading.book.domain.Book;
import lombok.Data;

@Data
public class BookResponse {
    private long bookId;
    private String title;
    private String summary;
    private int price;

    private int devCourse;
    private int junior;
    private int middle;
    private String fileUrl;

    public BookResponse(long bookId, String title, String summary, int price, int devCourse, int junior, int middle) {
        this.bookId = bookId;
        this.title = title;
        this.summary = summary;
        this.price = price;
        this.devCourse = devCourse;
        this.junior = junior;
        this.middle = middle;
    }

    public BookResponse(Book book) {
        this(book.getBookId(), book.getTitle(), book.getSummary(), book.getPrice(), book.getDevCourse(), book.getJunior(), book.getMiddle());

    }
}
