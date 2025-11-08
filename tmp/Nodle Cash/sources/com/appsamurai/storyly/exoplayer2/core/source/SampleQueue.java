package com.appsamurai.storyly.exoplayer2.core.source;

import android.os.Looper;
import androidx.annotation.CallSuper;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData;
import com.appsamurai.storyly.exoplayer2.common.upstream.DataReader;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.FormatHolder;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSession;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionEventListener;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionManager;
import com.appsamurai.storyly.exoplayer2.core.upstream.Allocator;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import java.io.IOException;

public class SampleQueue implements TrackOutput {
    @VisibleForTesting
    static final int SAMPLE_CAPACITY_INCREMENT = 1000;
    private static final String TAG = "SampleQueue";
    private int absoluteFirstIndex;
    private int capacity = 1000;
    private TrackOutput.CryptoData[] cryptoDatas = new TrackOutput.CryptoData[1000];
    @Nullable
    private DrmSession currentDrmSession;
    @Nullable
    private Format downstreamFormat;
    @Nullable
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    @Nullable
    private final DrmSessionManager drmSessionManager;
    private final SampleExtrasHolder extrasHolder = new SampleExtrasHolder();
    private int[] flags = new int[1000];
    private boolean isLastSampleQueued;
    private long largestDiscardedTimestampUs = Long.MIN_VALUE;
    private long largestQueuedTimestampUs = Long.MIN_VALUE;
    private int length;
    private boolean loggedUnexpectedNonSyncSample;
    private long[] offsets = new long[1000];
    private boolean pendingSplice;
    private int readPosition;
    private int relativeFirstIndex;
    private final SampleDataQueue sampleDataQueue;
    private long sampleOffsetUs;
    private final SpannedData<SharedSampleMetadata> sharedSampleMetadata = new SpannedData<>(new l(0));
    private int[] sizes = new int[1000];
    private int[] sourceIds = new int[1000];
    private long startTimeUs = Long.MIN_VALUE;
    private long[] timesUs = new long[1000];
    @Nullable
    private Format unadjustedUpstreamFormat;
    private boolean upstreamAllSamplesAreSyncSamples;
    @Nullable
    private Format upstreamFormat;
    private boolean upstreamFormatAdjustmentRequired;
    @Nullable
    private UpstreamFormatChangedListener upstreamFormatChangeListener;
    private boolean upstreamFormatRequired = true;
    private boolean upstreamKeyframeRequired = true;
    private int upstreamSourceId;

    public static final class SampleExtrasHolder {
        @Nullable
        public TrackOutput.CryptoData cryptoData;
        public long offset;
        public int size;
    }

    public static final class SharedSampleMetadata {
        public final DrmSessionManager.DrmSessionReference drmSessionReference;
        public final Format format;

        private SharedSampleMetadata(Format format2, DrmSessionManager.DrmSessionReference drmSessionReference2) {
            this.format = format2;
            this.drmSessionReference = drmSessionReference2;
        }
    }

    public interface UpstreamFormatChangedListener {
        void onUpstreamFormatChanged(Format format);
    }

    public SampleQueue(Allocator allocator, @Nullable DrmSessionManager drmSessionManager2, @Nullable DrmSessionEventListener.EventDispatcher eventDispatcher) {
        this.drmSessionManager = drmSessionManager2;
        this.drmEventDispatcher = eventDispatcher;
        this.sampleDataQueue = new SampleDataQueue(allocator);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000f, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean attemptSplice(long r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            int r0 = r5.length     // Catch:{ all -> 0x0010 }
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0012
            long r3 = r5.largestDiscardedTimestampUs     // Catch:{ all -> 0x0010 }
            int r6 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r6 <= 0) goto L_0x000e
            r1 = r2
        L_0x000e:
            monitor-exit(r5)
            return r1
        L_0x0010:
            r6 = move-exception
            goto L_0x0028
        L_0x0012:
            long r3 = r5.getLargestReadTimestampUs()     // Catch:{ all -> 0x0010 }
            int r0 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r0 < 0) goto L_0x001c
            monitor-exit(r5)
            return r1
        L_0x001c:
            int r6 = r5.countUnreadSamplesBefore(r6)     // Catch:{ all -> 0x0010 }
            int r7 = r5.absoluteFirstIndex     // Catch:{ all -> 0x0010 }
            int r7 = r7 + r6
            r5.discardUpstreamSampleMetadata(r7)     // Catch:{ all -> 0x0010 }
            monitor-exit(r5)
            return r2
        L_0x0028:
            monitor-exit(r5)     // Catch:{ all -> 0x0010 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.source.SampleQueue.attemptSplice(long):boolean");
    }

    private synchronized void commitSample(long j2, int i3, long j3, int i4, @Nullable TrackOutput.CryptoData cryptoData) {
        try {
            int i5 = this.length;
            if (i5 > 0) {
                int relativeIndex = getRelativeIndex(i5 - 1);
                Assertions.checkArgument(this.offsets[relativeIndex] + ((long) this.sizes[relativeIndex]) <= j3);
            }
            this.isLastSampleQueued = (536870912 & i3) != 0;
            this.largestQueuedTimestampUs = Math.max(this.largestQueuedTimestampUs, j2);
            int relativeIndex2 = getRelativeIndex(this.length);
            this.timesUs[relativeIndex2] = j2;
            this.offsets[relativeIndex2] = j3;
            this.sizes[relativeIndex2] = i4;
            this.flags[relativeIndex2] = i3;
            this.cryptoDatas[relativeIndex2] = cryptoData;
            this.sourceIds[relativeIndex2] = this.upstreamSourceId;
            if (this.sharedSampleMetadata.isEmpty() || !this.sharedSampleMetadata.getEndValue().format.equals(this.upstreamFormat)) {
                DrmSessionManager drmSessionManager2 = this.drmSessionManager;
                this.sharedSampleMetadata.appendSpan(getWriteIndex(), new SharedSampleMetadata((Format) Assertions.checkNotNull(this.upstreamFormat), drmSessionManager2 != null ? drmSessionManager2.preacquireSession(this.drmEventDispatcher, this.upstreamFormat) : DrmSessionManager.DrmSessionReference.EMPTY));
            }
            int i6 = this.length + 1;
            this.length = i6;
            int i7 = this.capacity;
            if (i6 == i7) {
                int i8 = i7 + 1000;
                int[] iArr = new int[i8];
                long[] jArr = new long[i8];
                long[] jArr2 = new long[i8];
                int[] iArr2 = new int[i8];
                int[] iArr3 = new int[i8];
                TrackOutput.CryptoData[] cryptoDataArr = new TrackOutput.CryptoData[i8];
                int i9 = this.relativeFirstIndex;
                int i10 = i7 - i9;
                System.arraycopy(this.offsets, i9, jArr, 0, i10);
                System.arraycopy(this.timesUs, this.relativeFirstIndex, jArr2, 0, i10);
                System.arraycopy(this.flags, this.relativeFirstIndex, iArr2, 0, i10);
                System.arraycopy(this.sizes, this.relativeFirstIndex, iArr3, 0, i10);
                System.arraycopy(this.cryptoDatas, this.relativeFirstIndex, cryptoDataArr, 0, i10);
                System.arraycopy(this.sourceIds, this.relativeFirstIndex, iArr, 0, i10);
                int i11 = this.relativeFirstIndex;
                System.arraycopy(this.offsets, 0, jArr, i10, i11);
                System.arraycopy(this.timesUs, 0, jArr2, i10, i11);
                System.arraycopy(this.flags, 0, iArr2, i10, i11);
                System.arraycopy(this.sizes, 0, iArr3, i10, i11);
                System.arraycopy(this.cryptoDatas, 0, cryptoDataArr, i10, i11);
                System.arraycopy(this.sourceIds, 0, iArr, i10, i11);
                this.offsets = jArr;
                this.timesUs = jArr2;
                this.flags = iArr2;
                this.sizes = iArr3;
                this.cryptoDatas = cryptoDataArr;
                this.sourceIds = iArr;
                this.relativeFirstIndex = 0;
                this.capacity = i8;
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    private int countUnreadSamplesBefore(long j2) {
        int i3 = this.length;
        int relativeIndex = getRelativeIndex(i3 - 1);
        while (i3 > this.readPosition && this.timesUs[relativeIndex] >= j2) {
            i3--;
            relativeIndex--;
            if (relativeIndex == -1) {
                relativeIndex = this.capacity - 1;
            }
        }
        return i3;
    }

    public static SampleQueue createWithDrm(Allocator allocator, DrmSessionManager drmSessionManager2, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        return new SampleQueue(allocator, (DrmSessionManager) Assertions.checkNotNull(drmSessionManager2), (DrmSessionEventListener.EventDispatcher) Assertions.checkNotNull(eventDispatcher));
    }

    public static SampleQueue createWithoutDrm(Allocator allocator) {
        return new SampleQueue(allocator, (DrmSessionManager) null, (DrmSessionEventListener.EventDispatcher) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0031, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized long discardSampleMetadataTo(long r11, boolean r13, boolean r14) {
        /*
            r10 = this;
            monitor-enter(r10)
            int r0 = r10.length     // Catch:{ all -> 0x001c }
            r1 = -1
            if (r0 == 0) goto L_0x0030
            long[] r3 = r10.timesUs     // Catch:{ all -> 0x001c }
            int r5 = r10.relativeFirstIndex     // Catch:{ all -> 0x001c }
            r3 = r3[r5]     // Catch:{ all -> 0x001c }
            int r3 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x0012
            goto L_0x0030
        L_0x0012:
            if (r14 == 0) goto L_0x001a
            int r14 = r10.readPosition     // Catch:{ all -> 0x001c }
            if (r14 == r0) goto L_0x001a
            int r0 = r14 + 1
        L_0x001a:
            r6 = r0
            goto L_0x001e
        L_0x001c:
            r11 = move-exception
            goto L_0x0032
        L_0x001e:
            r4 = r10
            r7 = r11
            r9 = r13
            int r11 = r4.findSampleBefore(r5, r6, r7, r9)     // Catch:{ all -> 0x001c }
            r12 = -1
            if (r11 != r12) goto L_0x002a
            monitor-exit(r10)
            return r1
        L_0x002a:
            long r11 = r10.discardSamples(r11)     // Catch:{ all -> 0x001c }
            monitor-exit(r10)
            return r11
        L_0x0030:
            monitor-exit(r10)
            return r1
        L_0x0032:
            monitor-exit(r10)     // Catch:{ all -> 0x001c }
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.source.SampleQueue.discardSampleMetadataTo(long, boolean, boolean):long");
    }

    private synchronized long discardSampleMetadataToEnd() {
        int i3 = this.length;
        if (i3 == 0) {
            return -1;
        }
        return discardSamples(i3);
    }

    @GuardedBy("this")
    private long discardSamples(int i3) {
        this.largestDiscardedTimestampUs = Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(i3));
        this.length -= i3;
        int i4 = this.absoluteFirstIndex + i3;
        this.absoluteFirstIndex = i4;
        int i5 = this.relativeFirstIndex + i3;
        this.relativeFirstIndex = i5;
        int i6 = this.capacity;
        if (i5 >= i6) {
            this.relativeFirstIndex = i5 - i6;
        }
        int i7 = this.readPosition - i3;
        this.readPosition = i7;
        if (i7 < 0) {
            this.readPosition = 0;
        }
        this.sharedSampleMetadata.discardTo(i4);
        if (this.length != 0) {
            return this.offsets[this.relativeFirstIndex];
        }
        int i8 = this.relativeFirstIndex;
        if (i8 == 0) {
            i8 = this.capacity;
        }
        int i9 = i8 - 1;
        return this.offsets[i9] + ((long) this.sizes[i9]);
    }

    private long discardUpstreamSampleMetadata(int i3) {
        int writeIndex = getWriteIndex() - i3;
        boolean z2 = false;
        Assertions.checkArgument(writeIndex >= 0 && writeIndex <= this.length - this.readPosition);
        int i4 = this.length - writeIndex;
        this.length = i4;
        this.largestQueuedTimestampUs = Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(i4));
        if (writeIndex == 0 && this.isLastSampleQueued) {
            z2 = true;
        }
        this.isLastSampleQueued = z2;
        this.sharedSampleMetadata.discardFrom(i3);
        int i5 = this.length;
        if (i5 == 0) {
            return 0;
        }
        int relativeIndex = getRelativeIndex(i5 - 1);
        return this.offsets[relativeIndex] + ((long) this.sizes[relativeIndex]);
    }

    private int findSampleBefore(int i3, int i4, long j2, boolean z2) {
        int i5 = -1;
        for (int i6 = 0; i6 < i4; i6++) {
            long j3 = this.timesUs[i3];
            if (j3 > j2) {
                return i5;
            }
            if (!z2 || (this.flags[i3] & 1) != 0) {
                if (j3 == j2) {
                    return i6;
                }
                i5 = i6;
            }
            i3++;
            if (i3 == this.capacity) {
                i3 = 0;
            }
        }
        return i5;
    }

    private long getLargestTimestamp(int i3) {
        long j2 = Long.MIN_VALUE;
        if (i3 == 0) {
            return Long.MIN_VALUE;
        }
        int relativeIndex = getRelativeIndex(i3 - 1);
        for (int i4 = 0; i4 < i3; i4++) {
            j2 = Math.max(j2, this.timesUs[relativeIndex]);
            if ((this.flags[relativeIndex] & 1) != 0) {
                break;
            }
            relativeIndex--;
            if (relativeIndex == -1) {
                relativeIndex = this.capacity - 1;
            }
        }
        return j2;
    }

    private int getRelativeIndex(int i3) {
        int i4 = this.relativeFirstIndex + i3;
        int i5 = this.capacity;
        return i4 < i5 ? i4 : i4 - i5;
    }

    private boolean hasNextSample() {
        return this.readPosition != this.length;
    }

    private boolean mayReadSample(int i3) {
        DrmSession drmSession = this.currentDrmSession;
        return drmSession == null || drmSession.getState() == 4 || ((this.flags[i3] & 1073741824) == 0 && this.currentDrmSession.playClearSamplesWithoutKeys());
    }

    private void onFormatResult(Format format, FormatHolder formatHolder) {
        Format format2 = this.downstreamFormat;
        boolean z2 = format2 == null;
        DrmInitData drmInitData = z2 ? null : format2.drmInitData;
        this.downstreamFormat = format;
        DrmInitData drmInitData2 = format.drmInitData;
        DrmSessionManager drmSessionManager2 = this.drmSessionManager;
        formatHolder.format = drmSessionManager2 != null ? format.copyWithCryptoType(drmSessionManager2.getCryptoType(format)) : format;
        formatHolder.drmSession = this.currentDrmSession;
        if (this.drmSessionManager != null) {
            if (z2 || !Util.areEqual(drmInitData, drmInitData2)) {
                DrmSession drmSession = this.currentDrmSession;
                DrmSession acquireSession = this.drmSessionManager.acquireSession(this.drmEventDispatcher, format);
                this.currentDrmSession = acquireSession;
                formatHolder.drmSession = acquireSession;
                if (drmSession != null) {
                    drmSession.release(this.drmEventDispatcher);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002d, code lost:
        return -3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized int peekSampleMetadata(com.appsamurai.storyly.exoplayer2.core.FormatHolder r5, com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer r6, boolean r7, boolean r8, com.appsamurai.storyly.exoplayer2.core.source.SampleQueue.SampleExtrasHolder r9) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            r6.waitingForKeys = r0     // Catch:{ all -> 0x001f }
            boolean r0 = r4.hasNextSample()     // Catch:{ all -> 0x001f }
            r1 = -4
            r2 = -3
            r3 = -5
            if (r0 != 0) goto L_0x0034
            if (r8 != 0) goto L_0x002e
            boolean r8 = r4.isLastSampleQueued     // Catch:{ all -> 0x001f }
            if (r8 == 0) goto L_0x0014
            goto L_0x002e
        L_0x0014:
            com.appsamurai.storyly.exoplayer2.common.Format r6 = r4.upstreamFormat     // Catch:{ all -> 0x001f }
            if (r6 == 0) goto L_0x002c
            if (r7 != 0) goto L_0x0021
            com.appsamurai.storyly.exoplayer2.common.Format r7 = r4.downstreamFormat     // Catch:{ all -> 0x001f }
            if (r6 == r7) goto L_0x002c
            goto L_0x0021
        L_0x001f:
            r5 = move-exception
            goto L_0x008b
        L_0x0021:
            java.lang.Object r6 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r6)     // Catch:{ all -> 0x001f }
            com.appsamurai.storyly.exoplayer2.common.Format r6 = (com.appsamurai.storyly.exoplayer2.common.Format) r6     // Catch:{ all -> 0x001f }
            r4.onFormatResult(r6, r5)     // Catch:{ all -> 0x001f }
            monitor-exit(r4)
            return r3
        L_0x002c:
            monitor-exit(r4)
            return r2
        L_0x002e:
            r5 = 4
            r6.setFlags(r5)     // Catch:{ all -> 0x001f }
            monitor-exit(r4)
            return r1
        L_0x0034:
            com.appsamurai.storyly.exoplayer2.core.source.SpannedData<com.appsamurai.storyly.exoplayer2.core.source.SampleQueue$SharedSampleMetadata> r8 = r4.sharedSampleMetadata     // Catch:{ all -> 0x001f }
            int r0 = r4.getReadIndex()     // Catch:{ all -> 0x001f }
            java.lang.Object r8 = r8.get(r0)     // Catch:{ all -> 0x001f }
            com.appsamurai.storyly.exoplayer2.core.source.SampleQueue$SharedSampleMetadata r8 = (com.appsamurai.storyly.exoplayer2.core.source.SampleQueue.SharedSampleMetadata) r8     // Catch:{ all -> 0x001f }
            com.appsamurai.storyly.exoplayer2.common.Format r8 = r8.format     // Catch:{ all -> 0x001f }
            if (r7 != 0) goto L_0x0086
            com.appsamurai.storyly.exoplayer2.common.Format r7 = r4.downstreamFormat     // Catch:{ all -> 0x001f }
            if (r8 == r7) goto L_0x0049
            goto L_0x0086
        L_0x0049:
            int r5 = r4.readPosition     // Catch:{ all -> 0x001f }
            int r5 = r4.getRelativeIndex(r5)     // Catch:{ all -> 0x001f }
            boolean r7 = r4.mayReadSample(r5)     // Catch:{ all -> 0x001f }
            if (r7 != 0) goto L_0x005a
            r5 = 1
            r6.waitingForKeys = r5     // Catch:{ all -> 0x001f }
            monitor-exit(r4)
            return r2
        L_0x005a:
            int[] r7 = r4.flags     // Catch:{ all -> 0x001f }
            r7 = r7[r5]     // Catch:{ all -> 0x001f }
            r6.setFlags(r7)     // Catch:{ all -> 0x001f }
            long[] r7 = r4.timesUs     // Catch:{ all -> 0x001f }
            r7 = r7[r5]     // Catch:{ all -> 0x001f }
            r6.timeUs = r7     // Catch:{ all -> 0x001f }
            long r2 = r4.startTimeUs     // Catch:{ all -> 0x001f }
            int r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r7 >= 0) goto L_0x0072
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            r6.addFlag(r7)     // Catch:{ all -> 0x001f }
        L_0x0072:
            int[] r6 = r4.sizes     // Catch:{ all -> 0x001f }
            r6 = r6[r5]     // Catch:{ all -> 0x001f }
            r9.size = r6     // Catch:{ all -> 0x001f }
            long[] r6 = r4.offsets     // Catch:{ all -> 0x001f }
            r6 = r6[r5]     // Catch:{ all -> 0x001f }
            r9.offset = r6     // Catch:{ all -> 0x001f }
            com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput$CryptoData[] r6 = r4.cryptoDatas     // Catch:{ all -> 0x001f }
            r5 = r6[r5]     // Catch:{ all -> 0x001f }
            r9.cryptoData = r5     // Catch:{ all -> 0x001f }
            monitor-exit(r4)
            return r1
        L_0x0086:
            r4.onFormatResult(r8, r5)     // Catch:{ all -> 0x001f }
            monitor-exit(r4)
            return r3
        L_0x008b:
            monitor-exit(r4)     // Catch:{ all -> 0x001f }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.source.SampleQueue.peekSampleMetadata(com.appsamurai.storyly.exoplayer2.core.FormatHolder, com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer, boolean, boolean, com.appsamurai.storyly.exoplayer2.core.source.SampleQueue$SampleExtrasHolder):int");
    }

    private void releaseDrmSessionReferences() {
        DrmSession drmSession = this.currentDrmSession;
        if (drmSession != null) {
            drmSession.release(this.drmEventDispatcher);
            this.currentDrmSession = null;
            this.downstreamFormat = null;
        }
    }

    private synchronized void rewind() {
        this.readPosition = 0;
        this.sampleDataQueue.rewind();
    }

    private synchronized boolean setUpstreamFormat(Format format) {
        try {
            this.upstreamFormatRequired = false;
            if (Util.areEqual(format, this.upstreamFormat)) {
                return false;
            }
            if (this.sharedSampleMetadata.isEmpty() || !this.sharedSampleMetadata.getEndValue().format.equals(format)) {
                this.upstreamFormat = format;
            } else {
                this.upstreamFormat = this.sharedSampleMetadata.getEndValue().format;
            }
            Format format2 = this.upstreamFormat;
            this.upstreamAllSamplesAreSyncSamples = MimeTypes.allSamplesAreSyncSamples(format2.sampleMimeType, format2.codecs);
            this.loggedUnexpectedNonSyncSample = false;
            return true;
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized long discardSampleMetadataToRead() {
        int i3 = this.readPosition;
        if (i3 == 0) {
            return -1;
        }
        return discardSamples(i3);
    }

    public final void discardTo(long j2, boolean z2, boolean z3) {
        this.sampleDataQueue.discardDownstreamTo(discardSampleMetadataTo(j2, z2, z3));
    }

    public final void discardToEnd() {
        this.sampleDataQueue.discardDownstreamTo(discardSampleMetadataToEnd());
    }

    public final void discardToRead() {
        this.sampleDataQueue.discardDownstreamTo(discardSampleMetadataToRead());
    }

    public final void discardUpstreamFrom(long j2) {
        if (this.length != 0) {
            Assertions.checkArgument(j2 > getLargestReadTimestampUs());
            discardUpstreamSamples(this.absoluteFirstIndex + countUnreadSamplesBefore(j2));
        }
    }

    public final void discardUpstreamSamples(int i3) {
        this.sampleDataQueue.discardUpstreamSampleBytes(discardUpstreamSampleMetadata(i3));
    }

    public final void format(Format format) {
        Format adjustedUpstreamFormat = getAdjustedUpstreamFormat(format);
        this.upstreamFormatAdjustmentRequired = false;
        this.unadjustedUpstreamFormat = format;
        boolean upstreamFormat2 = setUpstreamFormat(adjustedUpstreamFormat);
        UpstreamFormatChangedListener upstreamFormatChangedListener = this.upstreamFormatChangeListener;
        if (upstreamFormatChangedListener != null && upstreamFormat2) {
            upstreamFormatChangedListener.onUpstreamFormatChanged(adjustedUpstreamFormat);
        }
    }

    @CallSuper
    public Format getAdjustedUpstreamFormat(Format format) {
        return (this.sampleOffsetUs == 0 || format.subsampleOffsetUs == Long.MAX_VALUE) ? format : format.buildUpon().setSubsampleOffsetUs(format.subsampleOffsetUs + this.sampleOffsetUs).build();
    }

    public final int getFirstIndex() {
        return this.absoluteFirstIndex;
    }

    public final synchronized long getFirstTimestampUs() {
        return this.length == 0 ? Long.MIN_VALUE : this.timesUs[this.relativeFirstIndex];
    }

    public final synchronized long getLargestQueuedTimestampUs() {
        return this.largestQueuedTimestampUs;
    }

    public final synchronized long getLargestReadTimestampUs() {
        return Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(this.readPosition));
    }

    public final int getReadIndex() {
        return this.absoluteFirstIndex + this.readPosition;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003d, code lost:
        return 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized int getSkipCount(long r9, boolean r11) {
        /*
            r8 = this;
            monitor-enter(r8)
            int r0 = r8.readPosition     // Catch:{ all -> 0x0026 }
            int r2 = r8.getRelativeIndex(r0)     // Catch:{ all -> 0x0026 }
            boolean r0 = r8.hasNextSample()     // Catch:{ all -> 0x0026 }
            r7 = 0
            if (r0 == 0) goto L_0x003c
            long[] r0 = r8.timesUs     // Catch:{ all -> 0x0026 }
            r0 = r0[r2]     // Catch:{ all -> 0x0026 }
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x0017
            goto L_0x003c
        L_0x0017:
            long r0 = r8.largestQueuedTimestampUs     // Catch:{ all -> 0x0026 }
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x0028
            if (r11 == 0) goto L_0x0028
            int r9 = r8.length     // Catch:{ all -> 0x0026 }
            int r10 = r8.readPosition     // Catch:{ all -> 0x0026 }
            int r9 = r9 - r10
            monitor-exit(r8)
            return r9
        L_0x0026:
            r9 = move-exception
            goto L_0x003e
        L_0x0028:
            int r11 = r8.length     // Catch:{ all -> 0x0026 }
            int r0 = r8.readPosition     // Catch:{ all -> 0x0026 }
            int r3 = r11 - r0
            r6 = 1
            r1 = r8
            r4 = r9
            int r9 = r1.findSampleBefore(r2, r3, r4, r6)     // Catch:{ all -> 0x0026 }
            r10 = -1
            if (r9 != r10) goto L_0x003a
            monitor-exit(r8)
            return r7
        L_0x003a:
            monitor-exit(r8)
            return r9
        L_0x003c:
            monitor-exit(r8)
            return r7
        L_0x003e:
            monitor-exit(r8)     // Catch:{ all -> 0x0026 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.source.SampleQueue.getSkipCount(long, boolean):int");
    }

    @Nullable
    public final synchronized Format getUpstreamFormat() {
        return this.upstreamFormatRequired ? null : this.upstreamFormat;
    }

    public final int getWriteIndex() {
        return this.absoluteFirstIndex + this.length;
    }

    public final void invalidateUpstreamFormatAdjustment() {
        this.upstreamFormatAdjustmentRequired = true;
    }

    public final synchronized boolean isLastSampleQueued() {
        return this.isLastSampleQueued;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001b, code lost:
        return r1;
     */
    @androidx.annotation.CallSuper
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean isReady(boolean r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.hasNextSample()     // Catch:{ all -> 0x0017 }
            r1 = 1
            if (r0 != 0) goto L_0x001c
            if (r3 != 0) goto L_0x001a
            boolean r3 = r2.isLastSampleQueued     // Catch:{ all -> 0x0017 }
            if (r3 != 0) goto L_0x001a
            com.appsamurai.storyly.exoplayer2.common.Format r3 = r2.upstreamFormat     // Catch:{ all -> 0x0017 }
            if (r3 == 0) goto L_0x0019
            com.appsamurai.storyly.exoplayer2.common.Format r0 = r2.downstreamFormat     // Catch:{ all -> 0x0017 }
            if (r3 == r0) goto L_0x0019
            goto L_0x001a
        L_0x0017:
            r3 = move-exception
            goto L_0x003c
        L_0x0019:
            r1 = 0
        L_0x001a:
            monitor-exit(r2)
            return r1
        L_0x001c:
            com.appsamurai.storyly.exoplayer2.core.source.SpannedData<com.appsamurai.storyly.exoplayer2.core.source.SampleQueue$SharedSampleMetadata> r3 = r2.sharedSampleMetadata     // Catch:{ all -> 0x0017 }
            int r0 = r2.getReadIndex()     // Catch:{ all -> 0x0017 }
            java.lang.Object r3 = r3.get(r0)     // Catch:{ all -> 0x0017 }
            com.appsamurai.storyly.exoplayer2.core.source.SampleQueue$SharedSampleMetadata r3 = (com.appsamurai.storyly.exoplayer2.core.source.SampleQueue.SharedSampleMetadata) r3     // Catch:{ all -> 0x0017 }
            com.appsamurai.storyly.exoplayer2.common.Format r3 = r3.format     // Catch:{ all -> 0x0017 }
            com.appsamurai.storyly.exoplayer2.common.Format r0 = r2.downstreamFormat     // Catch:{ all -> 0x0017 }
            if (r3 == r0) goto L_0x0030
            monitor-exit(r2)
            return r1
        L_0x0030:
            int r3 = r2.readPosition     // Catch:{ all -> 0x0017 }
            int r3 = r2.getRelativeIndex(r3)     // Catch:{ all -> 0x0017 }
            boolean r3 = r2.mayReadSample(r3)     // Catch:{ all -> 0x0017 }
            monitor-exit(r2)
            return r3
        L_0x003c:
            monitor-exit(r2)     // Catch:{ all -> 0x0017 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.source.SampleQueue.isReady(boolean):boolean");
    }

    @CallSuper
    public void maybeThrowError() throws IOException {
        DrmSession drmSession = this.currentDrmSession;
        if (drmSession != null && drmSession.getState() == 1) {
            throw ((DrmSession.DrmSessionException) Assertions.checkNotNull(this.currentDrmSession.getError()));
        }
    }

    public final synchronized int peekSourceId() {
        try {
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return hasNextSample() ? this.sourceIds[getRelativeIndex(this.readPosition)] : this.upstreamSourceId;
    }

    @CallSuper
    public void preRelease() {
        discardToEnd();
        releaseDrmSessionReferences();
    }

    @CallSuper
    public int read(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i3, boolean z2) {
        boolean z3 = false;
        int peekSampleMetadata = peekSampleMetadata(formatHolder, decoderInputBuffer, (i3 & 2) != 0, z2, this.extrasHolder);
        if (peekSampleMetadata == -4 && !decoderInputBuffer.isEndOfStream()) {
            if ((i3 & 1) != 0) {
                z3 = true;
            }
            if ((i3 & 4) == 0) {
                if (z3) {
                    this.sampleDataQueue.peekToBuffer(decoderInputBuffer, this.extrasHolder);
                } else {
                    this.sampleDataQueue.readToBuffer(decoderInputBuffer, this.extrasHolder);
                }
            }
            if (!z3) {
                this.readPosition++;
            }
        }
        return peekSampleMetadata;
    }

    @CallSuper
    public void release() {
        reset(true);
        releaseDrmSessionReferences();
    }

    public final void reset() {
        reset(false);
    }

    public final int sampleData(DataReader dataReader, int i3, boolean z2, int i4) throws IOException {
        return this.sampleDataQueue.sampleData(dataReader, i3, z2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sampleMetadata(long r11, int r13, int r14, int r15, @androidx.annotation.Nullable com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput.CryptoData r16) {
        /*
            r10 = this;
            r0 = r10
            boolean r1 = r0.upstreamFormatAdjustmentRequired
            if (r1 == 0) goto L_0x0010
            com.appsamurai.storyly.exoplayer2.common.Format r1 = r0.unadjustedUpstreamFormat
            java.lang.Object r1 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkStateNotNull(r1)
            com.appsamurai.storyly.exoplayer2.common.Format r1 = (com.appsamurai.storyly.exoplayer2.common.Format) r1
            r10.format(r1)
        L_0x0010:
            r1 = r13 & 1
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0018
            r4 = r3
            goto L_0x0019
        L_0x0018:
            r4 = r2
        L_0x0019:
            boolean r5 = r0.upstreamKeyframeRequired
            if (r5 == 0) goto L_0x0022
            if (r4 != 0) goto L_0x0020
            return
        L_0x0020:
            r0.upstreamKeyframeRequired = r2
        L_0x0022:
            long r5 = r0.sampleOffsetUs
            long r5 = r5 + r11
            boolean r7 = r0.upstreamAllSamplesAreSyncSamples
            if (r7 == 0) goto L_0x0051
            long r7 = r0.startTimeUs
            int r7 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r7 >= 0) goto L_0x0030
            return
        L_0x0030:
            if (r1 != 0) goto L_0x0051
            boolean r1 = r0.loggedUnexpectedNonSyncSample
            if (r1 != 0) goto L_0x004d
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r7 = "Overriding unexpected non-sync sample for format: "
            r1.<init>(r7)
            com.appsamurai.storyly.exoplayer2.common.Format r7 = r0.upstreamFormat
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            java.lang.String r7 = "SampleQueue"
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r7, r1)
            r0.loggedUnexpectedNonSyncSample = r3
        L_0x004d:
            r1 = r13 | 1
            r3 = r1
            goto L_0x0052
        L_0x0051:
            r3 = r13
        L_0x0052:
            boolean r1 = r0.pendingSplice
            if (r1 == 0) goto L_0x0063
            if (r4 == 0) goto L_0x0062
            boolean r1 = r10.attemptSplice(r5)
            if (r1 != 0) goto L_0x005f
            goto L_0x0062
        L_0x005f:
            r0.pendingSplice = r2
            goto L_0x0063
        L_0x0062:
            return
        L_0x0063:
            com.appsamurai.storyly.exoplayer2.core.source.SampleDataQueue r1 = r0.sampleDataQueue
            long r1 = r1.getTotalBytesWritten()
            r7 = r14
            long r8 = (long) r7
            long r1 = r1 - r8
            r4 = r15
            long r8 = (long) r4
            long r8 = r1 - r8
            r0 = r10
            r1 = r5
            r4 = r8
            r6 = r14
            r7 = r16
            r0.commitSample(r1, r3, r4, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.source.SampleQueue.sampleMetadata(long, int, int, int, com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput$CryptoData):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean seekTo(int r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            r3.rewind()     // Catch:{ all -> 0x0018 }
            int r0 = r3.absoluteFirstIndex     // Catch:{ all -> 0x0018 }
            if (r4 < r0) goto L_0x001a
            int r1 = r3.length     // Catch:{ all -> 0x0018 }
            int r1 = r1 + r0
            if (r4 <= r1) goto L_0x000e
            goto L_0x001a
        L_0x000e:
            r1 = -9223372036854775808
            r3.startTimeUs = r1     // Catch:{ all -> 0x0018 }
            int r4 = r4 - r0
            r3.readPosition = r4     // Catch:{ all -> 0x0018 }
            monitor-exit(r3)
            r3 = 1
            return r3
        L_0x0018:
            r4 = move-exception
            goto L_0x001d
        L_0x001a:
            monitor-exit(r3)
            r3 = 0
            return r3
        L_0x001d:
            monitor-exit(r3)     // Catch:{ all -> 0x0018 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.source.SampleQueue.seekTo(int):boolean");
    }

    public final void setSampleOffsetUs(long j2) {
        if (this.sampleOffsetUs != j2) {
            this.sampleOffsetUs = j2;
            invalidateUpstreamFormatAdjustment();
        }
    }

    public final void setStartTimeUs(long j2) {
        this.startTimeUs = j2;
    }

    public final void setUpstreamFormatChangeListener(@Nullable UpstreamFormatChangedListener upstreamFormatChangedListener) {
        this.upstreamFormatChangeListener = upstreamFormatChangedListener;
    }

    public final synchronized void skip(int i3) {
        boolean z2;
        if (i3 >= 0) {
            try {
                if (this.readPosition + i3 <= this.length) {
                    z2 = true;
                    Assertions.checkArgument(z2);
                    this.readPosition += i3;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        z2 = false;
        Assertions.checkArgument(z2);
        this.readPosition += i3;
    }

    public final void sourceId(int i3) {
        this.upstreamSourceId = i3;
    }

    public final void splice() {
        this.pendingSplice = true;
    }

    @CallSuper
    public void reset(boolean z2) {
        this.sampleDataQueue.reset();
        this.length = 0;
        this.absoluteFirstIndex = 0;
        this.relativeFirstIndex = 0;
        this.readPosition = 0;
        this.upstreamKeyframeRequired = true;
        this.startTimeUs = Long.MIN_VALUE;
        this.largestDiscardedTimestampUs = Long.MIN_VALUE;
        this.largestQueuedTimestampUs = Long.MIN_VALUE;
        this.isLastSampleQueued = false;
        this.sharedSampleMetadata.clear();
        if (z2) {
            this.unadjustedUpstreamFormat = null;
            this.upstreamFormat = null;
            this.upstreamFormatRequired = true;
        }
    }

    public final void sampleData(ParsableByteArray parsableByteArray, int i3, int i4) {
        this.sampleDataQueue.sampleData(parsableByteArray, i3);
    }

    @Deprecated
    public static SampleQueue createWithDrm(Allocator allocator, Looper looper, DrmSessionManager drmSessionManager2, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        drmSessionManager2.setPlayer(looper, PlayerId.UNSET);
        return new SampleQueue(allocator, (DrmSessionManager) Assertions.checkNotNull(drmSessionManager2), (DrmSessionEventListener.EventDispatcher) Assertions.checkNotNull(eventDispatcher));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0041, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean seekTo(long r9, boolean r11) {
        /*
            r8 = this;
            monitor-enter(r8)
            r8.rewind()     // Catch:{ all -> 0x003e }
            int r0 = r8.readPosition     // Catch:{ all -> 0x003e }
            int r2 = r8.getRelativeIndex(r0)     // Catch:{ all -> 0x003e }
            boolean r0 = r8.hasNextSample()     // Catch:{ all -> 0x003e }
            r7 = 0
            if (r0 == 0) goto L_0x0040
            long[] r0 = r8.timesUs     // Catch:{ all -> 0x003e }
            r0 = r0[r2]     // Catch:{ all -> 0x003e }
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 < 0) goto L_0x0040
            long r0 = r8.largestQueuedTimestampUs     // Catch:{ all -> 0x003e }
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x0022
            if (r11 != 0) goto L_0x0022
            goto L_0x0040
        L_0x0022:
            int r11 = r8.length     // Catch:{ all -> 0x003e }
            int r0 = r8.readPosition     // Catch:{ all -> 0x003e }
            int r3 = r11 - r0
            r6 = 1
            r1 = r8
            r4 = r9
            int r11 = r1.findSampleBefore(r2, r3, r4, r6)     // Catch:{ all -> 0x003e }
            r0 = -1
            if (r11 != r0) goto L_0x0034
            monitor-exit(r8)
            return r7
        L_0x0034:
            r8.startTimeUs = r9     // Catch:{ all -> 0x003e }
            int r9 = r8.readPosition     // Catch:{ all -> 0x003e }
            int r9 = r9 + r11
            r8.readPosition = r9     // Catch:{ all -> 0x003e }
            monitor-exit(r8)
            r8 = 1
            return r8
        L_0x003e:
            r9 = move-exception
            goto L_0x0042
        L_0x0040:
            monitor-exit(r8)
            return r7
        L_0x0042:
            monitor-exit(r8)     // Catch:{ all -> 0x003e }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.source.SampleQueue.seekTo(long, boolean):boolean");
    }
}
