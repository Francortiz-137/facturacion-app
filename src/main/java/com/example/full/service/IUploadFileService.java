package com.example.full.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

public interface IUploadFileService {

    public Resource upload(String imgName) throws MalformedURLException;

    public String save(MultipartFile file) throws IOException;

    public boolean delete(String imgName);

    public Path getPath(String imgName);
}
