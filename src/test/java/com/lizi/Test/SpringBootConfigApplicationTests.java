package com.lizi.Test;

import ch.qos.logback.core.db.DBHelper;
import com.lizi.boot.Bean.Dog;
import com.lizi.boot.Bean.MyConfig;
import com.lizi.boot.Bean.User;
import com.lizi.boot.LeQiaoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootConfigApplicationTests {

    @Test
    public void contextLoads(){
        String appointStr = "-1,+2,2022-11-01,2022-11-02;-2,+3,2022-11-11,2022-11-12;";
        String[] str1 = appointStr.split(";");
        for (String s: str1) {
            System.out.println(s);
            String[] detail = s.split(",");
            System.out.println(detail[0]);
            System.out.println(detail[1]);
            System.out.println(detail[2]);
            System.out.println(detail[3]);
        }
    }

    /***
     * 测试注解
     * @Configuration、@Bean、@Import({User.class,DBHelper.class})
     *
     */
    @Test
    public static void test(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(LeQiaoApplication.class,args);
        String[] names = run.getBeanDefinitionNames();
        for (String name:names) {
            System.out.println(name);
        }

        // 从容器中获取组件
        Dog dog = run.getBean("tom",Dog.class);
        Dog dog2 = run.getBean("tom",Dog.class);
        // 输出 true
        System.out.println("组件：" + (dog == dog2));

        MyConfig bean = run.getBean(MyConfig.class);
        System.out.println(bean);

        // 如果(proxyBeanMethods = true)代理对象调用方法。SpringBoot总会检查容器中这个组件是否存在。
        User user = bean.user01();
        User user1 = bean.user01();
        System.out.println(user == user1);

        User user2 = run.getBean("user01",User.class);
        Dog dog1 = run.getBean("tom",Dog.class);
        // 如果（proxyBeanMethods = true）返回 True
        // 如果（proxyBeanMethods = false）返回 False
        System.out.println("小狗：" + (user2.getDog() == dog1));

        // 测试 @Import
        String[] getBeanName = run.getBeanNamesForType(User.class);
        System.out.println("=================");
        for (String beanName: getBeanName) {
            System.out.println(beanName);
        }
        DBHelper dbHelper = run.getBean(DBHelper.class);
        System.out.println("DBHelper" + (dbHelper));

        // 查看容器里面的组件
        // 测试条件装配
        boolean user01 = run.containsBean("user01");
        System.out.println("容器中user01：" + user01);

        boolean tom = run.containsBean("tom");
        System.out.println("容器中的tom：" + tom);
        // 测试导入配置文件
        boolean userLeQiao = run.containsBean("UserLeQiao");
        boolean tomLizi = run.containsBean("tomLiZi");
        System.out.println("haha："+userLeQiao);
        System.out.println("hehe："+tomLizi);
        run.close();
    }
}
