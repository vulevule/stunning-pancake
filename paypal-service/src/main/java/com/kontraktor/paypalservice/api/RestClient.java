package com.kontraktor.paypalservice.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RestClient {
	private static final String BASE_URL = "https://api.sandbox.paypal.com/";
	private static Retrofit.Builder builder =
            new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();
    
}
