package com.geek.dao;

import com.geek.entity.Book;

import java.util.List;

/**
 * @Author Robert
 * @create 2021/5/11 16:28
 * @Version 1.0
 * @Description:
 */
public interface BookDao {

    void add(Book book);

    void update(Book book);

    void delete(String id);

    int selectCount();

    Book selectOne(String id);

    List<Book> findAll();

    void batchAdd(List<Object[]> batchArgs);

    void batchUpdate(List<Object[]> batchArgs);

    void batchDelete(List<Object[]> batchArgs);
}
