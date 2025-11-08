package com.neovisionaries.ws.client;

abstract class PerMessageCompressionExtension extends WebSocketExtension {
    public PerMessageCompressionExtension(String str) {
        super(str);
    }

    public abstract byte[] compress(byte[] bArr) throws WebSocketException;

    public abstract byte[] decompress(byte[] bArr) throws WebSocketException;

    public PerMessageCompressionExtension(WebSocketExtension webSocketExtension) {
        super(webSocketExtension);
    }
}
