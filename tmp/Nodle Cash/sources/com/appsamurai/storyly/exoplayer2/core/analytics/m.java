package com.appsamurai.storyly.exoplayer2.core.analytics;

import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;

public final /* synthetic */ class m implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4433a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f4434b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f4435c;

    public /* synthetic */ m(AnalyticsListener.EventTime eventTime, String str, int i3) {
        this.f4433a = i3;
        this.f4434b = eventTime;
        this.f4435c = str;
    }

    public final void invoke(Object obj) {
        AnalyticsListener analyticsListener = (AnalyticsListener) obj;
        switch (this.f4433a) {
            case 0:
                analyticsListener.onAudioDecoderReleased(this.f4434b, this.f4435c);
                return;
            default:
                analyticsListener.onVideoDecoderReleased(this.f4434b, this.f4435c);
                return;
        }
    }
}
