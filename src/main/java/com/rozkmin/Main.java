package com.rozkmin;

import com.rozkmin.controllers.ControllerModule;

public class Main {

    public static void main(String[] args) {
        App app = new App(ControllerModule.getInstance().getControllers());
        app.start();
    }
}