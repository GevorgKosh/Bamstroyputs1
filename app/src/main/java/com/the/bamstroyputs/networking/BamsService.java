package com.the.bamstroyputs.networking;

import com.the.bamstroyputs.model.Data;
import com.the.bamstroyputs.model.ResponseModel;
import com.the.bamstroyputs.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BamsService {
    @FormUrlEncoded
    @POST("auth/SignIn")
    Call<ResponseModel<Data>> logIn(@Field("email") String email,
                                    @Field("password") String password);
}
