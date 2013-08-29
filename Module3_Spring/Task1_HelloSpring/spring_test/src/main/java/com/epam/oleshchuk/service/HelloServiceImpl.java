package com.epam.oleshchuk.service;

import com.epam.oleshchuk.dao.HelloDao;

/**
 * Created with IntelliJ IDEA.
 * User: Maksym_Oleshchuk
 * Date: 28.08.13
 * Time: 12:58
 * To change this template use File | Settings | File Templates.
 */
public class HelloServiceImpl implements HelloService{


    private HelloDao helloDao;

    public HelloServiceImpl(HelloDao helloDao) {
        this.helloDao = helloDao;
    }

    @Override
    public String getHelloWorld() {
       return helloDao.createHelloWorld();
    }
}
