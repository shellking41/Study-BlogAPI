package org.study.studyblogapi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.study.studyblogapi.model.dto.response.UserIconResponse;
import org.study.studyblogapi.model.enums.UsageType;

import java.io.IOException;
import java.util.Map;

public interface IMediaFileService {
   ResponseEntity<UserIconResponse> UploadUserIcon(MultipartFile multipartFile) throws IOException;
   Map<String,Object> UploadBlogPostMedia(MultipartFile multipartFile, UsageType usageType);
}
