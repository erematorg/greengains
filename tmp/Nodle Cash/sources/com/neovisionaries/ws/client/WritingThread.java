package com.neovisionaries.ws.client;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.neovisionaries.ws.client.StateManager;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

class WritingThread extends WebSocketThread {
    private static final int FLUSH_THRESHOLD = 1000;
    private static final int SHOULD_CONTINUE = 2;
    private static final int SHOULD_FLUSH = 3;
    private static final int SHOULD_SEND = 0;
    private static final int SHOULD_STOP = 1;
    private WebSocketFrame mCloseFrame;
    private boolean mFlushNeeded;
    private final LinkedList<WebSocketFrame> mFrames = new LinkedList<>();
    private final PerMessageCompressionExtension mPMCE;
    private boolean mStopRequested;
    private boolean mStopped;

    public WritingThread(WebSocket webSocket) {
        super("WritingThread", webSocket, ThreadType.WRITING_THREAD);
        this.mPMCE = webSocket.getPerMessageCompressionExtension();
    }

    private void addHighPriorityFrame(WebSocketFrame webSocketFrame) {
        Iterator<WebSocketFrame> it = this.mFrames.iterator();
        int i3 = 0;
        while (it.hasNext() && isHighPriorityFrame(it.next())) {
            i3++;
        }
        this.mFrames.add(i3, webSocketFrame);
    }

    private void changeToClosing() {
        WebSocketState webSocketState;
        boolean z2;
        StateManager stateManager = this.mWebSocket.getStateManager();
        synchronized (stateManager) {
            try {
                WebSocketState state = stateManager.getState();
                webSocketState = WebSocketState.CLOSING;
                if (state == webSocketState || state == WebSocketState.CLOSED) {
                    z2 = false;
                } else {
                    stateManager.changeToClosing(StateManager.CloseInitiator.CLIENT);
                    z2 = true;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (z2) {
            this.mWebSocket.getListenerManager().callOnStateChanged(webSocketState);
        }
    }

    private void doFlush() throws WebSocketException {
        try {
            flush();
            synchronized (this) {
                this.mFlushNeeded = false;
            }
        } catch (IOException e3) {
            WebSocketException webSocketException = new WebSocketException(WebSocketError.FLUSH_ERROR, a.h(e3, new StringBuilder("Flushing frames to the server failed: ")), e3);
            ListenerManager listenerManager = this.mWebSocket.getListenerManager();
            listenerManager.callOnError(webSocketException);
            listenerManager.callOnSendError(webSocketException, (WebSocketFrame) null);
            throw webSocketException;
        }
    }

    private void flush() throws IOException {
        this.mWebSocket.getOutput().flush();
    }

    private long flushIfLongInterval(long j2) throws WebSocketException {
        long currentTimeMillis = System.currentTimeMillis();
        if (1000 >= currentTimeMillis - j2) {
            return j2;
        }
        doFlush();
        return currentTimeMillis;
    }

    private void flushIgnoreError() {
        try {
            flush();
        } catch (IOException unused) {
        }
    }

    private boolean isFlushNeeded(boolean z2) {
        return z2 || this.mWebSocket.isAutoFlush() || this.mFlushNeeded || this.mCloseFrame != null;
    }

    private static boolean isHighPriorityFrame(WebSocketFrame webSocketFrame) {
        return webSocketFrame.isPingFrame() || webSocketFrame.isPongFrame();
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x001d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void main() {
        /*
            r3 = this;
            com.neovisionaries.ws.client.WebSocket r0 = r3.mWebSocket
            r0.onWritingThreadStarted()
        L_0x0005:
            int r0 = r3.waitForFrames()
            r1 = 1
            if (r0 != r1) goto L_0x000d
            goto L_0x001d
        L_0x000d:
            r2 = 3
            if (r0 != r2) goto L_0x0014
            r3.flushIgnoreError()
            goto L_0x0005
        L_0x0014:
            r2 = 2
            if (r0 != r2) goto L_0x0018
            goto L_0x0005
        L_0x0018:
            r0 = 0
            r3.sendFrames(r0)     // Catch:{ WebSocketException -> 0x001d }
            goto L_0x0005
        L_0x001d:
            r3.sendFrames(r1)     // Catch:{ WebSocketException -> 0x0020 }
        L_0x0020:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.WritingThread.main():void");
    }

    private void notifyFinished() {
        this.mWebSocket.onWritingThreadFinished(this.mCloseFrame);
    }

    private void sendFrame(WebSocketFrame webSocketFrame) throws WebSocketException {
        WebSocketFrame compressFrame = WebSocketFrame.compressFrame(webSocketFrame, this.mPMCE);
        this.mWebSocket.getListenerManager().callOnSendingFrame(compressFrame);
        if (this.mCloseFrame != null) {
            this.mWebSocket.getListenerManager().callOnFrameUnsent(compressFrame);
            return;
        }
        if (compressFrame.isCloseFrame()) {
            this.mCloseFrame = compressFrame;
        }
        if (compressFrame.isCloseFrame()) {
            changeToClosing();
        }
        try {
            this.mWebSocket.getOutput().write(compressFrame);
            this.mWebSocket.getListenerManager().callOnFrameSent(compressFrame);
        } catch (IOException e3) {
            WebSocketException webSocketException = new WebSocketException(WebSocketError.IO_ERROR_IN_WRITING, a.h(e3, new StringBuilder("An I/O error occurred when a frame was tried to be sent: ")), e3);
            ListenerManager listenerManager = this.mWebSocket.getListenerManager();
            listenerManager.callOnError(webSocketException);
            listenerManager.callOnSendError(webSocketException, compressFrame);
            throw webSocketException;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        sendFrame(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
        if (r2.isPingFrame() != false) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        if (r2.isPongFrame() == false) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0034, code lost:
        if (isFlushNeeded(r5) != false) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0037, code lost:
        r0 = flushIfLongInterval(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003c, code lost:
        doFlush();
        r0 = java.lang.System.currentTimeMillis();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        if (isFlushNeeded(r5) == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
        doFlush();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void sendFrames(boolean r5) throws com.neovisionaries.ws.client.WebSocketException {
        /*
            r4 = this;
            long r0 = java.lang.System.currentTimeMillis()
        L_0x0004:
            monitor-enter(r4)
            java.util.LinkedList<com.neovisionaries.ws.client.WebSocketFrame> r2 = r4.mFrames     // Catch:{ all -> 0x001d }
            java.lang.Object r2 = r2.poll()     // Catch:{ all -> 0x001d }
            com.neovisionaries.ws.client.WebSocketFrame r2 = (com.neovisionaries.ws.client.WebSocketFrame) r2     // Catch:{ all -> 0x001d }
            r4.notifyAll()     // Catch:{ all -> 0x001d }
            if (r2 != 0) goto L_0x001f
            monitor-exit(r4)     // Catch:{ all -> 0x001d }
            boolean r5 = r4.isFlushNeeded(r5)
            if (r5 == 0) goto L_0x001c
            r4.doFlush()
        L_0x001c:
            return
        L_0x001d:
            r5 = move-exception
            goto L_0x0044
        L_0x001f:
            monitor-exit(r4)     // Catch:{ all -> 0x001d }
            r4.sendFrame(r2)
            boolean r3 = r2.isPingFrame()
            if (r3 != 0) goto L_0x003c
            boolean r2 = r2.isPongFrame()
            if (r2 == 0) goto L_0x0030
            goto L_0x003c
        L_0x0030:
            boolean r2 = r4.isFlushNeeded(r5)
            if (r2 != 0) goto L_0x0037
            goto L_0x0004
        L_0x0037:
            long r0 = r4.flushIfLongInterval(r0)
            goto L_0x0004
        L_0x003c:
            r4.doFlush()
            long r0 = java.lang.System.currentTimeMillis()
            goto L_0x0004
        L_0x0044:
            monitor-exit(r4)     // Catch:{ all -> 0x001d }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.WritingThread.sendFrames(boolean):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:12|(2:14|(3:16|17|18)(2:19|20))|21|22|(2:24|25)(2:26|(2:28|(3:30|31|32)(2:33|34))(2:35|36))) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0025 */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0029 A[Catch:{ all -> 0x0008 }, DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x002b A[Catch:{ all -> 0x0008 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int waitForFrames() {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.mStopRequested     // Catch:{ all -> 0x0008 }
            r1 = 1
            if (r0 == 0) goto L_0x000a
            monitor-exit(r4)     // Catch:{ all -> 0x0008 }
            return r1
        L_0x0008:
            r0 = move-exception
            goto L_0x0040
        L_0x000a:
            com.neovisionaries.ws.client.WebSocketFrame r0 = r4.mCloseFrame     // Catch:{ all -> 0x0008 }
            if (r0 == 0) goto L_0x0010
            monitor-exit(r4)     // Catch:{ all -> 0x0008 }
            return r1
        L_0x0010:
            java.util.LinkedList<com.neovisionaries.ws.client.WebSocketFrame> r0 = r4.mFrames     // Catch:{ all -> 0x0008 }
            int r0 = r0.size()     // Catch:{ all -> 0x0008 }
            r2 = 3
            r3 = 0
            if (r0 != 0) goto L_0x0025
            boolean r0 = r4.mFlushNeeded     // Catch:{ all -> 0x0008 }
            if (r0 == 0) goto L_0x0022
            r4.mFlushNeeded = r3     // Catch:{ all -> 0x0008 }
            monitor-exit(r4)     // Catch:{ all -> 0x0008 }
            return r2
        L_0x0022:
            r4.wait()     // Catch:{ InterruptedException -> 0x0025 }
        L_0x0025:
            boolean r0 = r4.mStopRequested     // Catch:{ all -> 0x0008 }
            if (r0 == 0) goto L_0x002b
            monitor-exit(r4)     // Catch:{ all -> 0x0008 }
            return r1
        L_0x002b:
            java.util.LinkedList<com.neovisionaries.ws.client.WebSocketFrame> r0 = r4.mFrames     // Catch:{ all -> 0x0008 }
            int r0 = r0.size()     // Catch:{ all -> 0x0008 }
            if (r0 != 0) goto L_0x003e
            boolean r0 = r4.mFlushNeeded     // Catch:{ all -> 0x0008 }
            if (r0 == 0) goto L_0x003b
            r4.mFlushNeeded = r3     // Catch:{ all -> 0x0008 }
            monitor-exit(r4)     // Catch:{ all -> 0x0008 }
            return r2
        L_0x003b:
            monitor-exit(r4)     // Catch:{ all -> 0x0008 }
            r4 = 2
            return r4
        L_0x003e:
            monitor-exit(r4)     // Catch:{ all -> 0x0008 }
            return r3
        L_0x0040:
            monitor-exit(r4)     // Catch:{ all -> 0x0008 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.WritingThread.waitForFrames():int");
    }

    public void queueFlush() {
        synchronized (this) {
            this.mFlushNeeded = true;
            notifyAll();
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:1:0x0001 */
    /* JADX WARNING: Removed duplicated region for block: B:1:0x0001 A[LOOP:0: B:1:0x0001->B:33:0x0001, LOOP_START, SYNTHETIC, Splitter:B:1:0x0001] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean queueFrame(com.neovisionaries.ws.client.WebSocketFrame r3) {
        /*
            r2 = this;
            monitor-enter(r2)
        L_0x0001:
            boolean r0 = r2.mStopped     // Catch:{ all -> 0x0008 }
            if (r0 == 0) goto L_0x000a
            monitor-exit(r2)     // Catch:{ all -> 0x0008 }
            r2 = 0
            return r2
        L_0x0008:
            r3 = move-exception
            goto L_0x0045
        L_0x000a:
            boolean r0 = r2.mStopRequested     // Catch:{ all -> 0x0008 }
            if (r0 != 0) goto L_0x0030
            com.neovisionaries.ws.client.WebSocketFrame r0 = r2.mCloseFrame     // Catch:{ all -> 0x0008 }
            if (r0 == 0) goto L_0x0013
            goto L_0x0030
        L_0x0013:
            boolean r0 = r3.isControlFrame()     // Catch:{ all -> 0x0008 }
            if (r0 == 0) goto L_0x001a
            goto L_0x0030
        L_0x001a:
            com.neovisionaries.ws.client.WebSocket r0 = r2.mWebSocket     // Catch:{ all -> 0x0008 }
            int r0 = r0.getFrameQueueSize()     // Catch:{ all -> 0x0008 }
            if (r0 != 0) goto L_0x0023
            goto L_0x0030
        L_0x0023:
            java.util.LinkedList<com.neovisionaries.ws.client.WebSocketFrame> r1 = r2.mFrames     // Catch:{ all -> 0x0008 }
            int r1 = r1.size()     // Catch:{ all -> 0x0008 }
            if (r1 >= r0) goto L_0x002c
            goto L_0x0030
        L_0x002c:
            r2.wait()     // Catch:{ InterruptedException -> 0x0001 }
            goto L_0x0001
        L_0x0030:
            boolean r0 = isHighPriorityFrame(r3)     // Catch:{ all -> 0x0008 }
            if (r0 == 0) goto L_0x003a
            r2.addHighPriorityFrame(r3)     // Catch:{ all -> 0x0008 }
            goto L_0x003f
        L_0x003a:
            java.util.LinkedList<com.neovisionaries.ws.client.WebSocketFrame> r0 = r2.mFrames     // Catch:{ all -> 0x0008 }
            r0.addLast(r3)     // Catch:{ all -> 0x0008 }
        L_0x003f:
            r2.notifyAll()     // Catch:{ all -> 0x0008 }
            monitor-exit(r2)     // Catch:{ all -> 0x0008 }
            r2 = 1
            return r2
        L_0x0045:
            monitor-exit(r2)     // Catch:{ all -> 0x0008 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.WritingThread.queueFrame(com.neovisionaries.ws.client.WebSocketFrame):boolean");
    }

    public void requestStop() {
        synchronized (this) {
            this.mStopRequested = true;
            notifyAll();
        }
    }

    public void runMain() {
        try {
            main();
        } catch (Throwable th) {
            WebSocketError webSocketError = WebSocketError.UNEXPECTED_ERROR_IN_WRITING_THREAD;
            WebSocketException webSocketException = new WebSocketException(webSocketError, "An uncaught throwable was detected in the writing thread: " + th.getMessage(), th);
            ListenerManager listenerManager = this.mWebSocket.getListenerManager();
            listenerManager.callOnError(webSocketException);
            listenerManager.callOnUnexpectedError(webSocketException);
        }
        synchronized (this) {
            this.mStopped = true;
            notifyAll();
        }
        notifyFinished();
    }
}
