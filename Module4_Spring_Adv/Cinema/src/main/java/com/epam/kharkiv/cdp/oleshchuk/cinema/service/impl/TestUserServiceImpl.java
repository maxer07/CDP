package com.epam.kharkiv.cdp.oleshchuk.cinema.service.impl;

import com.epam.kharkiv.cdp.oleshchuk.cinema.dao.jdbc.impl.TestUserJdbcMySqlDao;
import com.epam.kharkiv.cdp.oleshchuk.cinema.service.TestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class TestUserServiceImpl implements TestUserService {

    @Autowired
    private TestUserJdbcMySqlDao userDao;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void insertUserReadOnlyPropagationSupports(String userName) {
        userDao.insertUser(userName);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public void insertUserReadOnlyPropagationRequired(String userName) {
        userDao.insertUser(userName);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void cleanTable() {
        userDao.cleanAllTable();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void insertUserPropagationRequiresNew(String userName) throws Exception{
        userDao.insertUser(userName);
        insertUserPropagationRequiresNewInner("innerUserName");
        throw new Exception("Something going wrong");
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void insertUserPropagationRequiresNewInner(String userName) throws Exception{
        userDao.insertUser(userName);
    }

    public int getAllCount() {
       return userDao.getAllCount();
    }

    @Transactional(readOnly = false, propagation=Propagation.REQUIRED)
    public void testRequired(String userName) throws Exception {
        userDao.insertUser(userName);
        requiresNewThrowsRuntimeException();
    }

    @Transactional(readOnly = false, propagation=Propagation.REQUIRES_NEW)
    public void requiresNewThrowsRuntimeException() {
        throw new RuntimeException("Rollback this transaction!");
    }



}
