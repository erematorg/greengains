package com.appsamurai.storyly.exoplayer2.core.analytics;

import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;
import com.appsamurai.storyly.exoplayer2.core.source.MediaLoadData;

public final /* synthetic */ class d implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4402a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f4403b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ MediaLoadData f4404c;

    public /* synthetic */ d(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData, int i3) {
        this.f4402a = i3;
        this.f4403b = eventTime;
        this.f4404c = mediaLoadData;
    }

    public final void invoke(Object obj) {
        AnalyticsListener analyticsListener = (AnalyticsListener) obj;
        switch (this.f4402a) {
            case 0:
                analyticsListener.onDownstreamFormatChanged(this.f4403b, this.f4404c);
                return;
            default:
                analyticsListener.onUpstreamDiscarded(this.f4403b, this.f4404c);
                return;
        }
    }
}
