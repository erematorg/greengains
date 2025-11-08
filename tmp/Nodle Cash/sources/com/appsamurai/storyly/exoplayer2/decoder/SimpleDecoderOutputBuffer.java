package com.appsamurai.storyly.exoplayer2.decoder;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderOutputBuffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class SimpleDecoderOutputBuffer extends DecoderOutputBuffer {
    @Nullable
    public ByteBuffer data;
    private final DecoderOutputBuffer.Owner<SimpleDecoderOutputBuffer> owner;

    public SimpleDecoderOutputBuffer(DecoderOutputBuffer.Owner<SimpleDecoderOutputBuffer> owner2) {
        this.owner = owner2;
    }

    public void clear() {
        super.clear();
        ByteBuffer byteBuffer = this.data;
        if (byteBuffer != null) {
            byteBuffer.clear();
        }
    }

    public ByteBuffer init(long j2, int i3) {
        this.timeUs = j2;
        ByteBuffer byteBuffer = this.data;
        if (byteBuffer == null || byteBuffer.capacity() < i3) {
            this.data = ByteBuffer.allocateDirect(i3).order(ByteOrder.nativeOrder());
        }
        this.data.position(0);
        this.data.limit(i3);
        return this.data;
    }

    public void release() {
        this.owner.releaseOutputBuffer(this);
    }
}
