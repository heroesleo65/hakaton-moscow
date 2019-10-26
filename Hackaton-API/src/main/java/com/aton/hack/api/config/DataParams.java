package com.aton.hack.api.config;

import com.aton.hack.api.dto.RoutePoint;
import com.aton.hack.api.dto.Tag;
import com.aton.hack.api.dto.Trip;
import com.aton.hack.api.dto.UserProfile;
import lombok.Data;

import java.util.List;

@Data
public class DataParams {
    private List<UserProfile> profiles;
    private List<RoutePoint> routePoints;
    private List<Trip> trips;
    private List<Tag> tags;
}
