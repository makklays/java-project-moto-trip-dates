package com.techmatrix18.user.application.query;

import java.util.List;

/**
 * SearchUsersQuery
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

public record SearchUsersQuery(
    String searchTerms,      // поиск по имени/email/телефону
    List<String> statuses,   // фильтр по DatingStatus (например, только DRIVER)
    String gender,           // фильтр по полу
    Integer minAge,          // минимальный возраст
    Integer maxAge,          // максимальный возраст
    int page,                // номер страницы (для пагинации)
    int size                 // размер страницы
) {}

