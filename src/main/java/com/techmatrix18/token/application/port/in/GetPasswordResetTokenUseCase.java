package com.techmatrix18.token.application.port.in;

import com.techmatrix18.token.application.query.GetPasswordResetTokenQuery;
import com.techmatrix18.token.domain.model.Token;

import java.util.Optional;

/**
 * GetPasswordResetTokenUseCase
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

public interface GetPasswordResetTokenUseCase {
    Optional<Token> execute(GetPasswordResetTokenQuery query);
}

