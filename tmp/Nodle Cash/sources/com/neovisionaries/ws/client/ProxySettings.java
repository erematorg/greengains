package com.neovisionaries.ws.client;

import com.adjust.sdk.Constants;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public class ProxySettings {
    private final Map<String, List<String>> mHeaders;
    private String mHost;
    private String mId;
    private String mPassword;
    private int mPort;
    private boolean mSecure;
    private String[] mServerNames;
    private final SocketFactorySettings mSocketFactorySettings;
    private final WebSocketFactory mWebSocketFactory;

    public ProxySettings(WebSocketFactory webSocketFactory) {
        this.mWebSocketFactory = webSocketFactory;
        this.mHeaders = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        this.mSocketFactorySettings = new SocketFactorySettings();
        reset();
    }

    private void setByScheme(String str) {
        if ("http".equalsIgnoreCase(str)) {
            this.mSecure = false;
        } else if (Constants.SCHEME.equalsIgnoreCase(str)) {
            this.mSecure = true;
        }
    }

    private void setByUserInfo(String str) {
        String str2;
        String str3;
        if (str != null) {
            String[] split = str.split(":", 2);
            int length = split.length;
            if (length == 1) {
                str3 = split[0];
                str2 = null;
            } else if (length == 2) {
                str3 = split[0];
                str2 = split[1];
            } else {
                return;
            }
            if (str3.length() != 0) {
                this.mId = str3;
                this.mPassword = str2;
            }
        }
    }

    public ProxySettings addHeader(String str, String str2) {
        if (!(str == null || str.length() == 0)) {
            List list = this.mHeaders.get(str);
            if (list == null) {
                list = new ArrayList();
                this.mHeaders.put(str, list);
            }
            list.add(str2);
        }
        return this;
    }

    public Map<String, List<String>> getHeaders() {
        return this.mHeaders;
    }

    public String getHost() {
        return this.mHost;
    }

    public String getId() {
        return this.mId;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public int getPort() {
        return this.mPort;
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

    public WebSocketFactory getWebSocketFactory() {
        return this.mWebSocketFactory;
    }

    public boolean isSecure() {
        return this.mSecure;
    }

    public ProxySettings reset() {
        this.mSecure = false;
        this.mHost = null;
        this.mPort = -1;
        this.mId = null;
        this.mPassword = null;
        this.mHeaders.clear();
        this.mServerNames = null;
        return this;
    }

    public SocketFactory selectSocketFactory() {
        return this.mSocketFactorySettings.selectSocketFactory(this.mSecure);
    }

    public ProxySettings setCredentials(String str, String str2) {
        return setId(str).setPassword(str2);
    }

    public ProxySettings setHost(String str) {
        this.mHost = str;
        return this;
    }

    public ProxySettings setId(String str) {
        this.mId = str;
        return this;
    }

    public ProxySettings setPassword(String str) {
        this.mPassword = str;
        return this;
    }

    public ProxySettings setPort(int i3) {
        this.mPort = i3;
        return this;
    }

    public ProxySettings setSSLContext(SSLContext sSLContext) {
        this.mSocketFactorySettings.setSSLContext(sSLContext);
        return this;
    }

    public ProxySettings setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.mSocketFactorySettings.setSSLSocketFactory(sSLSocketFactory);
        return this;
    }

    public ProxySettings setSecure(boolean z2) {
        this.mSecure = z2;
        return this;
    }

    public ProxySettings setServer(String str) {
        return str == null ? this : setServer(URI.create(str));
    }

    public ProxySettings setServerName(String str) {
        return setServerNames(new String[]{str});
    }

    public ProxySettings setServerNames(String[] strArr) {
        this.mServerNames = strArr;
        return this;
    }

    public ProxySettings setSocketFactory(SocketFactory socketFactory) {
        this.mSocketFactorySettings.setSocketFactory(socketFactory);
        return this;
    }

    public ProxySettings setServer(URL url) {
        if (url == null) {
            return this;
        }
        try {
            return setServer(url.toURI());
        } catch (URISyntaxException e3) {
            throw new IllegalArgumentException(e3);
        }
    }

    public ProxySettings setServer(URI uri) {
        if (uri == null) {
            return this;
        }
        return setServer(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort());
    }

    public ProxySettings(WebSocketFactory webSocketFactory, ProxySettings proxySettings) {
        this(webSocketFactory);
        this.mHeaders.putAll(proxySettings.mHeaders);
        this.mSecure = proxySettings.mSecure;
        this.mHost = proxySettings.mHost;
        this.mPort = proxySettings.mPort;
        this.mId = proxySettings.mId;
        this.mPassword = proxySettings.mPassword;
        String[] strArr = proxySettings.mServerNames;
        if (strArr != null) {
            String[] strArr2 = new String[strArr.length];
            this.mServerNames = strArr2;
            System.arraycopy(proxySettings.mServerNames, 0, strArr2, 0, strArr2.length);
        }
    }

    private ProxySettings setServer(String str, String str2, String str3, int i3) {
        setByScheme(str);
        setByUserInfo(str2);
        this.mHost = str3;
        this.mPort = i3;
        return this;
    }
}
