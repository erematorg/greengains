package com.appsamurai.storyly.exoplayer2.core.analytics;

import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;

public final /* synthetic */ class s implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4453a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f4454b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f4455c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ long f4456d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ long f4457e;

    public /* synthetic */ s(AnalyticsListener.EventTime eventTime, String str, long j2, long j3, int i3) {
        this.f4453a = i3;
        this.f4454b = eventTime;
        this.f4455c = str;
        this.f4456d = j2;
        this.f4457e = j3;
    }

    public final void invoke(Object obj) {
        switch (this.f4453a) {
            case 0:
                String str = this.f4455c;
                long j2 = this.f4456d;
                DefaultAnalyticsCollector.lambda$onAudioDecoderInitialized$4(this.f4454b, str, j2, this.f4457e, (AnalyticsListener) obj);
                return;
            default:
                String str2 = this.f4455c;
                long j3 = this.f4456d;
                DefaultAnalyticsCollector.lambda$onVideoDecoderInitialized$14(this.f4454b, str2, j3, this.f4457e, (AnalyticsListener) obj);
                return;
        }
    }
}
