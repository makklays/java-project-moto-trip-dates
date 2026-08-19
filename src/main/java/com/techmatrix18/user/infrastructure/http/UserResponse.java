package com.techmatrix18.user.infrastructure.http;

import com.techmatrix18.user.domain.model.DatingStatus;
import com.techmatrix18.user.domain.model.User;
import java.time.Instant;

/**
 * UserResponse
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

public record UserResponse(
        Long id,
        String username,
        String email,
        String baseRole,
        String mobile,
        String nickname,
        String gender,
        String avatarUrl,
        String bio,
        DatingStatus datingStatus,
        Instant createdAt
) {
    public static UserResponse fromDomain(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getBaseRole(),
                user.getMobile(),
                user.getNickname(),
                user.getGender(),
                user.getAvatarUrl(),
                user.getBio(),
                user.getDatingStatus() != null ? DatingStatus.valueOf(user.getDatingStatus()) : DatingStatus.DRIVER,
                user.getCreatedAt()
        );
    }
}

