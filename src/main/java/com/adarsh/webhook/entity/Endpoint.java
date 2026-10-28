package com.adarsh.webhook.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "endpoints")
@Getter
@Setter
public class Endpoint {

    @Id
    private UUID id;

    @Column(nullable = false, name = "tenant_id")
    private UUID tenantId;

    @Column(nullable = false, name = "url", length = 300)
    private String url;

    @Column(name = "secret_encrypted", nullable = false)
    private String secretEncrypted;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EndpointStatus status;

    @Column(nullable = false, name = "created_at")
    private OffsetDateTime createdAt;

    @Column(nullable = false, name = "updated_at")
    private OffsetDateTime updatedAt;
}
