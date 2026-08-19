package com.techmatrix18.user.application.query;

/**
 * GetNearbyUsersQuery
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

public record GetNearbyUsersQuery(
    double latitude,         // широта точки поиска
    double longitude,        // долгота точки поиска
    double radiusInKm,       // радиус поиска (например, 5.0 км)
    String datingStatus      // кого ищем (например, только "DRIVER")
) {}

