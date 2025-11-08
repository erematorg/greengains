package com.appsamurai.storyly.exoplayer2.core.source;

import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;

public final /* synthetic */ class a implements MediaSource.MediaSourceCaller {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CompositeMediaSource f4543a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f4544b;

    public /* synthetic */ a(CompositeMediaSource compositeMediaSource, Object obj) {
        this.f4543a = compositeMediaSource;
        this.f4544b = obj;
    }

    public final void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline) {
        this.f4543a.lambda$prepareChildSource$0(this.f4544b, mediaSource, timeline);
    }
}
