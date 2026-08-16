package com.techmatrix18.rider.infrastructure.http;

import com.techmatrix18.rider.application.command.CreateRiderProfileCommand;
import com.techmatrix18.rider.application.command.UpdateRiderProfileCommand;
import com.techmatrix18.rider.application.command.UpdateRiderStatusCommand;
import com.techmatrix18.rider.application.port.in.CreateRiderProfileUseCase;
import com.techmatrix18.rider.application.port.in.UpdateRiderProfileUseCase;
import com.techmatrix18.rider.application.port.in.UpdateRiderStatusUseCase;

import com.techmatrix18.rider.domain.model.Rider;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Inbound HTTP adapter handling motorcycle rider profile REST management endpoints.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

@RestController
@RequestMapping("/api/v1/riders")
public class RiderController {

    private final CreateRiderProfileUseCase createRiderProfileUseCase;
    private final UpdateRiderProfileUseCase updateRiderProfileUseCase;
    private final UpdateRiderStatusUseCase updateRiderStatusUseCase;

    public RiderController(CreateRiderProfileUseCase createRiderProfileUseCase,
                           UpdateRiderProfileUseCase updateRiderProfileUseCase,
                           UpdateRiderStatusUseCase updateRiderStatusUseCase) {
        this.createRiderProfileUseCase = createRiderProfileUseCase;
        this.updateRiderProfileUseCase = updateRiderProfileUseCase;
        this.updateRiderStatusUseCase = updateRiderStatusUseCase;
    }

    /**
     * POST /api/v1/riders - Creates a new rider profile.
     */
    @PostMapping
    public ResponseEntity<Rider> createProfile(@Valid @RequestBody CreateRiderRequest request) {
        CreateRiderProfileCommand command = new CreateRiderProfileCommand(
                request.userId(),
                request.nickname(),
                request.ridingSinceYear(),
                request.drivingStyle(),
                request.riderType(),
                request.hasHelmetForPassenger(),
                request.bloodType(),
                request.bio(),
                "PLANNING" // Начальный дефолтный статус сезона
        );

        Rider createdRider = createRiderProfileUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRider);
    }

    /**
     * PUT /api/v1/riders/{id} - Updates active rider profile metadata.
     * @param requesterId имитация заголовка авторизованного юзера (в будущем заменится на @AuthenticationPrincipal)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Rider> updateProfile(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long requesterId,
            @Valid @RequestBody UpdateRiderRequest request) {

        UpdateRiderProfileCommand command = new UpdateRiderProfileCommand(
                id,
                requesterId, // Проверка прав владения внутри доменного ядра
                request.nickname(),
                request.ridingSinceYear(),
                request.drivingStyle(),
                request.riderType(),
                request.hasHelmetForPassenger(),
                request.bloodType(),
                request.bio()
        );

        Rider updatedRider = updateRiderProfileUseCase.execute(command);
        return ResponseEntity.ok(updatedRider);
    }

    /**
     * PATCH /api/v1/riders/{id}/status - Transitions seasonal activity status (PLANNING, ACTIVE, FINISHED).
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<Rider> updateStatus(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long requesterId,
            @Valid @RequestBody UpdateStatusRequest request) {

        UpdateRiderStatusCommand command = new UpdateRiderStatusCommand(
                id,
                requesterId,
                request.status()
        );

        Rider updatedRider = updateRiderStatusUseCase.execute(command);
        return ResponseEntity.ok(updatedRider);
    }
}

