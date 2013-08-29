package com.epam.oleshchuk.dao;

/**
 * Created with IntelliJ IDEA.
 * User: Maksym_Oleshchuk
 * Date: 28.08.13
 * Time: 13:06
 * To change this template use File | Settings | File Templates.
 */
public class HelloDAOImplRus implements HelloDao {

    @Override
    public String createHelloWorld() {
        return "Привет мир!!!";
    }
}
