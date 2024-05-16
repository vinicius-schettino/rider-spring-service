package com.rider.user.entities;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Language language;
    private AppAppearance appAppearance;
    private Notification notification;
    private Ads ads;

    public UUID getId() {
        return id;
    }

    public Language getLanguage() {
        return language;
    }

    public AppAppearance getAppAppearance() {
        return appAppearance;
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

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setAppAppearance(AppAppearance appAppearance) {
        this.appAppearance = appAppearance;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public void setAds(Ads ads) {
        this.ads = ads;
    }

    public enum Language {
        PORTUGUESE, ENGLISH, CHINESE, SPANISH
    }

    public enum AppAppearance {
        DARK, LIGHT
    }

    public enum Notification {
        ON, OFF
    }

    public enum Ads {
        ON, OFF
    }

}