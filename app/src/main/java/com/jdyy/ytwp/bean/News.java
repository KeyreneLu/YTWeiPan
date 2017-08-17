package com.jdyy.ytwp.bean;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public class News {
    private String imageUrl;
    private String title;
    private String content;
    private String date;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public News(String imageUrl, String title, String content, String date) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.content = content;
        this.date = date;
    }
}
