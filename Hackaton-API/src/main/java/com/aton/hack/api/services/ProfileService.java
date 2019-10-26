package com.aton.hack.api.services;

import com.aton.hack.api.dto.Tag;
import com.aton.hack.api.dto.UserProfile;

import java.util.List;

public interface ProfileService {
    List<Tag> getTags();
    UserProfile getUserProfileById(String userId);
}
