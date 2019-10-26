package com.aton.hack.api.config;

import com.aton.hack.api.dto.RoutePoint;
import com.aton.hack.api.dto.Trip;
import com.aton.hack.api.dto.User;
import lombok.Data;

import java.util.List;

@Data
public class DataParams {
    private List<User> users;
    private List<RoutePoint> routePoints;
    private List<Trip> trips;
}
