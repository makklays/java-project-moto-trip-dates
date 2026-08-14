package com.techmatrix18.domain.exception;

/**
 * DomainException
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 14.08.2026
 */

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}

