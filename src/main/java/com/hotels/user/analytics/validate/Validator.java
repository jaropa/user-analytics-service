package com.hotels.user.analytics.validate;

import com.hotels.user.analytics.datamodel.Booking;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Validator {
    private final Set<String> users;

    public Validator(List<Booking> bookings) {
        users = bookings.stream().map(Booking::getUserId).collect(Collectors.toSet());
    }

    public boolean exists(String user) {
        return users.contains(user);
    }
}
