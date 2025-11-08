package com.neovisionaries.ws.client;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Comparator;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

class SocketConnector {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Address mAddress;
    private final int mConnectionTimeout;
    private int mDualStackFallbackDelay;
    private DualStackMode mDualStackMode;
    private final String mHost;
    private final int mPort;
    private final ProxyHandshaker mProxyHandshaker;
    private final SSLSocketFactory mSSLSocketFactory;
    private final String[] mServerNames;
    private Socket mSocket;
    private final SocketFactory mSocketFactory;
    private boolean mVerifyHostname;

    public SocketConnector(SocketFactory socketFactory, Address address, int i3, String[] strArr) {
        this(socketFactory, address, i3, strArr, (ProxyHandshaker) null, (SSLSocketFactory) null, (String) null, 0);
    }

    private void connectSocket() throws WebSocketException {
        try {
            this.mSocket = new SocketInitiator(this.mSocketFactory, this.mAddress, this.mConnectionTimeout, this.mServerNames, this.mDualStackMode, this.mDualStackFallbackDelay).establish(resolveHostname());
        } catch (Exception e3) {
            throw new WebSocketException(WebSocketError.SOCKET_CONNECT_ERROR, String.format("Failed to connect to %s'%s': %s", new Object[]{this.mProxyHandshaker != null ? "the proxy " : "", this.mAddress, e3.getMessage()}), e3);
        }
    }

    private void doConnect() throws WebSocketException {
        boolean z2 = this.mProxyHandshaker != null;
        connectSocket();
        Socket socket = this.mSocket;
        if (socket instanceof SSLSocket) {
            verifyHostname((SSLSocket) socket, this.mAddress.getHostname());
        }
        if (z2) {
            handshake();
        }
    }

    private void handshake() throws WebSocketException {
        try {
            this.mProxyHandshaker.perform(this.mSocket);
            SSLSocketFactory sSLSocketFactory = this.mSSLSocketFactory;
            if (sSLSocketFactory != null) {
                try {
                    Socket createSocket = sSLSocketFactory.createSocket(this.mSocket, this.mHost, this.mPort, true);
                    this.mSocket = createSocket;
                    try {
                        ((SSLSocket) createSocket).startHandshake();
                        verifyHostname((SSLSocket) this.mSocket, this.mProxyHandshaker.getProxiedHostname());
                    } catch (IOException e3) {
                        throw new WebSocketException(WebSocketError.SSL_HANDSHAKE_ERROR, String.format("SSL handshake with the WebSocket endpoint (%s) failed: %s", new Object[]{this.mAddress, e3.getMessage()}), e3);
                    }
                } catch (IOException e4) {
                    throw new WebSocketException(WebSocketError.SOCKET_OVERLAY_ERROR, a.h(e4, new StringBuilder("Failed to overlay an existing socket: ")), e4);
                }
            }
        } catch (IOException e5) {
            throw new WebSocketException(WebSocketError.PROXY_HANDSHAKE_ERROR, String.format("Handshake with the proxy server (%s) failed: %s", new Object[]{this.mAddress, e5.getMessage()}), e5);
        }
    }

    private InetAddress[] resolveHostname() throws WebSocketException {
        InetAddress[] inetAddressArr;
        UnknownHostException e3 = null;
        try {
            inetAddressArr = InetAddress.getAllByName(this.mAddress.getHostname());
            try {
                Arrays.sort(inetAddressArr, new Comparator<InetAddress>() {
                    public int compare(InetAddress inetAddress, InetAddress inetAddress2) {
                        if (inetAddress.getClass() == inetAddress2.getClass()) {
                            return 0;
                        }
                        return inetAddress instanceof Inet6Address ? -1 : 1;
                    }
                });
            } catch (UnknownHostException e4) {
                e3 = e4;
            }
        } catch (UnknownHostException e5) {
            UnknownHostException unknownHostException = e5;
            inetAddressArr = null;
            e3 = unknownHostException;
        }
        if (inetAddressArr != null && inetAddressArr.length > 0) {
            return inetAddressArr;
        }
        if (e3 == null) {
            e3 = new UnknownHostException("No IP addresses found");
        }
        throw new WebSocketException(WebSocketError.SOCKET_CONNECT_ERROR, String.format("Failed to resolve hostname %s: %s", new Object[]{this.mAddress, e3.getMessage()}), e3);
    }

    private void verifyHostname(SSLSocket sSLSocket, String str) throws HostnameUnverifiedException {
        if (this.mVerifyHostname && !OkHostnameVerifier.INSTANCE.verify(str, sSLSocket.getSession())) {
            throw new HostnameUnverifiedException(sSLSocket, str);
        }
    }

    public void closeSilently() {
        Socket socket = this.mSocket;
        if (socket != null) {
            try {
                socket.close();
            } catch (Throwable unused) {
            }
        }
    }

    public Socket connect() throws WebSocketException {
        try {
            doConnect();
            return this.mSocket;
        } catch (WebSocketException e3) {
            Socket socket = this.mSocket;
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException unused) {
                }
            }
            throw e3;
        }
    }

    public Socket getConnectedSocket() throws WebSocketException {
        if (this.mSocket == null) {
            connectSocket();
        }
        return this.mSocket;
    }

    public int getConnectionTimeout() {
        return this.mConnectionTimeout;
    }

    public Socket getSocket() {
        return this.mSocket;
    }

    public SocketConnector setDualStackSettings(DualStackMode dualStackMode, int i3) {
        this.mDualStackMode = dualStackMode;
        this.mDualStackFallbackDelay = i3;
        return this;
    }

    public SocketConnector setVerifyHostname(boolean z2) {
        this.mVerifyHostname = z2;
        return this;
    }

    public SocketConnector(SocketFactory socketFactory, Address address, int i3, String[] strArr, ProxyHandshaker proxyHandshaker, SSLSocketFactory sSLSocketFactory, String str, int i4) {
        this.mDualStackMode = DualStackMode.BOTH;
        this.mDualStackFallbackDelay = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        this.mSocketFactory = socketFactory;
        this.mAddress = address;
        this.mConnectionTimeout = i3;
        this.mServerNames = strArr;
        this.mProxyHandshaker = proxyHandshaker;
        this.mSSLSocketFactory = sSLSocketFactory;
        this.mHost = str;
        this.mPort = i4;
    }
}
