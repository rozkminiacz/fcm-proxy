package com.rozkmin;

import com.rozkmin.controllers.ControllerModule;

public class Main {

    public static void main(String[] args) {
//        Spark.port(81);
        App app = new App(ControllerModule.getInstance().getControllers());
        app.start();
    }
}