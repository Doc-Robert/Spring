# Spring 5 :candy:

# 一、框架概述

1. spring框架是一个轻量级的开源的JavaEE框架
2. 解决企业开发的复杂性

3. spring 核心部分 IOC 和 Aop
   - **ioc**：控制反转，即把创建对象的权利交给框架，也就是指将对象的创建、对象的存储、对象的管理交给了spring容器
   - **Aop**：面向切面，就是将那些与业务无关，却为业务模块所共同调用的逻辑或责任分开封装起来，便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可操作性和可维护性。

4. spring 特点

   > - **方便解耦，简化开发**
   > - **AOP[编程](https://baike.baidu.com/item/编程)的支持**
   > - **方便程序的测试**
   > - **方便集成各种优秀框架**
   > - **方便进行事务操作**
   > - **降低API开发难度**

Spring5 模块

![image-20210315093413195](Spring 5.assets/image-20210315093413195.png)



# 二、IOC容器

1. ioc底层原理
2. ioc 接口（BeanFactory）
3. IOC操作Bean管理（基于xml）
4. IOC操作Bean管理（基于注解）、

## 2.1 IOC 概念和底层原理

>  **IOC 概念**

**ioc** ：控制反转，把对象创建和对象之间的调用过程，交给Spring 进行管理

**使用目的**：降低耦合度



>  **IOC 底层原理**

- xml解析、工厂模式、反射



**图解IOC 底层原理**

IOC 过程

![image-20210315155621161](Spring 5.assets/image-20210315155621161.png)

- [ ] 

## 2.2 IOC 接口

- IOC 思想基于IOC 容器完成，IOC 容器底层就是对象工厂

- spring 提供IOC 容器 实现的两种方式：

  - BeanFactory : IOC 容器的基本实现，Spring内部的使用接口 。不提供开发人员使用

  特点：加载配置文件是不会创建对象，在获取对象（使用时）才创建

  - ApplicationContext：BeanFactory 接口的子接口，提供更多更强大的功能

  特点：加载配置文件时就会把配置文件对象创建

- ApplicationContext接口有实现类

![image-20210315192430084](Spring 5.assets/image-20210315192430084.png)

> 类路径下 与 c盘下



## 2.3 IOC操作Bean管理

**Bean管理**：

- Bean管理指两个操作

- spring 创建对象
- spring 注入属性

> Bean管理操作有两种方式
>
> 1. 基于xml配置文件方式实现
> 2. 基于注解方式实现



### 2.3.1 基于xml方式创建对象

```xml
<!--    配置user 类对象-->
<bean id="user" class="com.geek.User">
    <property name="name" value="wuhu"></property>
</bean>
```

- 使用Bean标签 实现对象创建

- Bean标签属性

  - id： 唯一标识
  - class ： 类全路径（包路径）

- 创建对象时，默认会执行无参数构造方法

  

### 2.3.2 基于xml方式注入属性

**DI**：依赖注入，注入属性

> - 第一种注入方式：使用set 方法注入
>   1. 创建类，定义属性和对应的set 方法
>
>   ~~~java
>   private String name;
>   
>   private String beanName;
>   
>   public void setName(String name) {
>       this.name = name;
>   }
>   public void setBeanName(String s) {
>       this.beanName = beanName;
>   }
>   ~~~
>
>   2. 配置文件，配置属性注入
>
>   ~~~xml
>       <bean id="user" class="com.geek.User">
>           <property name="name" value="name"></property>
>           <property name="beanName" value="beanName"></property>
>       </bean>
>   ~~~
>
> 
>
> - 第二种方式：使用有参构造注入
>   1. 创建类，定义属性，创建属性对应的有参构造方法
>
>   ~~~java
>   public class Order {
>   
>       private String oName;
>       private String address;
>   
>       public Order(String oName, String address) {
>           this.oName = oName;
>           this.address = address;
>       }
>   }
>   ~~~
>
>   2. 在spring 配置文件进行有参配置
>
>   ~~~xml
>   <!--    有参构造器注入属性-->
>       <bean id="order" class="com.geek.Order">
>   <!--        因为默认为无惨构造，所以加上有参构造-->
>           <constructor-arg name="oName" value="something"></constructor-arg>
>           <constructor-arg name="address" value="mars"></constructor-arg>
>       </bean>
>   ~~~
>



- #### xml注入其他类型属性

  - **字面量**

    > - null值
    >
    > ~~~xml
    >  <property name="author">
    >      <null/>
    > </property>
    > ~~~
    >
    > - 属性值包含特殊符号
    >
    > ~~~xml
    >         <!-- 属性值包含特殊符号
    >             1 把<>进行转义 &lt &gt
    >             2 内容写到CDATA
    >         -->
    >         <property name="address">
    >             <value><![CDATA[<<地区>>]]></value>
    >         </property>
    > ~~~
    >
    > 使用CDATA

  

  - **注入属性 -外部bean**

    1. 创建两个类service 类和dao 类

    2. 在service中调用dao里面的方法

       > dao层
       > ~~~java
       > public class UserDaoImpl implements UserDao{
       >     public void update() {
       >         System.out.println("dao update...");
       >     }
       > }
       > ~~~

       > service层
       > ~~~java
       > public class UserService {
       >     
       >     private UserDao userDao;
       >     
       >     public void setUserDao(UserDao userDao) {
       >         this.userDao = userDao;
       >     }
       >     
       >     public void add(){
       >         System.out.println("service add ....");
       >         userDao.update();//调用
       >     }
       >     
       > }
       > ~~~

       

    3. 在spring配置文件中进行配置

    ~~~xml
    <!--    1 service 和dao 对象的创建-->
        <bean id="userService" class="com.geek.service.UserService">
            <!--    注入 userDao 对象
                    name属性：类里面属性名称
                    ref属性：创建userDao对象bean标签id值
            -->
            <property name="userDao" ref="userDaoImpl"></property>
        </bean>
        <bean id="userDaoImpl" class="com.geek.dao.UserDaoImpl"></bean>
    ~~~
    
    
    
  - **注入属性 -内部bean和级联赋值**
  
    - 一对多的gaunx
    - 在实体类中表示一对多

~~~xml
<!--    员工配置文件-->
    <bean id="emp" class="com.geek.entity.Emp">
<!--        设置属性-->
        <property name="ename" value="Reines"></property>
        <property name="gender" value="girl"></property>
<!--    设置对象类型属性-->
        <property name="dept" ref="dept"></property>
        <property name="dept.dname" value="planet"></property>
    </bean>
    <bean id="dept" class="com.geek.entity.Dept">
        <property name="dname" value="magic"></property>
    </bean>
~~~



### 2.3.3 xml 注入集合属性

**1、注入数组类型属性**

**2、注入list类型属性**

**3、注入Map集合类型属性**

​	key value 以键值形式注入

**4、注入set类型**

 ~~~xml
    <bean id="collType" class="com.geek.entity.CollType">
<!--数组类型注入        -->
        <property name="courses">
            <array>
                <value>Reines</value>
                <value>magic</value>
            </array>
        </property>
<!--list类型注入        -->
        <property name="lists">
            <list>
                <value>Reines2</value>
                <value>magic2</value>
            </list>
        </property>
<!--map类型        -->
        <property name="maps">
            <map>
                <entry key="ccc" value="Reines3"></entry>
                <entry key="bbb" value="magic3"></entry>
            </map>
        </property>
<!--set类型        -->
        <property name="sets">
            <set>
                <value>Reines4</value>
                <value>magic4</value>
            </set>
        </property>
    </bean>
 ~~~

console

~~~console
[Reines, magic]
[Reines2, magic2]
{ccc=Reines3, bbb=magic3}
[Reines4, magic4]
~~~



**5、在list集合里设置对象**

~~~java
  //list 集合中放置对象
    private List<Course> courseList;
~~~

注入

~~~xml
<!--        增加的对象集合-->
        <property name="courseList">
            <list>
                <ref bean="course1"></ref>
                <ref bean="course2"></ref>
            </list>
        </property>
    </bean>

    <bean id="course1" class="com.geek.entity.Course">
        <property name="cname" value="spring"></property>
    </bean>
    <bean id="course2" class="com.geek.entity.Course">
        <property name="cname" value="mybatis"></property>
    </bean>
~~~

 **6、把集合注入部分提取出来**

-  xml配置文件中引入名称空间


    ~~~~XML
xmlns:util="http://www.springframework.org/schema/util"
    ~~~~

~~~xml
xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                    http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd">
~~~



- util标签完成list集合注入提取

    ~~~xml
    <!--提取list集合类型属性注入-->
    <util:list id="bookList">
        <value>Reines</value>
        <value>Gilbert</value>
        <value>magic</value>
    </util:list>
    <!--2 提取list 集合类型属性注入使用-->
    <bean id="book" class="com.geek.entity.Book">
        <property name="list" ref="bookList"></property>
    </bean>
    ~~~



​    

###     2.3.4 Factory Bean

- spring 两种bean ，普通bean 和工厂bean
- 普通bean： 配置文件中定义的bean 类型就是返回类型
- 工厂bean : 配置文件中定义的bean类型可以和返回类型不一致
  - 第一步，创建类，让这个类作为工厂bean，实现接口FactoryBean
  - 第二部，实现接口里的方法，在实现的方法中定义返回的bean类型

~~~java
public class MyBean implements FactoryBean<Course> {
    //返回bean的实例
    //定义FactoryBean<Course>后 返回的是Course 对象，而不是myBean对象
    public Course getObject() throws Exception {
        Course course = new Course();
        course.setCname("abcccc");
        return course;
    }
    //返回bean的类型
    public Class<?> getObjectType() {
        return null;
    }
    //bean 是否是一个单例
    public boolean isSingleton() {
        return false;
    }
}

~~~



### 2.3.5 bean的作用域

spring里 ，设置创建bean默认是单实例

![image-20210317111508410](Spring 5.assets/image-20210317111508410.png)

![image-20210317111547039](Spring 5.assets/image-20210317111547039.png)

输出的地址相同



> 设置bean 标签属性多实例
>
> - scope属性值
>   - singleton ；默认值，表示是单实例对象
>   - prototype： 表示多实例对象
>
> ~~~xml
> <!--prototype 设置为多实例-->
>     <bean id="myBean" class="com.geek.factorybean.MyBean" scope="prototype">
>     </bean>
> ~~~
>
> 再次执行对象地址不同
>
> ![image-20210317140216547](Spring 5.assets/image-20210317140216547.png)

`singleton 与 prototype`的区别：

1. 单实例与多实例
2. 设为scope默认值singleton时，或不设置时，加载spring配置文件时自动创建单实例对象 



### 2.3.6 Bean的声明周期

#### 1、生命周期

- 对象创建到对象销毁的过程 称为生命周期



#### 2、bean的生命周期

1. 通过构造器创建bean实例（无参数构造）
2. 为bean的属性设置值或对其他bean的引用（调用set方法）
3. 调用bean的初始化方法（需要进行配置初始化的方法）
4. bean可以使用了（对象获取到）
5. 当容器关闭时，调用bean的销毁的方法（需要配置销毁的方法）



#### 3、实际调用

~~~java
public class Orders {

    public Orders() {
        System.out.println("1. 执行无参构造器创建bean实例");
    }
    private String oname;

    public void setOname(String oname) {
        this.oname = oname;
        System.out.println("2. 调用set 方法设置属性值");
    }
    public void initMethod(){
        System.out.println("3. 执行初始化方法");
    }
    public void DestroyMethod(){
        System.out.println("5. 调用销毁方法");
    }
}

~~~

bean

~~~xml
    <bean name="orders" class="com.geek.entity.Orders" init-method="initMethod" destroy-method="DestroyMethod">
<!--        指定他的初始化方法 和销毁方法 需手动销毁-->
        <property name="oname" value="ccc"></property>
    </bean>
~~~

test

~~~java
//    声明周期测试
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
~~~



#### 4、bean的后置处理器 

bean生命周期共7步

1. 通过构造器创建bean实例（无参数构造）
2. 为bean的属性设置值或对其他bean的引用（调用set方法）
3. 把bean实例传递bean后置处理器方法
4. 调用bean的初始化方法（需要进行配置初始化的方法）
5. 调用完初始化方法后，再把bean实例传递bean后置处理器方法
6. bean可以使用了（对象获取到）
7. 当容器关闭时，调用bean的销毁的方法（需要配置销毁的方法）



- 创建类，实现接口BeanPostProcessor，创建后置处理器

~~~java
public class MyBeanPost implements BeanPostProcessor{
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return null;
    }
}
~~~



~~~xml
<!--    配置后置处理器-->
<!--    <bean name="MyBeanPost" class="com.geek.entity.MyBeanPost">-->
<!--    </bean>-->
~~~

### 2.3.7 xml自动装配

**根据指定的装配规则（属性名称或者属性类型 ），spring 自动将匹配的属性值进行注入**

实例：

~~~xml
<!--    xml自动装配
            bean标签 autowire，配置自动装配
            autowire属性值:
                byName:根据属性名称注入 ,注入bean的id值和类属性值名称一致
                byType:根据属性类型注入
-->
<bean id="emp" class="com.geek.autowire.Emp" autowire="byType">
    <!--        <property name="dept" ref="Dept"></property>-->
</bean>
<bean id="Dept" class="com.geek.autowire.Dept"> </bean>
~~~



### 2.3.8 外部属性文件

1.配置连接池 druid

~~~xml
<!--  直接配置druid连接池  -->
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
    <property name="url" value="jdbc:mysql://localhost:3306/db_test"></property>
    <property name="username" value="root"></property>
    <property name="password" value="123456"></property>
</bean>
~~~

2.引入外部属性文件配置数据库连接池

properties文件引入

~~~xml
 xmlns:context="http://www.springframework.org/schema/context"
~~~

引入名称空间

~~~xml
<!-- 引入properties外部文件   -->
<context:property-placeholder location="classpath:jdbc.properties"/>

<!-- 引入 -->
<property name="driverClassName" value="${prop.driverClass}"></property>
~~~

[properties]

~~~properties
prop.driverClass = com.mysql.jdbc.Driver
~~~



### 2.3.9 基于注解方式

![image-20210426145057521](Spring 5.assets/image-20210426145057521.png)

组件扫描

~~~xml
<!--    开启组件扫描，多个包之间用，号隔开-->
<context:component-scan base-package="com.geek.service,com.geek.dao"/>
<!--    扫描包的上层目录-->
<context:component-scan base-package="com.geek"/>
~~~

创建类，在类上添加创建对象注解

~~~java
//在注解里面value属性值可以忽略不写
//默认值是类名称，首字母小写
@Component
public class UserService {

    //    @Override
    public void add() {
        System.out.println("service add");
    }
}

~~~



开启组件扫描细节配置

```xml
<!--    组件扫描配置-->
<!--    1、
            use-default-filters="false" 不使用默认filters，自己配置filter
            include-filter 设置扫描哪些内容
-->
<context:component-scan base-package="com.geek" use-default-filters="false">
    <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>

<!--    2、
            下面设置扫描包的所有内容
            <context:exclude-filter 设置哪些内容不进行扫描
-->
<context:component-scan base-package="com.geek">
    <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>
```



基于注解的形式进行属性注入

- @Autowired 根据属性类型进行自动装配

- @Qualifier 根据属性名称进行注入

  - 要和@Autowired 一起使用

    @Autowired 

    @Qualifier(value = "userDaoImpl")

- @Resource  可以根据类型注入，也可以根据名称注入

- @Value 注入普通类型属性



> 完全注解开发

@Configuration

# 三、AOP概念

> AOP（面向切面编程）

在软件业，AOP为Aspect Oriented Programming的缩写，意为：[面向切面编程](https://baike.baidu.com/item/面向切面编程/6016335)，通过[预编译](https://baike.baidu.com/item/预编译/3191547)方式和运行期间动态代理实现程序功能的统一维护的一种技术。AOP是[OOP](https://baike.baidu.com/item/OOP)的延续，是软件开发中的一个热点，也是[Spring](https://baike.baidu.com/item/Spring)框架中的一个重要内容，是[函数式编程](https://baike.baidu.com/item/函数式编程/4035031)的一种衍生范型。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的[耦合度](https://baike.baidu.com/item/耦合度/2603938)降低，提高程序的可重用性，同时提高了开发的效率。





## 3.1 Aop底层原理

AOP底层**使用动态代理**

> 基于接口
>
> 基于类

- 两种代理情况
  - 有接口，使用jdk动态代理，增强类方法

    ![image-20210507091157809](Spring 5.assets/image-20210507091157809.png)

  - 没有接口情况，使用CGLIB动态 代理
    创建子类代理对象，增强类方法

  ![image-20210507091527506](Spring 5.assets/image-20210507091527506.png)

## 3.2 使用jdk动态代理

- 使用**Proxy**类里面的方法擦护功能键代理对象·

> java.lang.reflect
>
> ## Class Proxy
>
> - [java.lang.Object](https://www.matools.com/file/manual/jdk_api_1.8_google/java/lang/Object.html)
> - - java.lang.reflect.Proxy

- 调用newProxyInstance方法

![image-20210507092917923](Spring 5.assets/image-20210507092917923.png)

方法供三个参数：

第一个参数：类加载器

第二个参数：增强方法所在的类，这个类实现的接口，支持多个接口

第三个参数：用类 实现这个接口 InvocationHandler。创建代理对象，写增强的方法



- 编写jdk动态代理代码

  - 创建接口，编写方法
  - 创建实现类，实现该接口

  ~~~java
  //（1）创建接口，定义方法
  public interface UserDao {
      public int add(int a,int b);
      public String update(String id);
  }
  
  //（2）创建接口实现类，实现方法
  public class UserDaoImpl implements UserDao {
      @Override
      public int add(int a, int b) {
          return a+b;
      }
      @Override
      public String update(String id) {
          return id;
      }
  }
  
  ~~~

  

  

*使用 Proxy 类创建接口代理对象*

~~~java
//（3）使用 Proxy 类创建接口代理对象
public class JDKProxy {
 	public static void main(String[] args) {
         //创建接口实现类代理对象
         Class[] interfaces = {UserDao.class};
         UserDaoImpl userDao = new UserDaoImpl(); 
        /** 第一参数，类加载器 
            第二参数，增强方法所在的类，这个类实现的接口，(支持多个接口)
            第三参数，新建一个类，实现这个接口 InvocationHandler，创建代理对象，写增强的部分  */
         UserDao dao =(UserDao) Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces,new UserDaoProxy(userDao));
         int result = dao.add(1, 2);
        
         System.out.println("result:"+result);
     }
}

//创建代理对象代码
class UserDaoProxy implements InvocationHandler {
    
     //1 把创建的是谁的代理对象，把谁传递过来
     //有参数构造传递
     private Object obj;
     public UserDaoProxy(Object obj) {
     	this.obj = obj;
     }
    
     //增强的逻辑
     @Override
     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         //方法之前
         System.out.println("方法之前执行...."+method.getName()+" :传递的参数..."+ Arrays.toString(args));
         
         //被增强的方法执行
         Object res = method.invoke(obj, args);
         
         //方法之后
         System.out.println("方法之后执行...."+obj);
         
         return res;
     }
}
~~~

`动态代理的好处：`

- 一个动态代理类代理的是一个接口，一般就是对应一类业务
- 一个动态代理类可以代理多个类，只要实现同一接口





## 3.3 AOP术语

> 1. 链接点 ：
>
>    ​	类中哪些方法可以被增强，这些方法被称为连接点
>
> 2. 切入点
>
>    ​	实际被增强的方法，称为切入点
>
> 3. 通知（增强）
>
>    - 实际增强的逻辑部分称为通知（增强）
>    - 通知有多种类型
>      - 前置通知
>      - 后置通知
>      - 环绕通知
>      - 异常通知
>      - 最终通知（finally）
>
> 4. 切面
>
>    ​	把通知应用到切入点的过程

### Aop在spring中的作用

提供声明式事务，语序用户自定义切面

![image-20210509162212774](Spring 5.assets/image-20210509162212774.png)



![image-20210509163329372](Spring 5.assets/image-20210509163329372.png)

## 3.4 Aop 实现方式

使用AspectJ 框架

![image-20210509161220829](Spring 5.assets/image-20210509161220829.png)

### 方式一： spring原生api接口

导入[AspectJ Weaver](https://mvnrepository.com/artifact/org.aspectj/aspectjweaver)

```xml
<dependency>
   <groupId>org.aspectj</groupId>
   <artifactId>aspectjweaver</artifactId>
   <version>1.9.4</version>
</dependency>
```

service 实现

**接口**

```java
public interface UserService {

    public void add();
    public void update();
    public void delete();
    public void select();
    
}
```

**实现类**

```java
public class UserServiceImpl implements UserService{
    @Override
    public void add() {
        System.out.println("增加用户");
    }

    @Override
    public void update() {
        System.out.println("修改用户");

    }

    @Override
    public void delete() {
        System.out.println("删除用户");

    }

    @Override
    public void select() {
        System.out.println("查询用户");

    }
}
```

**两个log**

- 目标方法执行前

```java
public class Log implements MethodBeforeAdvice {

    // method :要执行的目标对象的方法
    // args： 参数
    // target：目标对象
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(target.getClass().getName()+"的"+method.getName()+"方法，被执行了");

    }

}
```

- 目标方法执行后

```java
public class AfterLog implements AfterReturningAdvice {

    //returnValue 执行后返回值

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("执行了"+method.getName()+"方法，返回了结果为："+returnValue);
    }
}
```

注册到spring 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--注册bean-->
    <bean id="userService" class="com.geek.springaop.service.UserServiceImpl" ></bean>
    <bean id="log" class="com.geek.springaop.log.Log"/>
    <bean id="afterLog" class="com.geek.springaop.log.AfterLog"/>

<!--    使用spring原生api-->
    <!--配置aop:需要导入aop约束-->
    <aop:config>
        <!--切入点：expression 表达式，execution()要执行的位置 UserServiceImpl.*(..) 下的所有方法 -->
        <aop:pointcut id="pointcut" expression="execution(* com.geek.springaop.service.UserServiceImpl.*(..))"/>

        <!--执行环绕增强-->
        <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>
    </aop:config>


</beans>
```

**测试**

```java
@Test
void contextLoads() {
   ApplicationContext context = new ClassPathXmlApplicationContext("springContext.xml");
   //动态代理 代理的是接口
   UserService userService = (UserService) context.getBean("userService");

   userService.add();

}
```

### 方式二：自定义类

> （切面定义）

自定义增强方法

```java
//自定义before after 方法
public class Diy {

    public void before(){
        System.out.println("===方法执行前===");
    }

    public void after(){
        System.out.println("===方法执行后===");
    }
}
```

xml注册自定义aop

```xml
<!-- 方式二: 自定义类   -->
<bean id="diy" class="com.geek.springaop.diy.Diy"/>
<aop:config>
    <!--自定义切面 ref 指定引用的类-->
    <aop:aspect ref="diy">
        <!--切入点-->
        <aop:pointcut id="point" expression="execution(* com.geek.springaop.service.UserServiceImpl.*(..))"/>
        <!--通知-->
        <aop:before method="before" pointcut-ref="point"></aop:before>
        <aop:after method="after" pointcut-ref="point"></aop:after>
    </aop:aspect>
</aop:config>
```



### 注解实现

> 注解实现aop

```xml
<!--方式三：通过注解方式-->
<bean id="annotationPointCut" class="com.geek.springaop.annotation.AnnotationPointCut"/>
<!--开启注解支持 自动代理 jdk (默认) proxy-target-class="false"-->
<aop:aspectj-autoproxy />
```



@Aspect 注解开启

```java
@Aspect
public class AnnotationPointCut {

    @Before("execution(* com.geek.springaop.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("===方法执行前===");
    }

    @After("execution(* com.geek.springaop.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("===方法执行后===");
    }

    //在环绕增强中，可以给定一个参数 代表我们要获取处理切入的点
    @Around("execution(* com.geek.springaop.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕前");

        Signature signature = joinPoint.getSignature();//获得签名  执行的该方法
        System.out.println(signature);

        joinPoint.proceed();//执行方法

        System.out.println("环绕后");
    }
}
```







# 四、spring-mybatis

http://mybatis.org/spring/







# 五、声明式事务

## 1、事务回顾

![image-20210510093522636](Spring 5.assets/image-20210510093522636.png)

http://mybatis.org/spring/transactions.html





# 六、JDBC模板

**概念：**

​	Spring框架 对jdbc 进行封装 使用jdbc template 快速对数据库进行crud

添加依赖：

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.3.6</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-orm</artifactId>
    <version>5.3.6</version>
</dependency>
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.2.6</version>
</dependency>
```

配置druid 数据库连接池

```properties
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/db_test?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=123456

```



## 6.1 jdbcTemplate 增删改操作

编写service 和dao

实体

```java
@Data
public class Book {

    private String userId;
    private String username;
    private String userStatus;
    
}
```

Service

```java
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
    
    public int selectCount(){
        return bookDao.selectCount();
    }

}
```

dao接口 和实现类

```java
public interface BookDao {

    void add(Book book);

    void update(Book book);

    void delete(String id);
    
    int selectCount();
}
```

```java
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
}
```



## 6.2 jdbcTemplate 查询

### 查询返回某个值

jdbcTemplate 查询表中记录数

![image-20210512094105481](Spring 5.assets/image-20210512094105481.png)

参数： sql 语句 和 返回类型Class



```java
@Override
public int selectCount() {
    String sql = "select count(*) from book";
    Integer query = jdbcTemplate.queryForObject(sql, Integer.class);
    return query;
}
```



测试方法

```java
@Autowired
private BookService bookService;

@Test
void contextLoads() {

    Book book = new Book();
    book.setUserId("2");
    book.setUsername("Reines");
    book.setUserStatus("void");

    //crud
    //bookService.add(book);
    //bookService.update(book);
    //bookService.delete("");

    //查询记录数
    int i = bookService.selectCount();
    System.out.println(i);
}
```



### 查询返回对象 和 集合

对象

![image-20210512095752717](Spring 5.assets/image-20210512095752717.png)

​	参数：

- sql语句
- RowMapper接口，针对返回不同数据类型，使用这个接口里面实现类完成数据封装
- 传入的sql 参数值

集合



```java
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
```



## 6.3 jdbcTemplate批量操作

![image-20210512110722725](Spring 5.assets/image-20210512110722725.png)

批量操作	参数：

- sql语句
- list集合 多条数据记录

### 批量添加，删除，修改

测试方法

```java
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
```

实现方法

```java
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
```