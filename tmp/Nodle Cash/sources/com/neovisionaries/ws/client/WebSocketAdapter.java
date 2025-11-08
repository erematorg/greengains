package com.neovisionaries.ws.client;

import java.util.List;
import java.util.Map;

public class WebSocketAdapter implements WebSocketListener {
    public void handleCallbackError(WebSocket webSocket, Throwable th) throws Exception {
    }

    public void onBinaryFrame(WebSocket webSocket, WebSocketFrame webSocketFrame) throws Exception {
    }

    public void onBinaryMessage(WebSocket webSocket, byte[] bArr) throws Exception {
    }

    public void onCloseFrame(WebSocket webSocket, WebSocketFrame webSocketFrame) throws Exception {
    }

    public void onConnectError(WebSocket webSocket, WebSocketException webSocketException) throws Exception {
    }

    public void onConnected(WebSocket webSocket, Map<String, List<String>> map) throws Exception {
    }

    public void onContinuationFrame(WebSocket webSocket, WebSocketFrame webSocketFrame) throws Exception {
    }

    public void onDisconnected(WebSocket webSocket, WebSocketFrame webSocketFrame, WebSocketFrame webSocketFrame2, boolean z2) throws Exception {
    }

    public void onError(WebSocket webSocket, WebSocketException webSocketException) throws Exception {
    }

    public void onFrame(WebSocket webSocket, WebSocketFrame webSocketFrame) throws Exception {
    }

    public void onFrameError(WebSocket webSocket, WebSocketException webSocketException, WebSocketFrame webSocketFrame) throws Exception {
    }

    public void onFrameSent(WebSocket webSocket, WebSocketFrame webSocketFrame) throws Exception {
    }

    public void onFrameUnsent(WebSocket webSocket, WebSocketFrame webSocketFrame) throws Exception {
    }

    public void onMessageDecompressionError(WebSocket webSocket, WebSocketException webSocketException, byte[] bArr) throws Exception {
    }

    public void onMessageError(WebSocket webSocket, WebSocketException webSocketException, List<WebSocketFrame> list) throws Exception {
    }

    public void onPingFrame(WebSocket webSocket, WebSocketFrame webSocketFrame) throws Exception {
    }

    public void onPongFrame(WebSocket webSocket, WebSocketFrame webSocketFrame) throws Exception {
    }

    public void onSendError(WebSocket webSocket, WebSocketException webSocketException, WebSocketFrame webSocketFrame) throws Exception {
    }

    public void onSendingFrame(WebSocket webSocket, WebSocketFrame webSocketFrame) throws Exception {
    }

    public void onSendingHandshake(WebSocket webSocket, String str, List<String[]> list) throws Exception {
    }

    public void onStateChanged(WebSocket webSocket, WebSocketState webSocketState) throws Exception {
    }

    public void onTextFrame(WebSocket webSocket, WebSocketFrame webSocketFrame) throws Exception {
    }

    public void onTextMessage(WebSocket webSocket, String str) throws Exception {
    }

    public void onTextMessageError(WebSocket webSocket, WebSocketException webSocketException, byte[] bArr) throws Exception {
    }

    public void onThreadCreated(WebSocket webSocket, ThreadType threadType, Thread thread) throws Exception {
    }

    public void onThreadStarted(WebSocket webSocket, ThreadType threadType, Thread thread) throws Exception {
    }

    public void onThreadStopping(WebSocket webSocket, ThreadType threadType, Thread thread) throws Exception {
    }

    public void onUnexpectedError(WebSocket webSocket, WebSocketException webSocketException) throws Exception {
    }

    public void onTextMessage(WebSocket webSocket, byte[] bArr) throws Exception {
    }
}
