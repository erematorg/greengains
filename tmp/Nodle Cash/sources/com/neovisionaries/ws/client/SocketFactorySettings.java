package com.neovisionaries.ws.client;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

class SocketFactorySettings {
    private SSLContext mSSLContext;
    private SSLSocketFactory mSSLSocketFactory;
    private SocketFactory mSocketFactory;

    public SocketFactorySettings() {
    }

    public SSLContext getSSLContext() {
        return this.mSSLContext;
    }

    public SSLSocketFactory getSSLSocketFactory() {
        return this.mSSLSocketFactory;
    }

    public SocketFactory getSocketFactory() {
        return this.mSocketFactory;
    }

    public SocketFactory selectSocketFactory(boolean z2) {
        if (z2) {
            SSLContext sSLContext = this.mSSLContext;
            if (sSLContext != null) {
                return sSLContext.getSocketFactory();
            }
            SSLSocketFactory sSLSocketFactory = this.mSSLSocketFactory;
            return sSLSocketFactory != null ? sSLSocketFactory : SSLSocketFactory.getDefault();
        }
        SocketFactory socketFactory = this.mSocketFactory;
        return socketFactory != null ? socketFactory : SocketFactory.getDefault();
    }

    public void setSSLContext(SSLContext sSLContext) {
        this.mSSLContext = sSLContext;
    }

    public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.mSSLSocketFactory = sSLSocketFactory;
    }

    public void setSocketFactory(SocketFactory socketFactory) {
        this.mSocketFactory = socketFactory;
    }

    public SocketFactorySettings(SocketFactorySettings socketFactorySettings) {
        this.mSocketFactory = socketFactorySettings.mSocketFactory;
        this.mSSLSocketFactory = socketFactorySettings.mSSLSocketFactory;
        this.mSSLContext = socketFactorySettings.mSSLContext;
    }
}
