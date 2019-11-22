package com.ibm.contentupload;

import java.io.Serializable;

    public class movie implements Serializable {

        public movie() {
        }


        private String Name, Language, Description, Image, Link, Genre;
        private int Year;
        private boolean isHollywood;

        public boolean isHollywood() {
            return isHollywood;
        }

        public void setHollywood(boolean hollywood) {
            isHollywood = hollywood;
        }


        public float getRating() {
            return Rating;
        }

        public void setRating(float rating) {
            Rating = rating;
        }

        private float Rating;

        public String getGenre() {
            return Genre;
        }

        public void setGenre(String genre) {
            Genre = genre;
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


    }

