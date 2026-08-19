package com.techmatrix18.user.infrastructure.mapper;

import com.techmatrix18.user.domain.model.User;
import com.techmatrix18.user.infrastructure.db.UserEntity;
import com.techmatrix18.user.domain.model.DatingStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * UserMapper (MapStruct)
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Из Entity БД в чистую доменную модель User
    @Mapping(target = "datingStatus", source = "datingStatus", qualifiedByName = "stringToEnum")
    User toDomain(UserEntity entity);

    // Из доменной модели User в Entity БД
    @Mapping(target = "datingStatus", source = "datingStatus", qualifiedByName = "enumToString")
    UserEntity toEntity(User domain);

    @Named("stringToEnum")
    default DatingStatus stringToEnum(String status) {
        if (status == null) {
            return DatingStatus.DRIVER; // значение по умолчанию
        }
        try {
            return DatingStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            return DatingStatus.DRIVER; // дефолт на случай некорректных данных в БД
        }
    }

    @Named("enumToString")
    default String enumToString(DatingStatus status) {
        return status != null ? status.name() : DatingStatus.DRIVER.name();
    }
}

