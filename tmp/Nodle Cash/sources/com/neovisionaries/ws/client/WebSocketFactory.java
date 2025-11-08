package com.neovisionaries.ws.client;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.adjust.sdk.Constants;
import com.appsamurai.storyly.exoplayer2.common.a;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public class WebSocketFactory {
    private int mConnectionTimeout;
    private int mDualStackFallbackDelay;
    private DualStackMode mDualStackMode;
    private final ProxySettings mProxySettings;
    private String[] mServerNames;
    private final SocketFactorySettings mSocketFactorySettings;
    private boolean mVerifyHostname;

    public WebSocketFactory() {
        this.mDualStackMode = DualStackMode.BOTH;
        this.mDualStackFallbackDelay = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        this.mVerifyHostname = true;
        this.mSocketFactorySettings = new SocketFactorySettings();
        this.mProxySettings = new ProxySettings(this);
    }

    private SocketConnector createDirectRawSocket(String str, int i3, boolean z2, int i4) {
        return new SocketConnector(this.mSocketFactorySettings.selectSocketFactory(z2), new Address(str, i3), i4, this.mServerNames).setDualStackSettings(this.mDualStackMode, this.mDualStackFallbackDelay).setVerifyHostname(this.mVerifyHostname);
    }

    private SocketConnector createProxiedRawSocket(String str, int i3, boolean z2, int i4) {
        return new SocketConnector(this.mProxySettings.selectSocketFactory(), new Address(this.mProxySettings.getHost(), determinePort(this.mProxySettings.getPort(), this.mProxySettings.isSecure())), i4, this.mProxySettings.getServerNames(), new ProxyHandshaker(str, i3, this.mProxySettings), z2 ? (SSLSocketFactory) this.mSocketFactorySettings.selectSocketFactory(z2) : null, str, i3).setDualStackSettings(this.mDualStackMode, this.mDualStackFallbackDelay).setVerifyHostname(this.mVerifyHostname);
    }

    private SocketConnector createRawSocket(String str, int i3, boolean z2, int i4) throws IOException {
        int determinePort = determinePort(i3, z2);
        return this.mProxySettings.getHost() != null ? createProxiedRawSocket(str, determinePort, z2, i4) : createDirectRawSocket(str, determinePort, z2, i4);
    }

    private WebSocket createWebSocket(boolean z2, String str, String str2, int i3, String str3, String str4, SocketConnector socketConnector) {
        if (i3 >= 0) {
            str2 = a.b(i3, str2, ":");
        }
        String str5 = str2;
        if (str4 != null) {
            str3 = android.support.v4.media.session.a.n(str3, "?", str4);
        }
        return new WebSocket(this, z2, str, str5, str3, socketConnector);
    }

    private static String determinePath(String str) {
        return (str == null || str.length() == 0) ? "/" : str.startsWith("/") ? str : "/".concat(str);
    }

    private static int determinePort(int i3, boolean z2) {
        return i3 >= 0 ? i3 : z2 ? 443 : 80;
    }

    private static boolean isSecureConnectionRequired(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("The scheme part is empty.");
        } else if ("wss".equalsIgnoreCase(str) || Constants.SCHEME.equalsIgnoreCase(str)) {
            return true;
        } else {
            if ("ws".equalsIgnoreCase(str) || "http".equalsIgnoreCase(str)) {
                return false;
            }
            throw new IllegalArgumentException("Bad scheme: ".concat(str));
        }
    }

    public WebSocket createSocket(String str) throws IOException {
        return createSocket(str, getConnectionTimeout());
    }

    public int getConnectionTimeout() {
        return this.mConnectionTimeout;
    }

    public int getDualStackFallbackDelay() {
        return this.mDualStackFallbackDelay;
    }

    public DualStackMode getDualStackMode() {
        return this.mDualStackMode;
    }

    public ProxySettings getProxySettings() {
        return this.mProxySettings;
    }

    public SSLContext getSSLContext() {
        return this.mSocketFactorySettings.getSSLContext();
    }

    public SSLSocketFactory getSSLSocketFactory() {
        return this.mSocketFactorySettings.getSSLSocketFactory();
    }

    public String[] getServerNames() {
        return this.mServerNames;
    }

    public SocketFactory getSocketFactory() {
        return this.mSocketFactorySettings.getSocketFactory();
    }

    public boolean getVerifyHostname() {
        return this.mVerifyHostname;
    }

    public WebSocketFactory setConnectionTimeout(int i3) {
        if (i3 >= 0) {
            this.mConnectionTimeout = i3;
            return this;
        }
        throw new IllegalArgumentException("timeout value cannot be negative.");
    }

    public WebSocketFactory setDualStackFallbackDelay(int i3) {
        if (i3 >= 0) {
            this.mDualStackFallbackDelay = i3;
            return this;
        }
        throw new IllegalArgumentException("delay value cannot be negative.");
    }

    public WebSocketFactory setDualStackMode(DualStackMode dualStackMode) {
        this.mDualStackMode = dualStackMode;
        return this;
    }

    public WebSocketFactory setSSLContext(SSLContext sSLContext) {
        this.mSocketFactorySettings.setSSLContext(sSLContext);
        return this;
    }

    public WebSocketFactory setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.mSocketFactorySettings.setSSLSocketFactory(sSLSocketFactory);
        return this;
    }

    public WebSocketFactory setServerName(String str) {
        return setServerNames(new String[]{str});
    }

    public WebSocketFactory setServerNames(String[] strArr) {
        this.mServerNames = strArr;
        return this;
    }

    public WebSocketFactory setSocketFactory(SocketFactory socketFactory) {
        this.mSocketFactorySettings.setSocketFactory(socketFactory);
        return this;
    }

    public WebSocketFactory setVerifyHostname(boolean z2) {
        this.mVerifyHostname = z2;
        return this;
    }

    public WebSocket createSocket(String str, int i3) throws IOException {
        if (str == null) {
            throw new IllegalArgumentException("The given URI is null.");
        } else if (i3 >= 0) {
            return createSocket(URI.create(str), i3);
        } else {
            throw new IllegalArgumentException("The given timeout value is negative.");
        }
    }

    public WebSocket createSocket(URL url) throws IOException {
        return createSocket(url, getConnectionTimeout());
    }

    public WebSocket createSocket(URL url, int i3) throws IOException {
        if (url == null) {
            throw new IllegalArgumentException("The given URL is null.");
        } else if (i3 >= 0) {
            try {
                return createSocket(url.toURI(), i3);
            } catch (URISyntaxException unused) {
                throw new IllegalArgumentException("Failed to convert the given URL into a URI.");
            }
        } else {
            throw new IllegalArgumentException("The given timeout value is negative.");
        }
    }

    public WebSocketFactory(WebSocketFactory webSocketFactory) {
        this.mDualStackMode = DualStackMode.BOTH;
        this.mDualStackFallbackDelay = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        this.mVerifyHostname = true;
        if (webSocketFactory != null) {
            this.mSocketFactorySettings = new SocketFactorySettings(webSocketFactory.mSocketFactorySettings);
            this.mProxySettings = new ProxySettings(this, webSocketFactory.mProxySettings);
            this.mConnectionTimeout = webSocketFactory.mConnectionTimeout;
            this.mDualStackMode = webSocketFactory.mDualStackMode;
            this.mDualStackFallbackDelay = webSocketFactory.mDualStackFallbackDelay;
            this.mVerifyHostname = webSocketFactory.mVerifyHostname;
            String[] strArr = webSocketFactory.mServerNames;
            if (strArr != null) {
                String[] strArr2 = new String[strArr.length];
                this.mServerNames = strArr2;
                System.arraycopy(webSocketFactory.mServerNames, 0, strArr2, 0, strArr2.length);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("The given WebSocketFactory is null");
    }

    public WebSocket createSocket(URI uri) throws IOException {
        return createSocket(uri, getConnectionTimeout());
    }

    public WebSocket createSocket(URI uri, int i3) throws IOException {
        if (uri == null) {
            throw new IllegalArgumentException("The given URI is null.");
        } else if (i3 >= 0) {
            return createSocket(uri.getScheme(), uri.getUserInfo(), Misc.extractHost(uri), uri.getPort(), uri.getRawPath(), uri.getRawQuery(), i3);
        } else {
            throw new IllegalArgumentException("The given timeout value is negative.");
        }
    }

    private WebSocket createSocket(String str, String str2, String str3, int i3, String str4, String str5, int i4) throws IOException {
        boolean isSecureConnectionRequired = isSecureConnectionRequired(str);
        if (str3 == null || str3.length() == 0) {
            throw new IllegalArgumentException("The host part is empty.");
        }
        return createWebSocket(isSecureConnectionRequired, str2, str3, i3, determinePath(str4), str5, createRawSocket(str3, i3, isSecureConnectionRequired, i4));
    }
}
