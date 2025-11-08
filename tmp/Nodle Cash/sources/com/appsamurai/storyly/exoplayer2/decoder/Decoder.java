package com.appsamurai.storyly.exoplayer2.decoder;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderException;

public interface Decoder<I, O, E extends DecoderException> {
    @Nullable
    I dequeueInputBuffer() throws DecoderException;

    @Nullable
    O dequeueOutputBuffer() throws DecoderException;

    void flush();

    String getName();

    void queueInputBuffer(I i3) throws DecoderException;

    void release();
}
