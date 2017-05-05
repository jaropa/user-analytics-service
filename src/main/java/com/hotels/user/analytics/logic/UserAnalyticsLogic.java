package com.hotels.user.analytics.logic;


import com.hotels.user.analytics.datamodel.Booking;

import java.math.BigDecimal;
import java.util.List;

public class UserAnalyticsLogic {

    public static Long getUserBookingsNumber(String userId, List<Booking> bookings) {
        return bookings.stream()
                .filter(booking -> booking.getUserId().equals(userId))
                .count();
    }

    public static BigDecimal getUserBookingsTotalValue(String userId, List<Booking> bookings) {
        return bookings.stream()
                .filter(booking -> booking.getUserId().equals(userId))
                .map(Booking::getTotalPriceInUSD).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Double getUserAvgStayLength(String userId, List<Booking> bookings) {
        return bookings.stream()
                .filter(booking -> booking.getUserId().equals(userId))
                .map(x -> x.getNumberOfNights()).mapToDouble(Number::doubleValue).average().orElse(0);
    }
}
