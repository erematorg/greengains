package com.bumptech.glide.util;

import androidx.annotation.NonNull;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MarkEnforcingInputStream extends FilterInputStream {
    private static final int END_OF_STREAM = -1;
    private static final int UNSET = Integer.MIN_VALUE;
    private int availableBytes = Integer.MIN_VALUE;

    public MarkEnforcingInputStream(@NonNull InputStream inputStream) {
        super(inputStream);
    }

    private long getBytesToRead(long j2) {
        int i3 = this.availableBytes;
        if (i3 == 0) {
            return -1;
        }
        return (i3 == Integer.MIN_VALUE || j2 <= ((long) i3)) ? j2 : (long) i3;
    }

    private void updateAvailableBytesAfterRead(long j2) {
        int i3 = this.availableBytes;
        if (i3 != Integer.MIN_VALUE && j2 != -1) {
            this.availableBytes = (int) (((long) i3) - j2);
        }
    }

    public int available() throws IOException {
        int i3 = this.availableBytes;
        return i3 == Integer.MIN_VALUE ? super.available() : Math.min(i3, super.available());
    }

    public synchronized void mark(int i3) {
        super.mark(i3);
        this.availableBytes = i3;
    }

    public int read() throws IOException {
        if (getBytesToRead(1) == -1) {
            return -1;
        }
        int read = super.read();
        updateAvailableBytesAfterRead(1);
        return read;
    }

    public synchronized void reset() throws IOException {
        super.reset();
        this.availableBytes = Integer.MIN_VALUE;
    }

    public long skip(long j2) throws IOException {
        long bytesToRead = getBytesToRead(j2);
        if (bytesToRead == -1) {
            return 0;
        }
        long skip = super.skip(bytesToRead);
        updateAvailableBytesAfterRead(skip);
        return skip;
    }

    public int read(@NonNull byte[] bArr, int i3, int i4) throws IOException {
        int bytesToRead = (int) getBytesToRead((long) i4);
        if (bytesToRead == -1) {
            return -1;
        }
        int read = super.read(bArr, i3, bytesToRead);
        updateAvailableBytesAfterRead((long) read);
        return read;
    }
}
