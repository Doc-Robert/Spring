import com.geek.entity.Book;
import com.geek.entity.CollType;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author Robert
 * @create 2021/3/16 15:32
 * @Version 1.0
 * @Description:
 */
public class TestBean {

    @Test
    public void testBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beanType.xml");
        CollType collType = context.getBean("collType", CollType.class);
        collType.test();
    }

    @Test
    public void testBook(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beanType.xml");
        Book book = context.getBean("book", Book.class);
        book.test();
    }

}
