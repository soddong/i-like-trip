package com.ssafy.image.model.service;

import com.ssafy.image.model.ImageInfoDto;
import com.ssafy.image.model.TempImageInfoDto;
import com.ssafy.image.model.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageMapper imageMapper;

    @Autowired
    public ImageServiceImpl(ImageMapper imageMapper) {
        super();
        this.imageMapper = imageMapper;
    }

    @Override
    @Transactional
    public int saveImage(ImageInfoDto searchImage) throws Exception {
        ImageInfoDto imageInfo = imageMapper.fetchImageInfo(searchImage.getSaveFile());
        imageInfo.setRefNo(searchImage.getRefNo());
        imageInfo.setSaveFile(searchImage.getSaveFile());
        imageInfo.setType(searchImage.getType());
        imageMapper.saveImage(imageInfo);
        return imageInfo.getFileNo();
    }

    @Override
    public ImageInfoDto getImageBySaveFile(String saveFile) throws Exception {
        return imageMapper.getImageBySaveFile(saveFile);
    }

    @Override
    @Transactional
    public void saveImageAtTempStore(TempImageInfoDto tmpImageInfo) throws Exception {
        imageMapper.saveImageAtTempStore(tmpImageInfo);
    }

    @Override
    public List<String> extractImageLinks(String content) {
        List<String> names = new ArrayList<>();
        Pattern linkPattern = Pattern.compile("!\\[.*?\\]\\((.*?)\\)");
        Matcher linkMatcher = linkPattern.matcher(content);

        while (linkMatcher.find()) {
            String imageUrl = linkMatcher.group(1);
            Pattern namePattern = Pattern.compile("name=([^&]*)");
            Matcher nameMatcher = namePattern.matcher(imageUrl);

            if (nameMatcher.find()) {
                names.add(nameMatcher.group(1));
            }
        }

        return names;
    }

    @Override
    public String extractImageName(String url) {
        if (url != null && url.contains("name=")) {
            String[] parts = url.split("name=");
            if (parts.length > 1) {
                return parts[1];
            }
        }
        return null;
    }

    @Override
    public ImageInfoDto getImageByArticleno(int articleno) {
        return imageMapper.getImageByArticleno(articleno);
    }
}
