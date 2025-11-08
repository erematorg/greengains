package com.neovisionaries.ws.client;

import androidx.camera.camera2.internal.C0118y;
import javax.net.ssl.SSLSocket;

public class HostnameUnverifiedException extends WebSocketException {
    private static final long serialVersionUID = 1;
    private final String mHostname;
    private final SSLSocket mSSLSocket;

    public HostnameUnverifiedException(SSLSocket sSLSocket, String str) {
        super(WebSocketError.HOSTNAME_UNVERIFIED, C0118y.g("The certificate of the peer", stringifyPrincipal(sSLSocket), " does not match the expected hostname (", str, ")"));
        this.mSSLSocket = sSLSocket;
        this.mHostname = str;
    }

    private static String stringifyPrincipal(SSLSocket sSLSocket) {
        try {
            String principal = sSLSocket.getSession().getPeerPrincipal().toString();
            return " (" + principal + ")";
        } catch (Exception unused) {
            return "";
        }
    }

    public String getHostname() {
        return this.mHostname;
    }

    public SSLSocket getSSLSocket() {
        return this.mSSLSocket;
    }
}
