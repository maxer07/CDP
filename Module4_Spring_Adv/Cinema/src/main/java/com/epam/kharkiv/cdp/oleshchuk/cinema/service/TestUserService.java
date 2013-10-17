package com.epam.kharkiv.cdp.oleshchuk.cinema.service;


public interface TestUserService {

    public void insertUserReadOnlyPropagationSupports(String userName);

    public void insertUserReadOnlyPropagationRequired(String userName);

    public void cleanTable();

    public void insertUserPropagationRequiresNew(String userName) throws Exception;

    public int getAllCount();

    public void testRequired(String userName) throws Exception;

}
