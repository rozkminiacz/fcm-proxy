package com.rozkmin;

import com.rozkmin.controllers.ControllerModule;
import spark.Spark;

public class Main {

    public static void main(String[] args) {
        Spark.port(8080);
        App app = new App(ControllerModule.getInstance().getControllers());
        app.start();
    }
}
