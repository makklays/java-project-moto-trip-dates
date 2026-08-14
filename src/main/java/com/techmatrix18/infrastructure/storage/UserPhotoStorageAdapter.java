package com.techmatrix18.infrastructure.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.UUID;

/**
 * UserPhotoStorageAdapter - Infrastructure persistence adapter managing
 * structural binary photo uploads to cloud object storage.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 15.08.2026
 */

@Component
public class UserPhotoStorageAdapter {

    private final S3Client s3Client;

    @Value("${app.s3.bucket-name}")
    private String bucketName;

    @Value("${app.s3.public-url-prefix}")
    private String publicUrlPrefix;

    public UserPhotoStorageAdapter(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    /**
     * Загружает байты фотографии в S3/MinIO и возвращает публичный URL файла.
     */
    public String uploadPhoto(Long userId, byte[] photoBytes, String contentType) {
        // Генерируем уникальное имя файла в папке пользователя: например, "users/12/photo-abc-123.jpg"
        String fileKey = String.format("users/%d/photo-%s.jpg", userId, UUID.randomUUID());

        var putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileKey)
                .contentType(contentType)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(photoBytes));

        // Возвращаем полную ссылку на картинку для сохранения в вашу таблицу user_photos
        return publicUrlPrefix + "/" + bucketName + "/" + fileKey;
    }
}

