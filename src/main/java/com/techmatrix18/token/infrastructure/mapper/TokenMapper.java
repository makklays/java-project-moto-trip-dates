package com.techmatrix18.token.infrastructure.mapper;

import com.techmatrix18.token.domain.model.Token;
import com.techmatrix18.token.infrastructure.db.TokenEntity;
import org.mapstruct.Mapper;

/**
 * TokenMapper
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

@Mapper(componentModel = "spring")
public interface TokenMapper {
    Token toDomain(TokenEntity entity);
    TokenEntity toEntity(Token domain);
}

