package com.example.alex.rasenshuriken;

public class TextMessage {
    String TextMessageID;
    String Textmessage;
    String domain;
    String subdomain;
    String title;
    String username;
    int page;

    public TextMessage(String textMessageID, String Textmessage, String domain, String subdomain, String title, String username, int page) {
        this.TextMessageID = textMessageID;
        this.Textmessage = Textmessage;
        this.domain = domain;
        this.subdomain = subdomain;
        this.title = title;
        this.username = username;
        this.page=page;
    }

    public String getTextMessageID() {
        return TextMessageID;
    }

    public void setTextMessageID(String textMessageID1) {
        TextMessageID = textMessageID1;
    }

    public String getText() {
        return Textmessage;
    }

    public void setText(String text1) {
        Textmessage = text1;
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
