package com.example.alex.rasenshuriken;

public class Upload {
    private String fileName;
    private String LinkUrl;
    private String lessonId;
    private String pageId;

    public Upload() {

    }

    public Upload(String name, String LinkUrl, String lessonId, String pageId) {
        if (name.trim().equals("")) {
            name = "No Name";
        }
       this.fileName = name;
        this.LinkUrl = LinkUrl;
        this.lessonId=lessonId;
        this.pageId=pageId;
    }

    public String getName() {
        return fileName;
    }

    public void setName(String name) {
       this.fileName = name;
    }

    public String getLinkUrl() {
        return LinkUrl;
    }

    public void setLinkUrl(String LinkUrl) {
        this.LinkUrl = LinkUrl;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

}