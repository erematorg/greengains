package com.google.zxing.oned.rss.expanded.decoders;

abstract class DecodedObject {
    private final int newPosition;

    public DecodedObject(int i3) {
        this.newPosition = i3;
    }

    public final int getNewPosition() {
        return this.newPosition;
    }
}
