package com.example.alex.rasenshuriken;

import java.util.List;

public class Lesson {

    private   String lessonId;
    private   String domain;
    private   String subdomain;
    private  String title;
    private   String username;
    private  List<Page> pages;
    private  String pageNumbers;

    public Lesson(String lessonId, String domain, String subdomain, String title, String username, List<Page> pages, String pageNumbers) {
        this.lessonId = lessonId;
        this.domain = domain;
        this.subdomain = subdomain;
        this.title = title;
        this.username = username;
        this.pages=pages;
        this.pageNumbers=pageNumbers;
    }

    public Lesson(){
    }

    public String getPageNumbers() {
        return pageNumbers;
    }

    public void setPageNumbers(String pageNumbers) {
        this.pageNumbers = pageNumbers;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
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
