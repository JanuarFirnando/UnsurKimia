package com.if4a.unsurkimia.API;

import com.if4a.unsurkimia.model.ModelResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ModelResponse> ardRetrieve();

    @FormUrlEncoded
    @POST("create.php")
    Call<ModelResponse> ardCreate(
            @Field("SimbolAtom") String SimbolAtom,
            @Field("NamaAtom") String NamaAtom,
            @Field("MassaAtom") String MassaAtom,
            @Field("NomorAtom") String NomorAtom,
            @Field("Keterangan") String Keterangan
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ModelResponse> ardUpdate(
            @Field("id") String id,
            @Field("SimbolAtom") String SimbolAtom,
            @Field("NamaAtom") String NamaAtom,
            @Field("MassaAtom") String MassaAtom,
            @Field("NomorAtom") String NomorAtom,
            @Field("Keterangan") String Keterangan
    );
    @FormUrlEncoded
    @POST("delete.php")
    Call<ModelResponse> ardDelete(
      @Field("id") String id
    );
}
