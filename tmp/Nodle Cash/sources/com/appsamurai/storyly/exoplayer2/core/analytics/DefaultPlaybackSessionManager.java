package com.appsamurai.storyly.exoplayer2.core.analytics;

import android.util.Base64;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlaybackSessionManager;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.google.common.base.Supplier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class DefaultPlaybackSessionManager implements PlaybackSessionManager {
    public static final Supplier<String> DEFAULT_SESSION_ID_GENERATOR = new Object();
    private static final Random RANDOM = new Random();
    private static final int SESSION_ID_LENGTH = 12;
    @Nullable
    private String currentSessionId;
    private Timeline currentTimeline;
    private PlaybackSessionManager.Listener listener;
    /* access modifiers changed from: private */
    public final Timeline.Period period;
    private final Supplier<String> sessionIdGenerator;
    private final HashMap<String, SessionDescriptor> sessions;
    /* access modifiers changed from: private */
    public final Timeline.Window window;

    public final class SessionDescriptor {
        /* access modifiers changed from: private */
        public MediaSource.MediaPeriodId adMediaPeriodId;
        /* access modifiers changed from: private */
        public boolean isActive;
        /* access modifiers changed from: private */
        public boolean isCreated;
        /* access modifiers changed from: private */
        public final String sessionId;
        /* access modifiers changed from: private */
        public int windowIndex;
        /* access modifiers changed from: private */
        public long windowSequenceNumber;

        public SessionDescriptor(String str, int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
            this.sessionId = str;
            this.windowIndex = i3;
            this.windowSequenceNumber = mediaPeriodId == null ? -1 : mediaPeriodId.windowSequenceNumber;
            if (mediaPeriodId != null && mediaPeriodId.isAd()) {
                this.adMediaPeriodId = mediaPeriodId;
            }
        }

        private int resolveWindowIndexToNewTimeline(Timeline timeline, Timeline timeline2, int i3) {
            if (i3 < timeline.getWindowCount()) {
                timeline.getWindow(i3, DefaultPlaybackSessionManager.this.window);
                for (int i4 = DefaultPlaybackSessionManager.this.window.firstPeriodIndex; i4 <= DefaultPlaybackSessionManager.this.window.lastPeriodIndex; i4++) {
                    int indexOfPeriod = timeline2.getIndexOfPeriod(timeline.getUidOfPeriod(i4));
                    if (indexOfPeriod != -1) {
                        return timeline2.getPeriod(indexOfPeriod, DefaultPlaybackSessionManager.this.period).windowIndex;
                    }
                }
                return -1;
            } else if (i3 < timeline2.getWindowCount()) {
                return i3;
            } else {
                return -1;
            }
        }

        public boolean belongsToSession(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
            if (mediaPeriodId == null) {
                return i3 == this.windowIndex;
            }
            MediaSource.MediaPeriodId mediaPeriodId2 = this.adMediaPeriodId;
            return mediaPeriodId2 == null ? !mediaPeriodId.isAd() && mediaPeriodId.windowSequenceNumber == this.windowSequenceNumber : mediaPeriodId.windowSequenceNumber == mediaPeriodId2.windowSequenceNumber && mediaPeriodId.adGroupIndex == mediaPeriodId2.adGroupIndex && mediaPeriodId.adIndexInAdGroup == mediaPeriodId2.adIndexInAdGroup;
        }

        public boolean isFinishedAtEventTime(AnalyticsListener.EventTime eventTime) {
            long j2 = this.windowSequenceNumber;
            if (j2 == -1) {
                return false;
            }
            MediaSource.MediaPeriodId mediaPeriodId = eventTime.mediaPeriodId;
            if (mediaPeriodId == null) {
                return this.windowIndex != eventTime.windowIndex;
            }
            if (mediaPeriodId.windowSequenceNumber > j2) {
                return true;
            }
            if (this.adMediaPeriodId == null) {
                return false;
            }
            int indexOfPeriod = eventTime.timeline.getIndexOfPeriod(mediaPeriodId.periodUid);
            int indexOfPeriod2 = eventTime.timeline.getIndexOfPeriod(this.adMediaPeriodId.periodUid);
            MediaSource.MediaPeriodId mediaPeriodId2 = eventTime.mediaPeriodId;
            if (mediaPeriodId2.windowSequenceNumber < this.adMediaPeriodId.windowSequenceNumber || indexOfPeriod < indexOfPeriod2) {
                return false;
            }
            if (indexOfPeriod > indexOfPeriod2) {
                return true;
            }
            if (mediaPeriodId2.isAd()) {
                MediaSource.MediaPeriodId mediaPeriodId3 = eventTime.mediaPeriodId;
                int i3 = mediaPeriodId3.adGroupIndex;
                int i4 = mediaPeriodId3.adIndexInAdGroup;
                MediaSource.MediaPeriodId mediaPeriodId4 = this.adMediaPeriodId;
                int i5 = mediaPeriodId4.adGroupIndex;
                return i3 > i5 || (i3 == i5 && i4 > mediaPeriodId4.adIndexInAdGroup);
            }
            int i6 = eventTime.mediaPeriodId.nextAdGroupIndex;
            return i6 == -1 || i6 > this.adMediaPeriodId.adGroupIndex;
        }

        public void maybeSetWindowSequenceNumber(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
            if (this.windowSequenceNumber == -1 && i3 == this.windowIndex && mediaPeriodId != null) {
                this.windowSequenceNumber = mediaPeriodId.windowSequenceNumber;
            }
        }

        public boolean tryResolvingToNewTimeline(Timeline timeline, Timeline timeline2) {
            int resolveWindowIndexToNewTimeline = resolveWindowIndexToNewTimeline(timeline, timeline2, this.windowIndex);
            this.windowIndex = resolveWindowIndexToNewTimeline;
            if (resolveWindowIndexToNewTimeline == -1) {
                return false;
            }
            MediaSource.MediaPeriodId mediaPeriodId = this.adMediaPeriodId;
            if (mediaPeriodId == null) {
                return true;
            }
            return timeline2.getIndexOfPeriod(mediaPeriodId.periodUid) != -1;
        }
    }

    public DefaultPlaybackSessionManager() {
        this(DEFAULT_SESSION_ID_GENERATOR);
    }

    /* access modifiers changed from: private */
    public static String generateDefaultSessionId() {
        byte[] bArr = new byte[12];
        RANDOM.nextBytes(bArr);
        return Base64.encodeToString(bArr, 10);
    }

    private SessionDescriptor getOrAddSession(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        int i4;
        SessionDescriptor sessionDescriptor = null;
        long j2 = Long.MAX_VALUE;
        for (SessionDescriptor next : this.sessions.values()) {
            next.maybeSetWindowSequenceNumber(i3, mediaPeriodId);
            if (next.belongsToSession(i3, mediaPeriodId)) {
                long access$100 = next.windowSequenceNumber;
                if (access$100 == -1 || access$100 < j2) {
                    sessionDescriptor = next;
                    j2 = access$100;
                } else if (!(i4 != 0 || ((SessionDescriptor) Util.castNonNull(sessionDescriptor)).adMediaPeriodId == null || next.adMediaPeriodId == null)) {
                    sessionDescriptor = next;
                }
            }
        }
        if (sessionDescriptor != null) {
            return sessionDescriptor;
        }
        String str = this.sessionIdGenerator.get();
        SessionDescriptor sessionDescriptor2 = new SessionDescriptor(str, i3, mediaPeriodId);
        this.sessions.put(str, sessionDescriptor2);
        return sessionDescriptor2;
    }

    @RequiresNonNull({"listener"})
    private void updateCurrentSession(AnalyticsListener.EventTime eventTime) {
        if (eventTime.timeline.isEmpty()) {
            this.currentSessionId = null;
            return;
        }
        SessionDescriptor sessionDescriptor = this.sessions.get(this.currentSessionId);
        SessionDescriptor orAddSession = getOrAddSession(eventTime.windowIndex, eventTime.mediaPeriodId);
        this.currentSessionId = orAddSession.sessionId;
        updateSessions(eventTime);
        MediaSource.MediaPeriodId mediaPeriodId = eventTime.mediaPeriodId;
        if (mediaPeriodId != null && mediaPeriodId.isAd()) {
            if (sessionDescriptor == null || sessionDescriptor.windowSequenceNumber != eventTime.mediaPeriodId.windowSequenceNumber || sessionDescriptor.adMediaPeriodId == null || sessionDescriptor.adMediaPeriodId.adGroupIndex != eventTime.mediaPeriodId.adGroupIndex || sessionDescriptor.adMediaPeriodId.adIndexInAdGroup != eventTime.mediaPeriodId.adIndexInAdGroup) {
                MediaSource.MediaPeriodId mediaPeriodId2 = eventTime.mediaPeriodId;
                this.listener.onAdPlaybackStarted(eventTime, getOrAddSession(eventTime.windowIndex, new MediaSource.MediaPeriodId(mediaPeriodId2.periodUid, mediaPeriodId2.windowSequenceNumber)).sessionId, orAddSession.sessionId);
            }
        }
    }

    public synchronized boolean belongsToSession(AnalyticsListener.EventTime eventTime, String str) {
        SessionDescriptor sessionDescriptor = this.sessions.get(str);
        if (sessionDescriptor == null) {
            return false;
        }
        sessionDescriptor.maybeSetWindowSequenceNumber(eventTime.windowIndex, eventTime.mediaPeriodId);
        return sessionDescriptor.belongsToSession(eventTime.windowIndex, eventTime.mediaPeriodId);
    }

    public synchronized void finishAllSessions(AnalyticsListener.EventTime eventTime) {
        PlaybackSessionManager.Listener listener2;
        this.currentSessionId = null;
        Iterator<SessionDescriptor> it = this.sessions.values().iterator();
        while (it.hasNext()) {
            SessionDescriptor next = it.next();
            it.remove();
            if (next.isCreated && (listener2 = this.listener) != null) {
                listener2.onSessionFinished(eventTime, next.sessionId, false);
            }
        }
    }

    @Nullable
    public synchronized String getActiveSessionId() {
        return this.currentSessionId;
    }

    public synchronized String getSessionForMediaPeriodId(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId) {
        return getOrAddSession(timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex, mediaPeriodId).sessionId;
    }

    public void setListener(PlaybackSessionManager.Listener listener2) {
        this.listener = listener2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0044, code lost:
        if (r0.mediaPeriodId.windowSequenceNumber < com.appsamurai.storyly.exoplayer2.core.analytics.DefaultPlaybackSessionManager.SessionDescriptor.access$100(r2)) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0047, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0115, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00df A[Catch:{ all -> 0x0037 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00f1 A[Catch:{ all -> 0x0037 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void updateSessions(com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener.EventTime r25) {
        /*
            r24 = this;
            r1 = r24
            r0 = r25
            monitor-enter(r24)
            com.appsamurai.storyly.exoplayer2.core.analytics.PlaybackSessionManager$Listener r2 = r1.listener     // Catch:{ all -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r2)     // Catch:{ all -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.common.Timeline r2 = r0.timeline     // Catch:{ all -> 0x0037 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x0037 }
            if (r2 == 0) goto L_0x0014
            monitor-exit(r24)
            return
        L_0x0014:
            java.util.HashMap<java.lang.String, com.appsamurai.storyly.exoplayer2.core.analytics.DefaultPlaybackSessionManager$SessionDescriptor> r2 = r1.sessions     // Catch:{ all -> 0x0037 }
            java.lang.String r3 = r1.currentSessionId     // Catch:{ all -> 0x0037 }
            java.lang.Object r2 = r2.get(r3)     // Catch:{ all -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.core.analytics.DefaultPlaybackSessionManager$SessionDescriptor r2 = (com.appsamurai.storyly.exoplayer2.core.analytics.DefaultPlaybackSessionManager.SessionDescriptor) r2     // Catch:{ all -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r3 = r0.mediaPeriodId     // Catch:{ all -> 0x0037 }
            if (r3 == 0) goto L_0x0048
            if (r2 == 0) goto L_0x0048
            long r3 = r2.windowSequenceNumber     // Catch:{ all -> 0x0037 }
            r5 = -1
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 != 0) goto L_0x003a
            int r2 = r2.windowIndex     // Catch:{ all -> 0x0037 }
            int r3 = r0.windowIndex     // Catch:{ all -> 0x0037 }
            if (r2 == r3) goto L_0x0048
            goto L_0x0046
        L_0x0037:
            r0 = move-exception
            goto L_0x0116
        L_0x003a:
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r3 = r0.mediaPeriodId     // Catch:{ all -> 0x0037 }
            long r3 = r3.windowSequenceNumber     // Catch:{ all -> 0x0037 }
            long r5 = r2.windowSequenceNumber     // Catch:{ all -> 0x0037 }
            int r2 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r2 >= 0) goto L_0x0048
        L_0x0046:
            monitor-exit(r24)
            return
        L_0x0048:
            int r2 = r0.windowIndex     // Catch:{ all -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r3 = r0.mediaPeriodId     // Catch:{ all -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.core.analytics.DefaultPlaybackSessionManager$SessionDescriptor r2 = r1.getOrAddSession(r2, r3)     // Catch:{ all -> 0x0037 }
            java.lang.String r3 = r1.currentSessionId     // Catch:{ all -> 0x0037 }
            if (r3 != 0) goto L_0x005a
            java.lang.String r3 = r2.sessionId     // Catch:{ all -> 0x0037 }
            r1.currentSessionId = r3     // Catch:{ all -> 0x0037 }
        L_0x005a:
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r3 = r0.mediaPeriodId     // Catch:{ all -> 0x0037 }
            r4 = 1
            if (r3 == 0) goto L_0x00d7
            boolean r3 = r3.isAd()     // Catch:{ all -> 0x0037 }
            if (r3 == 0) goto L_0x00d7
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r10 = new com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId     // Catch:{ all -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r3 = r0.mediaPeriodId     // Catch:{ all -> 0x0037 }
            java.lang.Object r5 = r3.periodUid     // Catch:{ all -> 0x0037 }
            long r6 = r3.windowSequenceNumber     // Catch:{ all -> 0x0037 }
            int r3 = r3.adGroupIndex     // Catch:{ all -> 0x0037 }
            r10.<init>(r5, r6, r3)     // Catch:{ all -> 0x0037 }
            int r3 = r0.windowIndex     // Catch:{ all -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.core.analytics.DefaultPlaybackSessionManager$SessionDescriptor r3 = r1.getOrAddSession(r3, r10)     // Catch:{ all -> 0x0037 }
            boolean r5 = r3.isCreated     // Catch:{ all -> 0x0037 }
            if (r5 != 0) goto L_0x00d7
            boolean unused = r3.isCreated = r4     // Catch:{ all -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.common.Timeline r5 = r0.timeline     // Catch:{ all -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r6 = r0.mediaPeriodId     // Catch:{ all -> 0x0037 }
            java.lang.Object r6 = r6.periodUid     // Catch:{ all -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r7 = r1.period     // Catch:{ all -> 0x0037 }
            r5.getPeriodByUid(r6, r7)     // Catch:{ all -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r5 = r1.period     // Catch:{ all -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r6 = r0.mediaPeriodId     // Catch:{ all -> 0x0037 }
            int r6 = r6.adGroupIndex     // Catch:{ all -> 0x0037 }
            long r5 = r5.getAdGroupTimeUs(r6)     // Catch:{ all -> 0x0037 }
            long r5 = com.appsamurai.storyly.exoplayer2.common.util.Util.usToMs(r5)     // Catch:{ all -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r7 = r1.period     // Catch:{ all -> 0x0037 }
            long r7 = r7.getPositionInWindowMs()     // Catch:{ all -> 0x0037 }
            long r5 = r5 + r7
            r7 = 0
            long r11 = java.lang.Math.max(r7, r5)     // Catch:{ all -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener$EventTime r15 = new com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener$EventTime     // Catch:{ all -> 0x0037 }
            long r6 = r0.realtimeMs     // Catch:{ all -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.common.Timeline r8 = r0.timeline     // Catch:{ all -> 0x0037 }
            int r9 = r0.windowIndex     // Catch:{ all -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.common.Timeline r13 = r0.currentTimeline     // Catch:{ all -> 0x0037 }
            int r14 = r0.currentWindowIndex     // Catch:{ all -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r5 = r0.currentMediaPeriodId     // Catch:{ all -> 0x0037 }
            r16 = r5
            long r4 = r0.currentPlaybackPositionMs     // Catch:{ all -> 0x0037 }
            r20 = r2
            r21 = r3
            long r2 = r0.totalBufferedDurationMs     // Catch:{ all -> 0x0037 }
            r22 = r4
            r4 = r16
            r16 = r22
            r5 = r15
            r0 = r15
            r15 = r4
            r18 = r2
            r5.<init>(r6, r8, r9, r10, r11, r13, r14, r15, r16, r18)     // Catch:{ all -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.core.analytics.PlaybackSessionManager$Listener r2 = r1.listener     // Catch:{ all -> 0x0037 }
            java.lang.String r3 = r21.sessionId     // Catch:{ all -> 0x0037 }
            r2.onSessionCreated(r0, r3)     // Catch:{ all -> 0x0037 }
            goto L_0x00d9
        L_0x00d7:
            r20 = r2
        L_0x00d9:
            boolean r0 = r20.isCreated     // Catch:{ all -> 0x0037 }
            if (r0 != 0) goto L_0x00f1
            r0 = r20
            r2 = 1
            boolean unused = r0.isCreated = r2     // Catch:{ all -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.core.analytics.PlaybackSessionManager$Listener r2 = r1.listener     // Catch:{ all -> 0x0037 }
            java.lang.String r3 = r0.sessionId     // Catch:{ all -> 0x0037 }
            r4 = r25
            r2.onSessionCreated(r4, r3)     // Catch:{ all -> 0x0037 }
            goto L_0x00f5
        L_0x00f1:
            r4 = r25
            r0 = r20
        L_0x00f5:
            java.lang.String r2 = r0.sessionId     // Catch:{ all -> 0x0037 }
            java.lang.String r3 = r1.currentSessionId     // Catch:{ all -> 0x0037 }
            boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x0037 }
            if (r2 == 0) goto L_0x0114
            boolean r2 = r0.isActive     // Catch:{ all -> 0x0037 }
            if (r2 != 0) goto L_0x0114
            r2 = 1
            boolean unused = r0.isActive = r2     // Catch:{ all -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.core.analytics.PlaybackSessionManager$Listener r2 = r1.listener     // Catch:{ all -> 0x0037 }
            java.lang.String r0 = r0.sessionId     // Catch:{ all -> 0x0037 }
            r2.onSessionActive(r4, r0)     // Catch:{ all -> 0x0037 }
        L_0x0114:
            monitor-exit(r24)
            return
        L_0x0116:
            monitor-exit(r24)     // Catch:{ all -> 0x0037 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.analytics.DefaultPlaybackSessionManager.updateSessions(com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener$EventTime):void");
    }

    public synchronized void updateSessionsWithDiscontinuity(AnalyticsListener.EventTime eventTime, int i3) {
        try {
            Assertions.checkNotNull(this.listener);
            boolean z2 = i3 == 0;
            Iterator<SessionDescriptor> it = this.sessions.values().iterator();
            while (it.hasNext()) {
                SessionDescriptor next = it.next();
                if (next.isFinishedAtEventTime(eventTime)) {
                    it.remove();
                    if (next.isCreated) {
                        boolean equals = next.sessionId.equals(this.currentSessionId);
                        boolean z3 = z2 && equals && next.isActive;
                        if (equals) {
                            this.currentSessionId = null;
                        }
                        this.listener.onSessionFinished(eventTime, next.sessionId, z3);
                    }
                }
            }
            updateCurrentSession(eventTime);
        } finally {
            while (true) {
            }
        }
    }

    public synchronized void updateSessionsWithTimelineChange(AnalyticsListener.EventTime eventTime) {
        try {
            Assertions.checkNotNull(this.listener);
            Timeline timeline = this.currentTimeline;
            this.currentTimeline = eventTime.timeline;
            Iterator<SessionDescriptor> it = this.sessions.values().iterator();
            while (it.hasNext()) {
                SessionDescriptor next = it.next();
                if (next.tryResolvingToNewTimeline(timeline, this.currentTimeline)) {
                    if (next.isFinishedAtEventTime(eventTime)) {
                    }
                }
                it.remove();
                if (next.isCreated) {
                    if (next.sessionId.equals(this.currentSessionId)) {
                        this.currentSessionId = null;
                    }
                    this.listener.onSessionFinished(eventTime, next.sessionId, false);
                }
            }
            updateCurrentSession(eventTime);
        } finally {
            while (true) {
            }
        }
    }

    public DefaultPlaybackSessionManager(Supplier<String> supplier) {
        this.sessionIdGenerator = supplier;
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        this.sessions = new HashMap<>();
        this.currentTimeline = Timeline.EMPTY;
    }
}
