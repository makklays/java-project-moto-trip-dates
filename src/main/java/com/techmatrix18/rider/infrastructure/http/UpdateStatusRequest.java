package com.techmatrix18.rider.infrastructure.http;

import jakarta.validation.constraints.NotBlank;

/**
 * UpdateStatusRequest
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public record UpdateStatusRequest(
        @NotBlank(message = "Status cannot be empty") String status
) {}

