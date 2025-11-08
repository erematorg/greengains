package com.appsamurai.storyly.exoplayer2.core.analytics;

import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;

public final /* synthetic */ class i implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4417a = 1;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f4418b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f4419c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ long f4420d;

    public /* synthetic */ i(AnalyticsListener.EventTime eventTime, int i3, long j2) {
        this.f4418b = eventTime;
        this.f4419c = i3;
        this.f4420d = j2;
    }

    public final void invoke(Object obj) {
        AnalyticsListener analyticsListener = (AnalyticsListener) obj;
        switch (this.f4417a) {
            case 0:
                analyticsListener.onVideoFrameProcessingOffset(this.f4418b, this.f4420d, this.f4419c);
                return;
            default:
                analyticsListener.onDroppedVideoFrames(this.f4418b, this.f4419c, this.f4420d);
                return;
        }
    }

    public /* synthetic */ i(AnalyticsListener.EventTime eventTime, long j2, int i3) {
        this.f4418b = eventTime;
        this.f4420d = j2;
        this.f4419c = i3;
    }
}
