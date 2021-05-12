package com.geek.dao;

import com.geek.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Robert
 * @create 2021/5/11 16:29
 * @Version 1.0
 * @Description:
 */

@Service
public class BookDaoImpl implements BookDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void add(Book book) {
        String sql = "insert into book value(?,?,?)";

        Object args[] = {book.getUserId(),book.getUsername(),book.getUserStatus()};

        int update = jdbcTemplate.update(sql, args);

        System.out.println(update);

    }

    @Override
    public void update(Book book) {
        String sql = "update book set username=?,user_status=? where user_id=?";

        Object args[] = {book.getUsername(),book.getUserStatus(),book.getUserId()};

        int update = jdbcTemplate.update(sql, args);
        System.out.println(update);
    }

    @Override
    public void delete(String id) {
        String sql = "delete from book where user_id=?";
        int update = jdbcTemplate.update(sql, id);

        System.out.println(update);
    }

    @Override
    public int selectCount() {
        String sql = "select count(*) from book";
        Integer query = jdbcTemplate.queryForObject(sql, Integer.class);
        return query;
    }

    @Override
    public Book selectOne(String id) {
        String sql = "select * from book where user_id=?";
        Book book = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Book>(Book.class), id);
        return book;
    }

    @Override
    public List<Book> findAll() {
        String sql = "select * from book";
        List<Book> bookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Book>(Book.class));
        return bookList;
    }

    @Override
    public void batchAdd(List<Object[]> batchArgs) {
        String sql = "insert into book value(?,?,?)";
        int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(ints);
    }

    @Override
    public void batchUpdate(List<Object[]> batchArgs) {
        String sql = "update book set username=?,user_status=? where user_id=?";
        int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(ints);
    }

    @Override
    public void batchDelete(List<Object[]> batchArgs) {
        String sql = "delete from book where user_id=?";
        int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(ints);
    }
}
