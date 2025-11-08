package com.neovisionaries.ws.client;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class WebSocket {
    private static final long DEFAULT_CLOSE_DELAY = 10000;
    private List<WebSocketExtension> mAgreedExtensions;
    private String mAgreedProtocol;
    private boolean mAutoFlush = true;
    private WebSocketFrame mClientCloseFrame;
    private boolean mDirectTextMessage;
    private boolean mExtended;
    private int mFrameQueueSize;
    private HandshakeBuilder mHandshakeBuilder;
    private WebSocketInputStream mInput;
    private final ListenerManager mListenerManager;
    private int mMaxPayloadSize;
    private boolean mMissingCloseFrameAllowed = true;
    private boolean mOnConnectedCalled;
    private Object mOnConnectedCalledLock = new Object();
    private WebSocketOutputStream mOutput;
    private PerMessageCompressionExtension mPerMessageCompressionExtension;
    private final PingSender mPingSender;
    private final PongSender mPongSender;
    private ReadingThread mReadingThread;
    private boolean mReadingThreadFinished;
    private boolean mReadingThreadStarted;
    private WebSocketFrame mServerCloseFrame;
    private Map<String, List<String>> mServerHeaders;
    private final SocketConnector mSocketConnector;
    private final StateManager mStateManager;
    private final Object mThreadsLock = new Object();
    private final WebSocketFactory mWebSocketFactory;
    private WritingThread mWritingThread;
    private boolean mWritingThreadFinished;
    private boolean mWritingThreadStarted;

    /* renamed from: com.neovisionaries.ws.client.WebSocket$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$neovisionaries$ws$client$WebSocketState;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.neovisionaries.ws.client.WebSocketState[] r0 = com.neovisionaries.ws.client.WebSocketState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$neovisionaries$ws$client$WebSocketState = r0
                com.neovisionaries.ws.client.WebSocketState r1 = com.neovisionaries.ws.client.WebSocketState.CREATED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$neovisionaries$ws$client$WebSocketState     // Catch:{ NoSuchFieldError -> 0x001d }
                com.neovisionaries.ws.client.WebSocketState r1 = com.neovisionaries.ws.client.WebSocketState.OPEN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.WebSocket.AnonymousClass1.<clinit>():void");
        }
    }

    public WebSocket(WebSocketFactory webSocketFactory, boolean z2, String str, String str2, String str3, SocketConnector socketConnector) {
        this.mWebSocketFactory = webSocketFactory;
        this.mSocketConnector = socketConnector;
        this.mStateManager = new StateManager();
        this.mHandshakeBuilder = new HandshakeBuilder(z2, str, str2, str3);
        this.mListenerManager = new ListenerManager(this);
        this.mPingSender = new PingSender(this, new CounterPayloadGenerator());
        this.mPongSender = new PongSender(this, new CounterPayloadGenerator());
    }

    private void callOnConnectedIfNotYet() {
        synchronized (this.mOnConnectedCalledLock) {
            try {
                if (!this.mOnConnectedCalled) {
                    this.mOnConnectedCalled = true;
                    this.mListenerManager.callOnConnected(this.mServerHeaders);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    private void changeStateOnConnect() throws WebSocketException {
        WebSocketState webSocketState;
        synchronized (this.mStateManager) {
            if (this.mStateManager.getState() == WebSocketState.CREATED) {
                StateManager stateManager = this.mStateManager;
                webSocketState = WebSocketState.CONNECTING;
                stateManager.setState(webSocketState);
            } else {
                throw new WebSocketException(WebSocketError.NOT_IN_CREATED_STATE, "The current state of the WebSocket is not CREATED.");
            }
        }
        this.mListenerManager.callOnStateChanged(webSocketState);
    }

    private PerMessageCompressionExtension findAgreedPerMessageCompressionExtension() {
        List<WebSocketExtension> list = this.mAgreedExtensions;
        if (list == null) {
            return null;
        }
        for (WebSocketExtension next : list) {
            if (next instanceof PerMessageCompressionExtension) {
                return (PerMessageCompressionExtension) next;
            }
        }
        return null;
    }

    private void finishAsynchronously() {
        FinishThread finishThread = new FinishThread(this);
        finishThread.callOnThreadCreated();
        finishThread.start();
    }

    private static String generateWebSocketKey() {
        byte[] bArr = new byte[16];
        Misc.nextBytes(bArr);
        return Base64.encode(bArr);
    }

    private boolean isInState(WebSocketState webSocketState) {
        boolean z2;
        synchronized (this.mStateManager) {
            z2 = this.mStateManager.getState() == webSocketState;
        }
        return z2;
    }

    private void onThreadsFinished() {
        finish();
    }

    private void onThreadsStarted() {
        this.mPingSender.start();
        this.mPongSender.start();
    }

    private WebSocketInputStream openInputStream(Socket socket) throws WebSocketException {
        try {
            return new WebSocketInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e3) {
            throw new WebSocketException(WebSocketError.SOCKET_INPUT_STREAM_FAILURE, a.h(e3, new StringBuilder("Failed to get the input stream of the raw socket: ")), e3);
        }
    }

    private WebSocketOutputStream openOutputStream(Socket socket) throws WebSocketException {
        try {
            return new WebSocketOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        } catch (IOException e3) {
            throw new WebSocketException(WebSocketError.SOCKET_OUTPUT_STREAM_FAILURE, a.h(e3, new StringBuilder("Failed to get the output stream from the raw socket: ")), e3);
        }
    }

    private Map<String, List<String>> readHandshake(WebSocketInputStream webSocketInputStream, String str) throws WebSocketException {
        return new HandshakeReader(this).readHandshake(webSocketInputStream, str);
    }

    private Map<String, List<String>> shakeHands(Socket socket) throws WebSocketException {
        WebSocketInputStream openInputStream = openInputStream(socket);
        WebSocketOutputStream openOutputStream = openOutputStream(socket);
        String generateWebSocketKey = generateWebSocketKey();
        writeHandshake(openOutputStream, generateWebSocketKey);
        Map<String, List<String>> readHandshake = readHandshake(openInputStream, generateWebSocketKey);
        this.mInput = openInputStream;
        this.mOutput = openOutputStream;
        return readHandshake;
    }

    private List<WebSocketFrame> splitIfNecessary(WebSocketFrame webSocketFrame) {
        return WebSocketFrame.splitIfNecessary(webSocketFrame, this.mMaxPayloadSize, this.mPerMessageCompressionExtension);
    }

    private void startThreads() {
        ReadingThread readingThread = new ReadingThread(this);
        WritingThread writingThread = new WritingThread(this);
        synchronized (this.mThreadsLock) {
            this.mReadingThread = readingThread;
            this.mWritingThread = writingThread;
        }
        readingThread.callOnThreadCreated();
        writingThread.callOnThreadCreated();
        readingThread.start();
        writingThread.start();
    }

    private void stopThreads(long j2) {
        ReadingThread readingThread;
        WritingThread writingThread;
        synchronized (this.mThreadsLock) {
            readingThread = this.mReadingThread;
            writingThread = this.mWritingThread;
            this.mReadingThread = null;
            this.mWritingThread = null;
        }
        if (readingThread != null) {
            readingThread.requestStop(j2);
        }
        if (writingThread != null) {
            writingThread.requestStop();
        }
    }

    private void writeHandshake(WebSocketOutputStream webSocketOutputStream, String str) throws WebSocketException {
        this.mHandshakeBuilder.setKey(str);
        String buildRequestLine = this.mHandshakeBuilder.buildRequestLine();
        List<String[]> buildHeaders = this.mHandshakeBuilder.buildHeaders();
        String build = HandshakeBuilder.build(buildRequestLine, buildHeaders);
        this.mListenerManager.callOnSendingHandshake(buildRequestLine, buildHeaders);
        try {
            webSocketOutputStream.write(build);
            webSocketOutputStream.flush();
        } catch (IOException e3) {
            throw new WebSocketException(WebSocketError.OPENING_HAHDSHAKE_REQUEST_FAILURE, a.h(e3, new StringBuilder("Failed to send an opening handshake request to the server: ")), e3);
        }
    }

    public WebSocket addExtension(WebSocketExtension webSocketExtension) {
        this.mHandshakeBuilder.addExtension(webSocketExtension);
        return this;
    }

    public WebSocket addHeader(String str, String str2) {
        this.mHandshakeBuilder.addHeader(str, str2);
        return this;
    }

    public WebSocket addListener(WebSocketListener webSocketListener) {
        this.mListenerManager.addListener(webSocketListener);
        return this;
    }

    public WebSocket addListeners(List<WebSocketListener> list) {
        this.mListenerManager.addListeners(list);
        return this;
    }

    public WebSocket addProtocol(String str) {
        this.mHandshakeBuilder.addProtocol(str);
        return this;
    }

    public WebSocket clearExtensions() {
        this.mHandshakeBuilder.clearExtensions();
        return this;
    }

    public WebSocket clearHeaders() {
        this.mHandshakeBuilder.clearHeaders();
        return this;
    }

    public WebSocket clearListeners() {
        this.mListenerManager.clearListeners();
        return this;
    }

    public WebSocket clearProtocols() {
        this.mHandshakeBuilder.clearProtocols();
        return this;
    }

    public WebSocket clearUserInfo() {
        this.mHandshakeBuilder.clearUserInfo();
        return this;
    }

    public WebSocket connect() throws WebSocketException {
        changeStateOnConnect();
        try {
            this.mServerHeaders = shakeHands(this.mSocketConnector.connect());
            this.mPerMessageCompressionExtension = findAgreedPerMessageCompressionExtension();
            StateManager stateManager = this.mStateManager;
            WebSocketState webSocketState = WebSocketState.OPEN;
            stateManager.setState(webSocketState);
            this.mListenerManager.callOnStateChanged(webSocketState);
            startThreads();
            return this;
        } catch (WebSocketException e3) {
            this.mSocketConnector.closeSilently();
            StateManager stateManager2 = this.mStateManager;
            WebSocketState webSocketState2 = WebSocketState.CLOSED;
            stateManager2.setState(webSocketState2);
            this.mListenerManager.callOnStateChanged(webSocketState2);
            throw e3;
        }
    }

    public WebSocket connectAsynchronously() {
        ConnectThread connectThread = new ConnectThread(this);
        ListenerManager listenerManager = this.mListenerManager;
        if (listenerManager != null) {
            listenerManager.callOnThreadCreated(ThreadType.CONNECT_THREAD, connectThread);
        }
        connectThread.start();
        return this;
    }

    public Callable<WebSocket> connectable() {
        return new Connectable(this);
    }

    public WebSocket disconnect() {
        return disconnect(1000, (String) null);
    }

    public void finalize() throws Throwable {
        if (isInState(WebSocketState.CREATED)) {
            finish();
        }
        super.finalize();
    }

    public void finish() {
        WebSocketState webSocketState;
        this.mPingSender.stop();
        this.mPongSender.stop();
        Socket socket = this.mSocketConnector.getSocket();
        if (socket != null) {
            try {
                socket.close();
            } catch (Throwable unused) {
            }
        }
        synchronized (this.mStateManager) {
            StateManager stateManager = this.mStateManager;
            webSocketState = WebSocketState.CLOSED;
            stateManager.setState(webSocketState);
        }
        this.mListenerManager.callOnStateChanged(webSocketState);
        this.mListenerManager.callOnDisconnected(this.mServerCloseFrame, this.mClientCloseFrame, this.mStateManager.getClosedByServer());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0016, code lost:
        r0 = r3.mWritingThread;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0018, code lost:
        if (r0 == null) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001a, code lost:
        r0.queueFlush();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001d, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.neovisionaries.ws.client.WebSocket flush() {
        /*
            r3 = this;
            com.neovisionaries.ws.client.StateManager r0 = r3.mStateManager
            monitor-enter(r0)
            com.neovisionaries.ws.client.StateManager r1 = r3.mStateManager     // Catch:{ all -> 0x0013 }
            com.neovisionaries.ws.client.WebSocketState r1 = r1.getState()     // Catch:{ all -> 0x0013 }
            com.neovisionaries.ws.client.WebSocketState r2 = com.neovisionaries.ws.client.WebSocketState.OPEN     // Catch:{ all -> 0x0013 }
            if (r1 == r2) goto L_0x0015
            com.neovisionaries.ws.client.WebSocketState r2 = com.neovisionaries.ws.client.WebSocketState.CLOSING     // Catch:{ all -> 0x0013 }
            if (r1 == r2) goto L_0x0015
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            return r3
        L_0x0013:
            r3 = move-exception
            goto L_0x001e
        L_0x0015:
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            com.neovisionaries.ws.client.WritingThread r0 = r3.mWritingThread
            if (r0 == 0) goto L_0x001d
            r0.queueFlush()
        L_0x001d:
            return r3
        L_0x001e:
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.WebSocket.flush():com.neovisionaries.ws.client.WebSocket");
    }

    public List<WebSocketExtension> getAgreedExtensions() {
        return this.mAgreedExtensions;
    }

    public String getAgreedProtocol() {
        return this.mAgreedProtocol;
    }

    public Socket getConnectedSocket() throws WebSocketException {
        return this.mSocketConnector.getConnectedSocket();
    }

    public int getFrameQueueSize() {
        return this.mFrameQueueSize;
    }

    public HandshakeBuilder getHandshakeBuilder() {
        return this.mHandshakeBuilder;
    }

    public WebSocketInputStream getInput() {
        return this.mInput;
    }

    public ListenerManager getListenerManager() {
        return this.mListenerManager;
    }

    public int getMaxPayloadSize() {
        return this.mMaxPayloadSize;
    }

    public WebSocketOutputStream getOutput() {
        return this.mOutput;
    }

    public PerMessageCompressionExtension getPerMessageCompressionExtension() {
        return this.mPerMessageCompressionExtension;
    }

    public long getPingInterval() {
        return this.mPingSender.getInterval();
    }

    public PayloadGenerator getPingPayloadGenerator() {
        return this.mPingSender.getPayloadGenerator();
    }

    public String getPingSenderName() {
        return this.mPingSender.getTimerName();
    }

    public long getPongInterval() {
        return this.mPongSender.getInterval();
    }

    public PayloadGenerator getPongPayloadGenerator() {
        return this.mPongSender.getPayloadGenerator();
    }

    public String getPongSenderName() {
        return this.mPongSender.getTimerName();
    }

    public Socket getSocket() {
        return this.mSocketConnector.getSocket();
    }

    public WebSocketState getState() {
        WebSocketState state;
        synchronized (this.mStateManager) {
            state = this.mStateManager.getState();
        }
        return state;
    }

    public StateManager getStateManager() {
        return this.mStateManager;
    }

    public URI getURI() {
        return this.mHandshakeBuilder.getURI();
    }

    public boolean isAutoFlush() {
        return this.mAutoFlush;
    }

    public boolean isDirectTextMessage() {
        return this.mDirectTextMessage;
    }

    public boolean isExtended() {
        return this.mExtended;
    }

    public boolean isMissingCloseFrameAllowed() {
        return this.mMissingCloseFrameAllowed;
    }

    public boolean isOpen() {
        return isInState(WebSocketState.OPEN);
    }

    public void onReadingThreadFinished(WebSocketFrame webSocketFrame) {
        synchronized (this.mThreadsLock) {
            try {
                this.mReadingThreadFinished = true;
                this.mServerCloseFrame = webSocketFrame;
                if (this.mWritingThreadFinished) {
                    onThreadsFinished();
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public void onReadingThreadStarted() {
        boolean z2;
        synchronized (this.mThreadsLock) {
            this.mReadingThreadStarted = true;
            z2 = this.mWritingThreadStarted;
        }
        callOnConnectedIfNotYet();
        if (z2) {
            onThreadsStarted();
        }
    }

    public void onWritingThreadFinished(WebSocketFrame webSocketFrame) {
        synchronized (this.mThreadsLock) {
            try {
                this.mWritingThreadFinished = true;
                this.mClientCloseFrame = webSocketFrame;
                if (this.mReadingThreadFinished) {
                    onThreadsFinished();
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public void onWritingThreadStarted() {
        boolean z2;
        synchronized (this.mThreadsLock) {
            this.mWritingThreadStarted = true;
            z2 = this.mReadingThreadStarted;
        }
        callOnConnectedIfNotYet();
        if (z2) {
            onThreadsStarted();
        }
    }

    public WebSocket recreate() throws IOException {
        return recreate(this.mSocketConnector.getConnectionTimeout());
    }

    public WebSocket removeExtension(WebSocketExtension webSocketExtension) {
        this.mHandshakeBuilder.removeExtension(webSocketExtension);
        return this;
    }

    public WebSocket removeExtensions(String str) {
        this.mHandshakeBuilder.removeExtensions(str);
        return this;
    }

    public WebSocket removeHeaders(String str) {
        this.mHandshakeBuilder.removeHeaders(str);
        return this;
    }

    public WebSocket removeListener(WebSocketListener webSocketListener) {
        this.mListenerManager.removeListener(webSocketListener);
        return this;
    }

    public WebSocket removeListeners(List<WebSocketListener> list) {
        this.mListenerManager.removeListeners(list);
        return this;
    }

    public WebSocket removeProtocol(String str) {
        this.mHandshakeBuilder.removeProtocol(str);
        return this;
    }

    public WebSocket sendBinary(byte[] bArr) {
        return sendFrame(WebSocketFrame.createBinaryFrame(bArr));
    }

    public WebSocket sendClose() {
        return sendFrame(WebSocketFrame.createCloseFrame());
    }

    public WebSocket sendContinuation() {
        return sendFrame(WebSocketFrame.createContinuationFrame());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0019, code lost:
        r0 = r3.mWritingThread;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001b, code lost:
        if (r0 != null) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001d, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001e, code lost:
        r1 = splitIfNecessary(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0022, code lost:
        if (r1 != null) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0024, code lost:
        r0.queueFrame(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0028, code lost:
        r4 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0030, code lost:
        if (r4.hasNext() == false) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0032, code lost:
        r0.queueFrame(r4.next());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003c, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.neovisionaries.ws.client.WebSocket sendFrame(com.neovisionaries.ws.client.WebSocketFrame r4) {
        /*
            r3 = this;
            if (r4 != 0) goto L_0x0003
            return r3
        L_0x0003:
            com.neovisionaries.ws.client.StateManager r0 = r3.mStateManager
            monitor-enter(r0)
            com.neovisionaries.ws.client.StateManager r1 = r3.mStateManager     // Catch:{ all -> 0x0016 }
            com.neovisionaries.ws.client.WebSocketState r1 = r1.getState()     // Catch:{ all -> 0x0016 }
            com.neovisionaries.ws.client.WebSocketState r2 = com.neovisionaries.ws.client.WebSocketState.OPEN     // Catch:{ all -> 0x0016 }
            if (r1 == r2) goto L_0x0018
            com.neovisionaries.ws.client.WebSocketState r2 = com.neovisionaries.ws.client.WebSocketState.CLOSING     // Catch:{ all -> 0x0016 }
            if (r1 == r2) goto L_0x0018
            monitor-exit(r0)     // Catch:{ all -> 0x0016 }
            return r3
        L_0x0016:
            r3 = move-exception
            goto L_0x003d
        L_0x0018:
            monitor-exit(r0)     // Catch:{ all -> 0x0016 }
            com.neovisionaries.ws.client.WritingThread r0 = r3.mWritingThread
            if (r0 != 0) goto L_0x001e
            return r3
        L_0x001e:
            java.util.List r1 = r3.splitIfNecessary(r4)
            if (r1 != 0) goto L_0x0028
            r0.queueFrame(r4)
            goto L_0x003c
        L_0x0028:
            java.util.Iterator r4 = r1.iterator()
        L_0x002c:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L_0x003c
            java.lang.Object r1 = r4.next()
            com.neovisionaries.ws.client.WebSocketFrame r1 = (com.neovisionaries.ws.client.WebSocketFrame) r1
            r0.queueFrame(r1)
            goto L_0x002c
        L_0x003c:
            return r3
        L_0x003d:
            monitor-exit(r0)     // Catch:{ all -> 0x0016 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.WebSocket.sendFrame(com.neovisionaries.ws.client.WebSocketFrame):com.neovisionaries.ws.client.WebSocket");
    }

    public WebSocket sendPing() {
        return sendFrame(WebSocketFrame.createPingFrame());
    }

    public WebSocket sendPong() {
        return sendFrame(WebSocketFrame.createPongFrame());
    }

    public WebSocket sendText(String str) {
        return sendFrame(WebSocketFrame.createTextFrame(str));
    }

    public void setAgreedExtensions(List<WebSocketExtension> list) {
        this.mAgreedExtensions = list;
    }

    public void setAgreedProtocol(String str) {
        this.mAgreedProtocol = str;
    }

    public WebSocket setAutoFlush(boolean z2) {
        this.mAutoFlush = z2;
        return this;
    }

    public WebSocket setDirectTextMessage(boolean z2) {
        this.mDirectTextMessage = z2;
        return this;
    }

    public WebSocket setExtended(boolean z2) {
        this.mExtended = z2;
        return this;
    }

    public WebSocket setFrameQueueSize(int i3) throws IllegalArgumentException {
        if (i3 >= 0) {
            this.mFrameQueueSize = i3;
            return this;
        }
        throw new IllegalArgumentException("size must not be negative.");
    }

    public WebSocket setMaxPayloadSize(int i3) throws IllegalArgumentException {
        if (i3 >= 0) {
            this.mMaxPayloadSize = i3;
            return this;
        }
        throw new IllegalArgumentException("size must not be negative.");
    }

    public WebSocket setMissingCloseFrameAllowed(boolean z2) {
        this.mMissingCloseFrameAllowed = z2;
        return this;
    }

    public WebSocket setPingInterval(long j2) {
        this.mPingSender.setInterval(j2);
        return this;
    }

    public WebSocket setPingPayloadGenerator(PayloadGenerator payloadGenerator) {
        this.mPingSender.setPayloadGenerator(payloadGenerator);
        return this;
    }

    public WebSocket setPingSenderName(String str) {
        this.mPingSender.setTimerName(str);
        return this;
    }

    public WebSocket setPongInterval(long j2) {
        this.mPongSender.setInterval(j2);
        return this;
    }

    public WebSocket setPongPayloadGenerator(PayloadGenerator payloadGenerator) {
        this.mPongSender.setPayloadGenerator(payloadGenerator);
        return this;
    }

    public WebSocket setPongSenderName(String str) {
        this.mPongSender.setTimerName(str);
        return this;
    }

    public WebSocket setUserInfo(String str) {
        this.mHandshakeBuilder.setUserInfo(str);
        return this;
    }

    public WebSocket addExtension(String str) {
        this.mHandshakeBuilder.addExtension(str);
        return this;
    }

    public WebSocket disconnect(int i3) {
        return disconnect(i3, (String) null);
    }

    public WebSocket recreate(int i3) throws IOException {
        if (i3 >= 0) {
            WebSocket createSocket = this.mWebSocketFactory.createSocket(getURI(), i3);
            createSocket.mHandshakeBuilder = new HandshakeBuilder(this.mHandshakeBuilder);
            createSocket.setPingInterval(getPingInterval());
            createSocket.setPongInterval(getPongInterval());
            createSocket.setPingPayloadGenerator(getPingPayloadGenerator());
            createSocket.setPongPayloadGenerator(getPongPayloadGenerator());
            createSocket.mExtended = this.mExtended;
            createSocket.mAutoFlush = this.mAutoFlush;
            createSocket.mMissingCloseFrameAllowed = this.mMissingCloseFrameAllowed;
            createSocket.mDirectTextMessage = this.mDirectTextMessage;
            createSocket.mFrameQueueSize = this.mFrameQueueSize;
            List<WebSocketListener> listeners = this.mListenerManager.getListeners();
            synchronized (listeners) {
                createSocket.addListeners(listeners);
            }
            return createSocket;
        }
        throw new IllegalArgumentException("The given timeout value is negative.");
    }

    public WebSocket sendBinary(byte[] bArr, boolean z2) {
        return sendFrame(WebSocketFrame.createBinaryFrame(bArr).setFin(z2));
    }

    public WebSocket sendClose(int i3) {
        return sendFrame(WebSocketFrame.createCloseFrame(i3));
    }

    public WebSocket sendContinuation(boolean z2) {
        return sendFrame(WebSocketFrame.createContinuationFrame().setFin(z2));
    }

    public WebSocket sendPing(byte[] bArr) {
        return sendFrame(WebSocketFrame.createPingFrame(bArr));
    }

    public WebSocket sendPong(byte[] bArr) {
        return sendFrame(WebSocketFrame.createPongFrame(bArr));
    }

    public WebSocket sendText(String str, boolean z2) {
        return sendFrame(WebSocketFrame.createTextFrame(str).setFin(z2));
    }

    public WebSocket setUserInfo(String str, String str2) {
        this.mHandshakeBuilder.setUserInfo(str, str2);
        return this;
    }

    public WebSocket disconnect(String str) {
        return disconnect(1000, str);
    }

    public WebSocket sendClose(int i3, String str) {
        return sendFrame(WebSocketFrame.createCloseFrame(i3, str));
    }

    public WebSocket sendContinuation(String str) {
        return sendFrame(WebSocketFrame.createContinuationFrame(str));
    }

    public WebSocket sendPing(String str) {
        return sendFrame(WebSocketFrame.createPingFrame(str));
    }

    public WebSocket sendPong(String str) {
        return sendFrame(WebSocketFrame.createPongFrame(str));
    }

    public WebSocket disconnect(int i3, String str) {
        return disconnect(i3, str, 10000);
    }

    public WebSocket sendContinuation(String str, boolean z2) {
        return sendFrame(WebSocketFrame.createContinuationFrame(str).setFin(z2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002a, code lost:
        r3.mListenerManager.callOnStateChanged(com.neovisionaries.ws.client.WebSocketState.CLOSING);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0035, code lost:
        if (r6 >= 0) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0037, code lost:
        r6 = 10000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0039, code lost:
        stopThreads(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003c, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.neovisionaries.ws.client.WebSocket disconnect(int r4, java.lang.String r5, long r6) {
        /*
            r3 = this;
            com.neovisionaries.ws.client.StateManager r0 = r3.mStateManager
            monitor-enter(r0)
            int[] r1 = com.neovisionaries.ws.client.WebSocket.AnonymousClass1.$SwitchMap$com$neovisionaries$ws$client$WebSocketState     // Catch:{ all -> 0x0019 }
            com.neovisionaries.ws.client.StateManager r2 = r3.mStateManager     // Catch:{ all -> 0x0019 }
            com.neovisionaries.ws.client.WebSocketState r2 = r2.getState()     // Catch:{ all -> 0x0019 }
            int r2 = r2.ordinal()     // Catch:{ all -> 0x0019 }
            r1 = r1[r2]     // Catch:{ all -> 0x0019 }
            r2 = 1
            if (r1 == r2) goto L_0x003d
            r2 = 2
            if (r1 == r2) goto L_0x001b
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            return r3
        L_0x0019:
            r3 = move-exception
            goto L_0x0042
        L_0x001b:
            com.neovisionaries.ws.client.StateManager r1 = r3.mStateManager     // Catch:{ all -> 0x0019 }
            com.neovisionaries.ws.client.StateManager$CloseInitiator r2 = com.neovisionaries.ws.client.StateManager.CloseInitiator.CLIENT     // Catch:{ all -> 0x0019 }
            r1.changeToClosing(r2)     // Catch:{ all -> 0x0019 }
            com.neovisionaries.ws.client.WebSocketFrame r4 = com.neovisionaries.ws.client.WebSocketFrame.createCloseFrame(r4, r5)     // Catch:{ all -> 0x0019 }
            r3.sendFrame(r4)     // Catch:{ all -> 0x0019 }
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            com.neovisionaries.ws.client.ListenerManager r4 = r3.mListenerManager
            com.neovisionaries.ws.client.WebSocketState r5 = com.neovisionaries.ws.client.WebSocketState.CLOSING
            r4.callOnStateChanged(r5)
            r4 = 0
            int r4 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r4 >= 0) goto L_0x0039
            r6 = 10000(0x2710, double:4.9407E-320)
        L_0x0039:
            r3.stopThreads(r6)
            return r3
        L_0x003d:
            r3.finishAsynchronously()     // Catch:{ all -> 0x0019 }
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            return r3
        L_0x0042:
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.WebSocket.disconnect(int, java.lang.String, long):com.neovisionaries.ws.client.WebSocket");
    }

    public WebSocket sendContinuation(byte[] bArr) {
        return sendFrame(WebSocketFrame.createContinuationFrame(bArr));
    }

    public WebSocket sendContinuation(byte[] bArr, boolean z2) {
        return sendFrame(WebSocketFrame.createContinuationFrame(bArr).setFin(z2));
    }

    public Future<WebSocket> connect(ExecutorService executorService) {
        return executorService.submit(connectable());
    }
}
