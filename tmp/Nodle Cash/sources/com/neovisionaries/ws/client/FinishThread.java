package com.neovisionaries.ws.client;

class FinishThread extends WebSocketThread {
    public FinishThread(WebSocket webSocket) {
        super("FinishThread", webSocket, ThreadType.FINISH_THREAD);
    }

    public void runMain() {
        this.mWebSocket.finish();
    }
}
