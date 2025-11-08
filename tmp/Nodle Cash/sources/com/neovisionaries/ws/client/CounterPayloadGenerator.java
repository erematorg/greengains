package com.neovisionaries.ws.client;

class CounterPayloadGenerator implements PayloadGenerator {
    private long mCount;

    private long increment() {
        long max = Math.max(this.mCount + 1, 1);
        this.mCount = max;
        return max;
    }

    public byte[] generate() {
        return Misc.getBytesUTF8(String.valueOf(increment()));
    }
}
