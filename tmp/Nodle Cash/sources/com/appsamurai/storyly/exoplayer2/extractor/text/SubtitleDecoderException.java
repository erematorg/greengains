package com.appsamurai.storyly.exoplayer2.extractor.text;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderException;

public class SubtitleDecoderException extends DecoderException {
    public SubtitleDecoderException(String str) {
        super(str);
    }

    public SubtitleDecoderException(@Nullable Throwable th) {
        super(th);
    }

    public SubtitleDecoderException(String str, @Nullable Throwable th) {
        super(str, th);
    }
}
