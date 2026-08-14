package com.techmatrix18.domain.exception;

/**
 * UserNotFoundException
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 14.08.2026
 */

public class UserNotFoundException extends DomainException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long id) {
        super("User not found with id: " + id);
    }
}

