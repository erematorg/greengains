package com.appsamurai.storyly.exoplayer2.core.analytics;

import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;

public final /* synthetic */ class f implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4407a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f4408b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ boolean f4409c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ int f4410d;

    public /* synthetic */ f(AnalyticsListener.EventTime eventTime, int i3, boolean z2) {
        this.f4407a = 0;
        this.f4408b = eventTime;
        this.f4410d = i3;
        this.f4409c = z2;
    }

    public final void invoke(Object obj) {
        AnalyticsListener analyticsListener = (AnalyticsListener) obj;
        switch (this.f4407a) {
            case 0:
                analyticsListener.onDeviceVolumeChanged(this.f4408b, this.f4410d, this.f4409c);
                return;
            case 1:
                analyticsListener.onPlayerStateChanged(this.f4408b, this.f4409c, this.f4410d);
                return;
            default:
                analyticsListener.onPlayWhenReadyChanged(this.f4408b, this.f4409c, this.f4410d);
                return;
        }
    }

    public /* synthetic */ f(AnalyticsListener.EventTime eventTime, boolean z2, int i3, int i4) {
        this.f4407a = i4;
        this.f4408b = eventTime;
        this.f4409c = z2;
        this.f4410d = i3;
    }
}
