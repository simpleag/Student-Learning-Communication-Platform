package com.zwp.slcp.sqlserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.zwp.slcp.sqlser.mapper")
@EnableTransactionManagement
@EnableEurekaClient
@EnableFeignClients
public class SqlServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqlServerApplication.class, args);
	}
}
