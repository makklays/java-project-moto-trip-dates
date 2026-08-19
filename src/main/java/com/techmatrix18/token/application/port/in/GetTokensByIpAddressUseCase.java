package com.techmatrix18.token.application.port.in;

import com.techmatrix18.token.application.query.GetTokensByIpAddressQuery;
import com.techmatrix18.token.domain.model.Token;

import java.util.List;

/**
 * GetTokensByIpAddressUseCase
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

public interface GetTokensByIpAddressUseCase {
    List<Token> execute(GetTokensByIpAddressQuery query);
}

