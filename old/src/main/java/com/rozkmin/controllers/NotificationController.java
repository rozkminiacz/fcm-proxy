package com.rozkmin.controllers;

import com.rozkmin.database.MongoConnector;
import com.rozkmin.util.JsonHelper;
import spark.Spark;


/**
 * Created by michalik on 04.04.17
 * All rights reserved
 */
public class NotificationController extends Controller {
    public NotificationController(String pathRoot) {
        super(pathRoot);
    }

    @Override
    public void init() {
        Spark.get(pathRoot, ((request, response) -> retrieveNotifications()));
    }

    private Object retrieveNotifications() {
        return MongoConnector.getInstance()
                .getNotificationsObservable(10)
                .toList()
                .map(JsonHelper::toJson)
                .toBlocking()
                .last();
    }
}
