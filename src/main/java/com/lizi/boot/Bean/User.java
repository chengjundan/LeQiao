package com.lizi.boot.Bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
//@AllArgsConstructor
public class User {
    private String Name;
    private Integer Age;
    private String Sex;
    private Dog dog;
    private String userName;
    private String passWord;

    public User(String name, Integer age, String sex) {
        Name = name;
        Age = age;
        Sex = sex;
    }
}
