package com.epam.oleshchuk;

import com.epam.oleshchuk.dao.HelloDao;
import com.epam.oleshchuk.service.HelloService;
import com.epam.oleshchuk.service.HelloServiceImpl;
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Maksym_Oleshchuk
 * Date: 28.08.13
 * Time: 14:43
 * To change this template use File | Settings | File Templates.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class HelloServiceTest {

    private static ClassPathXmlApplicationContext context = null;
    @Autowired
    private HelloService helloService;
    private HelloDao helloDao;

    @BeforeClass
    public static void initContext() {
        context =  new ClassPathXmlApplicationContext("spring-config.xml");
    }

    @Test
    public void testHelloServiceWithAutowired() {
        String expectedEng = "Hello world!!!";
        assertEquals(expectedEng, helloService.getHelloWorld());
    }

    @Test
    public void testHelloServiceWithEngDAOFromXML() {
        String expectedEng = "Hello world!!!";
        helloDao = (HelloDao) context.getBean("helloEng");
        helloService = new HelloServiceImpl(helloDao);
        assertEquals(expectedEng, helloService.getHelloWorld());
    }

    @Test
    public void testHelloServiceWithRusDAOFromXML() {
        String expectedRus = "Привет мир!!!";
        helloDao = (HelloDao) context.getBean("helloRus");
        helloService = new HelloServiceImpl(helloDao);
        assertEquals(expectedRus, helloService.getHelloWorld());
    }

    @Test
    public void testHelloServiceWithUkrDAOFromXML() {
        String expectedUkr = "Привіт світ!!!";
        helloDao = (HelloDao) context.getBean("helloUkr");
        helloService = new HelloServiceImpl(helloDao);
        assertEquals(expectedUkr, helloService.getHelloWorld());
    }

    @AfterClass
    public static void cleanUpAllParams(){
        context = null;
    }
}
