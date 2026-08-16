package com.techmatrix18.motorcycle.application.port.out;

import com.techmatrix18.motorcycle.domain.model.Motorcycle;
import java.util.List;
import java.util.Optional;

/**
 * Outbound port for managing motorcycle persistence operations.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 16.08.2026
 */

public interface MotorcycleRepository {

    Motorcycle save(Motorcycle motorcycle);

    Optional<Motorcycle> findById(Long id);

    List<Motorcycle> findByRiderId(Long riderId);

    void delete(Long id);

    boolean existsById(Long id);
}

