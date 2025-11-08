package com.appsamurai.storyly.exoplayer2.core.mediacodec;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import java.util.ArrayDeque;

@RequiresApi(23)
final class AsynchronousMediaCodecCallback extends MediaCodec.Callback {
    @GuardedBy("lock")
    private final IntArrayQueue availableInputBuffers;
    @GuardedBy("lock")
    private final IntArrayQueue availableOutputBuffers;
    @GuardedBy("lock")
    private final ArrayDeque<MediaCodec.BufferInfo> bufferInfos;
    private final HandlerThread callbackThread;
    @GuardedBy("lock")
    @Nullable
    private MediaFormat currentFormat;
    @GuardedBy("lock")
    private final ArrayDeque<MediaFormat> formats;
    private Handler handler;
    @GuardedBy("lock")
    @Nullable
    private IllegalStateException internalException;
    private final Object lock = new Object();
    @GuardedBy("lock")
    @Nullable
    private MediaCodec.CodecException mediaCodecException;
    @GuardedBy("lock")
    private long pendingFlushCount;
    @GuardedBy("lock")
    @Nullable
    private MediaFormat pendingOutputFormat;
    @GuardedBy("lock")
    private boolean shutDown;

    public AsynchronousMediaCodecCallback(HandlerThread handlerThread) {
        this.callbackThread = handlerThread;
        this.availableInputBuffers = new IntArrayQueue();
        this.availableOutputBuffers = new IntArrayQueue();
        this.bufferInfos = new ArrayDeque<>();
        this.formats = new ArrayDeque<>();
    }

    @GuardedBy("lock")
    private void addOutputFormat(MediaFormat mediaFormat) {
        this.availableOutputBuffers.add(-2);
        this.formats.add(mediaFormat);
    }

    @GuardedBy("lock")
    private void flushInternal() {
        if (!this.formats.isEmpty()) {
            this.pendingOutputFormat = this.formats.getLast();
        }
        this.availableInputBuffers.clear();
        this.availableOutputBuffers.clear();
        this.bufferInfos.clear();
        this.formats.clear();
        this.mediaCodecException = null;
    }

    @GuardedBy("lock")
    private boolean isFlushingOrShutdown() {
        return this.pendingFlushCount > 0 || this.shutDown;
    }

    @GuardedBy("lock")
    private void maybeThrowException() {
        maybeThrowInternalException();
        maybeThrowMediaCodecException();
    }

    @GuardedBy("lock")
    private void maybeThrowInternalException() {
        IllegalStateException illegalStateException = this.internalException;
        if (illegalStateException != null) {
            this.internalException = null;
            throw illegalStateException;
        }
    }

    @GuardedBy("lock")
    private void maybeThrowMediaCodecException() {
        MediaCodec.CodecException codecException = this.mediaCodecException;
        if (codecException != null) {
            this.mediaCodecException = null;
            throw codecException;
        }
    }

    /* access modifiers changed from: private */
    public void onFlushCompleted() {
        synchronized (this.lock) {
            try {
                if (!this.shutDown) {
                    long j2 = this.pendingFlushCount - 1;
                    this.pendingFlushCount = j2;
                    if (j2 <= 0) {
                        if (j2 < 0) {
                            setInternalException(new IllegalStateException());
                        } else {
                            flushInternal();
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void setInternalException(IllegalStateException illegalStateException) {
        synchronized (this.lock) {
            this.internalException = illegalStateException;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0021, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int dequeueInputBufferIndex() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.lock
            monitor-enter(r0)
            boolean r1 = r3.isFlushingOrShutdown()     // Catch:{ all -> 0x000c }
            r2 = -1
            if (r1 == 0) goto L_0x000e
            monitor-exit(r0)     // Catch:{ all -> 0x000c }
            return r2
        L_0x000c:
            r3 = move-exception
            goto L_0x0022
        L_0x000e:
            r3.maybeThrowException()     // Catch:{ all -> 0x000c }
            com.appsamurai.storyly.exoplayer2.core.mediacodec.IntArrayQueue r1 = r3.availableInputBuffers     // Catch:{ all -> 0x000c }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x000c }
            if (r1 == 0) goto L_0x001a
            goto L_0x0020
        L_0x001a:
            com.appsamurai.storyly.exoplayer2.core.mediacodec.IntArrayQueue r3 = r3.availableInputBuffers     // Catch:{ all -> 0x000c }
            int r2 = r3.remove()     // Catch:{ all -> 0x000c }
        L_0x0020:
            monitor-exit(r0)     // Catch:{ all -> 0x000c }
            return r2
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x000c }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.mediacodec.AsynchronousMediaCodecCallback.dequeueInputBufferIndex():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004b, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int dequeueOutputBufferIndex(android.media.MediaCodec.BufferInfo r9) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.lock
            monitor-enter(r0)
            boolean r1 = r8.isFlushingOrShutdown()     // Catch:{ all -> 0x000c }
            r2 = -1
            if (r1 == 0) goto L_0x000e
            monitor-exit(r0)     // Catch:{ all -> 0x000c }
            return r2
        L_0x000c:
            r8 = move-exception
            goto L_0x004c
        L_0x000e:
            r8.maybeThrowException()     // Catch:{ all -> 0x000c }
            com.appsamurai.storyly.exoplayer2.core.mediacodec.IntArrayQueue r1 = r8.availableOutputBuffers     // Catch:{ all -> 0x000c }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x000c }
            if (r1 == 0) goto L_0x001b
            monitor-exit(r0)     // Catch:{ all -> 0x000c }
            return r2
        L_0x001b:
            com.appsamurai.storyly.exoplayer2.core.mediacodec.IntArrayQueue r1 = r8.availableOutputBuffers     // Catch:{ all -> 0x000c }
            int r1 = r1.remove()     // Catch:{ all -> 0x000c }
            if (r1 < 0) goto L_0x003d
            android.media.MediaFormat r2 = r8.currentFormat     // Catch:{ all -> 0x000c }
            com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkStateNotNull(r2)     // Catch:{ all -> 0x000c }
            java.util.ArrayDeque<android.media.MediaCodec$BufferInfo> r8 = r8.bufferInfos     // Catch:{ all -> 0x000c }
            java.lang.Object r8 = r8.remove()     // Catch:{ all -> 0x000c }
            android.media.MediaCodec$BufferInfo r8 = (android.media.MediaCodec.BufferInfo) r8     // Catch:{ all -> 0x000c }
            int r3 = r8.offset     // Catch:{ all -> 0x000c }
            int r4 = r8.size     // Catch:{ all -> 0x000c }
            long r5 = r8.presentationTimeUs     // Catch:{ all -> 0x000c }
            int r7 = r8.flags     // Catch:{ all -> 0x000c }
            r2 = r9
            r2.set(r3, r4, r5, r7)     // Catch:{ all -> 0x000c }
            goto L_0x004a
        L_0x003d:
            r9 = -2
            if (r1 != r9) goto L_0x004a
            java.util.ArrayDeque<android.media.MediaFormat> r9 = r8.formats     // Catch:{ all -> 0x000c }
            java.lang.Object r9 = r9.remove()     // Catch:{ all -> 0x000c }
            android.media.MediaFormat r9 = (android.media.MediaFormat) r9     // Catch:{ all -> 0x000c }
            r8.currentFormat = r9     // Catch:{ all -> 0x000c }
        L_0x004a:
            monitor-exit(r0)     // Catch:{ all -> 0x000c }
            return r1
        L_0x004c:
            monitor-exit(r0)     // Catch:{ all -> 0x000c }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.mediacodec.AsynchronousMediaCodecCallback.dequeueOutputBufferIndex(android.media.MediaCodec$BufferInfo):int");
    }

    public void flush() {
        synchronized (this.lock) {
            this.pendingFlushCount++;
            ((Handler) Util.castNonNull(this.handler)).post(new c(this));
        }
    }

    public MediaFormat getOutputFormat() {
        MediaFormat mediaFormat;
        synchronized (this.lock) {
            try {
                mediaFormat = this.currentFormat;
                if (mediaFormat == null) {
                    throw new IllegalStateException();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return mediaFormat;
    }

    public void initialize(MediaCodec mediaCodec) {
        Assertions.checkState(this.handler == null);
        this.callbackThread.start();
        Handler handler2 = new Handler(this.callbackThread.getLooper());
        mediaCodec.setCallback(this, handler2);
        this.handler = handler2;
    }

    public void onError(MediaCodec mediaCodec, MediaCodec.CodecException codecException) {
        synchronized (this.lock) {
            this.mediaCodecException = codecException;
        }
    }

    public void onInputBufferAvailable(MediaCodec mediaCodec, int i3) {
        synchronized (this.lock) {
            this.availableInputBuffers.add(i3);
        }
    }

    public void onOutputBufferAvailable(MediaCodec mediaCodec, int i3, MediaCodec.BufferInfo bufferInfo) {
        synchronized (this.lock) {
            try {
                MediaFormat mediaFormat = this.pendingOutputFormat;
                if (mediaFormat != null) {
                    addOutputFormat(mediaFormat);
                    this.pendingOutputFormat = null;
                }
                this.availableOutputBuffers.add(i3);
                this.bufferInfos.add(bufferInfo);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        synchronized (this.lock) {
            addOutputFormat(mediaFormat);
            this.pendingOutputFormat = null;
        }
    }

    public void shutdown() {
        synchronized (this.lock) {
            this.shutDown = true;
            this.callbackThread.quit();
            flushInternal();
        }
    }
}
