package org.study.studyblogapi.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.study.studyblogapi.exception.MediaNotValidException;
import org.study.studyblogapi.model.dto.response.UserIconResponse;
import org.study.studyblogapi.model.entity.MediaFile;
import org.study.studyblogapi.model.entity.User;
import org.study.studyblogapi.model.enums.UsageType;
import org.study.studyblogapi.repository.MediaFileRepository;
import org.study.studyblogapi.repository.UserRepository;
import org.study.studyblogapi.service.IMediaFileService;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MediaFileService implements IMediaFileService {

    private final UserService userService;
    private final MediaFileRepository mediaFileRepository;
    private final UserRepository userRepository;

    //MAPPER HIÁNYZIK!!!!!
    //NULL AZ ID MIKOR CREATEDELJUK
    @Override
    @Transactional
    public ResponseEntity<UserIconResponse> UploadUserIcon(MultipartFile multipartFile) throws IOException {


        User user=userService.getAuthenticatedUser();

        if(multipartFile.isEmpty()){
            throw new MediaNotValidException("The file is Empty");
        }
        String contentType=multipartFile.getContentType();
        if(contentType==null || !contentType.startsWith("image")){
            throw new MediaNotValidException("User Icon has to be image");
        }

        Optional<MediaFile> existingIconOpt=mediaFileRepository.findByUserAndUsageType(user,UsageType.USER_ICON);
        Path rootPath= Paths.get("uploads");
        String fileName= UUID.randomUUID()+"_"+multipartFile.getOriginalFilename();
        MediaFile mediaFile;

        if(existingIconOpt.isPresent()){
            mediaFile = existingIconOpt.get();

            Path oldFilePath = rootPath.resolve(mediaFile.getPath());
            if(!mediaFile.getPath().equals("defaultUserIcon.jpg")){
                Files.deleteIfExists(oldFilePath);
            }


        // Update path
            mediaFile.setPath("user_icons/" + fileName);
        } else {
            // Create new entity
            mediaFile = MediaFile.builder()
                    .user(user)
                    .path("user_icons/" + fileName)
                    .usageType(UsageType.USER_ICON)
                    .build();
            user.setUserIcon(mediaFile);
        }

        Path subFolder=rootPath.resolve("user_icons");
        Files.createDirectories(subFolder);

        userRepository.save(user);

        Path filePath=subFolder.resolve(fileName);
        Files.write(filePath,multipartFile.getBytes());

        UserIconResponse response = UserIconResponse.builder()
                .id(mediaFile.getId())
                .userId(mediaFile.getUser().getId())
                .usageType(mediaFile.getUsageType())
                .path(mediaFile.getPath())
                .build();

        URI location = URI.create("user_icons/fileName");

        return ResponseEntity.created(location).body(response);

    }

    @Override
    public Map<String, Object> UploadBlogPostMedia(MultipartFile multipartFile, UsageType usageType) {
        return Map.of();
    }
}
