import com.geek.entity.Emp;
import com.geek.service.UserService;
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
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");

        UserService userService = context.getBean("userService", UserService.class);
        userService.add();

    }

    @Test
    public void testBeanDept(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");

        Emp emp = context.getBean("emp", Emp.class);
        emp.add();

    }
}
