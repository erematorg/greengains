package com.appsamurai.storyly.exoplayer2.extractor.text.cea;

import com.appsamurai.storyly.exoplayer2.decoder.DecoderOutputBuffer;
import com.appsamurai.storyly.exoplayer2.extractor.text.cea.CeaDecoder;

public final /* synthetic */ class b implements DecoderOutputBuffer.Owner {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CeaDecoder f4622a;

    public /* synthetic */ b(CeaDecoder ceaDecoder) {
        this.f4622a = ceaDecoder;
    }

    public final void releaseOutputBuffer(DecoderOutputBuffer decoderOutputBuffer) {
        this.f4622a.releaseOutputBuffer((CeaDecoder.CeaOutputBuffer) decoderOutputBuffer);
    }
}
