package com.neovisionaries.ws.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ListenerManager {
    private List<WebSocketListener> mCopiedListeners;
    private final List<WebSocketListener> mListeners = new ArrayList();
    private boolean mSyncNeeded = true;
    private final WebSocket mWebSocket;

    public ListenerManager(WebSocket webSocket) {
        this.mWebSocket = webSocket;
    }

    private void callHandleCallbackError(WebSocketListener webSocketListener, Throwable th) {
        try {
            webSocketListener.handleCallbackError(this.mWebSocket, th);
        } catch (Throwable unused) {
        }
    }

    private List<WebSocketListener> getSynchronizedListeners() {
        synchronized (this.mListeners) {
            try {
                if (!this.mSyncNeeded) {
                    List<WebSocketListener> list = this.mCopiedListeners;
                    return list;
                }
                ArrayList arrayList = new ArrayList(this.mListeners.size());
                for (WebSocketListener add : this.mListeners) {
                    arrayList.add(add);
                }
                this.mCopiedListeners = arrayList;
                this.mSyncNeeded = false;
                return arrayList;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void addListener(WebSocketListener webSocketListener) {
        if (webSocketListener != null) {
            synchronized (this.mListeners) {
                this.mListeners.add(webSocketListener);
                this.mSyncNeeded = true;
            }
        }
    }

    public void addListeners(List<WebSocketListener> list) {
        if (list != null) {
            synchronized (this.mListeners) {
                try {
                    for (WebSocketListener next : list) {
                        if (next != null) {
                            this.mListeners.add(next);
                            this.mSyncNeeded = true;
                        }
                    }
                } finally {
                }
            }
        }
    }

    public void callOnBinaryFrame(WebSocketFrame webSocketFrame) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onBinaryFrame(this.mWebSocket, webSocketFrame);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnBinaryMessage(byte[] bArr) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onBinaryMessage(this.mWebSocket, bArr);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnCloseFrame(WebSocketFrame webSocketFrame) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onCloseFrame(this.mWebSocket, webSocketFrame);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnConnectError(WebSocketException webSocketException) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onConnectError(this.mWebSocket, webSocketException);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnConnected(Map<String, List<String>> map) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onConnected(this.mWebSocket, map);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnContinuationFrame(WebSocketFrame webSocketFrame) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onContinuationFrame(this.mWebSocket, webSocketFrame);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnDisconnected(WebSocketFrame webSocketFrame, WebSocketFrame webSocketFrame2, boolean z2) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onDisconnected(this.mWebSocket, webSocketFrame, webSocketFrame2, z2);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnError(WebSocketException webSocketException) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onError(this.mWebSocket, webSocketException);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnFrame(WebSocketFrame webSocketFrame) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onFrame(this.mWebSocket, webSocketFrame);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnFrameError(WebSocketException webSocketException, WebSocketFrame webSocketFrame) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onFrameError(this.mWebSocket, webSocketException, webSocketFrame);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnFrameSent(WebSocketFrame webSocketFrame) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onFrameSent(this.mWebSocket, webSocketFrame);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnFrameUnsent(WebSocketFrame webSocketFrame) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onFrameUnsent(this.mWebSocket, webSocketFrame);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnMessageDecompressionError(WebSocketException webSocketException, byte[] bArr) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onMessageDecompressionError(this.mWebSocket, webSocketException, bArr);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnMessageError(WebSocketException webSocketException, List<WebSocketFrame> list) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onMessageError(this.mWebSocket, webSocketException, list);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnPingFrame(WebSocketFrame webSocketFrame) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onPingFrame(this.mWebSocket, webSocketFrame);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnPongFrame(WebSocketFrame webSocketFrame) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onPongFrame(this.mWebSocket, webSocketFrame);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnSendError(WebSocketException webSocketException, WebSocketFrame webSocketFrame) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onSendError(this.mWebSocket, webSocketException, webSocketFrame);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnSendingFrame(WebSocketFrame webSocketFrame) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onSendingFrame(this.mWebSocket, webSocketFrame);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnSendingHandshake(String str, List<String[]> list) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onSendingHandshake(this.mWebSocket, str, list);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnStateChanged(WebSocketState webSocketState) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onStateChanged(this.mWebSocket, webSocketState);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnTextFrame(WebSocketFrame webSocketFrame) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onTextFrame(this.mWebSocket, webSocketFrame);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnTextMessage(String str) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onTextMessage(this.mWebSocket, str);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnTextMessageError(WebSocketException webSocketException, byte[] bArr) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onTextMessageError(this.mWebSocket, webSocketException, bArr);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnThreadCreated(ThreadType threadType, Thread thread) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onThreadCreated(this.mWebSocket, threadType, thread);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnThreadStarted(ThreadType threadType, Thread thread) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onThreadStarted(this.mWebSocket, threadType, thread);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnThreadStopping(ThreadType threadType, Thread thread) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onThreadStopping(this.mWebSocket, threadType, thread);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void callOnUnexpectedError(WebSocketException webSocketException) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onUnexpectedError(this.mWebSocket, webSocketException);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }

    public void clearListeners() {
        synchronized (this.mListeners) {
            try {
                if (this.mListeners.size() != 0) {
                    this.mListeners.clear();
                    this.mCopiedListeners = null;
                    this.mSyncNeeded = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public List<WebSocketListener> getListeners() {
        return this.mListeners;
    }

    public void removeListener(WebSocketListener webSocketListener) {
        if (webSocketListener != null) {
            synchronized (this.mListeners) {
                try {
                    if (this.mListeners.remove(webSocketListener)) {
                        this.mSyncNeeded = true;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public void removeListeners(List<WebSocketListener> list) {
        if (list != null) {
            synchronized (this.mListeners) {
                try {
                    for (WebSocketListener next : list) {
                        if (next != null) {
                            if (this.mListeners.remove(next)) {
                                this.mSyncNeeded = true;
                            }
                        }
                    }
                } finally {
                }
            }
        }
    }

    public void callOnTextMessage(byte[] bArr) {
        for (WebSocketListener next : getSynchronizedListeners()) {
            try {
                next.onTextMessage(this.mWebSocket, bArr);
            } catch (Throwable th) {
                callHandleCallbackError(next, th);
            }
        }
    }
}
