package com.aton.hack.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoutePointDescription {
    private String title;

    private String youtube;
    private String sound;
    private List<String> image;
    private String description;

    private List<Tag> tags;
}
