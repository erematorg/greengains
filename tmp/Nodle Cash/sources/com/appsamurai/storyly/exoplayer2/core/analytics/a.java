package com.appsamurai.storyly.exoplayer2.core.analytics;

import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderCounters;

public final /* synthetic */ class a implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4392a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f4393b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ DecoderCounters f4394c;

    public /* synthetic */ a(AnalyticsListener.EventTime eventTime, int i3, DecoderCounters decoderCounters) {
        this.f4392a = i3;
        this.f4393b = eventTime;
        this.f4394c = decoderCounters;
    }

    public final void invoke(Object obj) {
        AnalyticsListener analyticsListener = (AnalyticsListener) obj;
        switch (this.f4392a) {
            case 0:
                DefaultAnalyticsCollector.lambda$onAudioEnabled$3(this.f4393b, this.f4394c, analyticsListener);
                return;
            case 1:
                DefaultAnalyticsCollector.lambda$onVideoDisabled$18(this.f4393b, this.f4394c, analyticsListener);
                return;
            case 2:
                DefaultAnalyticsCollector.lambda$onVideoEnabled$13(this.f4393b, this.f4394c, analyticsListener);
                return;
            default:
                DefaultAnalyticsCollector.lambda$onAudioDisabled$9(this.f4393b, this.f4394c, analyticsListener);
                return;
        }
    }
}
