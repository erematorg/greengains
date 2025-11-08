package com.appsamurai.storyly.exoplayer2.core.analytics;

import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;

public final /* synthetic */ class e implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4405a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f4406b;

    public /* synthetic */ e(AnalyticsListener.EventTime eventTime, int i3) {
        this.f4405a = i3;
        this.f4406b = eventTime;
    }

    public final void invoke(Object obj) {
        int i3 = this.f4405a;
        AnalyticsListener.EventTime eventTime = this.f4406b;
        AnalyticsListener analyticsListener = (AnalyticsListener) obj;
        switch (i3) {
            case 0:
                analyticsListener.onDrmSessionReleased(eventTime);
                return;
            case 1:
                analyticsListener.onSeekProcessed(eventTime);
                return;
            case 2:
                analyticsListener.onSeekStarted(eventTime);
                return;
            case 3:
                analyticsListener.onDrmKeysRemoved(eventTime);
                return;
            case 4:
                analyticsListener.onDrmKeysRestored(eventTime);
                return;
            case 5:
                analyticsListener.onPlayerReleased(eventTime);
                return;
            default:
                analyticsListener.onDrmKeysLoaded(eventTime);
                return;
        }
    }
}
