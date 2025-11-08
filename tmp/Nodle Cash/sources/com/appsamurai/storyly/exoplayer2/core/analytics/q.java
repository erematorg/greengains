package com.appsamurai.storyly.exoplayer2.core.analytics;

import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;
import com.appsamurai.storyly.exoplayer2.core.source.LoadEventInfo;
import com.appsamurai.storyly.exoplayer2.core.source.MediaLoadData;

public final /* synthetic */ class q implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4446a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f4447b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ LoadEventInfo f4448c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ MediaLoadData f4449d;

    public /* synthetic */ q(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i3) {
        this.f4446a = i3;
        this.f4447b = eventTime;
        this.f4448c = loadEventInfo;
        this.f4449d = mediaLoadData;
    }

    public final void invoke(Object obj) {
        AnalyticsListener analyticsListener = (AnalyticsListener) obj;
        switch (this.f4446a) {
            case 0:
                analyticsListener.onLoadCanceled(this.f4447b, this.f4448c, this.f4449d);
                return;
            case 1:
                analyticsListener.onLoadStarted(this.f4447b, this.f4448c, this.f4449d);
                return;
            default:
                analyticsListener.onLoadCompleted(this.f4447b, this.f4448c, this.f4449d);
                return;
        }
    }
}
