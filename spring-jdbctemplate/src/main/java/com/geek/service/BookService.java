package com.geek.service;

import com.geek.dao.BookDao;
import com.geek.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Robert
 * @create 2021/5/11 16:29
 * @Version 1.0
 * @Description:
 */

@Component
public class BookService {

    @Autowired
    private BookDao bookDao;

    public void add(Book book){
        bookDao.add(book);
    }

    public void update(Book book) {
        bookDao.update(book);
    }


    public void delete(String id){
        bookDao.delete(id);
    }

    //查询返回记录
    public int selectCount(){
        return bookDao.selectCount();
    }
    //查询返回对象
    public Book selectOne(String id){
        return bookDao.selectOne(id);
    }

    //查询返回集合
    public List<Book> findAll(){
        return bookDao.findAll();
    }

    public void batchAdd(List<Object[]> batchArgs){
        bookDao.batchAdd(batchArgs);
    }

    public void batchUpdate(List<Object[]> batchArgs){
        bookDao.batchUpdate(batchArgs);
    }

    public void batchDelete(List<Object[]> batchArgs){
        bookDao.batchDelete(batchArgs);
    }
}
