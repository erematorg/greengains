package com.appsamurai.storyly.exoplayer2.core.analytics;

import com.appsamurai.storyly.exoplayer2.common.PlaybackException;
import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;

public final /* synthetic */ class c implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4399a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f4400b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ PlaybackException f4401c;

    public /* synthetic */ c(AnalyticsListener.EventTime eventTime, PlaybackException playbackException, int i3) {
        this.f4399a = i3;
        this.f4400b = eventTime;
        this.f4401c = playbackException;
    }

    public final void invoke(Object obj) {
        AnalyticsListener analyticsListener = (AnalyticsListener) obj;
        switch (this.f4399a) {
            case 0:
                analyticsListener.onPlayerErrorChanged(this.f4400b, this.f4401c);
                return;
            default:
                analyticsListener.onPlayerError(this.f4400b, this.f4401c);
                return;
        }
    }
}
