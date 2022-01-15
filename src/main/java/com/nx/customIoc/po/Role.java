package com.nx.customIoc.po;

/**
 * @author: wep
 * @Since: 2021/5/10 20:24
 */
public class Role {

    private String name;

    private User user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", user=" + user +
                '}';
    }
}
