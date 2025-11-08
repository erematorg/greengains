package com.appsamurai.storyly.exoplayer2.core.source;

import com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId;
import com.appsamurai.storyly.exoplayer2.core.source.ProgressiveMediaExtractor;
import com.appsamurai.storyly.exoplayer2.core.source.ProgressiveMediaSource;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorsFactory;

public final /* synthetic */ class k implements ProgressiveMediaExtractor.Factory {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ExtractorsFactory f4577a;

    public /* synthetic */ k(ExtractorsFactory extractorsFactory) {
        this.f4577a = extractorsFactory;
    }

    public final ProgressiveMediaExtractor createProgressiveMediaExtractor(PlayerId playerId) {
        return ProgressiveMediaSource.Factory.lambda$new$0(this.f4577a, playerId);
    }
}
