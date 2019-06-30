package com.ibm.flix;

import android.os.Parcelable;

import java.io.Serializable;

public class movie implements Serializable {


    private String Name,Language,Description,Image,Link,Genre;
    private int Rating,Year;

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public movie(String name, String language, String description, String image, String link, String genre, int rating, int year) {
        Name = name;
        Language = language;
        Description = description;
        Image = image;
        Link = link;
        Genre = genre;
        Rating = rating;
        Year = year;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }
}
