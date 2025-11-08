package com.google.crypto.tink.util;

import com.google.crypto.tink.subtle.Hex;
import com.google.errorprone.annotations.Immutable;
import java.util.Arrays;

@Immutable
public final class Bytes {
    private final byte[] data;

    private Bytes(byte[] bArr, int i3, int i4) {
        byte[] bArr2 = new byte[i4];
        this.data = bArr2;
        System.arraycopy(bArr, i3, bArr2, 0, i4);
    }

    public static Bytes copyFrom(byte[] bArr) {
        if (bArr != null) {
            return copyFrom(bArr, 0, bArr.length);
        }
        throw new NullPointerException("data must be non-null");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Bytes)) {
            return false;
        }
        return Arrays.equals(((Bytes) obj).data, this.data);
    }

    public int hashCode() {
        return Arrays.hashCode(this.data);
    }

    public int size() {
        return this.data.length;
    }

    public byte[] toByteArray() {
        byte[] bArr = this.data;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public String toString() {
        return "Bytes(" + Hex.encode(this.data) + ")";
    }

    public static Bytes copyFrom(byte[] bArr, int i3, int i4) {
        if (bArr != null) {
            return new Bytes(bArr, i3, i4);
        }
        throw new NullPointerException("data must be non-null");
    }
}
