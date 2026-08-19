package com.techmatrix18.user.infrastructure.http;

import com.techmatrix18.user.application.command.RegisterUserCommand;
import com.techmatrix18.user.application.port.in.*;
import com.techmatrix18.user.application.query.GetNearbyUsersQuery;
import com.techmatrix18.user.application.query.GetPublicProfileQuery;
import com.techmatrix18.user.application.query.GetUserQuery;
import com.techmatrix18.user.application.query.SearchUsersQuery;
import com.techmatrix18.user.domain.model.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

/**
 * UserController
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final SearchUsersUseCase searchUsersUseCase;
    private final GetPublicProfileUseCase getPublicProfileUseCase;
    private final GetNearbyUsersUseCase getNearbyUsersUseCase;

    // Внедряем все Use Cases через единственный конструктор
    public UserController(
            RegisterUserUseCase registerUserUseCase,
            GetUserUseCase getUserUseCase,
            SearchUsersUseCase searchUsersUseCase,
            GetPublicProfileUseCase getPublicProfileUseCase,
            GetNearbyUsersUseCase getNearbyUsersUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.getUserUseCase = getUserUseCase;
        this.searchUsersUseCase = searchUsersUseCase;
        this.getPublicProfileUseCase = getPublicProfileUseCase;
        this.getNearbyUsersUseCase = getNearbyUsersUseCase;
    }

    /**
     * POST /api/v1/users/register - Registers a new User
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        RegisterUserCommand command = new RegisterUserCommand(
            request.username(),
            request.email(),
            request.password(),
            request.mobile(),
            request.nickname(),
            request.gender(),
            null, // age передаем как null, так как он рассчитается в домене автоматически
            request.birthDate(),
            request.bio(),
            request.datingStatus() != null ? request.datingStatus().name() : null
        );

        User registeredUser = registerUserUseCase.execute(command);
        // Маппинг доменного объекта в безопасный HTTP Response DTO (без пароля и т.д.)
        UserResponse userResponse = UserResponse.fromDomain(registeredUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    /**
     * GET /api/v1/users/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        GetUserQuery query = new GetUserQuery(id);
        User user = getUserUseCase.execute(query);
        UserResponse response = UserResponse.fromDomain(user);

        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/v1/users/search
     * Example: /api/v1/users/search?search=Alex&statuses=DRIVER&page=0&size=10
     */
    @GetMapping("/search")
    public ResponseEntity<Page<UserResponse>> searchUsers(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) List<String> statuses,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        SearchUsersQuery query = new SearchUsersQuery(search, statuses, gender, minAge, maxAge, page, size);
        Page<User> userPage = searchUsersUseCase.execute(query);

        // Мапим страницу доменных моделей в страницу безопасных DTO ответов
        Page<UserResponse> responsePage = userPage.map(UserResponse::fromDomain);
        return ResponseEntity.ok(responsePage);
    }

    /**
     * GET /api/v1/users/{id}/public
     * Позволяет посмотреть ограниченный публичный профиль пользователя
     */
    @GetMapping("/{id}/public")
    public ResponseEntity<UserResponse> getPublicProfile(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long requestedByUserId) { // ID того, кто делает запрос (из сессии/токена)

        GetPublicProfileQuery query = new GetPublicProfileQuery(id, requestedByUserId);
        User user = getPublicProfileUseCase.execute(query);
        return ResponseEntity.ok(UserResponse.fromDomain(user));
    }

    /**
     * GET /api/v1/users/nearby
     * Example: /api/v1/users/nearby?lat=50.45&lon=30.52&radius=5.0&status=DRIVER
     */
    @GetMapping("/nearby")
    public ResponseEntity<List<UserResponse>> getNearbyUsers(
            @RequestParam double lat,
            @RequestParam double lon,
            @RequestParam(defaultValue = "5.0") double radius,
            @RequestParam(required = false) String status) {

        GetNearbyUsersQuery query = new GetNearbyUsersQuery(lat, lon, radius, status);
        List<User> users = getNearbyUsersUseCase.execute(query);

        // Мапим стримом список из домена в инфраструктурные DTO
        List<UserResponse> responseList = users.stream()
                .map(UserResponse::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }
}

