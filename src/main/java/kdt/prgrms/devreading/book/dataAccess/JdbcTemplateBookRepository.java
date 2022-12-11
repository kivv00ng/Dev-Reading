package kdt.prgrms.devreading.book.dataAccess;

import kdt.prgrms.devreading.book.domain.Book;
import kdt.prgrms.devreading.book.domain.BookRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;


@Repository
public class JdbcTemplateBookRepository implements BookRepository {

    private static final int NOT_AFFECT_RESULT = 0;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcTemplateBookRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Book insert(Book book) {
        String sql = "insert into Book(title, summary, price, dev_course, junior, middle) values(:title, :summary, :price, :devCourse, :junior, :middle)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("title", book.getTitle())
                .addValue("summary", book.getSummary())
                .addValue("price", book.getPrice())
                .addValue("devCourse", book.getDevCourse())
                .addValue("junior", book.getJunior())
                .addValue("middle", book.getMiddle());

        jdbcTemplate.update(sql, param, keyHolder, new String[]{"book_Id"});
        long bookId = keyHolder.getKey().longValue();
        book.injectBookId(bookId);

        return book;
    }

    @Override
    public void update(Book book) {
        String sql = "update book set title = :title, summary = :summary, price = :price, dev_course = :devCourse, junior = :junior, middle = :middle where book_id = :bookId";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("title", book.getTitle())
                .addValue("summary", book.getSummary())
                .addValue("price", book.getPrice())
                .addValue("devCourse", book.getDevCourse())
                .addValue("junior", book.getJunior())
                .addValue("middle", book.getMiddle())
                .addValue("bookId", book.getBookId());

        int result = jdbcTemplate.update(sql, param);

        if (result == NOT_AFFECT_RESULT) {
            throw new RuntimeException("기웅아 해당 book을 찾을 수 없다고 CustomException만들어라~");
        }
    }

    @Override
    public Book findById(long bookId) {
        String sql = "select book_id, title, summary, price, dev_course, junior, middle from book where book_id = :bookId";
        Map<String, Object> param = Map.of("bookId", bookId);

        try {
            return jdbcTemplate.queryForObject(sql, param, bookRowMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("customException만들어라 기웅아");
        }
    }

    @Override
    public List<Book> findALl() {
        String sql = "select book_id, title, summary, price, dev_course, junior, middle from book";
        return jdbcTemplate.query(sql, bookRowMapper());
    }

    /**
     * 페이징 처리는 일단 보류..ㅎㅎ
     */
    @Override
    public List<Book> findOnePage(int pageNumber) {
        String sql = "select book_id, title, summary, price, dev_course, junior, middle from book";
        return jdbcTemplate.query(sql, bookRowMapper());
    }

    @Override
    public void delete(long bookId) {
        Map<String, Object> param = Map.of("bookId", bookId);
        jdbcTemplate.update("delete from book where book_id = :bookId", param);
    }


    private RowMapper<Book> bookRowMapper() {
        return ((rs, rowNum) -> {
            long bookId = rs.getLong("book_id");
            String title = rs.getString("title");
            String summary = rs.getString("summary");
            int price = rs.getInt("price");

            int debCourse = rs.getInt("dev_course");
            int junior = rs.getInt("junior");
            int middle = rs.getInt("middle");

            return new Book(bookId, title, summary, price, debCourse, junior, middle);
        });
    }
}
