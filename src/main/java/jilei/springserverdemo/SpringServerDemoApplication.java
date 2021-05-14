package jilei.springserverdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("jilei.springserverdemo.mapper")
@SpringBootApplication
public class SpringServerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringServerDemoApplication.class, args);
    }

}
