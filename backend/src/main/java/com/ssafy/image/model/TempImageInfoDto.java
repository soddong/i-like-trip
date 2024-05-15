package com.ssafy.image.model;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TempImageInfoDto {
    private int fileNo;
    private String saveFolder;
    private String originalFile;
    private String saveFile;

    public TempImageInfoDto(String path, String originFileName, String saveFileName) {
        saveFolder = path;
        originalFile = originFileName;
        saveFile = saveFileName;
    }
}
