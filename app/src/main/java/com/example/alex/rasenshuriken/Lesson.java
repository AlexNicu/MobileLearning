package com.example.alex.rasenshuriken;

import java.util.List;

public class Lesson {

    String lessonId;
    String domain;
    String subdomain;
    String title;
    String username;


    public Lesson(String lessonId, String domain, String subdomain, String title, String username) {
        this.lessonId = lessonId;
        this.domain = domain;
        this.subdomain = subdomain;
        this.title = title;
        this.username = username;
    }

    public Lesson(){

    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSubdomain() {
        return subdomain;
    }

    public void setSubdomain(String subdomain) {
        this.subdomain = subdomain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userId) {
        this.username = userId;
    }
}
