package com.cybersoft.entities;

/**
 * @author andrey
 *         Date: 11/1/12
 *         Time: 3:20 PM
 */
public class Match {
    private String data;

    public Match(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }
}
