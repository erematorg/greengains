package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

@ElementTypesAreNonnullByDefault
@Beta
public final class HashingInputStream extends FilterInputStream {
    private final Hasher hasher;

    public HashingInputStream(HashFunction hashFunction, InputStream inputStream) {
        super((InputStream) Preconditions.checkNotNull(inputStream));
        this.hasher = (Hasher) Preconditions.checkNotNull(hashFunction.newHasher());
    }

    public HashCode hash() {
        return this.hasher.hash();
    }

    public void mark(int i3) {
    }

    public boolean markSupported() {
        return false;
    }

    @CanIgnoreReturnValue
    public int read() throws IOException {
        int read = this.in.read();
        if (read != -1) {
            this.hasher.putByte((byte) read);
        }
        return read;
    }

    public void reset() throws IOException {
        throw new IOException("reset not supported");
    }

    @CanIgnoreReturnValue
    public int read(byte[] bArr, int i3, int i4) throws IOException {
        int read = this.in.read(bArr, i3, i4);
        if (read != -1) {
            this.hasher.putBytes(bArr, i3, read);
        }
        return read;
    }
}
