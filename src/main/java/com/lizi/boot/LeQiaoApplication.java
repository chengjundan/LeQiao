package com.lizi.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.cache.interceptor.CacheAspectSupport;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/***
 * 主程序类
 * @SpringBootApplication
 * 可以使用三个注解代替
 * @SpringBootConfiguration (表示当前类是一个配置类)
 * @EnableAutoConfiguration 里主要配置(使用@AutoConfigurationPackage 自动配置包)
 *      @AutoConfigurationPackage {
 *          (该注解里使用@Import(AutoConfigurationPackages.Registrar.class) 给容器中导入了一个组件)
 *          使用Registrar 将该组件指定包下（MainApplication所在的包下）的所有组件导入到容器中
 *      }
 *      @Import({AutoConfigurationImportSelector.class}) {
 *          1、利用getAutoConfigurationEntry(annotationMetadata);给容器中批量导入一些组件
 *          2、调用List<String> configurations = getCandidateConfigurations(annotationMetadata, attributes)获取到所有需要导入到容器中的配置类
 *          3、利用工厂加载 Map<String, List<String>> loadSpringFactories(@Nullable ClassLoader classLoader)；得到所有的组件
 *          4、从META-INF/spring.factories位置来加载一个文件。
 * 	        默认扫描我们当前系统里spring-boot-autoconfigure-2.3.4.RELEASE.jar包的 META-INF/spring.factories位置的文件
 * 	        spring.factories 文件里面写死了spring-boot一启动就要给容器中加载的所有配置类
 *      }
 * @ComponentScan("com.lizi") (指定扫描路径,将路径下符合扫描规则的类装配到容器中)
 *
 */
//
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("com.lizi.boot")
public class LeQiaoApplication {

    public static void main(String[] args) {
        // 返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(LeQiaoApplication.class,args);

        String[] names = run.getBeanDefinitionNames();
        for (String name:names) {
            System.out.println(name);
        }

        int beanDefinitionCount = run.getBeanDefinitionCount();
        System.out.println("++++++++++++++++++++++++++");
        System.out.println("Count:" + beanDefinitionCount);

        String[] beanNamesForType = run.getBeanNamesForType(CacheAspectSupport.class);
        System.out.println("======="+beanNamesForType);

        String[] beanNamesForType1 = run.getBeanNamesForType(WebMvcProperties.class);
        System.out.println("======="+beanNamesForType1);

        //SpringApplication.run(LeQiaoApplication.class,args);
    }



}
