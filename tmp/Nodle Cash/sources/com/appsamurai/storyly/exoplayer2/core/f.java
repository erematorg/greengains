package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.core.ExoPlayer;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.google.common.base.Supplier;

public final /* synthetic */ class f implements Supplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4496a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ MediaSource.Factory f4497b;

    public /* synthetic */ f(MediaSource.Factory factory, int i3) {
        this.f4496a = i3;
        this.f4497b = factory;
    }

    public final Object get() {
        int i3 = this.f4496a;
        MediaSource.Factory factory = this.f4497b;
        switch (i3) {
            case 0:
                return ExoPlayer.Builder.lambda$new$5(factory);
            case 1:
                return ExoPlayer.Builder.lambda$setMediaSourceFactory$17(factory);
            case 2:
                return ExoPlayer.Builder.lambda$new$7(factory);
            default:
                return ExoPlayer.Builder.lambda$new$9(factory);
        }
    }
}
