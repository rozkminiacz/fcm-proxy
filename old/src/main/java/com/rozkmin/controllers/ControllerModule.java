package com.rozkmin.controllers;

import java.util.HashSet;
import java.util.Set;

public class ControllerModule {

    public static ControllerModule instance;

    Set<Controller> controllers;

    public static ControllerModule getInstance() {
        return instance == null ? new ControllerModule() : instance;
    }

    private ControllerModule() {
        instance = this;
        controllers = new HashSet<>();
        controllers.add(pushController());
        controllers.add(notificationController());
    }

    public Set<Controller> getControllers() {
        return controllers;
    }

    private Controller pushController() {
        return new PushSendController("/device");
    }

    private Controller notificationController() {
        return new NotificationController("/notifications");
    }
}
