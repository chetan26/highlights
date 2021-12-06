package com.highlights.common.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("contents")
public class Content {
    @Id
    private String id;
    private String title;
    private String type;
    private String imgUrl;
    private String duration;
    private int highlights;
    private String code;
    private int version;
    private String launchUrl;


    public Content(String id, String title, String type, String imgUrl, String duration, int highlights,String code,int version,String launchUrl) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.imgUrl = imgUrl;
        this.duration = duration;
        this.highlights = highlights;
        this.code=code;
        this.version=version;
        this.launchUrl=launchUrl;
    }

    public Content(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getHighlights() {
        return highlights;
    }

    public void setHighlights(int highlights) {
        this.highlights = highlights;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getLaunchUrl() {
        return launchUrl;
    }

    public void setLaunchUrl(String launchUrl) {
        this.launchUrl = launchUrl;
    }
}
