package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.common.util.Clock;
import com.appsamurai.storyly.exoplayer2.core.ExoPlayer;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsCollector;
import com.google.common.base.Function;

public final /* synthetic */ class h implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4500a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AnalyticsCollector f4501b;

    public /* synthetic */ h(AnalyticsCollector analyticsCollector, int i3) {
        this.f4500a = i3;
        this.f4501b = analyticsCollector;
    }

    public final Object apply(Object obj) {
        int i3 = this.f4500a;
        AnalyticsCollector analyticsCollector = this.f4501b;
        Clock clock = (Clock) obj;
        switch (i3) {
            case 0:
                return ExoPlayer.Builder.lambda$setAnalyticsCollector$21(analyticsCollector, clock);
            default:
                return ExoPlayer.Builder.lambda$new$13(analyticsCollector, clock);
        }
    }
}
