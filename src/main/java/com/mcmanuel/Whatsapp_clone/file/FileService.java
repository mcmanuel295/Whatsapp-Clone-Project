package com.mcmanuel.Whatsapp_clone.file;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class FileService {

    @Value("${Application.file.uploads.media-output.path}")
    private String fileUploaded;

    public String saveFile(@NonNull MultipartFile sourceFile, @NonNull  String senderId) {

        final String  fileUploadSubPath = "user"+ File.pathSeparator+senderId;
        return uploadFile(sourceFile,fileUploadSubPath);
    }

    private String uploadFile(@NonNull MultipartFile sourceFile, @NonNull String fileUploadSubPath) {
        final String uploadPath = fileUploadSubPath+File.pathSeparator+fileUploadSubPath;
        return "";
    }
}
