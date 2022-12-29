package com.ljt.house;

import com.ljt.house.dto.ApplyDto;
import com.ljt.house.persistence.ApplyMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest//SpringBootTest 是springboot 用于测试的注解，可指定启动类或者测试环境等，这里直接默认。
public class HouseApplicationTests {

    @Autowired
    private ApplyMapper applyMapper;

    //可以测试
    @Test
    public void contextLoads() {
        List<ApplyDto> apply = applyMapper.findApplylist();
        System.out.println(apply + "----------------------------------1");
        System.out.println("22222222222222222222");
    }

    //可以测试
    @Test
    public void test() {
        System.out.println("---------333333333333333333");
    }

}
