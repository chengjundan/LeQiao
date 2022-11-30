package com.lizi.boot.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/***
 * properties.yaml
 * 数据类型:
 * ● 字面量：单个的、不可再分的值。date、boolean、string、number、null
 * ● 对象：键值对的集合。map、hash、set、object
 * ● 数组：一组按次序排列的值。array、list、queue
 * 写法:
 * ● key: value；kv之间有空格
 * ● 大小写敏感
 * ● 使用缩进表示层级关系
 * ● 缩进不允许使用tab，只允许空格
 * ● 缩进的空格数不重要，只要相同层级的元素左对齐即可
 * ● '#'表示注释
 * ● 字符串无需加引号，如果要加，''与""表示字符串内容 会被 转义/不转义
 */
@Component
@ConfigurationProperties(prefix = "person")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String userName;
    private Boolean boss;
    private Date birth;
    private Integer age;
    private Dog dog;
    private String[] interests;
    private List<String> animal;
    private Map<String,Object> score;
    private Set<Double> salarys;
    private Map<String,List<Dog>> allPets;
}
