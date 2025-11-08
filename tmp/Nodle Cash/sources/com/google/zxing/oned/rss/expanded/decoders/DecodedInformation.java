package com.google.zxing.oned.rss.expanded.decoders;

final class DecodedInformation extends DecodedObject {
    private final String newString;
    private final boolean remaining;
    private final int remainingValue;

    public DecodedInformation(int i3, String str) {
        super(i3);
        this.newString = str;
        this.remaining = false;
        this.remainingValue = 0;
    }

    public String getNewString() {
        return this.newString;
    }

    public int getRemainingValue() {
        return this.remainingValue;
    }

    public boolean isRemaining() {
        return this.remaining;
    }

    public DecodedInformation(int i3, String str, int i4) {
        super(i3);
        this.remaining = true;
        this.remainingValue = i4;
        this.newString = str;
    }
}
