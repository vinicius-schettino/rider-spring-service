package com.rider.user.entities;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;
    @Column(nullable = false)
    private Languages languages;
    @Column(nullable = false)
    private AppAppearance appappearance;
    @Column(nullable = false)
    private Notification notification;
    @Column(nullable = false)
    private Ads ads;

    @OneToOne
    private Profile profile;

    public UUID getId() {
        return id;
    }

    public Languages getLanguages() {
        return languages;
    }

    public AppAppearance getAppAppearance() {
        return appappearance;
    }

    public Notification getNotification() {
        return notification;
    }

    public Ads getAds() {
        return ads;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setLanguage(Languages languages) {
        this.languages = languages;
    }

    public void setAppAppearance(AppAppearance appappearance) {
        this.appappearance = appappearance;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public void setAds(Ads ads) {
        this.ads = ads;
    }

    public enum Languages {
        Portuguese, English, Chinese, Spanish
    }

    public enum AppAppearance {
        Dark, Light
    }

    public enum Notification {
        ON, OFF
    }

    public enum Ads {
        ON, OFF
    }

}