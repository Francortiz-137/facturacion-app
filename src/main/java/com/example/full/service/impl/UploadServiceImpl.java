package com.example.full.service.impl;

import com.example.full.service.IUploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadServiceImpl implements IUploadFileService {

    private final static String UPLOAD_PATH = "uploads";
    private final Logger log = LoggerFactory.getLogger(UploadServiceImpl.class);

    @Override
    public Resource upload(String imgName) throws MalformedURLException {


        Path filePath = getPath(imgName);
        Resource resource = new UrlResource(filePath.toUri());
        log.info(filePath.toString());


        if (!resource.exists() && !resource.isReadable()){
            filePath = Paths.get("src/resources/static/img/").resolve("user.png").toAbsolutePath();

            resource = new UrlResource(filePath.toUri());
            log.error("Error: no se pudo cargar la imagen");
        }
        return resource;
    }

    @Override
    public String save(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ","");
        Path filePath = getPath(fileName);
        log.info(filePath.toString());

        Files.copy(file.getInputStream(), filePath);

        return fileName;
    }

    @Override
    public boolean delete(String imgName) {
        if (imgName != null && imgName.length()>0){
            Path oldImgPath = Paths.get("uploads").resolve(imgName).toAbsolutePath();
            File oldImgFile = oldImgPath.toFile();
            if (oldImgFile.exists() && oldImgFile.canRead()){
                oldImgFile.delete();
                return true;
            }
        }
        return false;
    }

    @Override
    public Path getPath(String imgName) {
        return Paths.get(UPLOAD_PATH).resolve(imgName).toAbsolutePath();
    }
}
