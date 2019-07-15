package com.example.alex.rasenshuriken;

public class TextMessage {
    private   String TextMessageID;
    private  String Textmessage;
    private  String domain;
    private  String subdomain;
    private  String title;
    private  String username;
    private String lessonID;
    private  int page;

    public TextMessage(String textMessageID,String lessonID, String textMessage , String domain, String subdomain, String title, String username, int page) {
        this.TextMessageID = textMessageID;
        this.lessonID=lessonID;
        this.Textmessage = textMessage ;
        this.domain = domain;
        this.subdomain = subdomain;
        this.title = title;
        this.username = username;
        this.page=page;
    }


    public TextMessage(){}

    public String getTextMessageID() {
        return TextMessageID;
    }

    public void setTextMessageID(String textMessageID1) {
        TextMessageID = textMessageID1;
    }

    public String getText() {
        return Textmessage;
    }

    public void setText(String textMessage ) {
        Textmessage = textMessage ;
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

    public String getLessonID() {
        return lessonID;
    }

    public void setLessonID(String lessonID) {
        this.lessonID = lessonID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
