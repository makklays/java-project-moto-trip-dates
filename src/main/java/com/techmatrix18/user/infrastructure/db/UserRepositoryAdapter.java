package com.techmatrix18.user.infrastructure.db;

import com.techmatrix18.user.application.port.out.UserRepository;
import com.techmatrix18.user.domain.model.User;
import com.techmatrix18.user.infrastructure.mapper.UserMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * UserRepositoryAdapter
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

@Component
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;
    private final UserMapper userMapper;

    public UserRepositoryAdapter(UserJpaRepository jpaRepository, UserMapper userMapper) {
        this.jpaRepository = jpaRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedEntity = jpaRepository.save(userEntity);
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public User saveAndFlush(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedEntity = jpaRepository.saveAndFlush(userEntity); // вызываем jpaRepository.saveAndFlush()
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id).map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(userMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaRepository.existsByUsername(username);
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Page<User> findByFilters(String searchTerms, List<String> statuses, String gender,
                                    Integer minAge, Integer maxAge, int page, int size) {
        // Создаем объект пагинации Spring Data
        PageRequest pageable = PageRequest.of(page, size);

        // Получаем страницу сущностей из БД
        Page<UserEntity> entityPage = jpaRepository.findByFiltersWithPage(searchTerms, statuses, gender, pageable);

        // Мапим каждую сущность внутри страницы в доменный объект User
        return entityPage.map(userMapper::toDomain);
    }

    @Override
    public List<User> findNearby(double latitude, double longitude, double radiusInKm, String datingStatus) {
        // Получаем список сущностей из БД по гео-координатам
        List<UserEntity> entities = jpaRepository.findNearbyUsers(latitude, longitude, radiusInKm, datingStatus);

        // Мапим список инфраструктуры в список домена
        return entities.stream()
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }
}

