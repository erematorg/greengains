package com.bumptech.glide.load.resource.bytes;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Preconditions;

public class BytesResource implements Resource<byte[]> {
    private final byte[] bytes;

    public BytesResource(byte[] bArr) {
        this.bytes = (byte[]) Preconditions.checkNotNull(bArr);
    }

    @NonNull
    public Class<byte[]> getResourceClass() {
        return byte[].class;
    }

    public int getSize() {
        return this.bytes.length;
    }

    public void recycle() {
    }

    @NonNull
    public byte[] get() {
        return this.bytes;
    }
}
