package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.annotation.NonNull;
import java.io.OutputStream;

final class zzfb extends OutputStream {
    private long zza = 0;

    public final void write(int i3) {
        this.zza++;
    }

    public final long zza() {
        return this.zza;
    }

    public final void write(byte[] bArr) {
        this.zza += (long) bArr.length;
    }

    public final void write(@NonNull byte[] bArr, int i3, int i4) {
        int length;
        int i5;
        if (i3 < 0 || i3 > (length = bArr.length) || i4 < 0 || (i5 = i3 + i4) > length || i5 < 0) {
            throw new IndexOutOfBoundsException();
        }
        this.zza += (long) i4;
    }
}
