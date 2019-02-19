package com.the.bamstroyputs.networking;

import com.the.bamstroyputs.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BamsService {
    @POST("auth/SignIn")
    Call<User> logIn(@Query("email") String email,
                     @Query("password") String password);
}
