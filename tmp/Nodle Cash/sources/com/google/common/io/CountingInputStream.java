package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
public final class CountingInputStream extends FilterInputStream {
    private long count;
    private long mark = -1;

    public CountingInputStream(InputStream inputStream) {
        super((InputStream) Preconditions.checkNotNull(inputStream));
    }

    public long getCount() {
        return this.count;
    }

    public synchronized void mark(int i3) {
        this.in.mark(i3);
        this.mark = this.count;
    }

    public int read() throws IOException {
        int read = this.in.read();
        if (read != -1) {
            this.count++;
        }
        return read;
    }

    public synchronized void reset() throws IOException {
        if (!this.in.markSupported()) {
            throw new IOException("Mark not supported");
        } else if (this.mark != -1) {
            this.in.reset();
            this.count = this.mark;
        } else {
            throw new IOException("Mark not set");
        }
    }

    public long skip(long j2) throws IOException {
        long skip = this.in.skip(j2);
        this.count += skip;
        return skip;
    }

    public int read(byte[] bArr, int i3, int i4) throws IOException {
        int read = this.in.read(bArr, i3, i4);
        if (read != -1) {
            this.count += (long) read;
        }
        return read;
    }
}
