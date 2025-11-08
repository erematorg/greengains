package com.appsamurai.storyly.exoplayer2.core.source;

import android.os.Bundle;
import com.appsamurai.storyly.exoplayer2.common.Bundleable;
import com.appsamurai.storyly.exoplayer2.common.util.Consumer;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId;
import com.appsamurai.storyly.exoplayer2.core.source.ProgressiveMediaExtractor;
import com.appsamurai.storyly.exoplayer2.core.source.SampleQueue;

public final /* synthetic */ class l implements Consumer, ProgressiveMediaExtractor.Factory, Bundleable.Creator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4578a;

    public /* synthetic */ l(int i3) {
        this.f4578a = i3;
    }

    public void accept(Object obj) {
        switch (this.f4578a) {
            case 0:
                ((SampleQueue.SharedSampleMetadata) obj).drmSessionReference.release();
                return;
            default:
                SpannedData.lambda$new$0(obj);
                return;
        }
    }

    public ProgressiveMediaExtractor createProgressiveMediaExtractor(PlayerId playerId) {
        return new MediaParserExtractorAdapter(playerId);
    }

    public Bundleable fromBundle(Bundle bundle) {
        return TrackGroupArray.lambda$static$0(bundle);
    }
}
