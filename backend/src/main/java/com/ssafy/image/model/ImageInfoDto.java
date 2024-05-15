package com.ssafy.image.model;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ImageSearchDto {
    private int fileNo;
    private int refNo;
    private String saveFolder;
    private String originalFile;
    private String saveFile;
    private ImageType type;
}
