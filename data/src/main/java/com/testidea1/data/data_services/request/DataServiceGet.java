package com.testidea1.data.data_services.request;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DataServiceGet extends DataService {

    @Override
    public Response execute(String url, String json){
        Request.Builder builder = getBuilder(url);
        RequestBody requestBody = getRequestBody("",json);
        builder.get();
        Request request = builder.build();

        return callRequest(request);
    }
}
