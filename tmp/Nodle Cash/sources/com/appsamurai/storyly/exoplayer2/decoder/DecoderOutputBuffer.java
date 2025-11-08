package com.appsamurai.storyly.exoplayer2.decoder;

public abstract class DecoderOutputBuffer extends Buffer {
    public int skippedOutputBufferCount;
    public long timeUs;

    public interface Owner<S extends DecoderOutputBuffer> {
        void releaseOutputBuffer(S s3);
    }

    public abstract void release();
}
