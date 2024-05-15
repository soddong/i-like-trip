package com.ssafy.image.model;

public class ImageUploadResultDto {
    private int status;
    private String imageAccessUrl;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImageAccessUrl() {
        return imageAccessUrl;
    }

    public void setImageAccessUrl(String imageAccessUrl) {
        this.imageAccessUrl = imageAccessUrl;
    }
}