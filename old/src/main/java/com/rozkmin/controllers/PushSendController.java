package com.rozkmin.controllers;

import com.google.gson.Gson;
import com.rozkmin.notifications.FcmNotification;
import com.rozkmin.notifications.Push;
import spark.Request;
import spark.Response;
import spark.Spark;

/**
 * Created by michalik on 04.04.17
 * All rights reserved
 */
public class PushSendController extends Controller {

    public PushSendController(String pathRoot) {
        super(pathRoot);
    }


    @Override
    public void init() {
        Spark.post(pathRoot, (request, response) -> {
            Gson gson = new Gson();

            return new Push().push(request.headers("fcm-token"), gson.fromJson(request.body(), FcmNotification.class))
                    .toBlocking()
                    .first();
        });
    }


    private Response body(Request request, Response response) {
        return response;
    }
}
