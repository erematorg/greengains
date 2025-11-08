package com.appsamurai.storyly.exoplayer2.decoder;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderException;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderOutputBuffer;
import java.util.ArrayDeque;

public abstract class SimpleDecoder<I extends DecoderInputBuffer, O extends DecoderOutputBuffer, E extends DecoderException> implements Decoder<I, O, E> {
    private int availableInputBufferCount;
    private final I[] availableInputBuffers;
    private int availableOutputBufferCount;
    private final O[] availableOutputBuffers;
    private final Thread decodeThread;
    @Nullable
    private I dequeuedInputBuffer;
    @Nullable
    private E exception;
    private boolean flushed;
    private final Object lock = new Object();
    private final ArrayDeque<I> queuedInputBuffers = new ArrayDeque<>();
    private final ArrayDeque<O> queuedOutputBuffers = new ArrayDeque<>();
    private boolean released;
    private int skippedOutputBufferCount;

    public SimpleDecoder(I[] iArr, O[] oArr) {
        this.availableInputBuffers = iArr;
        this.availableInputBufferCount = iArr.length;
        for (int i3 = 0; i3 < this.availableInputBufferCount; i3++) {
            this.availableInputBuffers[i3] = createInputBuffer();
        }
        this.availableOutputBuffers = oArr;
        this.availableOutputBufferCount = oArr.length;
        for (int i4 = 0; i4 < this.availableOutputBufferCount; i4++) {
            this.availableOutputBuffers[i4] = createOutputBuffer();
        }
        AnonymousClass1 r4 = new Thread("ExoPlayer:SimpleDecoder") {
            public void run() {
                SimpleDecoder.this.run();
            }
        };
        this.decodeThread = r4;
        r4.start();
    }

    private boolean canDecodeBuffer() {
        return !this.queuedInputBuffers.isEmpty() && this.availableOutputBufferCount > 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0038, code lost:
        if (r1.isEndOfStream() == false) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003a, code lost:
        r3.addFlag(4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0043, code lost:
        if (r1.isDecodeOnly() == false) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0045, code lost:
        r3.addFlag(Integer.MIN_VALUE);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004e, code lost:
        if (r1.isFirstSample() == false) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0050, code lost:
        r3.addFlag(com.appsamurai.storyly.exoplayer2.common.C.BUFFER_FLAG_FIRST_SAMPLE);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r0 = decode(r1, r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005b, code lost:
        r0 = createUnexpectedDecodeException(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0060, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0061, code lost:
        r0 = createUnexpectedDecodeException(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean decode() throws java.lang.InterruptedException {
        /*
            r6 = this;
            java.lang.Object r0 = r6.lock
            monitor-enter(r0)
        L_0x0003:
            boolean r1 = r6.released     // Catch:{ all -> 0x0013 }
            if (r1 != 0) goto L_0x0016
            boolean r1 = r6.canDecodeBuffer()     // Catch:{ all -> 0x0013 }
            if (r1 != 0) goto L_0x0016
            java.lang.Object r1 = r6.lock     // Catch:{ all -> 0x0013 }
            r1.wait()     // Catch:{ all -> 0x0013 }
            goto L_0x0003
        L_0x0013:
            r6 = move-exception
            goto L_0x009f
        L_0x0016:
            boolean r1 = r6.released     // Catch:{ all -> 0x0013 }
            r2 = 0
            if (r1 == 0) goto L_0x001d
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            return r2
        L_0x001d:
            java.util.ArrayDeque<I> r1 = r6.queuedInputBuffers     // Catch:{ all -> 0x0013 }
            java.lang.Object r1 = r1.removeFirst()     // Catch:{ all -> 0x0013 }
            com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer r1 = (com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer) r1     // Catch:{ all -> 0x0013 }
            O[] r3 = r6.availableOutputBuffers     // Catch:{ all -> 0x0013 }
            int r4 = r6.availableOutputBufferCount     // Catch:{ all -> 0x0013 }
            r5 = 1
            int r4 = r4 - r5
            r6.availableOutputBufferCount = r4     // Catch:{ all -> 0x0013 }
            r3 = r3[r4]     // Catch:{ all -> 0x0013 }
            boolean r4 = r6.flushed     // Catch:{ all -> 0x0013 }
            r6.flushed = r2     // Catch:{ all -> 0x0013 }
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            boolean r0 = r1.isEndOfStream()
            if (r0 == 0) goto L_0x003f
            r0 = 4
            r3.addFlag(r0)
            goto L_0x0071
        L_0x003f:
            boolean r0 = r1.isDecodeOnly()
            if (r0 == 0) goto L_0x004a
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            r3.addFlag(r0)
        L_0x004a:
            boolean r0 = r1.isFirstSample()
            if (r0 == 0) goto L_0x0055
            r0 = 134217728(0x8000000, float:3.85186E-34)
            r3.addFlag(r0)
        L_0x0055:
            com.appsamurai.storyly.exoplayer2.decoder.DecoderException r0 = r6.decode(r1, r3, r4)     // Catch:{ RuntimeException -> 0x0060, OutOfMemoryError -> 0x005a }
            goto L_0x0065
        L_0x005a:
            r0 = move-exception
            com.appsamurai.storyly.exoplayer2.decoder.DecoderException r0 = r6.createUnexpectedDecodeException(r0)
            goto L_0x0065
        L_0x0060:
            r0 = move-exception
            com.appsamurai.storyly.exoplayer2.decoder.DecoderException r0 = r6.createUnexpectedDecodeException(r0)
        L_0x0065:
            if (r0 == 0) goto L_0x0071
            java.lang.Object r4 = r6.lock
            monitor-enter(r4)
            r6.exception = r0     // Catch:{ all -> 0x006e }
            monitor-exit(r4)     // Catch:{ all -> 0x006e }
            return r2
        L_0x006e:
            r6 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x006e }
            throw r6
        L_0x0071:
            java.lang.Object r4 = r6.lock
            monitor-enter(r4)
            boolean r0 = r6.flushed     // Catch:{ all -> 0x007c }
            if (r0 == 0) goto L_0x007e
            r3.release()     // Catch:{ all -> 0x007c }
            goto L_0x0098
        L_0x007c:
            r6 = move-exception
            goto L_0x009d
        L_0x007e:
            boolean r0 = r3.isDecodeOnly()     // Catch:{ all -> 0x007c }
            if (r0 == 0) goto L_0x008d
            int r0 = r6.skippedOutputBufferCount     // Catch:{ all -> 0x007c }
            int r0 = r0 + r5
            r6.skippedOutputBufferCount = r0     // Catch:{ all -> 0x007c }
            r3.release()     // Catch:{ all -> 0x007c }
            goto L_0x0098
        L_0x008d:
            int r0 = r6.skippedOutputBufferCount     // Catch:{ all -> 0x007c }
            r3.skippedOutputBufferCount = r0     // Catch:{ all -> 0x007c }
            r6.skippedOutputBufferCount = r2     // Catch:{ all -> 0x007c }
            java.util.ArrayDeque<O> r0 = r6.queuedOutputBuffers     // Catch:{ all -> 0x007c }
            r0.addLast(r3)     // Catch:{ all -> 0x007c }
        L_0x0098:
            r6.releaseInputBufferInternal(r1)     // Catch:{ all -> 0x007c }
            monitor-exit(r4)     // Catch:{ all -> 0x007c }
            return r5
        L_0x009d:
            monitor-exit(r4)     // Catch:{ all -> 0x007c }
            throw r6
        L_0x009f:
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.decoder.SimpleDecoder.decode():boolean");
    }

    private void maybeNotifyDecodeLoop() {
        if (canDecodeBuffer()) {
            this.lock.notify();
        }
    }

    private void maybeThrowException() throws DecoderException {
        E e3 = this.exception;
        if (e3 != null) {
            throw e3;
        }
    }

    private void releaseInputBufferInternal(I i3) {
        i3.clear();
        I[] iArr = this.availableInputBuffers;
        int i4 = this.availableInputBufferCount;
        this.availableInputBufferCount = i4 + 1;
        iArr[i4] = i3;
    }

    private void releaseOutputBufferInternal(O o3) {
        o3.clear();
        O[] oArr = this.availableOutputBuffers;
        int i3 = this.availableOutputBufferCount;
        this.availableOutputBufferCount = i3 + 1;
        oArr[i3] = o3;
    }

    /* access modifiers changed from: private */
    public void run() {
        do {
            try {
            } catch (InterruptedException e3) {
                throw new IllegalStateException(e3);
            }
        } while (decode());
    }

    public abstract I createInputBuffer();

    public abstract O createOutputBuffer();

    public abstract E createUnexpectedDecodeException(Throwable th);

    @Nullable
    public abstract E decode(I i3, O o3, boolean z2);

    public final void flush() {
        synchronized (this.lock) {
            try {
                this.flushed = true;
                this.skippedOutputBufferCount = 0;
                I i3 = this.dequeuedInputBuffer;
                if (i3 != null) {
                    releaseInputBufferInternal(i3);
                    this.dequeuedInputBuffer = null;
                }
                while (!this.queuedInputBuffers.isEmpty()) {
                    releaseInputBufferInternal((DecoderInputBuffer) this.queuedInputBuffers.removeFirst());
                }
                while (!this.queuedOutputBuffers.isEmpty()) {
                    ((DecoderOutputBuffer) this.queuedOutputBuffers.removeFirst()).release();
                }
            } finally {
            }
        }
    }

    @CallSuper
    public void release() {
        synchronized (this.lock) {
            this.released = true;
            this.lock.notify();
        }
        try {
            this.decodeThread.join();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    @CallSuper
    public void releaseOutputBuffer(O o3) {
        synchronized (this.lock) {
            releaseOutputBufferInternal(o3);
            maybeNotifyDecodeLoop();
        }
    }

    public final void setInitialInputBufferSize(int i3) {
        Assertions.checkState(this.availableInputBufferCount == this.availableInputBuffers.length);
        for (I ensureSpaceForWrite : this.availableInputBuffers) {
            ensureSpaceForWrite.ensureSpaceForWrite(i3);
        }
    }

    @Nullable
    public final I dequeueInputBuffer() throws DecoderException {
        I i3;
        synchronized (this.lock) {
            maybeThrowException();
            Assertions.checkState(this.dequeuedInputBuffer == null);
            int i4 = this.availableInputBufferCount;
            if (i4 == 0) {
                i3 = null;
            } else {
                I[] iArr = this.availableInputBuffers;
                int i5 = i4 - 1;
                this.availableInputBufferCount = i5;
                i3 = iArr[i5];
            }
            this.dequeuedInputBuffer = i3;
        }
        return i3;
    }

    @Nullable
    public final O dequeueOutputBuffer() throws DecoderException {
        synchronized (this.lock) {
            try {
                maybeThrowException();
                if (this.queuedOutputBuffers.isEmpty()) {
                    return null;
                }
                O o3 = (DecoderOutputBuffer) this.queuedOutputBuffers.removeFirst();
                return o3;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void queueInputBuffer(I i3) throws DecoderException {
        synchronized (this.lock) {
            maybeThrowException();
            Assertions.checkArgument(i3 == this.dequeuedInputBuffer);
            this.queuedInputBuffers.addLast(i3);
            maybeNotifyDecodeLoop();
            this.dequeuedInputBuffer = null;
        }
    }
}
