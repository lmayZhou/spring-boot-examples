package com.lmaye.sqltoy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * -- Sql Toy Application
 *
 * @author Lmay Zhou
 * @date 2022/11/14 13:46
 * @email lmay@lmaye.com
 * @since JDK8
 */
@SpringBootApplication
@EnableTransactionManagement
public class SqlToyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SqlToyApplication.class, args);
    }
}
