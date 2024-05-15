package com.ssafy.image.model.mapper;

import com.ssafy.image.model.ImageInfoDto;
import com.ssafy.image.model.TempImageInfoDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {
    void saveImage(ImageInfoDto imageInfo);
    void saveImage(String saveFile);
    ImageInfoDto getImageBySaveFile(String saveFile);

    void saveImageAtTempStore(TempImageInfoDto tempImageInfo);
    ImageInfoDto fetchImageInfo(String saveFile);

    ImageInfoDto getImageByArticleno(int articleno);
}
