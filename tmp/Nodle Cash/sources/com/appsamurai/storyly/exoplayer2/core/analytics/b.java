package com.appsamurai.storyly.exoplayer2.core.analytics;

import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderReuseEvaluation;

public final /* synthetic */ class b implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4395a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f4396b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Format f4397c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ DecoderReuseEvaluation f4398d;

    public /* synthetic */ b(AnalyticsListener.EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation, int i3) {
        this.f4395a = i3;
        this.f4396b = eventTime;
        this.f4397c = format;
        this.f4398d = decoderReuseEvaluation;
    }

    public final void invoke(Object obj) {
        AnalyticsListener analyticsListener = (AnalyticsListener) obj;
        switch (this.f4395a) {
            case 0:
                DefaultAnalyticsCollector.lambda$onVideoInputFormatChanged$15(this.f4396b, this.f4397c, this.f4398d, analyticsListener);
                return;
            default:
                DefaultAnalyticsCollector.lambda$onAudioInputFormatChanged$5(this.f4396b, this.f4397c, this.f4398d, analyticsListener);
                return;
        }
    }
}
