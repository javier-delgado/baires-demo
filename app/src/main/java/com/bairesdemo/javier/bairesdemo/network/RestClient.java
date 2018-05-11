package com.bairesdemo.javier.bairesdemo.network;

/**
 * Created by vortex on 31/1/17
 *
 *
 * */

import android.content.Context;
import android.util.Log;

import com.bairesdemo.javier.bairesdemo.BuildConfig;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestClient {
    private static final String BASE_URL = "https://api.github.com/";
    private static Api API_SERVICE;
    private static Retrofit RETROFIT;

    private RestClient() { }

    public static void initializeRestClient(final Context context) {
        if (RETROFIT != null){
            Log.w("RestClient", "El método initializeRestClient solo debe ser llamado una vez!");
            return;
        }

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.MINUTES)
                .connectTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES);





        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        OkHttpClient okHttpClient = builder.build();

        RETROFIT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API_SERVICE = RETROFIT.create(Api.class);
    }

    public static Api getInstance() {
        return API_SERVICE;
    }
    public static Retrofit getRetrofit() { return RETROFIT; }
}
