package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
class AppendableWriter extends Writer {
    private boolean closed;
    private final Appendable target;

    public AppendableWriter(Appendable appendable) {
        this.target = (Appendable) Preconditions.checkNotNull(appendable);
    }

    private void checkNotClosed() throws IOException {
        if (this.closed) {
            throw new IOException("Cannot write to a closed writer.");
        }
    }

    public void close() throws IOException {
        this.closed = true;
        Appendable appendable = this.target;
        if (appendable instanceof Closeable) {
            ((Closeable) appendable).close();
        }
    }

    public void flush() throws IOException {
        checkNotClosed();
        Appendable appendable = this.target;
        if (appendable instanceof Flushable) {
            ((Flushable) appendable).flush();
        }
    }

    public void write(char[] cArr, int i3, int i4) throws IOException {
        checkNotClosed();
        this.target.append(new String(cArr, i3, i4));
    }

    public void write(int i3) throws IOException {
        checkNotClosed();
        this.target.append((char) i3);
    }

    public Writer append(char c3) throws IOException {
        checkNotClosed();
        this.target.append(c3);
        return this;
    }

    public void write(String str) throws IOException {
        Preconditions.checkNotNull(str);
        checkNotClosed();
        this.target.append(str);
    }

    public Writer append(@CheckForNull CharSequence charSequence) throws IOException {
        checkNotClosed();
        this.target.append(charSequence);
        return this;
    }

    public Writer append(@CheckForNull CharSequence charSequence, int i3, int i4) throws IOException {
        checkNotClosed();
        this.target.append(charSequence, i3, i4);
        return this;
    }

    public void write(String str, int i3, int i4) throws IOException {
        Preconditions.checkNotNull(str);
        checkNotClosed();
        this.target.append(str, i3, i4 + i3);
    }
}
