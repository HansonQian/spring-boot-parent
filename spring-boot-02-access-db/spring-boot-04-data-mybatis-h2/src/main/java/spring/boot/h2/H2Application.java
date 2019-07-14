package spring.boot.h2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = {"spring.boot.h2.mapper"})
public class H2Application {
    public static void main(String[] args) {
        SpringApplication.run(H2Application.class, args);
    }
}
