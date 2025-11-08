package com.neovisionaries.ws.client;

class PingSender extends PeriodicalFrameSender {
    private static final String TIMER_NAME = "PingSender";

    public PingSender(WebSocket webSocket, PayloadGenerator payloadGenerator) {
        super(webSocket, TIMER_NAME, payloadGenerator);
    }

    public WebSocketFrame createFrame(byte[] bArr) {
        return WebSocketFrame.createPingFrame(bArr);
    }
}
