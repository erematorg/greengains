package com.appsamurai.storyly.exoplayer2.decoder;

import androidx.annotation.Nullable;

public class DecoderException extends Exception {
    public DecoderException(String str) {
        super(str);
    }

    public DecoderException(@Nullable Throwable th) {
        super(th);
    }

    public DecoderException(String str, @Nullable Throwable th) {
        super(str, th);
    }
}
