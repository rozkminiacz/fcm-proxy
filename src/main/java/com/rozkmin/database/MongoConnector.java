package com.rozkmin.database;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.rozkmin.notifications.FcmNotification;
import com.rozkmin.util.JsonHelper;
import rx.Observable;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

/**
 * Created by michalik on 04.04.17
 * All rights reserved
 */
public class MongoConnector {

    private static final String DATABASE_NAME = "NOTIFICATIONS";
    private static final String COLLECITON = "NOTIFICATIONS_COLLECTION";
    private static MongoConnector instance;
    private DB db;

    public static MongoConnector getInstance() {
        return instance == null ? new MongoConnector() : instance;
    }


    private MongoConnector() {
        instance = this;

        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        if (mongoClient != null) {
            db = mongoClient.getDB(DATABASE_NAME);
        }
        DBObject options = new BasicDBObject();

        if (db.getCollection(COLLECITON) == null) {
            db.createCollection(COLLECITON, options);
        }

        System.out.println("DB initialized " + db.getName());

        System.out.println(db.getCollectionNames());
    }

    public FcmNotification saveNotification(FcmNotification notification) {
        DBObject dbObject = JsonHelper.toDbObject(notification);
        db.getCollection(COLLECITON).save(dbObject);
        return ((FcmNotification) db.getCollection(COLLECITON).find(dbObject).curr());
    }

    @SuppressWarnings("unused")
    public Observable<List<FcmNotification>> getAllNotifications() {
        return Observable.from(db.getCollection(COLLECITON).find().toArray())
                .map(dbObject -> JsonHelper.fromDbObject(dbObject, FcmNotification.class))
                .map(o -> ((FcmNotification) o))
                .toList();
    }

    public Observable<FcmNotification> getNotificationsObservable() {
        return Observable.from(db.getCollection(COLLECITON).find().toArray())
                .map(dbObject -> JsonHelper.fromDbObject(dbObject, FcmNotification.class))
                .map(o -> ((FcmNotification) o));
    }

    @SuppressWarnings("unused")
    public Observable<Map<String, String>> getNotificationsObservable(int limit) {
        return Observable.from(db.getCollection(COLLECITON).find().toArray())
                .map(dbObject -> JsonHelper.fromDbObject(dbObject, FcmNotification.class))
                .map(o -> ((FcmNotification) o))
                .map(FcmNotification::getData);
    }

    @SuppressWarnings("unused")
    public Observable<FcmNotification> getNotificationsObservable(String topic) {
        return Observable.from(db.getCollection(COLLECITON).find().toArray())
                .map(dbObject -> JsonHelper.fromDbObject(dbObject, FcmNotification.class))
                .map(o -> ((FcmNotification) o))
                .filter(fcmNotification -> fcmNotification.getTo().contentEquals("/topics/" + topic));
    }
}
