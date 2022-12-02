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
        * * *
        * */
    }


    // * ● 当配置类只有一个有参构造器时,构造器中的所有参数会从容器中确定。
    // *
    // * ● SpringMVC功能的自动配置类 WebMvcAutoConfiguration,生效
    // * 代码：
    // * @Configuration(proxyBeanMethods = false)
    //   @ConditionalOnWebApplication(type = Type.SERVLET)
    //   @ConditionalOnClass({ Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class })
    //   @ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
    //   @AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
    //   @AutoConfigureAfter({ DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class, ValidationAutoConfiguration.class })
    //   public class WebMvcAutoConfiguration {}
    //  -->*
    //     *
    //     * ● 向容器中配置了属性及其绑定
    //     * 如：WebMvcProperties == spring.mvc 、ResourceProperties == spring.resources
    //     * 代码：
    //     * @Configuration(proxyBeanMethods = false)
    //       @Import(EnableWebMvcConfiguration.class)
    //       @EnableConfigurationProperties({ WebMvcProperties.class, ResourceProperties.class })
    //       @Order(0)
    //       public static class WebMvcAutoConfigurationAdapter implements WebMvcConfigurer {}
    //        -->*
    //           *
    //           * ● ResourceProperties resourceProperties;获取和spring.resources绑定的所有的值的对象
    //           * ● WebMvcProperties mvcProperties 获取和spring.mvc绑定的所有的值的对象
    //           * ● ListableBeanFactory beanFactory Spring的beanFactory
    //           * ● HttpMessageConverters 找到所有的HttpMessageConverters
    //           * ● ResourceHandlerRegistrationCustomizer 找到资源处理器的自定义器
    //           * ● DispatcherServletPath （**有疑问在新的版本中没有DispatcherServletPath 与 ServletRegistrationBean）
    //           * ● ServletRegistrationBean  给应用注册Servlet、Filter....
    //           * public WebMvcAutoConfigurationAdapter(ResourceProperties resourceProperties, WebMvcProperties mvcProperties,
    //                     ListableBeanFactory beanFactory, ObjectProvider<HttpMessageConverters> messageConvertersProvider,
    //                     ObjectProvider<ResourceHandlerRegistrationCustomizer> resourceHandlerRegistrationCustomizerProvider,
    //                     ObjectProvider<DispatcherServletPath> dispatcherServletPath,
    //                     ObjectProvider<ServletRegistrationBean<?>> servletRegistrations) {
    //                 this.resourceProperties = resourceProperties;
    //                 this.mvcProperties = mvcProperties;
    //                 this.beanFactory = beanFactory;
    //                 this.messageConvertersProvider = messageConvertersProvider;
    //                 this.resourceHandlerRegistrationCustomizer = resourceHandlerRegistrationCustomizerProvider.getIfAvailable();
    //                 this.dispatcherServletPath = dispatcherServletPath;
    //                 this.servletRegistrations = servletRegistrations;
    //             }
    //             *
    //             * ● 访问静态资源的默认配置规则
    //             * ● 引入了jQuery这个静态资源/webjars/**这个前缀已经默认配置好了，同时也添加了/META-INF/resources/webjars/这个静态资源访问路径
    //             * ● 把自定义的 spring.mvc.static-path-pattern 的路径添加到资源处理器中
    //             * ● !this.resourceProperties.isAddMappings()控制静态资源的访问是否生效
    //             * ● DurationcachePeriod=this.resourceProperties.getCache().getPeriod();静态资源存放的时间
    //              -->*
    //                 * public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //                     if (!this.resourceProperties.isAddMappings()) {
    //                         logger.debug("Default resource handling disabled");
    //                     } else {
    //                         Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
    //                         CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
    //                         if (!registry.hasMappingForPattern("/webjars/**")) {
    //                             this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{"/webjars/**"})
    //                             .addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"})
    //                             .setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
    //                         }
    //
    //                         String staticPathPattern = this.mvcProperties.getStaticPathPattern();
    //                         if (!registry.hasMappingForPattern(staticPathPattern)) {
    //                             this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{staticPathPattern})
    //                             .addResourceLocations(WebMvcAutoConfiguration.getResourceLocations(this.resourceProperties.getStaticLocations()))
    //                             .setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
    //                         }
    //
    //                     }
    //                 }
    //              -->*
    //                 *
    //                 *
    //                 *
    //                 *HandlerMapping：处理器映射。保存了每一个Handler能处理哪些请求。
    //
    //	                @Bean
    //		            public WelcomePageHandlerMapping welcomePageHandlerMapping(ApplicationContext applicationContext,
    //				        FormattingConversionService mvcConversionService, ResourceUrlProvider mvcResourceUrlProvider) {
    //			            WelcomePageHandlerMapping welcomePageHandlerMapping = new WelcomePageHandlerMapping(
    //					        new TemplateAvailabilityProviders(applicationContext), applicationContext, getWelcomePage(),
    //					        this.mvcProperties.getStaticPathPattern());
    //			            welcomePageHandlerMapping.setInterceptors(getInterceptors(mvcConversionService, mvcResourceUrlProvider));
    //			            welcomePageHandlerMapping.setCorsConfigurations(getCorsConfigurations());
    //			            return welcomePageHandlerMapping;
    //		            }
    //
    //	                WelcomePageHandlerMapping(TemplateAvailabilityProviders templateAvailabilityProviders,
    //			        ApplicationContext applicationContext, Optional<Resource> welcomePage, String staticPathPattern) {
    //                  ● 欢迎页不可以指定映射前缀
    //		            if (welcomePage.isPresent() && "/**".equals(staticPathPattern)) {
    //                      //要用欢迎页功能，必须是/**
    //			            logger.info("Adding welcome page: " + welcomePage.get());
    //			            setRootViewName("forward:index.html");
    //		            }
    //		            else if (welcomeTemplateExists(templateAvailabilityProviders, applicationContext)) {
    //                      // 调用Controller  /index
    //			            logger.info("Adding welcome page template: index");
    //			            setRootViewName("index");
    //		            }
    //	            }
    //             *
    //                  ● 创建xxxConfig类并实现WebMvcConfiger,就会使WebMvcAutoConfiguration配置类失效,
    //                  ● 因为在该类中有@ConditionalOnMissingBean（WebMvcConfigurationSupport.class）,这个注解会触发 ,
    //                  ● 这是因为我们在自定义的xxxConfig类中实现了WebMvcConfiger,点进这个接口就可以知道它继承了WebMvcConfigurationSupport,
    //                  ● 所以springboot自动帮我们配置好的webMvcAutoConfiguration就会失效,注意只是我们在xxxConfig类中重写的default失效。

    //                  ● 由于favicon.ico图标是由浏览器自动发送请求/favicon.ico获取并保存在session域中的。
    //                  ● 因此，如果我们在配置文件中设置了静态资源访问前缀，那么浏览器发送的/favicon.ico由于不符合访问前缀要求，就会获取不到相对应的图标了(图标也是静态资源的一种)。
    //             *
    //             *
    //             *
    //             *
    //             *
    // *
}
