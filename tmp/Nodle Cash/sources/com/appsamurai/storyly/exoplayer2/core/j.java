package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.core.ExoPlayer;
import com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelector;
import com.google.common.base.Supplier;

public final /* synthetic */ class j implements Supplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4504a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ TrackSelector f4505b;

    public /* synthetic */ j(TrackSelector trackSelector, int i3) {
        this.f4504a = i3;
        this.f4505b = trackSelector;
    }

    public final Object get() {
        int i3 = this.f4504a;
        TrackSelector trackSelector = this.f4505b;
        switch (i3) {
            case 0:
                return ExoPlayer.Builder.lambda$setTrackSelector$18(trackSelector);
            default:
                return ExoPlayer.Builder.lambda$new$10(trackSelector);
        }
    }
}
