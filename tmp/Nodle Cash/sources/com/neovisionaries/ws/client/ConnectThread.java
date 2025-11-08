package com.neovisionaries.ws.client;

class ConnectThread extends WebSocketThread {
    public ConnectThread(WebSocket webSocket) {
        super("ConnectThread", webSocket, ThreadType.CONNECT_THREAD);
    }

    private void handleError(WebSocketException webSocketException) {
        ListenerManager listenerManager = this.mWebSocket.getListenerManager();
        listenerManager.callOnError(webSocketException);
        listenerManager.callOnConnectError(webSocketException);
    }

    public void runMain() {
        try {
            this.mWebSocket.connect();
        } catch (WebSocketException e3) {
            handleError(e3);
        }
    }
}
