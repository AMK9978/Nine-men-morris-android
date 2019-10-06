package com.amk.morris.API;

import com.amk.morris.Model.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @GET("rank")
    Call<List<Person>> ranking();


    @GET("turn")
    Call<Integer> getTurn();


    @GET("forgetowd")
    Call<String> forgetPwd(@Field("username") String username);


    @GET("bins/pq7mn")
    Call<Person> getPerson();

    @POST("startgame")
    @FormUrlEncoded
    Call<Person> startGame(@Field("username") String username);

    @POST("signup")
    @FormUrlEncoded
    Call<Person> signUp(@Field("username") String username, @Field("password") String password,
                        @Field("email") String email);

    @POST("login")
    @FormUrlEncoded
    Call<Person> login(@Field("username") String username,
                       @Field("password") String password);

}
