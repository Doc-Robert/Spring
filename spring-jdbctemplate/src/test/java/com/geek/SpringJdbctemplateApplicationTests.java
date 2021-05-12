package com.geek;

import com.geek.dao.BookDao;
import com.geek.entity.Book;
import com.geek.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringJdbctemplateApplicationTests {



    @Autowired
    private BookService bookService;

    @Test
    void contextLoads() {

        Book book = new Book();
        book.setUserId("2");
        book.setUsername("Reines");
        book.setUserStatus("void");

        // crud
//        bookService.add(book);
//        bookService.update(book);
//        bookService.delete("");
//
        // 查询记录数
//        int i = bookService.selectCount();
//        System.out.println(i);
        // 查询返回对象
//        Book selectOne = bookService.selectOne("1");
//        System.out.println(selectOne);
        // 查询返回集合
        List<Book> bookList = bookService.findAll();
        System.out.println(bookList);


    }
    @Test
    public void addBatch(){
        List<Object[]> arrayList = new ArrayList<>();
        Object[] o1 ={"3","violet","code"};
        Object[] o2 ={"4","under","code"};

        arrayList.add(o1);
        arrayList.add(o2);
        bookService.batchAdd(arrayList);
    }

    @Test
    public void updateBatch(){
        List<Object[]> arrayList = new ArrayList<>();
        Object[] o1 ={"violet1","code","3"};
        Object[] o2 ={"under1","code","4"};

        arrayList.add(o1);
        arrayList.add(o2);
        bookService.batchUpdate(arrayList);
    }

    @Test
    public void deleteBatch(){
        List<Object[]> arrayList = new ArrayList<>();
        Object[] o1 ={"3"};
        Object[] o2 ={"4"};

        arrayList.add(o1);
        arrayList.add(o2);
        bookService.batchDelete(arrayList);
    }

}
