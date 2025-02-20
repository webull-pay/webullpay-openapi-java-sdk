package com.webullpay.openapi.example.config;

import com.webullpay.openapi.common.Region;

public final class Env {

    private Env() {
    }

    //<your_app_key>
    public static final String APP_KEY = "<your_app_key>";
    //<your_app_secret>
    public static final String APP_SECRET = "<your_app_secret>";
    public static final String REGION_ID = Region.us.name(); // your region
}
