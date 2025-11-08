package com.appsamurai.storyly.exoplayer2.core;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.IllegalSeekPositionException;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Clock;

public final class PlayerMessage {
    private final Clock clock;
    private boolean deleteAfterDelivery = true;
    private boolean isCanceled;
    private boolean isDelivered;
    private boolean isProcessed;
    private boolean isSent;
    private Looper looper;
    private int mediaItemIndex;
    @Nullable
    private Object payload;
    private long positionMs = C.TIME_UNSET;
    private final Sender sender;
    private final Target target;
    private final Timeline timeline;
    private int type;

    public interface Sender {
        void sendMessage(PlayerMessage playerMessage);
    }

    public interface Target {
        void handleMessage(int i3, @Nullable Object obj) throws ExoPlaybackException;
    }

    public PlayerMessage(Sender sender2, Target target2, Timeline timeline2, int i3, Clock clock2, Looper looper2) {
        this.sender = sender2;
        this.target = target2;
        this.timeline = timeline2;
        this.looper = looper2;
        this.clock = clock2;
        this.mediaItemIndex = i3;
    }

    public synchronized boolean blockUntilDelivered() throws InterruptedException {
        try {
            Assertions.checkState(this.isSent);
            Assertions.checkState(this.looper.getThread() != Thread.currentThread());
            while (!this.isProcessed) {
                wait();
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this.isDelivered;
    }

    public synchronized PlayerMessage cancel() {
        Assertions.checkState(this.isSent);
        this.isCanceled = true;
        markAsProcessed(false);
        return this;
    }

    public boolean getDeleteAfterDelivery() {
        return this.deleteAfterDelivery;
    }

    public Looper getLooper() {
        return this.looper;
    }

    public int getMediaItemIndex() {
        return this.mediaItemIndex;
    }

    @Nullable
    public Object getPayload() {
        return this.payload;
    }

    public long getPositionMs() {
        return this.positionMs;
    }

    public Target getTarget() {
        return this.target;
    }

    public Timeline getTimeline() {
        return this.timeline;
    }

    public int getType() {
        return this.type;
    }

    public synchronized boolean isCanceled() {
        return this.isCanceled;
    }

    public synchronized void markAsProcessed(boolean z2) {
        this.isDelivered = z2 | this.isDelivered;
        this.isProcessed = true;
        notifyAll();
    }

    public PlayerMessage send() {
        Assertions.checkState(!this.isSent);
        if (this.positionMs == C.TIME_UNSET) {
            Assertions.checkArgument(this.deleteAfterDelivery);
        }
        this.isSent = true;
        this.sender.sendMessage(this);
        return this;
    }

    public PlayerMessage setDeleteAfterDelivery(boolean z2) {
        Assertions.checkState(!this.isSent);
        this.deleteAfterDelivery = z2;
        return this;
    }

    @Deprecated
    public PlayerMessage setHandler(Handler handler) {
        return setLooper(handler.getLooper());
    }

    public PlayerMessage setLooper(Looper looper2) {
        Assertions.checkState(!this.isSent);
        this.looper = looper2;
        return this;
    }

    public PlayerMessage setPayload(@Nullable Object obj) {
        Assertions.checkState(!this.isSent);
        this.payload = obj;
        return this;
    }

    public PlayerMessage setPosition(long j2) {
        Assertions.checkState(!this.isSent);
        this.positionMs = j2;
        return this;
    }

    public PlayerMessage setType(int i3) {
        Assertions.checkState(!this.isSent);
        this.type = i3;
        return this;
    }

    public PlayerMessage setPosition(int i3, long j2) {
        boolean z2 = true;
        Assertions.checkState(!this.isSent);
        if (j2 == C.TIME_UNSET) {
            z2 = false;
        }
        Assertions.checkArgument(z2);
        if (i3 < 0 || (!this.timeline.isEmpty() && i3 >= this.timeline.getWindowCount())) {
            throw new IllegalSeekPositionException(this.timeline, i3, j2);
        }
        this.mediaItemIndex = i3;
        this.positionMs = j2;
        return this;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003e A[Catch:{ all -> 0x003a }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0042 A[SYNTHETIC, Splitter:B:18:0x0042] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean blockUntilDelivered(long r6) throws java.lang.InterruptedException, java.util.concurrent.TimeoutException {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.isSent     // Catch:{ all -> 0x003a }
            com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkState(r0)     // Catch:{ all -> 0x003a }
            android.os.Looper r0 = r5.looper     // Catch:{ all -> 0x003a }
            java.lang.Thread r0 = r0.getThread()     // Catch:{ all -> 0x003a }
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x003a }
            if (r0 == r1) goto L_0x0014
            r0 = 1
            goto L_0x0015
        L_0x0014:
            r0 = 0
        L_0x0015:
            com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkState(r0)     // Catch:{ all -> 0x003a }
            com.appsamurai.storyly.exoplayer2.common.util.Clock r0 = r5.clock     // Catch:{ all -> 0x003a }
            long r0 = r0.elapsedRealtime()     // Catch:{ all -> 0x003a }
            long r0 = r0 + r6
        L_0x001f:
            boolean r2 = r5.isProcessed     // Catch:{ all -> 0x003a }
            if (r2 != 0) goto L_0x003c
            r3 = 0
            int r3 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r3 <= 0) goto L_0x003c
            com.appsamurai.storyly.exoplayer2.common.util.Clock r2 = r5.clock     // Catch:{ all -> 0x003a }
            r2.onThreadBlocked()     // Catch:{ all -> 0x003a }
            r5.wait(r6)     // Catch:{ all -> 0x003a }
            com.appsamurai.storyly.exoplayer2.common.util.Clock r6 = r5.clock     // Catch:{ all -> 0x003a }
            long r6 = r6.elapsedRealtime()     // Catch:{ all -> 0x003a }
            long r6 = r0 - r6
            goto L_0x001f
        L_0x003a:
            r6 = move-exception
            goto L_0x004a
        L_0x003c:
            if (r2 == 0) goto L_0x0042
            boolean r6 = r5.isDelivered     // Catch:{ all -> 0x003a }
            monitor-exit(r5)
            return r6
        L_0x0042:
            java.util.concurrent.TimeoutException r6 = new java.util.concurrent.TimeoutException     // Catch:{ all -> 0x003a }
            java.lang.String r7 = "Message delivery timed out."
            r6.<init>(r7)     // Catch:{ all -> 0x003a }
            throw r6     // Catch:{ all -> 0x003a }
        L_0x004a:
            monitor-exit(r5)     // Catch:{ all -> 0x003a }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.PlayerMessage.blockUntilDelivered(long):boolean");
    }
}
