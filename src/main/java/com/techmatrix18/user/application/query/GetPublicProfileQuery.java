package com.techmatrix18.user.application.query;

/**
 * GetPublicProfileQuery
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

public record GetPublicProfileQuery(
    Long targetUserId,       // чей профиль смотрим
    Long requestedByUserId   // кто смотрит (для проверки прав/блокировок)
) {}

