package com.hotels.user.analytics.logic;


import com.hotels.user.analytics.data.DataReader;
import com.hotels.user.analytics.datamodel.Booking;
import com.hotels.user.analytics.response.ResponseHelper;
import com.hotels.user.analytics.validate.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Response;

import java.text.DecimalFormat;
import java.util.List;

public class UserAnalytics {

    private final Validator validator;
    private final List<Booking> bookings;
    private DecimalFormat format = new DecimalFormat("#.00");
    private final static Logger logger = LoggerFactory.getLogger(UserAnalytics.class);

    public UserAnalytics(List<Booking> bookings) {
        this.validator = new Validator(bookings);
        this.bookings = bookings;
    }

    public String getUserBookingsNumber(String userId, Response response) {
        logger.info("Processing bookings number for user:[{}]", userId);
        if (!validator.exists(userId))
            return ResponseHelper.getInvalidUserIdResponse(response);
        String result = UserAnalyticsLogic.getUserBookingsNumber(userId, bookings).toString();
        return ResponseHelper.getOkResponse(response, result);
    }

    public String getUserBookingsTotalValue(String userId, Response response) {
        logger.info("Processing bookings total USD value for user:[{}]", userId);
        if (!validator.exists(userId))
            return ResponseHelper.getInvalidUserIdResponse(response);
        String result = UserAnalyticsLogic.getUserBookingsTotalValue(userId, bookings).toString();
        return ResponseHelper.getOkResponse(response, result);
    }

    public String getUserAvgStayLength(String userId, Response response) {
        logger.info("Processing average stay length for user:[{}]", userId);
        if (!validator.exists(userId))
            return ResponseHelper.getInvalidUserIdResponse(response);
        String result = format.format(UserAnalyticsLogic.getUserAvgStayLength(userId, bookings));
        return ResponseHelper.getOkResponse(response, result);
    }
}
