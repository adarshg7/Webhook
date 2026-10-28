package com.adarsh.webhook.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CreateEventRequest {

    @NotBlank
    private String eventType;

    @NotNull
    private Map<String,Object> payload;
}
