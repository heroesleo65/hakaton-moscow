package com.aton.hack.api.controller;

import com.aton.hack.api.dto.HackatonRequest;
import com.aton.hack.api.dto.HackatonRequestBody;
import com.aton.hack.api.dto.Trip;
import com.aton.hack.api.services.PlacesService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {
    private final PlacesService placesService;

    @PostMapping(path = "reg", produces = APPLICATION_JSON_VALUE)
    public void registration(@RequestBody HackatonRequest request) {
    }

    @PostMapping(path = "places", produces = APPLICATION_JSON_VALUE)
    public Object places(@RequestBody HackatonRequestBody requestBody) throws IOException {
        log.debug("Response body: {}", requestBody);

        var location = Optional.of(requestBody)
                .map(HackatonRequestBody::getRequest)
                .map(HackatonRequest::getLocation)
                .orElseThrow(() -> new IllegalArgumentException("Not found location in request"));
//        var mapView = Optional.of(requestBody)
//                .map(HackatonRequestBody::getRequest)
//                .map(HackatonRequest::getDeviceState)
//                .map(Device::getMapView)
//                .orElse(null);

        if (location.getLat() == null || location.getLon() == null) {
            throw new IllegalArgumentException("Not found location in request");
        }

//        var urlBuilder = new HttpUrl.Builder()
//                .scheme("https")
//                .host(hereHostsParams.getPlaces())
//                .addPathSegment("places/v1/discover/explore")
//                .addQueryParameter("app_id", hereApplicationParams.getId())
//                .addQueryParameter("app_code", hereApplicationParams.getCode());

//        if (mapView != null) {
//            var allLocation = Stream.of(
//                    mapView.getWestLon(), mapView.getSouthLat(), mapView.getEastLon(), mapView.getNorthLat()
//            );
//            if (allLocation.anyMatch(Objects::isNull)) {
//                throw new IllegalArgumentException("Not found mapView in request");
//            }
//
//            var inLocation = mapView.getWestLon() + ","
//                    + mapView.getSouthLat() + "," + mapView.getEastLon() + "," + mapView.getNorthLat();
//
//            urlBuilder.addQueryParameter("in", inLocation);
//        } else {
//            var atLocation = location.getLat() + "," + location.getLon();
//            urlBuilder.addQueryParameter("at", atLocation);
//        }
//
//        var hereRequest = new Request.Builder()
//                .url(urlBuilder.build())
//                .build();
//
//        String text;
//        try (Response response = okHttpClient.newCall(hereRequest).execute()) {
//            text = response.body().string();
//        }

        return new TripWrapper(placesService.getTrips(location.getLat(), location.getLon(), Double.MAX_VALUE));
    }

    @PostMapping(path = "trip/{id}", produces = APPLICATION_JSON_VALUE)
    public Object trip(@PathVariable String id, @RequestBody HackatonRequestBody requestBody) throws IOException {
        log.debug("Response body: {}", requestBody);

        var location = Optional.of(requestBody)
                .map(HackatonRequestBody::getRequest)
                .map(HackatonRequest::getLocation)
                .orElseThrow(() -> new IllegalArgumentException("Not found location in request"));

        if (location.getLat() == null || location.getLon() == null) {
            throw new IllegalArgumentException("Not found location in request");
        }

        var trip = placesService.getTripById(id, location.getLat(), location.getLat());
        if (trip == null) {
            throw new IllegalArgumentException("Not found trip by id " + id);
        }

        return trip;
    }

    @PostMapping(path = "story/{tripId}/{pointId}", produces = APPLICATION_JSON_VALUE)
    public Object story(
            @PathVariable String tripId, @PathVariable String pointId, @RequestBody HackatonRequestBody requestBody
    ) {
        log.debug("Response body: {}", requestBody);

        var location = Optional.of(requestBody)
                .map(HackatonRequestBody::getRequest)
                .map(HackatonRequest::getLocation)
                .orElseThrow(() -> new IllegalArgumentException("Not found location in request"));

        if (location.getLat() == null || location.getLon() == null) {
            throw new IllegalArgumentException("Not found location in request");
        }

        return placesService.getStory(tripId, pointId);
    }

    @Data
    private static class TripWrapper {
        private final List<Trip> trips;
    }
}
