package com.hotels.user.analytics;

import com.hotels.user.analytics.datamodel.Booking;
import com.hotels.user.analytics.logic.UserAnalytics;

import java.util.List;

import static spark.Spark.*;

public class UserAnalyticsService
{
    private final UserAnalytics userAnalytics;

    public UserAnalyticsService(UserAnalytics userAnalytics) {
        this.userAnalytics = userAnalytics;
    }

    public UserAnalyticsService(List<Booking> bookings){
        userAnalytics = new UserAnalytics(bookings);
    }

    public void start(){
        get("/user/:userId/bookingsNumber", (request, response)
                -> userAnalytics.getUserBookingsNumber(request.params(":userId"), response));

        get("/user/:userId/bookingsTotalValue", (request, response)
                -> userAnalytics.getUserBookingsTotalValue(request.params(":userId"), response));

        get("/user/:userId/avgStayLength", (request, response)
                -> userAnalytics.getUserAvgStayLength(request.params(":userId"), response));

    }

}
