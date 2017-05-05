package com.hotels.user.analytics.data;


import com.google.gson.Gson;
import com.hotels.user.analytics.datamodel.Booking;
import com.hotels.user.analytics.parse.GsonUtil;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.google.common.io.Resources;
import java.net.URL;

public class DataReaderTest {

    private final static Gson gson = GsonUtil.getGsonInstance();
    private final static String TEST_FILE_DATA = "user_features_test.txt";

    private final String testData =
            "{\"userId\":\"3\", \"hotelId\":\"19\", \"numberOfNights\":8, \"totalPriceInUSD\":456}\n" +
                    "{\"userId\":\"6\", \"hotelId\":\"40\", \"numberOfNights\":8, \"totalPriceInUSD\":664}";

    @Test
    public void readDataFromString() {

        List<Booking> data = DataReader.loadDataFromString(testData);
        assertThat(data, hasItem(equalTo(new Booking("3", "19", 8, BigDecimal.valueOf(456)))));
        assertThat(data, hasItem(equalTo(new Booking("6", "40", 8, BigDecimal.valueOf(664)))));
    }

    @Test
    public void readDataFromFile() throws IOException {

        URL dataUrl = Resources.getResource(TEST_FILE_DATA);
        List<Booking> data = DataReader.loadDataFromFile(dataUrl.getPath());
        assertThat(data, hasItem(equalTo(new Booking("3", "19", 8, BigDecimal.valueOf(456)))));
        assertThat(data, hasItem(equalTo(new Booking("6", "40", 8, BigDecimal.valueOf(664)))));
    }
}
