package com.cybersoft.entities;

/**
 * @author andrey
 *         Date: 11/1/12
 *         Time: 3:21 PM
 */
public class Config {
    private String login;
    private String password;

    public Config(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
