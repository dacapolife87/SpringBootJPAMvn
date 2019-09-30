package org.hjjang.springjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringjpaApplication {

    public static void main(String[] args) {
        Hello hello = new Hello();
        hello.setData("test");
        System.out.println(hello.getData());
        SpringApplication.run(SpringjpaApplication.class, args);
    }

}
