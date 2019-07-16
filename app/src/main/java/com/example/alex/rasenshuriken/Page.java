package com.example.alex.rasenshuriken;

public class Page {

    private String LessonId;
    private  String TextMessage;
    private String LinkURL;//scos
    private String pageNumber;
    private String pageID;



    public Page(String lessonId, String textMessage, String linkURL, String pageNumber, String pageID) {
        this.LessonId = lessonId;
        this.TextMessage = textMessage;
        this.LinkURL = linkURL;
        this.pageNumber = pageNumber;
        this.pageID = pageID;
    }

    public Page() {
    }

    public String getLessonId() {
        return LessonId;
    }

    public void setLessonId(String lessonId) {
        this.LessonId = lessonId;
    }

    public String getTextMessage() {
        return TextMessage;
    }

    public void setTextMessage(String textMessage) {
       this. TextMessage = textMessage;
    }

    public String getLinkURL() {
        return LinkURL;
    }

    public void setLinkURL(String linkURL) {
        this.LinkURL = linkURL;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageID() {
        return pageID;
    }

    public void setPageID(String pageID) {
        this.pageID = pageID;
    }
}
