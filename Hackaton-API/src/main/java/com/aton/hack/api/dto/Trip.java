package com.aton.hack.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class Trip {
    private String id;
    private String title;
    private String description;

    private Double distance;
    private String distanceText;
    private List<RoutePoint> route;

    private String creatorName;
    private String creatorUuid;
    private String creatorUrl;

    private Double averageRating;
    private Long countRatings;

    private Long duration;
    private List<Tag> tags;
}
