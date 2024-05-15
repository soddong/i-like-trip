package com.ssafy.image.model;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ImageInfoDto {
    private int fileNo;
    private int refNo;
    private String saveFolder;
    private String originalFile;
    private String saveFile;
    private int type;

    public ImageInfoDto() {
    }

    public ImageInfoDto(int articleNo, String path, String originFileName, String saveFileName, int imageType) {
        refNo = articleNo;
        saveFolder = path;
        originalFile = originFileName;
        saveFile = saveFileName;
        type = imageType;
    }
}
