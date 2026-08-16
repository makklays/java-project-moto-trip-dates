package com.techmatrix18.user_photo.application.service;

import com.techmatrix18.user_photo.application.command.UploadUserPhotoCommand;
import com.techmatrix18.user_photo.application.port.in.UploadUserPhotoUseCase;
import com.techmatrix18.user_photo.application.port.out.UserPhotoRepository;
import com.techmatrix18.user_photo.domain.model.UserPhoto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service orchestrator that handles registering a newly uploaded photo in user's profile.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

@Service
public class UploadUserPhotoService implements UploadUserPhotoUseCase {

    private final UserPhotoRepository userPhotoRepository;

    public UploadUserPhotoService(UserPhotoRepository userPhotoRepository) {
        this.userPhotoRepository = userPhotoRepository;
    }

    @Override
    @Transactional
    public UserPhoto execute(UploadUserPhotoCommand command) {
        // Сборка доменного агрегата через канонический Builder
        UserPhoto userPhoto = UserPhoto.builder()
                .userId(command.userId())
                .photoUrl(command.photoUrl())
                .displayOrder(command.displayOrder())
                .build();

        return userPhotoRepository.save(userPhoto);
    }
}

