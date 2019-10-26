package com.aton.hack.api.dto;

import lombok.Data;

@Data
public class HackatonRequest {
    private Device deviceState;
    private Profile profile;
    private UserLocation location;
    private String uuid;
}
