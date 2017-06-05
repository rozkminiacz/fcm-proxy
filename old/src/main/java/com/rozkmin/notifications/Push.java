package com.rozkmin.notifications;

import com.google.gson.JsonObject;
import com.rozkmin.database.MongoConnector;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.joda.time.DateTime;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by michalik on 04.04.17
 * All rights reserved
 */
public class Push {

    private static final String BASE_URL = "https://fcm.googleapis.com";

    private Interceptor interceptor = chain -> {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder()
                .addHeader("Content-Type", "application/json");
        return chain.proceed(requestBuilder.build());
    };

    private OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
            .addInterceptor(interceptor);

    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(clientBuilder.build())
            .baseUrl(BASE_URL)
            .build();
    private String headers;

    interface FcmApi {

        @POST("/fcm/send")
        Observable<JsonObject> postNotification(@Header("Authorization") String key, @Body FcmNotification notification);
    }

    public Observable<JsonObject> push(String headers, FcmNotification fcmNotification) {
        DateTime dateTime = DateTime.now();
        this.headers = headers;
        fcmNotification.setCreatedAt(dateTime.toDateTimeISO().toString());
        return retrofit.create(FcmApi.class)
                .postNotification("key="+headers, fcmNotification)
                .doOnNext(System.out::println);
    }
}
