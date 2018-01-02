package com.gsww;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @Description 初始化
 * @author Xander
 * @Date 2017/12/27 下午5:32
 * @see com.gsww
 * The word 'impossible' is not in my dictionary.
 */
@EnableAutoConfiguration
@ComponentScan
@Configuration
@EnableScheduling
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }

}
