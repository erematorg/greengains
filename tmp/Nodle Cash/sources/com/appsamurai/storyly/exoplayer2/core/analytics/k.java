package com.appsamurai.storyly.exoplayer2.core.analytics;

import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;

public final /* synthetic */ class k implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4424a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f4425b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f4426c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ long f4427d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ long f4428e;

    public /* synthetic */ k(AnalyticsListener.EventTime eventTime, int i3, long j2, long j3, int i4) {
        this.f4424a = i4;
        this.f4425b = eventTime;
        this.f4426c = i3;
        this.f4427d = j2;
        this.f4428e = j3;
    }

    public final void invoke(Object obj) {
        switch (this.f4424a) {
            case 0:
                int i3 = this.f4426c;
                long j2 = this.f4427d;
                ((AnalyticsListener) obj).onBandwidthEstimate(this.f4425b, i3, j2, this.f4428e);
                return;
            default:
                int i4 = this.f4426c;
                long j3 = this.f4427d;
                ((AnalyticsListener) obj).onAudioUnderrun(this.f4425b, i4, j3, this.f4428e);
                return;
        }
    }
}
