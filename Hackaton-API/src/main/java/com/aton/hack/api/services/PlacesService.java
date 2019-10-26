package com.aton.hack.api.services;

import com.aton.hack.api.dto.Story;
import com.aton.hack.api.dto.Trip;

import java.util.List;

public interface PlacesService {
    List<Trip> getTrips(double lat, double lon, double maxDistance);
    Trip getTripById(String id, double lat, double lon);
    Story getStory(String tripId, String pointId);
}
