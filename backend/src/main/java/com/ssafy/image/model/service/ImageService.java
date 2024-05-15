package com.ssafy.image.model.service;

import com.ssafy.image.model.ImageInfoDto;
import com.ssafy.image.model.TempImageInfoDto;

import java.util.List;

public interface ImageService {
    void saveImage(ImageInfoDto imageInfo) throws Exception;
    void saveImage(String saveFile) throws Exception;
    ImageInfoDto getImageBySaveFile(String saveFile) throws Exception;
    void saveImageAtTempStore(TempImageInfoDto tmpImageInfo) throws Exception;

    List<String> extractImageLinks(String content);
    ImageInfoDto getImageByArticleno(int articleno);
}
