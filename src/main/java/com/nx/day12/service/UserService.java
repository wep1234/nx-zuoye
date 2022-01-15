package com.nx.day12.service;

import com.nx.day12.RollbackException;

/**
 * @author: wep
 * @Since: 2021/5/15 15:04
 */
public interface UserService {

    public void getUser();

    public void saveUser() throws RollbackException;

    public void saveUser2();

    public void createSchema();

}
