package com.appsamurai.storyly.exoplayer2.core.analytics;

import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;

public final /* synthetic */ class l implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f4429a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ int f4430b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Player.PositionInfo f4431c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Player.PositionInfo f4432d;

    public /* synthetic */ l(AnalyticsListener.EventTime eventTime, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i3) {
        this.f4429a = eventTime;
        this.f4430b = i3;
        this.f4431c = positionInfo;
        this.f4432d = positionInfo2;
    }

    public final void invoke(Object obj) {
        int i3 = this.f4430b;
        Player.PositionInfo positionInfo = this.f4431c;
        DefaultAnalyticsCollector.lambda$onPositionDiscontinuity$43(this.f4429a, i3, positionInfo, this.f4432d, (AnalyticsListener) obj);
    }
}
