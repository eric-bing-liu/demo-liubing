package com.sinosoft.codegenerator.model;

import java.util.Date;

public class SUser {
    private String id;

    private String userName;

    private Date tdate;

    private Date ttimestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Date getTdate() {
        return tdate;
    }

    public void setTdate(Date tdate) {
        this.tdate = tdate;
    }

    public Date getTtimestamp() {
        return ttimestamp;
    }

    public void setTtimestamp(Date ttimestamp) {
        this.ttimestamp = ttimestamp;
    }
}