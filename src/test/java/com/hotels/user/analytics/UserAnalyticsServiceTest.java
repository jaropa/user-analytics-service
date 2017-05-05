package com.hotels.user.analytics;

import com.google.gson.Gson;
import com.hotels.user.analytics.data.DataReader;
import com.hotels.user.analytics.datamodel.Booking;
import com.hotels.user.analytics.parse.GsonUtil;
import com.hotels.user.analytics.response.BaseResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static com.hotels.user.analytics.response.ResponseConstant.*;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserAnalyticsServiceTest {

    private static final Gson gson = GsonUtil.getGsonInstance();
    private static final String BASE_URL = "http://localhost:4567";

    private final static String testData =
            "{\"userId\":\"3\", \"hotelId\":\"69\", \"numberOfNights\":12, \"totalPriceInUSD\":1344}\n" +
            "{\"userId\":\"3\", \"hotelId\":\"68\", \"numberOfNights\":4, \"totalPriceInUSD\":480}\n" +
            "{\"userId\":\"3\", \"hotelId\":\"37\", \"numberOfNights\":3, \"totalPriceInUSD\":111}\n";


    @BeforeClass
    public static  void setUpOnce()  {
        List<Booking> bookings = DataReader.loadDataFromString(testData);
        UserAnalyticsService userAnalyticsService = new UserAnalyticsService(bookings);
        userAnalyticsService.start();
    }

    @Test
    public void getUserBookingsNumberRestCall() {

        Response response =
                when()
                        .get(BASE_URL + "/user/3/bookingsNumber").
                then()
                        .statusCode(OK_REQUEST)
                        .contentType(ContentType.JSON)
                        .extract().response();

        BaseResponse baseResponse = gson.fromJson(response.getBody().asString(), BaseResponse.class);

        assertThat(OK_CODE, equalTo(baseResponse.getCode()));
        assertThat(OK_MESSAGE, equalTo(baseResponse.getMessage()));
        assertThat("3", equalTo(baseResponse.getData()));
    }

    @Test
    public void getUserBookingsTotalValueRestCall() {

        Response response =
                when()
                        .get(BASE_URL + "/user/3/bookingsTotalValue").
                then()
                        .statusCode(OK_REQUEST)
                        .contentType(ContentType.JSON)
                        .extract().response();

        BaseResponse baseResponse = gson.fromJson(response.getBody().asString(), BaseResponse.class);

        assertThat(OK_CODE, equalTo(baseResponse.getCode()));
        assertThat(OK_MESSAGE, equalTo(baseResponse.getMessage()));
        assertThat("1935", equalTo(baseResponse.getData()));
    }

    @Test
    public void getUserAvgStayLengthRestCall() {

        Response response =
                when()
                        .get(BASE_URL + "/user/3/avgStayLength").
                then()
                        .statusCode(OK_REQUEST)
                        .contentType(ContentType.JSON)
                        .extract().response();

        BaseResponse baseResponse = gson.fromJson(response.getBody().asString(), BaseResponse.class);

        assertThat(OK_CODE, equalTo(baseResponse.getCode()));
        assertThat(OK_MESSAGE, equalTo(baseResponse.getMessage()));
        assertThat("6.33", equalTo(baseResponse.getData()));
    }

    @Test
    public void invalidUserId(){
        Response response =
                when()
                        .get(BASE_URL + "/user/88/avgStayLength").
                then()
                        .statusCode(BAD_REQUEST)
                        .contentType(ContentType.JSON)
                        .extract().response();

        BaseResponse baseResponse = gson.fromJson(response.getBody().asString(), BaseResponse.class);

        assertThat(ERROR_CODE, equalTo(baseResponse.getCode()));
        assertThat(ERROR_MESSAGE, equalTo(baseResponse.getMessage()));
        assertThat(INVALID_USER_ID, equalTo(baseResponse.getData()));
    }
}
