package com.appsamurai.storyly.exoplayer2.core.analytics;

import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;

public final /* synthetic */ class o implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f4439a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ float f4440b;

    public /* synthetic */ o(AnalyticsListener.EventTime eventTime, float f2) {
        this.f4439a = eventTime;
        this.f4440b = f2;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onVolumeChanged(this.f4439a, this.f4440b);
    }
}
