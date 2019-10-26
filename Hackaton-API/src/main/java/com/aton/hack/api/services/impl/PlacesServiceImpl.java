package com.aton.hack.api.services.impl;

import com.aton.hack.api.config.DataParams;
import com.aton.hack.api.config.HereApplicationParams;
import com.aton.hack.api.config.HereHostsParams;
import com.aton.hack.api.dto.Story;
import com.aton.hack.api.dto.Trip;
import com.aton.hack.api.services.PlacesService;
import com.aton.hack.api.utils.DistanceUtils;
import lombok.Data;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Service
public class PlacesServiceImpl implements PlacesService {
    private final HereApplicationParams hereApplicationParams;
    private final HereHostsParams hereHostsParams;
    private final OkHttpClient okHttpClient;

    private final DataParams dataParams;

    @Override
    public List<Trip> getTrips(double lat, double lon, double maxDistance) {
        var trips = new ArrayList<Trip>();
        for (var trip : dataParams.getTrips()) {
            if (trip.getRoute() != null && !trip.getRoute().isEmpty()) {
                var routePoint = trip.getRoute().get(0);
                var routeLocation = routePoint.getLocation();

                var distance = DistanceUtils.distance(lat, lon, routeLocation.getLat(), routeLocation.getLon());
                if (Math.abs(distance) < maxDistance) {
                    var resultTrip = new Trip();
                    resultTrip.setId(trip.getId());
                    resultTrip.setTitle(trip.getTitle());
                    resultTrip.setDescription(trip.getDescription());
                    resultTrip.setDistance(distance);
                    resultTrip.setDistanceText(getDistanceText(distance));
                    resultTrip.setRoute(Collections.singletonList(routePoint));
                    resultTrip.setCreatorName(trip.getCreatorName());
                    resultTrip.setCreatorUuid(trip.getCreatorUuid());
                    resultTrip.setCreatorUrl(trip.getCreatorUrl());
                    resultTrip.setAverageRating(trip.getAverageRating());
                    resultTrip.setCountRatings(trip.getCountRatings());
                    resultTrip.setDuration(trip.getDuration());
                    resultTrip.setTags(trip.getTags());

                    trips.add(resultTrip);
                }
            }
        }

        return trips;
    }

    @Override
    public Trip getTripById(String id, double lat, double lon) {
        for (var trip : dataParams.getTrips()) {
            if (trip.getId().equalsIgnoreCase(id)) {
                if (trip.getRoute() != null && !trip.getRoute().isEmpty()) {
                    var routePoint = trip.getRoute().get(0);
                    var routeLocation = routePoint.getLocation();

                    var distance = DistanceUtils.distance(lat, lon, routeLocation.getLat(), routeLocation.getLon());

                    var resultTrip = new Trip();
                    resultTrip.setId(trip.getId());
                    resultTrip.setTitle(trip.getTitle());
                    resultTrip.setDescription(trip.getDescription());
                    resultTrip.setDistance(distance);
                    resultTrip.setDistanceText(getDistanceText(distance));
                    resultTrip.setRoute(trip.getRoute());
                    resultTrip.setCreatorName(trip.getCreatorName());
                    resultTrip.setCreatorUuid(trip.getCreatorUuid());
                    resultTrip.setCreatorUrl(trip.getCreatorUrl());
                    resultTrip.setAverageRating(trip.getAverageRating());
                    resultTrip.setCountRatings(trip.getCountRatings());
                    resultTrip.setDuration(trip.getDuration());
                    resultTrip.setTags(trip.getTags());
                    return resultTrip;
                }

                return null;
            }
        }

        return null;
    }

    @Override
    public Story getStory(String tripId, String pointId) {
        var story = new Story();
        story.setPages(Collections.emptyList());
        return story;
    }

    private String getDistanceText(double distance) {
        if (distance < 1000) {
            return (long)(distance) + " m";
        }

        return (long)(distance / 1000) + " km";
    }
}
