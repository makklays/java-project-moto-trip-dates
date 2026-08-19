package com.techmatrix18.token.application.query;

/**
 * GetPasswordResetTokenQuery
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

public record GetPasswordResetTokenQuery(
    String passwordResetToken
) {}

