package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.core.ExoPlayer;
import com.google.common.base.Supplier;

public final /* synthetic */ class b implements Supplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4479a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ LoadControl f4480b;

    public /* synthetic */ b(LoadControl loadControl, int i3) {
        this.f4479a = i3;
        this.f4480b = loadControl;
    }

    public final Object get() {
        int i3 = this.f4479a;
        LoadControl loadControl = this.f4480b;
        switch (i3) {
            case 0:
                return ExoPlayer.Builder.lambda$setLoadControl$19(loadControl);
            default:
                return ExoPlayer.Builder.lambda$new$11(loadControl);
        }
    }
}
