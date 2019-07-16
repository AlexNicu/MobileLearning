package com.example.alex.rasenshuriken;

public class Upload {
    private String mName;
    private String LinkUrl;

    public Upload() {
        //empty constructor needed
    }

    public Upload(String name, String LinkUrl) {
        if (name.trim().equals("")) {
            name = "No Name";
        }
       this.mName = name;
        this.LinkUrl = LinkUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getLinkUrl() {
        return LinkUrl;
    }

    public void setLinkUrl(String LinkUrl) {
        this.LinkUrl = LinkUrl;
    }
}