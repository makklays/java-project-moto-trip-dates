package com.techmatrix18.user.application.service;

import com.techmatrix18.user.application.port.in.SearchUsersUseCase;
import com.techmatrix18.user.application.query.SearchUsersQuery;
import com.techmatrix18.user.application.port.out.UserRepository;
import com.techmatrix18.user.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service orchestrator executing user search with complex filters and pagination.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

@Service
public class SearchUsersService implements SearchUsersUseCase {

    private final UserRepository userRepository;

    public SearchUsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> execute(SearchUsersQuery query) {
        // Логика делегируется репозиторию. В инфраструктуре JPA репозиторий
        // использует PageRequest.of(query.page(), query.size()) и Criteria API/QueryDSL.
        return userRepository.findByFilters(
            query.searchTerms(),
            query.statuses(),
            query.gender(),
            query.minAge(),
            query.maxAge(),
            query.page(),
            query.size()
        );
    }
}

