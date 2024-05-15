package com.ssafy.image.model;

public enum ImageType {
    UNDEFINED,
    BOARD_IMAGE_TYPE,
    PROFILE_IMAGE_TYPE;

    public int toInt() {
        switch (this) {
            case UNDEFINED:
                return 0;
            case BOARD_IMAGE_TYPE:
                return 1;
            case PROFILE_IMAGE_TYPE:
                return 2;
            default:
                throw new IllegalArgumentException("Unknown ImageType: " + this);
        }
    }
}