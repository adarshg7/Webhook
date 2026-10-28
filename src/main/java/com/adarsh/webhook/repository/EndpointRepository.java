package com.adarsh.webhook.repository;

import com.adarsh.webhook.entity.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EndpointRepository extends JpaRepository<Endpoint, UUID> {

}
