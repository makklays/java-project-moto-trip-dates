package com.techmatrix18.user.application.port.in;

import com.techmatrix18.user.application.query.CheckUserIdentityAvailabilityQuery;

/**
 * Use case for real-time validation of email/username uniqueness during registration.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

public interface CheckUserIdentityAvailabilityUseCase {

    // Внутренний рекорд для ответа
    record AvailabilityResult(boolean emailAvailable, boolean usernameAvailable) {}

    AvailabilityResult execute(CheckUserIdentityAvailabilityQuery query);
}

