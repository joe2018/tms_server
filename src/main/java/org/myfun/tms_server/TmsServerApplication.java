package org.myfun.tms_server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author Admin
 */
@SpringBootApplication
@EnableOpenApi
public class TmsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmsServerApplication.class, args);
    }

}
