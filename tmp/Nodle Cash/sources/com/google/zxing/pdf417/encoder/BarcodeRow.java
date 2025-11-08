package com.google.zxing.pdf417.encoder;

final class BarcodeRow {
    private int currentLocation = 0;
    private final byte[] row;

    public BarcodeRow(int i3) {
        this.row = new byte[i3];
    }

    public void addBar(boolean z2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = this.currentLocation;
            this.currentLocation = i5 + 1;
            set(i5, z2);
        }
    }

    public byte[] getScaledRow(int i3) {
        int length = this.row.length * i3;
        byte[] bArr = new byte[length];
        for (int i4 = 0; i4 < length; i4++) {
            bArr[i4] = this.row[i4 / i3];
        }
        return bArr;
    }

    public void set(int i3, byte b3) {
        this.row[i3] = b3;
    }

    private void set(int i3, boolean z2) {
        this.row[i3] = z2 ? (byte) 1 : 0;
    }
}
