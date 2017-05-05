package com.hotels.user.analytics.parse;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
    private static final Gson gsonInstance;
    static {
        gsonInstance = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();
    }

    public static Gson getGsonInstance(){
        return gsonInstance;
    }
}
