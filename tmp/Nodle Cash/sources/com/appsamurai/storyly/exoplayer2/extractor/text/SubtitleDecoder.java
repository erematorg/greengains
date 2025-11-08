package com.appsamurai.storyly.exoplayer2.extractor.text;

import com.appsamurai.storyly.exoplayer2.decoder.Decoder;

public interface SubtitleDecoder extends Decoder<SubtitleInputBuffer, SubtitleOutputBuffer, SubtitleDecoderException> {
    void setPositionUs(long j2);
}
