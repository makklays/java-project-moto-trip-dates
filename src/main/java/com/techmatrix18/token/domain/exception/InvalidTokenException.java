package com.techmatrix18.token.domain.exception;

import com.techmatrix18.user.domain.exception.DomainException;

/**
 * InvalidTokenException
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 14.08.2026
 */

public class InvalidTokenException extends DomainException {
    public InvalidTokenException(String message) {
        super(message);
    }
}

