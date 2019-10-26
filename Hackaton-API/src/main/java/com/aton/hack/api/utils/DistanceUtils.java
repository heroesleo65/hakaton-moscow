package com.aton.hack.api.utils;

public final class DistanceUtils {
    private DistanceUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        if (lat1 == lat2 && lon1 == lon2) {
            return 0;
        }

        var theta = lon1 - lon2;
        var dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515; // Miles
        return dist * 1609.344; // Meters
    }
}
