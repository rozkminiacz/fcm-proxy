package com.rozkmin;

import com.rozkmin.controllers.Controller;
import spark.Spark;

import java.util.Set;

class App {

    private final Set<Controller> controllers;

    App(Set<Controller> controllers) {
        this.controllers = controllers;
    }

    void start() {
        for (Controller c : controllers) {
            c.init();
        }
    }
}
