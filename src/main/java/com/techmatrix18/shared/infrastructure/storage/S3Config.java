package com.techmatrix18.shared.infrastructure.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;

/**
 * S3Config - Configuration class for initializing the Amazon S3 Client
 * bean with support for local MinIO overrides during development.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 15.08.2026
 */

@Configuration
public class S3Config {

    @Value("${app.s3.endpoint:#{null}}")
    private String endpoint;

    @Value("${app.s3.access-key}")
    private String accessKey;

    @Value("${app.s3.secret-key}")
    private String secretKey;

    @Value("${app.s3.region}")
    private String region;

    /**
     * Creates and configures the S3Client instance.
     * Integrates path-style routing if a local endpoint wrapper is present.
     *
     * @return configured S3Client instance
     */
    @Bean
    public S3Client s3Client() {
        var credentials = AwsBasicCredentials.create(accessKey, secretKey);
        var builder = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.of(region));

        // Если указан локальный endpoint (для MinIO), перенаправляем туда
        if (endpoint != null && !endpoint.isBlank()) {
            builder.endpointOverride(URI.create(endpoint))
                    .serviceConfiguration(S3Configuration.builder()
                            .pathStyleAccessEnabled(true) // Обязательно для MinIO
                            .build());
        }

        return builder.build();
    }
}

