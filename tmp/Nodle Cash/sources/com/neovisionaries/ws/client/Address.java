package com.neovisionaries.ws.client;

class Address {
    private final String mHost;
    private final int mPort;
    private transient String mString;

    public Address(String str, int i3) {
        this.mHost = str;
        this.mPort = i3;
    }

    public String getHostname() {
        return this.mHost;
    }

    public int getPort() {
        return this.mPort;
    }

    public String toString() {
        if (this.mString == null) {
            this.mString = String.format("%s:%d", new Object[]{this.mHost, Integer.valueOf(this.mPort)});
        }
        return this.mString;
    }
}
