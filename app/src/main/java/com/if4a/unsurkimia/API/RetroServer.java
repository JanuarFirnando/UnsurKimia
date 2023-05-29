package com.if4a.unsurkimia.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    private static final String AlamatServer = "https://kulinerjanuar.000webhostapp.com/";

    private static Retrofit retro;

    public static Retrofit konekRetrofit()
    {
        if(retro == null)
        {
            retro = new Retrofit.Builder()
                    .baseUrl(AlamatServer)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }
}
