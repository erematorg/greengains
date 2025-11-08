package com.appsamurai.storyly.exoplayer2.core.source;

import T1.a;
import android.os.Handler;
import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public interface MediaSourceEventListener {

    public static class EventDispatcher {
        private final CopyOnWriteArrayList<ListenerAndHandler> listenerAndHandlers;
        @Nullable
        public final MediaSource.MediaPeriodId mediaPeriodId;
        private final long mediaTimeOffsetMs;
        public final int windowIndex;

        public static final class ListenerAndHandler {
            public Handler handler;
            public MediaSourceEventListener listener;

            public ListenerAndHandler(Handler handler2, MediaSourceEventListener mediaSourceEventListener) {
                this.handler = handler2;
                this.listener = mediaSourceEventListener;
            }
        }

        public EventDispatcher() {
            this(new CopyOnWriteArrayList(), 0, (MediaSource.MediaPeriodId) null, 0);
        }

        private long adjustMediaTime(long j2) {
            long usToMs = Util.usToMs(j2);
            return usToMs == C.TIME_UNSET ? C.TIME_UNSET : this.mediaTimeOffsetMs + usToMs;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$downstreamFormatChanged$5(MediaSourceEventListener mediaSourceEventListener, MediaLoadData mediaLoadData) {
            mediaSourceEventListener.onDownstreamFormatChanged(this.windowIndex, this.mediaPeriodId, mediaLoadData);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$loadCanceled$2(MediaSourceEventListener mediaSourceEventListener, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            mediaSourceEventListener.onLoadCanceled(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$loadCompleted$1(MediaSourceEventListener mediaSourceEventListener, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            mediaSourceEventListener.onLoadCompleted(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$loadError$3(MediaSourceEventListener mediaSourceEventListener, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z2) {
            mediaSourceEventListener.onLoadError(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData, iOException, z2);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$loadStarted$0(MediaSourceEventListener mediaSourceEventListener, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            mediaSourceEventListener.onLoadStarted(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$upstreamDiscarded$4(MediaSourceEventListener mediaSourceEventListener, MediaSource.MediaPeriodId mediaPeriodId2, MediaLoadData mediaLoadData) {
            mediaSourceEventListener.onUpstreamDiscarded(this.windowIndex, mediaPeriodId2, mediaLoadData);
        }

        public void addEventListener(Handler handler, MediaSourceEventListener mediaSourceEventListener) {
            Assertions.checkNotNull(handler);
            Assertions.checkNotNull(mediaSourceEventListener);
            this.listenerAndHandlers.add(new ListenerAndHandler(handler, mediaSourceEventListener));
        }

        public void downstreamFormatChanged(int i3, @Nullable Format format, int i4, @Nullable Object obj, long j2) {
            downstreamFormatChanged(new MediaLoadData(1, i3, format, i4, obj, adjustMediaTime(j2), C.TIME_UNSET));
        }

        public void loadCanceled(LoadEventInfo loadEventInfo, int i3) {
            loadCanceled(loadEventInfo, i3, -1, (Format) null, 0, (Object) null, C.TIME_UNSET, C.TIME_UNSET);
        }

        public void loadCompleted(LoadEventInfo loadEventInfo, int i3) {
            loadCompleted(loadEventInfo, i3, -1, (Format) null, 0, (Object) null, C.TIME_UNSET, C.TIME_UNSET);
        }

        public void loadError(LoadEventInfo loadEventInfo, int i3, IOException iOException, boolean z2) {
            loadError(loadEventInfo, i3, -1, (Format) null, 0, (Object) null, C.TIME_UNSET, C.TIME_UNSET, iOException, z2);
        }

        public void loadStarted(LoadEventInfo loadEventInfo, int i3) {
            loadStarted(loadEventInfo, i3, -1, (Format) null, 0, (Object) null, C.TIME_UNSET, C.TIME_UNSET);
        }

        public void removeEventListener(MediaSourceEventListener mediaSourceEventListener) {
            Iterator<ListenerAndHandler> it = this.listenerAndHandlers.iterator();
            while (it.hasNext()) {
                ListenerAndHandler next = it.next();
                if (next.listener == mediaSourceEventListener) {
                    this.listenerAndHandlers.remove(next);
                }
            }
        }

        public void upstreamDiscarded(int i3, long j2, long j3) {
            long j4 = j2;
            upstreamDiscarded(new MediaLoadData(1, i3, (Format) null, 3, (Object) null, adjustMediaTime(j2), adjustMediaTime(j3)));
        }

        @CheckResult
        public EventDispatcher withParameters(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId2, long j2) {
            return new EventDispatcher(this.listenerAndHandlers, i3, mediaPeriodId2, j2);
        }

        private EventDispatcher(CopyOnWriteArrayList<ListenerAndHandler> copyOnWriteArrayList, int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId2, long j2) {
            this.listenerAndHandlers = copyOnWriteArrayList;
            this.windowIndex = i3;
            this.mediaPeriodId = mediaPeriodId2;
            this.mediaTimeOffsetMs = j2;
        }

        public void loadCanceled(LoadEventInfo loadEventInfo, int i3, int i4, @Nullable Format format, int i5, @Nullable Object obj, long j2, long j3) {
            LoadEventInfo loadEventInfo2 = loadEventInfo;
            loadCanceled(loadEventInfo, new MediaLoadData(i3, i4, format, i5, obj, adjustMediaTime(j2), adjustMediaTime(j3)));
        }

        public void loadCompleted(LoadEventInfo loadEventInfo, int i3, int i4, @Nullable Format format, int i5, @Nullable Object obj, long j2, long j3) {
            LoadEventInfo loadEventInfo2 = loadEventInfo;
            loadCompleted(loadEventInfo, new MediaLoadData(i3, i4, format, i5, obj, adjustMediaTime(j2), adjustMediaTime(j3)));
        }

        public void loadError(LoadEventInfo loadEventInfo, int i3, int i4, @Nullable Format format, int i5, @Nullable Object obj, long j2, long j3, IOException iOException, boolean z2) {
            LoadEventInfo loadEventInfo2 = loadEventInfo;
            loadError(loadEventInfo, new MediaLoadData(i3, i4, format, i5, obj, adjustMediaTime(j2), adjustMediaTime(j3)), iOException, z2);
        }

        public void loadStarted(LoadEventInfo loadEventInfo, int i3, int i4, @Nullable Format format, int i5, @Nullable Object obj, long j2, long j3) {
            LoadEventInfo loadEventInfo2 = loadEventInfo;
            loadStarted(loadEventInfo, new MediaLoadData(i3, i4, format, i5, obj, adjustMediaTime(j2), adjustMediaTime(j3)));
        }

        public void downstreamFormatChanged(MediaLoadData mediaLoadData) {
            Iterator<ListenerAndHandler> it = this.listenerAndHandlers.iterator();
            while (it.hasNext()) {
                ListenerAndHandler next = it.next();
                Util.postOrRun(next.handler, new a(this, 13, next.listener, mediaLoadData));
            }
        }

        public void upstreamDiscarded(MediaLoadData mediaLoadData) {
            MediaSource.MediaPeriodId mediaPeriodId2 = (MediaSource.MediaPeriodId) Assertions.checkNotNull(this.mediaPeriodId);
            Iterator<ListenerAndHandler> it = this.listenerAndHandlers.iterator();
            while (it.hasNext()) {
                ListenerAndHandler next = it.next();
                Util.postOrRun(next.handler, new K0.a(8, this, next.listener, mediaPeriodId2, mediaLoadData));
            }
        }

        public void loadCanceled(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            Iterator<ListenerAndHandler> it = this.listenerAndHandlers.iterator();
            while (it.hasNext()) {
                ListenerAndHandler next = it.next();
                Util.postOrRun(next.handler, new g(this, next.listener, loadEventInfo, mediaLoadData, 2));
            }
        }

        public void loadCompleted(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            Iterator<ListenerAndHandler> it = this.listenerAndHandlers.iterator();
            while (it.hasNext()) {
                ListenerAndHandler next = it.next();
                Util.postOrRun(next.handler, new g(this, next.listener, loadEventInfo, mediaLoadData, 0));
            }
        }

        public void loadError(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z2) {
            Iterator<ListenerAndHandler> it = this.listenerAndHandlers.iterator();
            while (it.hasNext()) {
                ListenerAndHandler next = it.next();
                Util.postOrRun(next.handler, new h(this, next.listener, loadEventInfo, mediaLoadData, iOException, z2));
            }
        }

        public void loadStarted(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            Iterator<ListenerAndHandler> it = this.listenerAndHandlers.iterator();
            while (it.hasNext()) {
                ListenerAndHandler next = it.next();
                Util.postOrRun(next.handler, new g(this, next.listener, loadEventInfo, mediaLoadData, 1));
            }
        }
    }

    void onDownstreamFormatChanged(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
    }

    void onLoadCanceled(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    void onLoadCompleted(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    void onLoadError(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z2) {
    }

    void onLoadStarted(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    void onUpstreamDiscarded(int i3, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
    }
}
