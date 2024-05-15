package com.ssafy.image.controller;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.image.model.ImageInfoDto;
import com.ssafy.image.model.ImageType;
import com.ssafy.image.model.ImageUploadResultDto;
import com.ssafy.image.model.TempImageInfoDto;
import com.ssafy.image.model.service.ImageService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/upload")
@Slf4j
public class ImageController {

    private final String PATH = "upload/";
    private final String IMAGE_SERVER = "http://localhost:8080/";

    private ImageService imageService;


    public ImageController(ImageService imageService) {
        super();
        this.imageService = imageService;
    }

    @PostMapping
    public ImageUploadResultDto uploadImage(@RequestParam("image") MultipartFile file) throws IOException {

        log.info("uploadImage- {}", file.getOriginalFilename());
        String uuid = UUID.randomUUID().toString();

        int extractorIndex = file.getOriginalFilename().lastIndexOf(".");
        String type = file.getOriginalFilename().substring(extractorIndex);
        String originFileName = file.getOriginalFilename();
        String saveFileName = uuid + type;

        File folder = new File(PATH);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        FileCopyUtils.copy(file.getBytes(), new File(PATH + saveFileName));

        String accessUrl = IMAGE_SERVER + "upload?name=" + saveFileName;
        log.info("accessUrl- {}", accessUrl);
        ImageUploadResultDto ImageUploadResultDto = new ImageUploadResultDto();
        ImageUploadResultDto.setStatus(200);
        ImageUploadResultDto.setImageAccessUrl(accessUrl);

        TempImageInfoDto tmpImageInfo = new TempImageInfoDto(PATH, originFileName, saveFileName);
        try {
            imageService.saveImageAtTempStore(tmpImageInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ImageUploadResultDto;
    }

    @GetMapping
    public ResponseEntity<Resource> downloadImage(HttpServletRequest request) {

        log.info("downloadImage- {}", request.getParameter("name"));
        String imageName = request.getParameter("name");
        Resource resource = new FileSystemResource(PATH + imageName);

        HttpHeaders header = new HttpHeaders();
        try {
            Path filePath = Paths.get(PATH + imageName);
            header.add("Content-Type", Files.probeContentType(filePath));
            log.info("accessUrl- {}", filePath.getFileName());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }

    @GetMapping("/{articleno}")
    public ResponseEntity<Resource> downloadMainImageByArticleno(HttpServletRequest request,
                                                                 @PathVariable("articleno") @Parameter(name = "articleno", required = true) int articleno) {

        ImageInfoDto imageInfoDto = imageService.getImageByArticleno(articleno);
        if (imageInfoDto != null) {
            Resource resource = new FileSystemResource(imageInfoDto.getSaveFolder() + imageInfoDto.getSaveFile());
            HttpHeaders header = new HttpHeaders();
            try {
                Path filePath = Paths.get(imageInfoDto.getSaveFolder() + imageInfoDto.getSaveFile());
                header.add("Content-Type", Files.probeContentType(filePath));
                log.info("accessUrl- {}", filePath.getFileName());
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return new ResponseEntity<Resource>(resource, header, HttpStatus.CREATED);
        }

        return new ResponseEntity<Resource>(HttpStatus.NO_CONTENT);
    }

}
