package com.techmatrix18.motorcycle.domain.model;

import java.time.Instant;

/**
 * Motorcycle domain entity representing a user's bike with
 * engine details, manufacturing specs, and profile customization attributes.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 15.08.2026
 */

public class Motorcycle {
    private final Long id;
    private final Long riderId;
    private String brand;
    private String model;
    private String bikeType;
    private Integer engineCapacity;
    private Integer manufactureYear;
    private final Instant createdAt;
    private Instant updatedAt;

    // Закрытый конструктор - создание объектов строго через конструктор
    private Motorcycle(Builder builder){
        this.id = builder.id;
        this.riderId = builder.riderId;
        this.brand = builder.brand;
        this.model = builder.model;
        this.bikeType = builder.bikeType;
        this.engineCapacity = builder.engineCapacity;
        this.manufactureYear = builder.manufactureYear;
        this.createdAt = builder.createdAt != null ? builder.createdAt : Instant.now();
        this.updatedAt = builder.updatedAt != null ? builder.updatedAt : Instant.now();
    }

    /**
     * Factory method to initialize a new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    // --- STATIC INNER BUILDER ---
    public static class Builder {
        private Long id;
        private Long riderId;
        private String brand;
        private String model;
        private String bikeType;
        private Integer engineCapacity;
        private Integer manufactureYear;
        private Instant createdAt;
        private Instant updatedAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder riderId(Long riderId) { this.riderId = riderId; return this; }
        public Builder brand(String brand) { this.brand = brand; return this; }
        public Builder model(String model) { this.model = model; return this; }
        public Builder bikeType(String bikeType) { this.bikeType = bikeType; return this; }
        public Builder engineCapacity(Integer engineCapacity) { this.engineCapacity = engineCapacity; return this; }
        public Builder manufactureYear(Integer manufactureYear) { this.manufactureYear = manufactureYear; return this; }
        public Builder createdAt(Instant createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(Instant updatedAt) { this.updatedAt = updatedAt; return this; }

        public Motorcycle build() {
            if (this.riderId == null) {
                throw new IllegalStateException("Domain validation failed: Rider ID cannot be null");
            }
            if (this.brand == null || this.brand.isBlank()) {
                throw new IllegalStateException("Domain validation failed: Brand cannot be empty");
            }
            if (this.model == null || this.model.isBlank()) {
                throw new IllegalStateException("Domain validation failed: Model cannot be empty");
            }
            return new Motorcycle(this);
        }
    }

    // --- GETTERS ---

    public Long getId() { return id; }
    public Long getRiderId() { return riderId; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getBikeType() { return bikeType; }
    public Integer getEngineCapacity() { return engineCapacity; }
    public Integer getManufactureYear() { return manufactureYear; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

    // --- DOMAIN BEHAVIOR METHODS (Бизнес-логика изменения состояния) ---

    /**
     * Updates core specifications and details of the motorcycle.
     */
    public void updateDetails(String brand, String model, String bikeType,
                              Integer engineCapacity, Integer manufactureYear) {
        if (brand == null || brand.isBlank()) throw new IllegalArgumentException("Brand cannot be empty");
        if (model == null || model.isBlank()) throw new IllegalArgumentException("Model cannot be empty");

        this.brand = brand;
        this.model = model;
        this.bikeType = bikeType;
        this.engineCapacity = engineCapacity;
        this.manufactureYear = manufactureYear;
        this.updatedAt = Instant.now();
    }

    /**
     * Compiles a full descriptive name of the motorcycle for display layouts.
     * Example: "BMW R1250GS (1254cc)"
     */
    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        sb.append(brand).append(" ").append(model);
        if (engineCapacity != null && engineCapacity > 0) {
            sb.append(" (").append(engineCapacity).append("cc)");
        }
        return sb.toString();
    }
}

