package com.adarsh.webhook.service;

import com.adarsh.webhook.dto.CreateEndpointRequest;
import com.adarsh.webhook.entity.Endpoint;
import com.adarsh.webhook.entity.EndpointStatus;
import com.adarsh.webhook.repository.EndpointRepository;
import com.adarsh.webhook.util.EncryptionUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.UUID;

@Service
public class EndpointService {

    private final EndpointRepository endpointRepository;
    private final EncryptionUtil encryptionUtil;

    public EndpointService(EndpointRepository endpointRepository, EncryptionUtil encryptionUtil){

        this.endpointRepository = endpointRepository;
        this.encryptionUtil = encryptionUtil;
    }

    @Transactional
    public Endpoint createEndpoint(UUID tenantId, CreateEndpointRequest request){
        byte[] rawSecretBytes = new byte[32];

        new SecureRandom().nextBytes(rawSecretBytes);

        String rawSecret = Base64.getEncoder().encodeToString(rawSecretBytes);

        String encryptedSecret = encryptionUtil.encrypt(rawSecret);

        Endpoint endpoint = new Endpoint();
        endpoint.setId(UUID.randomUUID());
        endpoint.setTenantId(tenantId);
        endpoint.setUrl(request.getUrl());
        endpoint.setSecretEncrypted(encryptedSecret);
        endpoint.setStatus(EndpointStatus.ACTIVE);
        endpoint.setCreatedAt(OffsetDateTime.now());
        endpoint.setUpdatedAt(OffsetDateTime.now());

        return endpointRepository.save(endpoint);
    }

}
