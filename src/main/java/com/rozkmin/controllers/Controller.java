package com.rozkmin.controllers;

import spark.Spark;

public abstract class Controller {

    final String pathRoot;

    Controller(String pathRoot) {
        this.pathRoot = pathRoot;

        Spark.before((req, res) -> {
            res.type("application/json");
            System.out.println(req.host());
            System.out.println(req.ip());
            System.out.println(req.userAgent());
            System.out.println(req.headers());
        });
        Spark.after((req, res) -> {
            res.type("application/json");
        });
    }

    /**
     * The controller's opportunity to initialize its own routes.
     */
    public abstract void init();

}
