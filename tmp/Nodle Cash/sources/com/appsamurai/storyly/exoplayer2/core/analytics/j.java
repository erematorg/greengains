package com.appsamurai.storyly.exoplayer2.core.analytics;

import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;

public final /* synthetic */ class j implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4421a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f4422b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f4423c;

    public /* synthetic */ j(AnalyticsListener.EventTime eventTime, long j2, int i3) {
        this.f4421a = i3;
        this.f4422b = eventTime;
        this.f4423c = j2;
    }

    public final void invoke(Object obj) {
        AnalyticsListener analyticsListener = (AnalyticsListener) obj;
        switch (this.f4421a) {
            case 0:
                analyticsListener.onSeekForwardIncrementChanged(this.f4422b, this.f4423c);
                return;
            case 1:
                analyticsListener.onMaxSeekToPreviousPositionChanged(this.f4422b, this.f4423c);
                return;
            case 2:
                analyticsListener.onAudioPositionAdvancing(this.f4422b, this.f4423c);
                return;
            default:
                analyticsListener.onSeekBackIncrementChanged(this.f4422b, this.f4423c);
                return;
        }
    }
}
