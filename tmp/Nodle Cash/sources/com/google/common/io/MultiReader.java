package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
class MultiReader extends Reader {
    @CheckForNull
    private Reader current;
    private final Iterator<? extends CharSource> it;

    public MultiReader(Iterator<? extends CharSource> it2) throws IOException {
        this.it = it2;
        advance();
    }

    private void advance() throws IOException {
        close();
        if (this.it.hasNext()) {
            this.current = ((CharSource) this.it.next()).openStream();
        }
    }

    public void close() throws IOException {
        Reader reader = this.current;
        if (reader != null) {
            try {
                reader.close();
            } finally {
                this.current = null;
            }
        }
    }

    public int read(char[] cArr, int i3, int i4) throws IOException {
        Preconditions.checkNotNull(cArr);
        Reader reader = this.current;
        if (reader == null) {
            return -1;
        }
        int read = reader.read(cArr, i3, i4);
        if (read != -1) {
            return read;
        }
        advance();
        return read(cArr, i3, i4);
    }

    public boolean ready() throws IOException {
        Reader reader = this.current;
        return reader != null && reader.ready();
    }

    public long skip(long j2) throws IOException {
        int i3 = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
        Preconditions.checkArgument(i3 >= 0, "n is negative");
        if (i3 > 0) {
            while (true) {
                Reader reader = this.current;
                if (reader == null) {
                    break;
                }
                long skip = reader.skip(j2);
                if (skip > 0) {
                    return skip;
                }
                advance();
            }
        }
        return 0;
    }
}
