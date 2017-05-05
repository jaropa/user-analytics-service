package com.hotels.user.analytics;


import com.google.common.io.Resources;
import com.hotels.user.analytics.data.DataReader;
import com.hotels.user.analytics.datamodel.Booking;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class RunUserAnalyticsService {

    private final static String USERS_DATA = "user_features.txt";

    public static void main(String[] args) throws IOException {
        URL dataUrl = Resources.getResource(USERS_DATA);
        List<Booking> bookings = DataReader.loadDataFromFile(dataUrl.getPath());
        UserAnalyticsService userAnalyticsService = new UserAnalyticsService(bookings);
        userAnalyticsService.start();
    }
}
