package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.google.common.collect.ImmutableList;

public final /* synthetic */ class y implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaPeriodQueue f4613a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ImmutableList.Builder f4614b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ MediaSource.MediaPeriodId f4615c;

    public /* synthetic */ y(MediaPeriodQueue mediaPeriodQueue, ImmutableList.Builder builder, MediaSource.MediaPeriodId mediaPeriodId) {
        this.f4613a = mediaPeriodQueue;
        this.f4614b = builder;
        this.f4615c = mediaPeriodId;
    }

    public final void run() {
        this.f4613a.lambda$notifyQueueUpdate$0(this.f4614b, this.f4615c);
    }
}
