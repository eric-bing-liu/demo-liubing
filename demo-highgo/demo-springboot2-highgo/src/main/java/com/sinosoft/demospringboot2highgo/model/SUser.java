package com.sinosoft.demospringboot2highgo.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class SUser implements Serializable {

    private String id;

    private String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "SUser{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
