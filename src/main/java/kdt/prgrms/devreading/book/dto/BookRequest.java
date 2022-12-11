package kdt.prgrms.devreading.book.dto;

import kdt.prgrms.devreading.book.domain.Book;
import lombok.Data;

@Data
public class BookRequest {
    private String title;
    private String summary;
    private int price;

    private int devCourse;
    private int junior;
    private int middle;

    public BookRequest() {
    }

    public BookRequest(String title, String summary, int price, int devCourse, int junior, int middle) {
        this.title = title;
        this.summary = summary;
        this.price = price;
        this.devCourse = devCourse;
        this.junior = junior;
        this.middle = middle;
    }

    public Book createBook() {
        return new Book(this.title, this.summary, this.price, this.devCourse, this.junior, this.middle);
    }

}
