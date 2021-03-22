import com.geek.entity.Book;
import com.geek.entity.CollType;
import com.geek.entity.Course;
import com.geek.entity.Orders;
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

    @Test
    public void factorybean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("factorybean.xml");
        Course course = context.getBean("myBean", Course.class);
        System.out.println(course);
    }

//    生命周期测试
    @Test
    public void beanTest1(){
//        因为ApplicationContext没有close（）方法 所有需要调用子类ClassPathXmlApplicationContext的close
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanTest1.xml");
        Orders orders = context.getBean("orders", Orders.class);
        System.out.println("4.获取创建的bean实例对象");
        System.out.println(orders);
//        需要手动销毁
        context.close();

    }
}
