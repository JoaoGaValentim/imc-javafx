package com.github.imccalculator.app.models;

public enum Icons {
    WARNING("https://static.vecteezy.com/system/resources/thumbnails/012/042/301/small/warning-sign-icon-transparent-background-free-png.png"),
    SUCCESS("https://cdn-icons-png.flaticon.com/512/7518/7518748.png"),
    ERROR("https://www.freeiconspng.com/thumbs/alert-icon/alert-icon-red-11.png");

    private String iconUrl;

    private Icons(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}
