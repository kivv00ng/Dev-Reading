package kdt.prgrms.devreading.book.domain;

public class Book {
    private long bookId;
    private String title;
    private String summary;
    private int price;

    private int devCourse;
    private int junior;
    private int middle;


    public Book(String title, String summary, int price, int devCourse, int junior, int middle) {
        this.title = title;
        this.summary = summary;
        this.price = price;
        this.devCourse = devCourse;
        this.junior = junior;
        this.middle = middle;
    }

    public Book(long bookId, String title, String summary, int price, int devCourse, int junior, int middle) {
        this.bookId = bookId;
        this.title = title;
        this.summary = summary;
        this.price = price;
        this.devCourse = devCourse;
        this.junior = junior;
        this.middle = middle;
    }

    public void injectBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public int getPrice() {
        return price;
    }

    public int getDevCourse() {
        return devCourse;
    }

    public int getJunior() {
        return junior;
    }

    public int getMiddle() {
        return middle;
    }
}
