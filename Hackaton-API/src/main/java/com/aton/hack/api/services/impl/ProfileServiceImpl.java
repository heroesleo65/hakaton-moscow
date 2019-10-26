package com.aton.hack.api.services.impl;

import com.aton.hack.api.config.DataParams;
import com.aton.hack.api.dto.Tag;
import com.aton.hack.api.dto.UserProfile;
import com.aton.hack.api.services.ProfileService;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class ProfileServiceImpl implements ProfileService {
    private final DataParams dataParams;

    @Override
    public List<Tag> getTags() {
        return dataParams.getTags();
    }

    @Override
    public UserProfile getUserProfileById(String userId) {
        for (var profile : dataParams.getProfiles()) {
            var user = profile.getUser();
            if (user.getId().equalsIgnoreCase(userId)) {
                var userProfile = new UserProfile();
                userProfile.setUser(user);
                userProfile.setFavorite(profile.getFavorite());
                userProfile.getUser().setFullName(getFullName(user.getFirstName(), user.getLastName(), user.getMiddleName()));
                return userProfile;
            }
        }

        return null;
    }

    private static String getFullName(String firstName, String lastName, String middleName) {
        var builder = new StringBuilder();
        if (Strings.isNotBlank(firstName)) {
            builder.append(firstName);
        }
        if (Strings.isNotBlank(lastName)) {
            if (builder.length() != 0) {
                builder.append(' ');
            }
            builder.append(lastName);
        }
        if (Strings.isNotBlank(middleName)) {
            if (builder.length() != 0) {
                builder.append(' ');
            }
            builder.append(middleName);
        }

        return builder.toString();
    }

    private static boolean isEmpty(String value) {
        return value == null || value.isBlank();
    }
}
