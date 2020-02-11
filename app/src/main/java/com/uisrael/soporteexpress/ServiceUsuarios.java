package com.uisrael.soporteexpress;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceUsuarios {

    @GET("personas")
    Call<List<ResponseUsuarios>>getUsersGet();

    @POST("personas")
    Call<List<ResponseUsuarios>>getUsersPost();

//    @POST("personas")
//    @FormUrlEncoded
//    Call<ResponseUsuarios> savePost(@Field("codPersona") String codPersona,
//                                    @Field("nombres") String nombres,
//                                    @Field("apellidos") String apellidos),
//                                    @Field("email") String email,
//                                    @Field("telefono") String telefono),
//                                    @Field("codRol") String codRol);

}

