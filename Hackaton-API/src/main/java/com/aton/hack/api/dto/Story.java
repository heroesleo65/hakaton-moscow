package com.aton.hack.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class Story {
    private List<StoryPage> pages;
}
