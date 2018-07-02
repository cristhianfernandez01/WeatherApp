package com.testidea1.data.data_services.request;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class DataService {

    private final OkHttpClient client = new OkHttpClient.Builder()
        .connectTimeout(1200, TimeUnit.MILLISECONDS)
        .writeTimeout(1200, TimeUnit.MILLISECONDS)
        .readTimeout(1200, TimeUnit.MILLISECONDS)
        .build();

    private static final String AUTHOTIZATION = "Authorization";
    private static final String STRINGEMPTY = "";
    static final String TYPE_JSON = "application/json; charset=utf-8";

    public abstract Response execute(String url, String json);

    Request.Builder getBuilder(String url){

        return new Request.Builder()
            .url(url);
    }

    RequestBody getRequestBody(String TypeFormat, String json){
        MediaType type = MediaType.parse(TypeFormat);
        return RequestBody.create(type, json);
    }

    Response callRequest(Request request){
        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}