# Spring 5

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
>   

