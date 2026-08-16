package com.techmatrix18.user_photo.application.service;

import com.techmatrix18.user.domain.exception.DomainException;
import com.techmatrix18.user_photo.application.command.ChangePhotoOrderCommand;
import com.techmatrix18.user_photo.application.port.in.ChangePhotoOrderUseCase;
import com.techmatrix18.user_photo.application.port.out.UserPhotoRepository;
import com.techmatrix18.user_photo.domain.model.UserPhoto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service orchestrator that updates photo display priority with ownership validation.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

@Service
public class ChangePhotoOrderService implements ChangePhotoOrderUseCase {

    private final UserPhotoRepository userPhotoRepository;

    public ChangePhotoOrderService(UserPhotoRepository userPhotoRepository) {
        this.userPhotoRepository = userPhotoRepository;
    }

    @Override
    @Transactional
    public UserPhoto execute(ChangePhotoOrderCommand command) {
        // 1. Ищем фотографию в репозитории
        UserPhoto userPhoto = userPhotoRepository.findById(command.id())
                .orElseThrow(() -> new DomainException("Photo with ID " + command.id() + " not found"));

        // 2. Проверяем инвариант безопасности: изменять порядок может только владелец
        if (!userPhoto.getUserId().equals(command.userId())) {
            throw new DomainException("Access denied: You do not have permission to modify this photo");
        }

        // 3. Мутируем состояние Rich-модели через бизнес-метод поведения
        userPhoto.changeDisplayOrder(command.newOrder());

        return userPhotoRepository.save(userPhoto);
    }
}

