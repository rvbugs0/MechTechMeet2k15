package com.mech.tech.meet.event.info;

/**
 * Created by bugs-pc on 2/21/2015.
 */


public class NotificationStructure {
    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getHeaderImageResource() {
        return headerImageResource;
    }

    public void setHeaderImageResource(String headerImageResource) {
        this.headerImageResource = headerImageResource;
    }

    private  String conclusion;
    private String headerImageResource;

    public int getNotificationCode() {
        return notificationCode;
    }

    public void setNotificationCode(int notificationCode) {
        this.notificationCode = notificationCode;
    }

    private int notificationCode;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String title;
    private String introduction;
    private String imageResource;
    private String description;


}
