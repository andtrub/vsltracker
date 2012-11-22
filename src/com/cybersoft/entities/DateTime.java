package com.cybersoft.entities;

import java.util.Date;

/**
 * @author andrey
 *         Date: 11/1/12
 *         Time: 3:20 PM
 */
public class DateTime {
    private Date date;

    public DateTime(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
