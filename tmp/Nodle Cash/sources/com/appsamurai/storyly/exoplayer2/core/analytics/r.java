package com.appsamurai.storyly.exoplayer2.core.analytics;

import com.appsamurai.storyly.exoplayer2.common.MediaMetadata;
import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;

public final /* synthetic */ class r implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4450a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f4451b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ MediaMetadata f4452c;

    public /* synthetic */ r(AnalyticsListener.EventTime eventTime, MediaMetadata mediaMetadata, int i3) {
        this.f4450a = i3;
        this.f4451b = eventTime;
        this.f4452c = mediaMetadata;
    }

    public final void invoke(Object obj) {
        AnalyticsListener analyticsListener = (AnalyticsListener) obj;
        switch (this.f4450a) {
            case 0:
                analyticsListener.onPlaylistMetadataChanged(this.f4451b, this.f4452c);
                return;
            default:
                analyticsListener.onMediaMetadataChanged(this.f4451b, this.f4452c);
                return;
        }
    }
}
