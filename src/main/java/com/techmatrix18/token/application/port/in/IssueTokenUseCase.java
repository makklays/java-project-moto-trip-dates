package com.techmatrix18.token.application.port.in;

import com.techmatrix18.token.application.command.IssueTokenCommand;
import com.techmatrix18.token.domain.model.Token;

/**
 * Inbound port for issuing a new authentication token pair.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public interface IssueTokenUseCase {
    Token execute(IssueTokenCommand command);
}

