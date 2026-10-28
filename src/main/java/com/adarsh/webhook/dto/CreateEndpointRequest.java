package com.adarsh.webhook.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEndpointRequest {

    @NotBlank
    private String url;
}
