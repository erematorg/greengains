package com.appsamurai.storyly.exoplayer2.core.analytics;

import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;

public final /* synthetic */ class g implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4411a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f4412b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Exception f4413c;

    public /* synthetic */ g(AnalyticsListener.EventTime eventTime, Exception exc, int i3) {
        this.f4411a = i3;
        this.f4412b = eventTime;
        this.f4413c = exc;
    }

    public final void invoke(Object obj) {
        AnalyticsListener analyticsListener = (AnalyticsListener) obj;
        switch (this.f4411a) {
            case 0:
                analyticsListener.onDrmSessionManagerError(this.f4412b, this.f4413c);
                return;
            case 1:
                analyticsListener.onVideoCodecError(this.f4412b, this.f4413c);
                return;
            case 2:
                analyticsListener.onAudioSinkError(this.f4412b, this.f4413c);
                return;
            default:
                analyticsListener.onAudioCodecError(this.f4412b, this.f4413c);
                return;
        }
    }
}
