package com.appsamurai.storyly.exoplayer2.core.analytics;

import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;
import com.appsamurai.storyly.exoplayer2.core.source.LoadEventInfo;
import com.appsamurai.storyly.exoplayer2.core.source.MediaLoadData;
import java.io.IOException;

public final /* synthetic */ class p implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f4441a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ LoadEventInfo f4442b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ MediaLoadData f4443c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ IOException f4444d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ boolean f4445e;

    public /* synthetic */ p(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z2) {
        this.f4441a = eventTime;
        this.f4442b = loadEventInfo;
        this.f4443c = mediaLoadData;
        this.f4444d = iOException;
        this.f4445e = z2;
    }

    public final void invoke(Object obj) {
        MediaLoadData mediaLoadData = this.f4443c;
        IOException iOException = this.f4444d;
        ((AnalyticsListener) obj).onLoadError(this.f4441a, this.f4442b, mediaLoadData, iOException, this.f4445e);
    }
}
