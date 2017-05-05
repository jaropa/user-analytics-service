package com.hotels.user.analytics.response;

import com.google.gson.Gson;
import com.hotels.user.analytics.parse.GsonUtil;

import static com.hotels.user.analytics.response.ResponseConstant.*;

public class ResponseHelper {

    private static final Gson gson = GsonUtil.getGsonInstance();

    public static String getInvalidUserIdResponse(spark.Response response) {
        response.status(BAD_REQUEST);
        response.type("application/json");
        return gson.toJson(new BaseResponse(ERROR_CODE, ERROR_MESSAGE, INVALID_USER_ID));
    }

    public static String getOkResponse(spark.Response response, String result) {
        response.status(OK_REQUEST);
        response.type("application/json");
        return gson.toJson(new BaseResponse(OK_CODE, OK_MESSAGE, result));
    }
}
