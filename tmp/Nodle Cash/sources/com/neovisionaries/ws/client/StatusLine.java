package com.neovisionaries.ws.client;

public class StatusLine {
    private final String mHttpVersion;
    private final String mReasonPhrase;
    private final int mStatusCode;
    private final String mString;

    public StatusLine(String str) {
        String[] split = str.split(" +", 3);
        if (split.length >= 2) {
            this.mHttpVersion = split[0];
            this.mStatusCode = Integer.parseInt(split[1]);
            this.mReasonPhrase = split.length == 3 ? split[2] : null;
            this.mString = str;
            return;
        }
        throw new IllegalArgumentException();
    }

    public String getHttpVersion() {
        return this.mHttpVersion;
    }

    public String getReasonPhrase() {
        return this.mReasonPhrase;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public String toString() {
        return this.mString;
    }
}
