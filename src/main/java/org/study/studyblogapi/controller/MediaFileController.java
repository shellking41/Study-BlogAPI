package org.study.studyblogapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.study.studyblogapi.model.dto.response.UserIconResponse;
import org.study.studyblogapi.service.IMediaFileService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor

@RequestMapping("api/v1/media")
public class MediaFileController {

    private final IMediaFileService mediaFileService;

    @PutMapping(path = "/upload/userIcon",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<UserIconResponse> UploadUserIcon(@RequestParam MultipartFile multipartFile) throws IOException {
        return mediaFileService.UploadUserIcon(multipartFile);
    }
}
