package com.rozkmin.util;

/**
 * Created by michalik on 04.04.17
 * All rights reserved
 */

import com.google.gson.GsonBuilder;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class JsonHelper {
    public static String toJson(Object o) {
        return new GsonBuilder()
                .create()
                .toJson(o, o.getClass());
    }

    public static Object fromJson(String jsonBody, Class clazz) {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .fromJson(jsonBody, clazz);
    }

    public static DBObject toDbObject(Object o) {
        return (DBObject) JSON.parse(toJson(o));
    }

    public static Object fromDbObject(DBObject o, Class clazz) {
        return fromJson(o.toString(), clazz);
    }
}

