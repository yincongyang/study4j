package com.yincongyang.spring.controller;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by yincongyang on 17/11/15.
 */
@XmlRootElement
public class House {
    private User user;
    private String location;

    public House() {
    }

    public House(User user, String location) {
        this.user = user;
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "House{" + "user=" + user + ", location='" + location + '\'' + '}';
    }
}
