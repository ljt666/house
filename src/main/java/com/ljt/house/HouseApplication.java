package com.ljt.house;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@NacosPropertySource(dataId="dev-house",groupId = "dev-house-ljt",autoRefreshed=true)
@MapperScan("com.ljt.house.persistence")
public class HouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(HouseApplication.class, args);
    }

}
