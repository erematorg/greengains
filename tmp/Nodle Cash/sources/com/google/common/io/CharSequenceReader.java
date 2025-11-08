package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.Objects;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
final class CharSequenceReader extends Reader {
    private int mark;
    private int pos;
    @CheckForNull
    private CharSequence seq;

    public CharSequenceReader(CharSequence charSequence) {
        this.seq = (CharSequence) Preconditions.checkNotNull(charSequence);
    }

    private void checkOpen() throws IOException {
        if (this.seq == null) {
            throw new IOException("reader closed");
        }
    }

    private boolean hasRemaining() {
        return remaining() > 0;
    }

    private int remaining() {
        Objects.requireNonNull(this.seq);
        return this.seq.length() - this.pos;
    }

    public synchronized void close() throws IOException {
        this.seq = null;
    }

    public synchronized void mark(int i3) throws IOException {
        Preconditions.checkArgument(i3 >= 0, "readAheadLimit (%s) may not be negative", i3);
        checkOpen();
        this.mark = this.pos;
    }

    public boolean markSupported() {
        return true;
    }

    public synchronized int read(CharBuffer charBuffer) throws IOException {
        Preconditions.checkNotNull(charBuffer);
        checkOpen();
        Objects.requireNonNull(this.seq);
        if (!hasRemaining()) {
            return -1;
        }
        int min = Math.min(charBuffer.remaining(), remaining());
        for (int i3 = 0; i3 < min; i3++) {
            CharSequence charSequence = this.seq;
            int i4 = this.pos;
            this.pos = i4 + 1;
            charBuffer.put(charSequence.charAt(i4));
        }
        return min;
    }

    public synchronized boolean ready() throws IOException {
        checkOpen();
        return true;
    }

    public synchronized void reset() throws IOException {
        checkOpen();
        this.pos = this.mark;
    }

    public synchronized long skip(long j2) throws IOException {
        int min;
        Preconditions.checkArgument(j2 >= 0, "n (%s) may not be negative", j2);
        checkOpen();
        min = (int) Math.min((long) remaining(), j2);
        this.pos += min;
        return (long) min;
    }

    public synchronized int read() throws IOException {
        char c3;
        checkOpen();
        Objects.requireNonNull(this.seq);
        if (hasRemaining()) {
            CharSequence charSequence = this.seq;
            int i3 = this.pos;
            this.pos = i3 + 1;
            c3 = charSequence.charAt(i3);
        } else {
            c3 = 65535;
        }
        return c3;
    }

    public synchronized int read(char[] cArr, int i3, int i4) throws IOException {
        Preconditions.checkPositionIndexes(i3, i3 + i4, cArr.length);
        checkOpen();
        Objects.requireNonNull(this.seq);
        if (!hasRemaining()) {
            return -1;
        }
        int min = Math.min(i4, remaining());
        for (int i5 = 0; i5 < min; i5++) {
            CharSequence charSequence = this.seq;
            int i6 = this.pos;
            this.pos = i6 + 1;
            cArr[i3 + i5] = charSequence.charAt(i6);
        }
        return min;
    }
}
