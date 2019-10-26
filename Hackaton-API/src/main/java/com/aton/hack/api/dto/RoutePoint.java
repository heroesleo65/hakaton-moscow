package com.aton.hack.api.dto;

import lombok.Data;

@Data
public class RoutePoint {
    private String id;
    private UserLocation location;
    private String address;
    private RoutePointDescription description;
}
