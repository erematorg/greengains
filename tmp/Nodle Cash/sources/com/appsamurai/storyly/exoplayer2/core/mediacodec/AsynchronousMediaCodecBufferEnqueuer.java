package com.appsamurai.storyly.exoplayer2.core.mediacodec;

import android.media.MediaCodec;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.ConditionVariable;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.decoder.CryptoInfo;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

@RequiresApi(23)
class AsynchronousMediaCodecBufferEnqueuer {
    @GuardedBy("MESSAGE_PARAMS_INSTANCE_POOL")
    private static final ArrayDeque<MessageParams> MESSAGE_PARAMS_INSTANCE_POOL = new ArrayDeque<>();
    private static final int MSG_OPEN_CV = 2;
    private static final int MSG_QUEUE_INPUT_BUFFER = 0;
    private static final int MSG_QUEUE_SECURE_INPUT_BUFFER = 1;
    private static final Object QUEUE_SECURE_LOCK = new Object();
    private final MediaCodec codec;
    private final ConditionVariable conditionVariable;
    private Handler handler;
    private final HandlerThread handlerThread;
    private final AtomicReference<RuntimeException> pendingRuntimeException;
    private boolean started;

    public static class MessageParams {
        public final MediaCodec.CryptoInfo cryptoInfo = new MediaCodec.CryptoInfo();
        public int flags;
        public int index;
        public int offset;
        public long presentationTimeUs;
        public int size;

        public void setQueueParams(int i3, int i4, int i5, long j2, int i6) {
            this.index = i3;
            this.offset = i4;
            this.size = i5;
            this.presentationTimeUs = j2;
            this.flags = i6;
        }
    }

    public AsynchronousMediaCodecBufferEnqueuer(MediaCodec mediaCodec, HandlerThread handlerThread2) {
        this(mediaCodec, handlerThread2, new ConditionVariable());
    }

    private void blockUntilHandlerThreadIsIdle() throws InterruptedException {
        this.conditionVariable.close();
        ((Handler) Assertions.checkNotNull(this.handler)).obtainMessage(2).sendToTarget();
        this.conditionVariable.block();
    }

    private static void copy(CryptoInfo cryptoInfo, MediaCodec.CryptoInfo cryptoInfo2) {
        cryptoInfo2.numSubSamples = cryptoInfo.numSubSamples;
        cryptoInfo2.numBytesOfClearData = copy(cryptoInfo.numBytesOfClearData, cryptoInfo2.numBytesOfClearData);
        cryptoInfo2.numBytesOfEncryptedData = copy(cryptoInfo.numBytesOfEncryptedData, cryptoInfo2.numBytesOfEncryptedData);
        cryptoInfo2.key = (byte[]) Assertions.checkNotNull(copy(cryptoInfo.key, cryptoInfo2.key));
        cryptoInfo2.iv = (byte[]) Assertions.checkNotNull(copy(cryptoInfo.iv, cryptoInfo2.iv));
        cryptoInfo2.mode = cryptoInfo.mode;
        if (Util.SDK_INT >= 24) {
            cryptoInfo2.setPattern(new MediaCodec.CryptoInfo.Pattern(cryptoInfo.encryptedBlocks, cryptoInfo.clearBlocks));
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: com.appsamurai.storyly.exoplayer2.core.mediacodec.AsynchronousMediaCodecBufferEnqueuer$MessageParams} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: com.appsamurai.storyly.exoplayer2.core.mediacodec.AsynchronousMediaCodecBufferEnqueuer$MessageParams} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0018 A[LOOP:0: B:7:0x0018->B:10:0x0023, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void doHandleMessage(android.os.Message r11) {
        /*
            r10 = this;
            int r0 = r11.what
            if (r0 == 0) goto L_0x0040
            r1 = 1
            if (r0 == r1) goto L_0x002c
            r1 = 2
            r2 = 0
            if (r0 == r1) goto L_0x0026
            java.util.concurrent.atomic.AtomicReference<java.lang.RuntimeException> r0 = r10.pendingRuntimeException
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            int r10 = r11.what
            java.lang.String r10 = java.lang.String.valueOf(r10)
            r1.<init>(r10)
        L_0x0018:
            boolean r10 = r0.compareAndSet(r2, r1)
            if (r10 == 0) goto L_0x001f
            goto L_0x0053
        L_0x001f:
            java.lang.Object r10 = r0.get()
            if (r10 == 0) goto L_0x0018
            goto L_0x0053
        L_0x0026:
            com.appsamurai.storyly.exoplayer2.common.util.ConditionVariable r10 = r10.conditionVariable
            r10.open()
            goto L_0x0053
        L_0x002c:
            java.lang.Object r11 = r11.obj
            r2 = r11
            com.appsamurai.storyly.exoplayer2.core.mediacodec.AsynchronousMediaCodecBufferEnqueuer$MessageParams r2 = (com.appsamurai.storyly.exoplayer2.core.mediacodec.AsynchronousMediaCodecBufferEnqueuer.MessageParams) r2
            int r4 = r2.index
            int r5 = r2.offset
            android.media.MediaCodec$CryptoInfo r6 = r2.cryptoInfo
            long r7 = r2.presentationTimeUs
            int r9 = r2.flags
            r3 = r10
            r3.doQueueSecureInputBuffer(r4, r5, r6, r7, r9)
            goto L_0x0053
        L_0x0040:
            java.lang.Object r11 = r11.obj
            r2 = r11
            com.appsamurai.storyly.exoplayer2.core.mediacodec.AsynchronousMediaCodecBufferEnqueuer$MessageParams r2 = (com.appsamurai.storyly.exoplayer2.core.mediacodec.AsynchronousMediaCodecBufferEnqueuer.MessageParams) r2
            int r4 = r2.index
            int r5 = r2.offset
            int r6 = r2.size
            long r7 = r2.presentationTimeUs
            int r9 = r2.flags
            r3 = r10
            r3.doQueueInputBuffer(r4, r5, r6, r7, r9)
        L_0x0053:
            if (r2 == 0) goto L_0x0058
            recycleMessageParams(r2)
        L_0x0058:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.mediacodec.AsynchronousMediaCodecBufferEnqueuer.doHandleMessage(android.os.Message):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x000e A[LOOP:0: B:4:0x000e->B:7:0x001a, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void doQueueInputBuffer(int r8, int r9, int r10, long r11, int r13) {
        /*
            r7 = this;
            android.media.MediaCodec r0 = r7.codec     // Catch:{ RuntimeException -> 0x000b }
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r6 = r13
            r0.queueInputBuffer(r1, r2, r3, r4, r6)     // Catch:{ RuntimeException -> 0x000b }
            goto L_0x001c
        L_0x000b:
            r8 = move-exception
            java.util.concurrent.atomic.AtomicReference<java.lang.RuntimeException> r7 = r7.pendingRuntimeException
        L_0x000e:
            r9 = 0
            boolean r9 = r7.compareAndSet(r9, r8)
            if (r9 == 0) goto L_0x0016
            goto L_0x001c
        L_0x0016:
            java.lang.Object r9 = r7.get()
            if (r9 == 0) goto L_0x000e
        L_0x001c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.mediacodec.AsynchronousMediaCodecBufferEnqueuer.doQueueInputBuffer(int, int, int, long, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0015 A[LOOP:0: B:13:0x0015->B:16:0x0021, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void doQueueSecureInputBuffer(int r9, int r10, android.media.MediaCodec.CryptoInfo r11, long r12, int r14) {
        /*
            r8 = this;
            java.lang.Object r0 = QUEUE_SECURE_LOCK     // Catch:{ RuntimeException -> 0x0012 }
            monitor-enter(r0)     // Catch:{ RuntimeException -> 0x0012 }
            android.media.MediaCodec r1 = r8.codec     // Catch:{ all -> 0x000f }
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r12
            r7 = r14
            r1.queueSecureInputBuffer(r2, r3, r4, r5, r7)     // Catch:{ all -> 0x000f }
            monitor-exit(r0)     // Catch:{ all -> 0x000f }
            goto L_0x0023
        L_0x000f:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x000f }
            throw r9     // Catch:{ RuntimeException -> 0x0012 }
        L_0x0012:
            r9 = move-exception
            java.util.concurrent.atomic.AtomicReference<java.lang.RuntimeException> r8 = r8.pendingRuntimeException
        L_0x0015:
            r10 = 0
            boolean r10 = r8.compareAndSet(r10, r9)
            if (r10 == 0) goto L_0x001d
            goto L_0x0023
        L_0x001d:
            java.lang.Object r10 = r8.get()
            if (r10 == 0) goto L_0x0015
        L_0x0023:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.mediacodec.AsynchronousMediaCodecBufferEnqueuer.doQueueSecureInputBuffer(int, int, android.media.MediaCodec$CryptoInfo, long, int):void");
    }

    private void flushHandlerThread() throws InterruptedException {
        ((Handler) Assertions.checkNotNull(this.handler)).removeCallbacksAndMessages((Object) null);
        blockUntilHandlerThreadIsIdle();
    }

    private static MessageParams getMessageParams() {
        ArrayDeque<MessageParams> arrayDeque = MESSAGE_PARAMS_INSTANCE_POOL;
        synchronized (arrayDeque) {
            try {
                if (arrayDeque.isEmpty()) {
                    MessageParams messageParams = new MessageParams();
                    return messageParams;
                }
                MessageParams removeFirst = arrayDeque.removeFirst();
                return removeFirst;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void maybeThrowException() {
        RuntimeException andSet = this.pendingRuntimeException.getAndSet((Object) null);
        if (andSet != null) {
            throw andSet;
        }
    }

    private static void recycleMessageParams(MessageParams messageParams) {
        ArrayDeque<MessageParams> arrayDeque = MESSAGE_PARAMS_INSTANCE_POOL;
        synchronized (arrayDeque) {
            arrayDeque.add(messageParams);
        }
    }

    public void flush() {
        if (this.started) {
            try {
                flushHandlerThread();
            } catch (InterruptedException e3) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException(e3);
            }
        }
    }

    public void queueInputBuffer(int i3, int i4, int i5, long j2, int i6) {
        maybeThrowException();
        MessageParams messageParams = getMessageParams();
        messageParams.setQueueParams(i3, i4, i5, j2, i6);
        ((Handler) Util.castNonNull(this.handler)).obtainMessage(0, messageParams).sendToTarget();
    }

    public void queueSecureInputBuffer(int i3, int i4, CryptoInfo cryptoInfo, long j2, int i5) {
        maybeThrowException();
        MessageParams messageParams = getMessageParams();
        messageParams.setQueueParams(i3, i4, 0, j2, i5);
        copy(cryptoInfo, messageParams.cryptoInfo);
        ((Handler) Util.castNonNull(this.handler)).obtainMessage(1, messageParams).sendToTarget();
    }

    @VisibleForTesting(otherwise = 5)
    public void setPendingRuntimeException(RuntimeException runtimeException) {
        this.pendingRuntimeException.set(runtimeException);
    }

    public void shutdown() {
        if (this.started) {
            flush();
            this.handlerThread.quit();
        }
        this.started = false;
    }

    public void start() {
        if (!this.started) {
            this.handlerThread.start();
            this.handler = new Handler(this.handlerThread.getLooper()) {
                public void handleMessage(Message message) {
                    AsynchronousMediaCodecBufferEnqueuer.this.doHandleMessage(message);
                }
            };
            this.started = true;
        }
    }

    public void waitUntilQueueingComplete() throws InterruptedException {
        blockUntilHandlerThreadIsIdle();
    }

    @VisibleForTesting
    public AsynchronousMediaCodecBufferEnqueuer(MediaCodec mediaCodec, HandlerThread handlerThread2, ConditionVariable conditionVariable2) {
        this.codec = mediaCodec;
        this.handlerThread = handlerThread2;
        this.conditionVariable = conditionVariable2;
        this.pendingRuntimeException = new AtomicReference<>();
    }

    @Nullable
    private static int[] copy(@Nullable int[] iArr, @Nullable int[] iArr2) {
        if (iArr == null) {
            return iArr2;
        }
        if (iArr2 == null || iArr2.length < iArr.length) {
            return Arrays.copyOf(iArr, iArr.length);
        }
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        return iArr2;
    }

    @Nullable
    private static byte[] copy(@Nullable byte[] bArr, @Nullable byte[] bArr2) {
        if (bArr == null) {
            return bArr2;
        }
        if (bArr2 == null || bArr2.length < bArr.length) {
            return Arrays.copyOf(bArr, bArr.length);
        }
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }
}
