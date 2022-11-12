package com.fedex.location;

import java.util.HashMap;
import java.util.Map;

public class LocationProvider {
    private final Map<String, String> locations = new HashMap<>();

    public LocationProvider() {
        locations.put("Amsterdam", "Ferdinand Bolstraat 93");
        locations.put("Rotterdam", "Lusthofstraat 27A");
        locations.put("Utrecht", "Maliebaan 15");
    }

    public String getLocationDetails(String location) {
        return locations.get(location);
    }
}
