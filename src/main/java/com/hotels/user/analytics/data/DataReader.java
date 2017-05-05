package com.hotels.user.analytics.data;


import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.hotels.user.analytics.datamodel.Booking;
import com.hotels.user.analytics.parse.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class DataReader {

    private final static Gson gson = GsonUtil.getGsonInstance();
    private final static Logger logger = LoggerFactory.getLogger(DataReader.class);

    private DataReader() {}

    public static List<Booking> loadDataFromFile(String filePath) throws IOException {
        try {
            return load(new String(Files.readAllBytes(Paths.get(filePath.replaceFirst("^/(.:/)", "$1")))));
        } catch (IOException e) {
            logger.error("Failed to load users booking data from file", e);
            throw new IOException();
        }
    }

    public static List<Booking> loadDataFromString(String data) {
        return load(data);
    }

    private static List<Booking> load(String data) throws JsonParseException {
        String[] rows = data.split("\n");
        try {
            return Arrays.stream(rows).map(row -> gson.fromJson(row, Booking.class)).collect(Collectors.toList());
        } catch (JsonParseException e) {
            logger.error("Failed to parse user data", e);
            throw new JsonParseException("Failed to parse user data");
        }
    }
}
