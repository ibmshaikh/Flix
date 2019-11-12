package com.ibm.flix;

import java.io.Serializable;

public class modelSeason implements Serializable {
    public modelSeason() {
    }

    private String name,link,sname;

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
