package com.the.bamstroyputs.networking;

import com.the.bamstroyputs.controller.DataController;
import com.the.bamstroyputs.util.Constants;
import com.the.bamstroyputs.util.Utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BamsClient {
    private static final String BASE_URL = "http://109.75.38.152/bsp/";
    public static final String IMAGE_BASE_URL = BASE_URL + "assets/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        request = request.newBuilder().addHeader("bsp-secret-key", Constants.REQUEST_HEADER).build();

                        Response response = chain.proceed(request);

                        // todo deal with the issues the way you need to
                        if (response.code() == 401 || response.code() == 500) {
                            return response;
                        }

                        return response;
                    }
                })
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
