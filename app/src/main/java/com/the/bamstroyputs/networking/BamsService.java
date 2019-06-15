package com.the.bamstroyputs.networking;

import com.the.bamstroyputs.model.Building;
import com.the.bamstroyputs.model.Floor;
import com.the.bamstroyputs.model.Order;
import com.the.bamstroyputs.model.Project;
import com.the.bamstroyputs.model.ProjectEditResponse;
import com.the.bamstroyputs.model.ResponseModel;
import com.the.bamstroyputs.model.Room;
import com.the.bamstroyputs.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BamsService {
    @FormUrlEncoded
    @POST("auth/SignIn")
    Call<ResponseModel<User>> logIn(@Field("email") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/ForgotPassword")
    Call<ResponseModel> sendEmail(@Field("email") String email);


    @FormUrlEncoded
    @POST("manager/GetProjects")
    Call<ResponseModel<List<Project>>> getProjects(@Field("token") String token,
                                                   @Field("page") String page,
                                                   @Field("per_page") String per_page);

    @FormUrlEncoded
    @POST("manager/CreateProject")
    Call<ResponseModel<Project>> createProject(@Field("token") String token,
                                               @Field("name") String name);

    @FormUrlEncoded
    @POST("manager/EditProject")
    Call<ResponseModel<ProjectEditResponse>> changeProjectName(@Field("token") String token,
                                                               @Field("name") String name,
                                                               @Field("project_id") String project_id);

    @FormUrlEncoded
    @POST("manager/GetBuildings")
    Call<ResponseModel<List<Building>>> getBuildings(@Field("token") String token,
                                                     @Field("page") String page,
                                                     @Field("per_page") String per_page,
                                                     @Field("project_id") String project_id);

    @FormUrlEncoded
    @POST("manager/CreateBuilding")
    Call<ResponseModel<Building>> createBuilding(@Field("token") String token,
                                                 @Field("name") String name,
                                                 @Field("naumber_of_foolrs") String numberFloors,
                                                 @Field("project_id") String project_id);

    @FormUrlEncoded
    @POST("manager/EditBuilding")
    Call<ResponseModel> changeBuildingNameFloorNumbers(@Field("token") String token,
                                                   @Field("naumber_of_foolrs") String numberOfFloors,
                                                   @Field("project_id") String project_id,
                                                   @Field("building_id") String building_id,
                                                   @Field("name") String name);

    @FormUrlEncoded
    @POST("manager/GetFloors")
    Call<ResponseModel<List<Floor>>> getFloors(@Field("token") String token,
                                               @Field("building_id") String building_id,
                                               @Field("page") String page,
                                               @Field("per_page") String per_page);

    @FormUrlEncoded
    @POST("manager/CreateRoom")
    Call<ResponseModel<Room>> createRoom(@Field("token") String token,
                                         @Field("username") String username,
                                         @Field("room_number") String room_number,
                                         @Field("floor_id") String floor_id);

    @FormUrlEncoded
    @POST("manager/CreateFloor")
    Call<ResponseModel<Floor>> createFloor(@Field("token") String token,
                                           @Field("name") String name,
                                           @Field("building_id") String building_id);

    @FormUrlEncoded
    @POST("manager/GetRooms")
    Call<ResponseModel<List<Room>>> getRooms(@Field("token") String token,
                                             @Field("floor_id") String floor_id,
                                             @Field("page") String page,
                                             @Field("per_page") String per_page);

    @FormUrlEncoded
    @POST("manager/GetOrders")
    Call<ResponseModel<List<Order>>> getOrders(@Field("token") String token,
                                               @Field("page") String page,
                                               @Field("per_page") String per_page,
                                               @Field("room_id") String room_id);
}
