package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
public final class CountingOutputStream extends FilterOutputStream {
    private long count;

    public CountingOutputStream(OutputStream outputStream) {
        super((OutputStream) Preconditions.checkNotNull(outputStream));
    }

    public void close() throws IOException {
        this.out.close();
    }

    public long getCount() {
        return this.count;
    }

    public void write(byte[] bArr, int i3, int i4) throws IOException {
        this.out.write(bArr, i3, i4);
        this.count += (long) i4;
    }

    public void write(int i3) throws IOException {
        this.out.write(i3);
        this.count++;
    }
}
