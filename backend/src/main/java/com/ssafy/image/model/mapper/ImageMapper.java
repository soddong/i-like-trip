package com.ssafy.image.model.mapper;

import com.ssafy.image.model.ImageInfoDto;
import com.ssafy.image.model.TempImageInfoDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {
    int saveImage(ImageInfoDto imageInfo);
    ImageInfoDto getImageBySaveFile(String saveFile);

    void saveImageAtTempStore(TempImageInfoDto tempImageInfo);
    ImageInfoDto fetchImageInfo(String saveFile);

    ImageInfoDto getImageByArticleno(int articleno);
}
