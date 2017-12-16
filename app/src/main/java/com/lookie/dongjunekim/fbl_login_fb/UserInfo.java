package com.lookie.dongjunekim.fbl_login_fb;

import java.io.Serializable;

/**
 * Created by dongjunekim on 2017-12-08.
 */

public class UserInfo implements Serializable {
    private String name;
    private String email;
    private String id;
    private String fName;
    private String rName;

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", id='" + id + '\'' +
                ", fName='" + fName + '\'' +
                ", rName='" + rName + '\'' +
                '}';
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
