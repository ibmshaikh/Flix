package com.ibm.flix;

import java.io.Serializable;

public class modelEpisode implements Serializable {

    private String name,img,Sname;

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public modelEpisode() {
    }
}
