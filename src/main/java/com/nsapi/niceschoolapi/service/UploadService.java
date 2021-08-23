package com.nsapi.niceschoolapi.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface UploadService {

    String upload(MultipartFile file) throws IOException, NoSuchAlgorithmException;
}
