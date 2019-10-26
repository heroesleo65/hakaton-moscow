package com.aton.hack.api.controller;

import com.aton.hack.api.dto.HackatonRequest;
import com.aton.hack.api.dto.HackatonRequestBody;
import com.aton.hack.api.dto.Trip;
import com.aton.hack.api.services.PlacesService;
import com.aton.hack.api.services.ProfileService;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {
    private final PlacesService placesService;
    private final ProfileService profileService;

    @PostMapping(path = "reg", produces = APPLICATION_JSON_VALUE)
    public void registration(@RequestBody HackatonRequest request) {
    }

    @PostMapping(path = "places/vip", produces = APPLICATION_JSON_VALUE)
    public Object vip(@RequestBody HackatonRequestBody requestBody) {
        log.debug("Response body: {}", requestBody);

        return ImmutableList.of(
                ImmutableMap.of("image", "https://cdn-st1.rtr-vesti.ru/p/xw_1490927.jpg", "text", "Ночная Москва"),
                ImmutableMap.of("image", "http://iloveturizm.ru/wp-content/uploads/2016/02/5-2.jpg", "text", "Музеи Москвы")
        );
    }

    @PostMapping(path = "places", produces = APPLICATION_JSON_VALUE)
    public Object places(@RequestBody HackatonRequestBody requestBody) {
        log.debug("Response body: {}", requestBody);

        var location = Optional.of(requestBody)
                .map(HackatonRequestBody::getRequest)
                .map(HackatonRequest::getLocation)
                .orElseThrow(() -> new IllegalArgumentException("Not found location in request"));

        if (location.getLat() == null || location.getLon() == null) {
            throw new IllegalArgumentException("Not found location in request");
        }

        return new TripWrapper(placesService.getTrips(location.getLat(), location.getLon(), Double.MAX_VALUE));
    }

    @PostMapping(path = "trip/{id}", produces = APPLICATION_JSON_VALUE)
    public Object trip(@PathVariable String id, @RequestBody HackatonRequestBody requestBody) {
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

    @GetMapping(path = "tags", produces = APPLICATION_JSON_VALUE)
    public Object getTags() {
        return profileService.getTags();
    }

    @GetMapping(path = "profile", produces = APPLICATION_JSON_VALUE)
    public Object getProfile(@RequestParam("userId") String userId) {
        return profileService.getUserProfileById(userId);
    }

    @Data
    private static class TripWrapper {
        private final List<Trip> trips;
    }
}
