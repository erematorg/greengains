package com.appsamurai.storyly.exoplayer2.core.source;

import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorsFactory;

public final /* synthetic */ class c implements ExtractorsFactory {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Format f4555a;

    public /* synthetic */ c(Format format) {
        this.f4555a = format;
    }

    public final Extractor[] createExtractors() {
        return DefaultMediaSourceFactory.lambda$createMediaSource$0(this.f4555a);
    }
}
