package com.appsamurai.storyly.exoplayer2.core;

import android.content.Context;
import com.appsamurai.storyly.exoplayer2.core.ExoPlayer;
import com.appsamurai.storyly.exoplayer2.core.upstream.DefaultBandwidthMeter;
import com.google.common.base.Supplier;

public final /* synthetic */ class c implements Supplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4481a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Context f4482b;

    public /* synthetic */ c(Context context, int i3) {
        this.f4481a = i3;
        this.f4482b = context;
    }

    public final Object get() {
        int i3 = this.f4481a;
        Context context = this.f4482b;
        switch (i3) {
            case 0:
                return ExoPlayer.Builder.lambda$new$14(context);
            case 1:
                return DefaultBandwidthMeter.getSingletonInstance(context);
            case 2:
                return ExoPlayer.Builder.lambda$new$4(context);
            case 3:
                return ExoPlayer.Builder.lambda$new$0(context);
            case 4:
                return ExoPlayer.Builder.lambda$new$1(context);
            default:
                return ExoPlayer.Builder.lambda$new$3(context);
        }
    }
}
