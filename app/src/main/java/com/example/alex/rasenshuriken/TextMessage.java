package com.example.alex.rasenshuriken;

public class TextMessage {
    private String TextMessageID;
    private String Textmessage;
    private String domain;
    private String subdomain;
    private String title;
    private String username;
    private String lessonID;
    private String pageId;

    public TextMessage(String textMessageID, String textmessage, String domain, String subdomain, String title, String username, String lessonID, String pageId) {
        TextMessageID = textMessageID;
        Textmessage = textmessage;
        this.domain = domain;
        this.subdomain = subdomain;
        this.title = title;
        this.username = username;
        this.lessonID = lessonID;
        this.pageId = pageId;
    }

    public TextMessage() {
    }

    public String getTextMessageID() {
        return TextMessageID;
    }

    public void setTextMessageID(String textMessageID) {
        this.TextMessageID = textMessageID;
    }

    public String getTextmessage() {
        return Textmessage;
    }

    public void setTextmessage(String textmessage) {
        this.Textmessage = textmessage;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLessonID() {
        return lessonID;
    }

    public void setLessonID(String lessonID) {
        this.lessonID = lessonID;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
}