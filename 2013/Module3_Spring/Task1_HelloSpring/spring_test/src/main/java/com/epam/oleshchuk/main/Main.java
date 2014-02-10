package com.epam.oleshchuk.main;

import com.epam.oleshchuk.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: Maksym_Oleshchuk
 * Date: 28.08.13
 * Time: 13:10
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        HelloService helloService = (HelloService) context.getBean("helloService");
        String helloWorld = helloService.getHelloWorld();
        System.out.println(helloWorld);
    }
}
