package com.aton.hack.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserProfile {
    private User user;
    private List<Tag> favorite;
}
