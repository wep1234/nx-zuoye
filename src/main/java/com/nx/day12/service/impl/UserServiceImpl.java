package com.nx.day12.service.impl;

import com.nx.day12.RollbackException;
import com.nx.day12.service.UserService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: wep
 * @Since: 2021/5/15 15:05
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void getUser() {
        System.out.println("total:"+jdbcTemplate.queryForObject("SELECT COUNT(*) FROM User", Long.class));
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveUser() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO User (name) VALUES ('DDD')");
        this.saveUser2();
        //((UserService)AopContext.currentProxy()).saveUser2();
        throw new RollbackException();
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void saveUser2() {
        jdbcTemplate.execute("INSERT INTO User (name) VALUES ('BBB')");
    }

    @Override
    public void createSchema(){
        jdbcTemplate.execute("CREATE TABLE User (ID INT IDENTITY, name VARCHAR(64));");
    }
}
