package com.neovisionaries.ws.client;

class PongSender extends PeriodicalFrameSender {
    private static final String TIMER_NAME = "PongSender";

    public PongSender(WebSocket webSocket, PayloadGenerator payloadGenerator) {
        super(webSocket, TIMER_NAME, payloadGenerator);
    }

    public WebSocketFrame createFrame(byte[] bArr) {
        return WebSocketFrame.createPongFrame(bArr);
    }
}
