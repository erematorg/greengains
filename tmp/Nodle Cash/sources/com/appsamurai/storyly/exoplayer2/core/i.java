package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.core.ExoPlayer;
import com.google.common.base.Supplier;

public final /* synthetic */ class i implements Supplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4502a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ RenderersFactory f4503b;

    public /* synthetic */ i(RenderersFactory renderersFactory, int i3) {
        this.f4502a = i3;
        this.f4503b = renderersFactory;
    }

    public final Object get() {
        int i3 = this.f4502a;
        RenderersFactory renderersFactory = this.f4503b;
        switch (i3) {
            case 0:
                return ExoPlayer.Builder.lambda$new$6(renderersFactory);
            case 1:
                return ExoPlayer.Builder.lambda$setRenderersFactory$16(renderersFactory);
            case 2:
                return ExoPlayer.Builder.lambda$new$8(renderersFactory);
            default:
                return ExoPlayer.Builder.lambda$new$2(renderersFactory);
        }
    }
}
