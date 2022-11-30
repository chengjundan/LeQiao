package com.lizi.boot.Bean;

import ch.qos.logback.classic.db.DBHelper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

/***
 * !!! SpringBoot 默认会在底层配好所有的组件,但是如果用户自己配置了以用户的优先!
 *
 * // @Configuration 表示配置类(用以替代配置文件)，也表示这个类是容器中的一个组件
 * // proxyBeanMethods：代理Bean的方法
 * // Full(proxyBeanMethods = true) 【保证每个@Bean方法被调用多少次返回的组件都是单实例的】
 * // Lite(proxyBeanMethods = false) 【每个@Bean方法被调用多少次返回的组件都是新创建的】
 * // 组件依赖必须使用Full模式
 *
 * // @Import({User.class,DBHelper.class})
 * // 给容器中自动创建出这两个类型的组件,默认组件的名字是全类名。
 * // com.lizi.Bean.User , DBHelperch.qos.logback.core.db.DBHelper@701a32
 *
 * // @ImportResource("classpath:beans.xml") 配置文件引入注册
 *
 * // 把Car这个组件自动注册到容器中
 * // @EnableConfigurationProperties(Car.class) 开启Car属性配置绑定功能
 *
 */
// @Import({User.class, DBHelper.class})
// @ConditionalOnMissingBean(name = "tom")
@ImportResource("classpath:beans.xml")
@Configuration(proxyBeanMethods = true)
@EnableConfigurationProperties(Car.class)
@Import({User.class, DBHelper.class,MyImportSelector.class})
public class MyConfig {
    // 使用Bean向容器中注册组件，默认单实例
    // Full: 外部无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册容器中的单实例对象
    @Bean
    public User user01(){ // 给容器中添加组件。以方法名作为组件的id。返回类型就是组件类型。返回的值，就是组件在容器中的实例
        User zhangsan = new User("张三",18,"男");
        zhangsan.setDog(dog01());
        return zhangsan;
    }

    /***
     * 条件装配（可以使用在方法或类中）
     * @Conditional
     * @ConditionalOnBean(name="user01") 当容器中有user01才注册组件
     * @ConditionalOnMissingBean(name = "tom") 当容器中没有tom才注册组件
     *
     * @return
     */
    @ConditionalOnBean(name = "user01")
    @Bean("tom")
    public Dog dog01(){
        return new Dog("lin",18);
    }

    /***
     *  // 给容器中加入了文件上传解析器；
     *  @Bean
     *  @ConditionalOnBean(MultipartResolver.class)  // 容器中有这个类型组件
     *  @ConditionalOnMissingBean(name = DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME) // 容器中没有这个名字 multipartResolver 的组件
     *  public MultipartResolver multipartResolver(MultipartResolver resolver) {
     *      ●  给@Bean标注的方法传入了对象参数，这个参数的值就会从容器中找并赋值。
     *      ●  SpringMVC multipartResolver 防止有些用户配置的文件上传解析器不符合规范
     *      ●  Detect if the user has created a MultipartResolver but named it incorrectly
     *      return resolver;
     *  }
    */


    public void zongJie(){
        /*
        *总结：
        ● SpringBoot先加载所有的自动配置类  xxxxxAutoConfiguration
        ● 每个自动配置类按照条件进行生效, 默认都会绑定配置文件指定的值, xxxxProperties里面拿, xxxProperties和配置文件进行了绑定
        ● 生效的配置类就会给容器中装配很多组件
        ● 只要容器中有这些组件，相当于这些功能就有了
        ● 定制化配置
            ○ 用户直接自己@Bean替换底层的组件
            ○ 用户去看这个组件是获取的配置文件什么值就去修改。
        xxxxxAutoConfiguration ---> 组件 ---> xxxxProperties里面拿值  ----> application.properties
        * */
    }

}
