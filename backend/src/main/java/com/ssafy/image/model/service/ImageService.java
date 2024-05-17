package com.ssafy.image.model.service;

import com.ssafy.image.model.ImageInfoDto;
import com.ssafy.image.model.TempImageInfoDto;

import java.util.List;

public interface ImageService {
    int saveImage(ImageInfoDto imageInfo) throws Exception;
    ImageInfoDto getImageBySaveFile(String saveFile) throws Exception;
    void saveImageAtTempStore(TempImageInfoDto tmpImageInfo) throws Exception;

    List<String> extractImageLinks(String content);
    String extractImageName(String url);
    ImageInfoDto getImageByArticleno(int articleno);

}
