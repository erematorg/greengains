package com.appsamurai.storyly.exoplayer2.core.analytics;

import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;

public final /* synthetic */ class h implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4414a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f4415b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f4416c;

    public /* synthetic */ h(AnalyticsListener.EventTime eventTime, int i3, int i4) {
        this.f4414a = i4;
        this.f4415b = eventTime;
        this.f4416c = i3;
    }

    public final void invoke(Object obj) {
        AnalyticsListener analyticsListener = (AnalyticsListener) obj;
        switch (this.f4414a) {
            case 0:
                DefaultAnalyticsCollector.lambda$onDrmSessionAcquired$62(this.f4415b, this.f4416c, analyticsListener);
                return;
            case 1:
                analyticsListener.onPlaybackSuppressionReasonChanged(this.f4415b, this.f4416c);
                return;
            case 2:
                analyticsListener.onTimelineChanged(this.f4415b, this.f4416c);
                return;
            case 3:
                analyticsListener.onRepeatModeChanged(this.f4415b, this.f4416c);
                return;
            case 4:
                analyticsListener.onPlaybackStateChanged(this.f4415b, this.f4416c);
                return;
            default:
                analyticsListener.onAudioSessionIdChanged(this.f4415b, this.f4416c);
                return;
        }
    }
}
