package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@ElementTypesAreNonnullByDefault
@Beta
public final class HashingOutputStream extends FilterOutputStream {
    private final Hasher hasher;

    public HashingOutputStream(HashFunction hashFunction, OutputStream outputStream) {
        super((OutputStream) Preconditions.checkNotNull(outputStream));
        this.hasher = (Hasher) Preconditions.checkNotNull(hashFunction.newHasher());
    }

    public void close() throws IOException {
        this.out.close();
    }

    public HashCode hash() {
        return this.hasher.hash();
    }

    public void write(int i3) throws IOException {
        this.hasher.putByte((byte) i3);
        this.out.write(i3);
    }

    public void write(byte[] bArr, int i3, int i4) throws IOException {
        this.hasher.putBytes(bArr, i3, i4);
        this.out.write(bArr, i3, i4);
    }
}
