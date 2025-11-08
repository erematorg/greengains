package com.google.firebase.crashlytics.internal.network;

public class HttpResponse {
    private final String body;
    private final int code;

    public HttpResponse(int i3, String str) {
        this.code = i3;
        this.body = str;
    }

    public String body() {
        return this.body;
    }

    public int code() {
        return this.code;
    }
}
