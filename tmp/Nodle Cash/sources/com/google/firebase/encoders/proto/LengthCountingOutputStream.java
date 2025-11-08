package com.google.firebase.encoders.proto;

import androidx.annotation.NonNull;
import java.io.OutputStream;

final class LengthCountingOutputStream extends OutputStream {
    private long length = 0;

    public long getLength() {
        return this.length;
    }

    public void write(int i3) {
        this.length++;
    }

    public void write(byte[] bArr) {
        this.length += (long) bArr.length;
    }

    public void write(@NonNull byte[] bArr, int i3, int i4) {
        int i5;
        if (i3 < 0 || i3 > bArr.length || i4 < 0 || (i5 = i3 + i4) > bArr.length || i5 < 0) {
            throw new IndexOutOfBoundsException();
        }
        this.length += (long) i4;
    }
}
