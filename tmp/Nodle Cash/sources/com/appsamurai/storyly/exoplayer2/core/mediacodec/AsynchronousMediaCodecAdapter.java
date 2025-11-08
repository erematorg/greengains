package com.appsamurai.storyly.exoplayer2.core.mediacodec;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.PersistableBundle;
import android.view.Surface;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.appsamurai.storyly.exoplayer2.common.util.TraceUtil;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter;
import com.appsamurai.storyly.exoplayer2.decoder.CryptoInfo;
import com.google.common.base.Supplier;
import java.nio.ByteBuffer;

@RequiresApi(23)
final class AsynchronousMediaCodecAdapter implements MediaCodecAdapter {
    private static final int STATE_CREATED = 0;
    private static final int STATE_INITIALIZED = 1;
    private static final int STATE_SHUT_DOWN = 2;
    private final AsynchronousMediaCodecCallback asynchronousMediaCodecCallback;
    private final AsynchronousMediaCodecBufferEnqueuer bufferEnqueuer;
    private final MediaCodec codec;
    private boolean codecReleased;
    private int state;
    private final boolean synchronizeCodecInteractionsWithQueueing;

    public static final class Factory implements MediaCodecAdapter.Factory {
        private final Supplier<HandlerThread> callbackThreadSupplier;
        private final Supplier<HandlerThread> queueingThreadSupplier;
        private final boolean synchronizeCodecInteractionsWithQueueing;

        public Factory(int i3, boolean z2) {
            this(new b(i3, 0), new b(i3, 1), z2);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ HandlerThread lambda$new$0(int i3) {
            return new HandlerThread(AsynchronousMediaCodecAdapter.createCallbackThreadLabel(i3));
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ HandlerThread lambda$new$1(int i3) {
            return new HandlerThread(AsynchronousMediaCodecAdapter.createQueueingThreadLabel(i3));
        }

        @VisibleForTesting
        public Factory(Supplier<HandlerThread> supplier, Supplier<HandlerThread> supplier2, boolean z2) {
            this.callbackThreadSupplier = supplier;
            this.queueingThreadSupplier = supplier2;
            this.synchronizeCodecInteractionsWithQueueing = z2;
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x004e  */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x0054  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.appsamurai.storyly.exoplayer2.core.mediacodec.AsynchronousMediaCodecAdapter createAdapter(com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter.Configuration r10) throws java.io.IOException {
            /*
                r9 = this;
                java.lang.String r0 = "createCodec:"
                com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecInfo r1 = r10.codecInfo
                java.lang.String r1 = r1.name
                r2 = 0
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004a }
                r3.<init>(r0)     // Catch:{ Exception -> 0x004a }
                r3.append(r1)     // Catch:{ Exception -> 0x004a }
                java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x004a }
                com.appsamurai.storyly.exoplayer2.common.util.TraceUtil.beginSection(r0)     // Catch:{ Exception -> 0x004a }
                android.media.MediaCodec r0 = android.media.MediaCodec.createByCodecName(r1)     // Catch:{ Exception -> 0x004a }
                com.appsamurai.storyly.exoplayer2.core.mediacodec.AsynchronousMediaCodecAdapter r1 = new com.appsamurai.storyly.exoplayer2.core.mediacodec.AsynchronousMediaCodecAdapter     // Catch:{ Exception -> 0x0048 }
                com.google.common.base.Supplier<android.os.HandlerThread> r3 = r9.callbackThreadSupplier     // Catch:{ Exception -> 0x0048 }
                java.lang.Object r3 = r3.get()     // Catch:{ Exception -> 0x0048 }
                r5 = r3
                android.os.HandlerThread r5 = (android.os.HandlerThread) r5     // Catch:{ Exception -> 0x0048 }
                com.google.common.base.Supplier<android.os.HandlerThread> r3 = r9.queueingThreadSupplier     // Catch:{ Exception -> 0x0048 }
                java.lang.Object r3 = r3.get()     // Catch:{ Exception -> 0x0048 }
                r6 = r3
                android.os.HandlerThread r6 = (android.os.HandlerThread) r6     // Catch:{ Exception -> 0x0048 }
                boolean r7 = r9.synchronizeCodecInteractionsWithQueueing     // Catch:{ Exception -> 0x0048 }
                r8 = 0
                r3 = r1
                r4 = r0
                r3.<init>(r4, r5, r6, r7)     // Catch:{ Exception -> 0x0048 }
                com.appsamurai.storyly.exoplayer2.common.util.TraceUtil.endSection()     // Catch:{ Exception -> 0x0045 }
                android.media.MediaFormat r9 = r10.mediaFormat     // Catch:{ Exception -> 0x0045 }
                android.view.Surface r2 = r10.surface     // Catch:{ Exception -> 0x0045 }
                android.media.MediaCrypto r3 = r10.crypto     // Catch:{ Exception -> 0x0045 }
                int r10 = r10.flags     // Catch:{ Exception -> 0x0045 }
                r1.initialize(r9, r2, r3, r10)     // Catch:{ Exception -> 0x0045 }
                return r1
            L_0x0045:
                r9 = move-exception
                r2 = r1
                goto L_0x004c
            L_0x0048:
                r9 = move-exception
                goto L_0x004c
            L_0x004a:
                r9 = move-exception
                r0 = r2
            L_0x004c:
                if (r2 != 0) goto L_0x0054
                if (r0 == 0) goto L_0x0057
                r0.release()
                goto L_0x0057
            L_0x0054:
                r2.release()
            L_0x0057:
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.mediacodec.AsynchronousMediaCodecAdapter.Factory.createAdapter(com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter$Configuration):com.appsamurai.storyly.exoplayer2.core.mediacodec.AsynchronousMediaCodecAdapter");
        }
    }

    /* access modifiers changed from: private */
    public static String createCallbackThreadLabel(int i3) {
        return createThreadLabel(i3, "ExoPlayer:MediaCodecAsyncAdapter:");
    }

    /* access modifiers changed from: private */
    public static String createQueueingThreadLabel(int i3) {
        return createThreadLabel(i3, "ExoPlayer:MediaCodecQueueingThread:");
    }

    private static String createThreadLabel(int i3, String str) {
        StringBuilder sb = new StringBuilder(str);
        if (i3 == 1) {
            sb.append("Audio");
        } else if (i3 == 2) {
            sb.append("Video");
        } else {
            sb.append("Unknown(");
            sb.append(i3);
            sb.append(")");
        }
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public void initialize(@Nullable MediaFormat mediaFormat, @Nullable Surface surface, @Nullable MediaCrypto mediaCrypto, int i3) {
        this.asynchronousMediaCodecCallback.initialize(this.codec);
        TraceUtil.beginSection("configureCodec");
        this.codec.configure(mediaFormat, surface, mediaCrypto, i3);
        TraceUtil.endSection();
        this.bufferEnqueuer.start();
        TraceUtil.beginSection("startCodec");
        this.codec.start();
        TraceUtil.endSection();
        this.state = 1;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setOnFrameRenderedListener$0(MediaCodecAdapter.OnFrameRenderedListener onFrameRenderedListener, MediaCodec mediaCodec, long j2, long j3) {
        onFrameRenderedListener.onFrameRendered(this, j2, j3);
    }

    private void maybeBlockOnQueueing() {
        if (this.synchronizeCodecInteractionsWithQueueing) {
            try {
                this.bufferEnqueuer.waitUntilQueueingComplete();
            } catch (InterruptedException e3) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException(e3);
            }
        }
    }

    public int dequeueInputBufferIndex() {
        return this.asynchronousMediaCodecCallback.dequeueInputBufferIndex();
    }

    public int dequeueOutputBufferIndex(MediaCodec.BufferInfo bufferInfo) {
        return this.asynchronousMediaCodecCallback.dequeueOutputBufferIndex(bufferInfo);
    }

    public void flush() {
        this.bufferEnqueuer.flush();
        this.codec.flush();
        this.asynchronousMediaCodecCallback.flush();
        this.codec.start();
    }

    @Nullable
    public ByteBuffer getInputBuffer(int i3) {
        return this.codec.getInputBuffer(i3);
    }

    @RequiresApi(26)
    public PersistableBundle getMetrics() {
        maybeBlockOnQueueing();
        return this.codec.getMetrics();
    }

    @Nullable
    public ByteBuffer getOutputBuffer(int i3) {
        return this.codec.getOutputBuffer(i3);
    }

    public MediaFormat getOutputFormat() {
        return this.asynchronousMediaCodecCallback.getOutputFormat();
    }

    public boolean needsReconfiguration() {
        return false;
    }

    @VisibleForTesting
    public void onError(MediaCodec.CodecException codecException) {
        this.asynchronousMediaCodecCallback.onError(this.codec, codecException);
    }

    @VisibleForTesting
    public void onOutputFormatChanged(MediaFormat mediaFormat) {
        this.asynchronousMediaCodecCallback.onOutputFormatChanged(this.codec, mediaFormat);
    }

    public void queueInputBuffer(int i3, int i4, int i5, long j2, int i6) {
        this.bufferEnqueuer.queueInputBuffer(i3, i4, i5, j2, i6);
    }

    public void queueSecureInputBuffer(int i3, int i4, CryptoInfo cryptoInfo, long j2, int i5) {
        this.bufferEnqueuer.queueSecureInputBuffer(i3, i4, cryptoInfo, j2, i5);
    }

    public void release() {
        try {
            if (this.state == 1) {
                this.bufferEnqueuer.shutdown();
                this.asynchronousMediaCodecCallback.shutdown();
            }
            this.state = 2;
            if (!this.codecReleased) {
                this.codec.release();
                this.codecReleased = true;
            }
        } catch (Throwable th) {
            if (!this.codecReleased) {
                this.codec.release();
                this.codecReleased = true;
            }
            throw th;
        }
    }

    public void releaseOutputBuffer(int i3, boolean z2) {
        this.codec.releaseOutputBuffer(i3, z2);
    }

    public void setOnFrameRenderedListener(MediaCodecAdapter.OnFrameRenderedListener onFrameRenderedListener, Handler handler) {
        maybeBlockOnQueueing();
        this.codec.setOnFrameRenderedListener(new a(this, onFrameRenderedListener, 0), handler);
    }

    public void setOutputSurface(Surface surface) {
        maybeBlockOnQueueing();
        this.codec.setOutputSurface(surface);
    }

    public void setParameters(Bundle bundle) {
        maybeBlockOnQueueing();
        this.codec.setParameters(bundle);
    }

    public void setVideoScalingMode(int i3) {
        maybeBlockOnQueueing();
        this.codec.setVideoScalingMode(i3);
    }

    private AsynchronousMediaCodecAdapter(MediaCodec mediaCodec, HandlerThread handlerThread, HandlerThread handlerThread2, boolean z2) {
        this.codec = mediaCodec;
        this.asynchronousMediaCodecCallback = new AsynchronousMediaCodecCallback(handlerThread);
        this.bufferEnqueuer = new AsynchronousMediaCodecBufferEnqueuer(mediaCodec, handlerThread2);
        this.synchronizeCodecInteractionsWithQueueing = z2;
        this.state = 0;
    }

    public void releaseOutputBuffer(int i3, long j2) {
        this.codec.releaseOutputBuffer(i3, j2);
    }
}
