package com.example.app;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static OkHttpClient client = new OkHttpClient();

    public static OkHttpClient.Builder getHttpClient() {
        return httpClient;
    }

    public static void setClient() {
        client = httpClient.build();
    }

    public static void setBuilder() {
        builder.client(client);
    }

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl("https://31eada7a17cb.ngrok.io/")
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit.Builder builder2;


    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass, String username, String password) {
        if (username != null && password != null) {
            final String credentials = username + ":" + password;
            final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), android.util.Base64.NO_WRAP);
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", basic)
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://2a906dcf364b.ngrok.io/")
                .client(client)
                .build();
        return retrofit.create(serviceClass);
    }

}


