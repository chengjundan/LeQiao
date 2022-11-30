package com.lizi.boot.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/***
 * @Component
 * 不使用@Component 注册可以在配置类中使用 @EnableConfigurationProperties(Car.class) 绑定组件
 * 组合方式:
 * // 1、@EnableConfigurationProperties + @ConfigurationProperties
 * // 2、@Component + @ConfigurationProperties
 */
@ConfigurationProperties(prefix = "mycar")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private String brand;
    private Integer price;
}
