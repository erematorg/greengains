package com.google.zxing.oned.rss.expanded.decoders;

final class DecodedChar extends DecodedObject {
    static final char FNC1 = '$';
    private final char value;

    public DecodedChar(int i3, char c3) {
        super(i3);
        this.value = c3;
    }

    public char getValue() {
        return this.value;
    }

    public boolean isFNC1() {
        return this.value == '$';
    }
}
