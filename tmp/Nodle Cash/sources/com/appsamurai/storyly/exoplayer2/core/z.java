package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;

public final /* synthetic */ class z implements MediaSource.MediaSourceCaller {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaSourceList f4616a;

    public /* synthetic */ z(MediaSourceList mediaSourceList) {
        this.f4616a = mediaSourceList;
    }

    public final void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline) {
        this.f4616a.lambda$prepareChildSource$0(mediaSource, timeline);
    }
}
