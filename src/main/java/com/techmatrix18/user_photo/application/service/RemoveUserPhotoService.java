package com.techmatrix18.user_photo.application.service;

import com.techmatrix18.user.domain.exception.DomainException;
import com.techmatrix18.user_photo.application.command.RemoveUserPhotoCommand;
import com.techmatrix18.user_photo.application.port.in.RemoveUserPhotoUseCase;
import com.techmatrix18.user_photo.application.port.out.UserPhotoRepository;
import com.techmatrix18.user_photo.domain.model.UserPhoto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service orchestrator handling photo removal with ownership validation.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

@Service
public class RemoveUserPhotoService implements RemoveUserPhotoUseCase {

    private final UserPhotoRepository userPhotoRepository;

    public RemoveUserPhotoService(UserPhotoRepository userPhotoRepository) {
        this.userPhotoRepository = userPhotoRepository;
    }

    @Override
    @Transactional
    public void execute(RemoveUserPhotoCommand command) {
        // 1. Извлекаем фотографию
        UserPhoto userPhoto = userPhotoRepository.findById(command.id())
                .orElseThrow(() -> new DomainException("Photo with ID " + command.id() + " not found"));

        // 2. Проверяем инвариант безопасности: удалять может только владелец
        if (!userPhoto.getUserId().equals(command.userId())) {
            throw new DomainException("Access denied: You do not have permission to delete this photo");
        }

        // 3. Удаляем через выходной порт
        userPhotoRepository.delete(command.id());
    }
}

