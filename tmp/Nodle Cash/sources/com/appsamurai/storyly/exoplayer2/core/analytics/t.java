package com.appsamurai.storyly.exoplayer2.core.analytics;

import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

public final /* synthetic */ class t implements ListenerSet.Event, SynchronizationGuard.CriticalSection {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4458a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f4459b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f4460c;

    public /* synthetic */ t(Object obj, Object obj2, int i3) {
        this.f4459b = obj;
        this.f4460c = obj2;
        this.f4458a = i3;
    }

    public Object execute() {
        return ((Uploader) this.f4459b).lambda$upload$0((TransportContext) this.f4460c, this.f4458a);
    }

    public void invoke(Object obj) {
        ((AnalyticsListener) obj).onMediaItemTransition((AnalyticsListener.EventTime) this.f4459b, (MediaItem) this.f4460c, this.f4458a);
    }
}
