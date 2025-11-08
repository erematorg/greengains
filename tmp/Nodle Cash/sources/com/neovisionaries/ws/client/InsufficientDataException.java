package com.neovisionaries.ws.client;

class InsufficientDataException extends WebSocketException {
    private static final long serialVersionUID = 1;
    private final int mReadByteCount;
    private final int mRequestedByteCount;

    public InsufficientDataException(int i3, int i4) {
        super(WebSocketError.INSUFFICENT_DATA, "The end of the stream has been reached unexpectedly.");
        this.mRequestedByteCount = i3;
        this.mReadByteCount = i4;
    }

    public int getReadByteCount() {
        return this.mReadByteCount;
    }

    public int getRequestedByteCount() {
        return this.mRequestedByteCount;
    }
}
