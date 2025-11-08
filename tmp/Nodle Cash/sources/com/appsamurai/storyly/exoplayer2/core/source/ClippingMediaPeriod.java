package com.appsamurai.storyly.exoplayer2.core.source;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.FormatHolder;
import com.appsamurai.storyly.exoplayer2.core.SeekParameters;
import com.appsamurai.storyly.exoplayer2.core.source.ClippingMediaSource;
import com.appsamurai.storyly.exoplayer2.core.source.MediaPeriod;
import com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer;
import java.io.IOException;

public final class ClippingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    @Nullable
    private MediaPeriod.Callback callback;
    @Nullable
    private ClippingMediaSource.IllegalClippingException clippingError;
    long endUs;
    public final MediaPeriod mediaPeriod;
    private long pendingInitialDiscontinuityPositionUs;
    private ClippingSampleStream[] sampleStreams = new ClippingSampleStream[0];
    long startUs;

    public final class ClippingSampleStream implements SampleStream {
        public final SampleStream childStream;
        private boolean sentEos;

        public ClippingSampleStream(SampleStream sampleStream) {
            this.childStream = sampleStream;
        }

        public void clearSentEos() {
            this.sentEos = false;
        }

        public boolean isReady() {
            return !ClippingMediaPeriod.this.isPendingInitialDiscontinuity() && this.childStream.isReady();
        }

        public void maybeThrowError() throws IOException {
            this.childStream.maybeThrowError();
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i3) {
            if (ClippingMediaPeriod.this.isPendingInitialDiscontinuity()) {
                return -3;
            }
            if (this.sentEos) {
                decoderInputBuffer.setFlags(4);
                return -4;
            }
            int readData = this.childStream.readData(formatHolder, decoderInputBuffer, i3);
            if (readData == -5) {
                Format format = (Format) Assertions.checkNotNull(formatHolder.format);
                int i4 = format.encoderDelay;
                if (!(i4 == 0 && format.encoderPadding == 0)) {
                    ClippingMediaPeriod clippingMediaPeriod = ClippingMediaPeriod.this;
                    int i5 = 0;
                    if (clippingMediaPeriod.startUs != 0) {
                        i4 = 0;
                    }
                    if (clippingMediaPeriod.endUs == Long.MIN_VALUE) {
                        i5 = format.encoderPadding;
                    }
                    formatHolder.format = format.buildUpon().setEncoderDelay(i4).setEncoderPadding(i5).build();
                }
                return -5;
            }
            ClippingMediaPeriod clippingMediaPeriod2 = ClippingMediaPeriod.this;
            long j2 = clippingMediaPeriod2.endUs;
            if (j2 == Long.MIN_VALUE || ((readData != -4 || decoderInputBuffer.timeUs < j2) && (readData != -3 || clippingMediaPeriod2.getBufferedPositionUs() != Long.MIN_VALUE || decoderInputBuffer.waitingForKeys))) {
                return readData;
            }
            decoderInputBuffer.clear();
            decoderInputBuffer.setFlags(4);
            this.sentEos = true;
            return -4;
        }

        public int skipData(long j2) {
            if (ClippingMediaPeriod.this.isPendingInitialDiscontinuity()) {
                return -3;
            }
            return this.childStream.skipData(j2);
        }
    }

    public ClippingMediaPeriod(MediaPeriod mediaPeriod2, boolean z2, long j2, long j3) {
        this.mediaPeriod = mediaPeriod2;
        this.pendingInitialDiscontinuityPositionUs = z2 ? j2 : C.TIME_UNSET;
        this.startUs = j2;
        this.endUs = j3;
    }

    private SeekParameters clipSeekParameters(long j2, SeekParameters seekParameters) {
        long constrainValue = Util.constrainValue(seekParameters.toleranceBeforeUs, 0, j2 - this.startUs);
        long j3 = seekParameters.toleranceAfterUs;
        long j4 = this.endUs;
        long constrainValue2 = Util.constrainValue(j3, 0, j4 == Long.MIN_VALUE ? Long.MAX_VALUE : j4 - j2);
        return (constrainValue == seekParameters.toleranceBeforeUs && constrainValue2 == seekParameters.toleranceAfterUs) ? seekParameters : new SeekParameters(constrainValue, constrainValue2);
    }

    private static boolean shouldKeepInitialDiscontinuity(long j2, ExoTrackSelection[] exoTrackSelectionArr) {
        if (j2 != 0) {
            for (ExoTrackSelection exoTrackSelection : exoTrackSelectionArr) {
                if (exoTrackSelection != null) {
                    Format selectedFormat = exoTrackSelection.getSelectedFormat();
                    if (!MimeTypes.allSamplesAreSyncSamples(selectedFormat.sampleMimeType, selectedFormat.codecs)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean continueLoading(long j2) {
        return this.mediaPeriod.continueLoading(j2);
    }

    public void discardBuffer(long j2, boolean z2) {
        this.mediaPeriod.discardBuffer(j2, z2);
    }

    public long getAdjustedSeekPositionUs(long j2, SeekParameters seekParameters) {
        long j3 = this.startUs;
        if (j2 == j3) {
            return j3;
        }
        return this.mediaPeriod.getAdjustedSeekPositionUs(j2, clipSeekParameters(j2, seekParameters));
    }

    public long getBufferedPositionUs() {
        long bufferedPositionUs = this.mediaPeriod.getBufferedPositionUs();
        if (bufferedPositionUs != Long.MIN_VALUE) {
            long j2 = this.endUs;
            if (j2 == Long.MIN_VALUE || bufferedPositionUs < j2) {
                return bufferedPositionUs;
            }
        }
        return Long.MIN_VALUE;
    }

    public long getNextLoadPositionUs() {
        long nextLoadPositionUs = this.mediaPeriod.getNextLoadPositionUs();
        if (nextLoadPositionUs != Long.MIN_VALUE) {
            long j2 = this.endUs;
            if (j2 == Long.MIN_VALUE || nextLoadPositionUs < j2) {
                return nextLoadPositionUs;
            }
        }
        return Long.MIN_VALUE;
    }

    public TrackGroupArray getTrackGroups() {
        return this.mediaPeriod.getTrackGroups();
    }

    public boolean isLoading() {
        return this.mediaPeriod.isLoading();
    }

    public boolean isPendingInitialDiscontinuity() {
        return this.pendingInitialDiscontinuityPositionUs != C.TIME_UNSET;
    }

    public void maybeThrowPrepareError() throws IOException {
        ClippingMediaSource.IllegalClippingException illegalClippingException = this.clippingError;
        if (illegalClippingException == null) {
            this.mediaPeriod.maybeThrowPrepareError();
            return;
        }
        throw illegalClippingException;
    }

    public void onPrepared(MediaPeriod mediaPeriod2) {
        if (this.clippingError == null) {
            ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onPrepared(this);
        }
    }

    public void prepare(MediaPeriod.Callback callback2, long j2) {
        this.callback = callback2;
        this.mediaPeriod.prepare(this, j2);
    }

    public long readDiscontinuity() {
        if (isPendingInitialDiscontinuity()) {
            long j2 = this.pendingInitialDiscontinuityPositionUs;
            this.pendingInitialDiscontinuityPositionUs = C.TIME_UNSET;
            long readDiscontinuity = readDiscontinuity();
            return readDiscontinuity != C.TIME_UNSET ? readDiscontinuity : j2;
        }
        long readDiscontinuity2 = this.mediaPeriod.readDiscontinuity();
        if (readDiscontinuity2 == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        boolean z2 = false;
        Assertions.checkState(readDiscontinuity2 >= this.startUs);
        long j3 = this.endUs;
        if (j3 == Long.MIN_VALUE || readDiscontinuity2 <= j3) {
            z2 = true;
        }
        Assertions.checkState(z2);
        return readDiscontinuity2;
    }

    public void reevaluateBuffer(long j2) {
        this.mediaPeriod.reevaluateBuffer(j2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0032, code lost:
        if (r0 > r5) goto L_0x0035;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long seekToUs(long r6) {
        /*
            r5 = this;
            r0 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r5.pendingInitialDiscontinuityPositionUs = r0
            com.appsamurai.storyly.exoplayer2.core.source.ClippingMediaPeriod$ClippingSampleStream[] r0 = r5.sampleStreams
            int r1 = r0.length
            r2 = 0
            r3 = r2
        L_0x000c:
            if (r3 >= r1) goto L_0x0018
            r4 = r0[r3]
            if (r4 == 0) goto L_0x0015
            r4.clearSentEos()
        L_0x0015:
            int r3 = r3 + 1
            goto L_0x000c
        L_0x0018:
            com.appsamurai.storyly.exoplayer2.core.source.MediaPeriod r0 = r5.mediaPeriod
            long r0 = r0.seekToUs(r6)
            int r6 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r6 == 0) goto L_0x0034
            long r6 = r5.startUs
            int r6 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r6 < 0) goto L_0x0035
            long r5 = r5.endUs
            r3 = -9223372036854775808
            int r7 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r7 == 0) goto L_0x0034
            int r5 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r5 > 0) goto L_0x0035
        L_0x0034:
            r2 = 1
        L_0x0035:
            com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkState(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.source.ClippingMediaPeriod.seekToUs(long):long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005e, code lost:
        if (r2 > r4) goto L_0x0061;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long selectTracks(com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection[] r13, boolean[] r14, com.appsamurai.storyly.exoplayer2.core.source.SampleStream[] r15, boolean[] r16, long r17) {
        /*
            r12 = this;
            r0 = r12
            r1 = r15
            int r2 = r1.length
            com.appsamurai.storyly.exoplayer2.core.source.ClippingMediaPeriod$ClippingSampleStream[] r2 = new com.appsamurai.storyly.exoplayer2.core.source.ClippingMediaPeriod.ClippingSampleStream[r2]
            r0.sampleStreams = r2
            int r2 = r1.length
            com.appsamurai.storyly.exoplayer2.core.source.SampleStream[] r9 = new com.appsamurai.storyly.exoplayer2.core.source.SampleStream[r2]
            r10 = 0
            r2 = r10
        L_0x000c:
            int r3 = r1.length
            r11 = 0
            if (r2 >= r3) goto L_0x0021
            com.appsamurai.storyly.exoplayer2.core.source.ClippingMediaPeriod$ClippingSampleStream[] r3 = r0.sampleStreams
            r4 = r1[r2]
            com.appsamurai.storyly.exoplayer2.core.source.ClippingMediaPeriod$ClippingSampleStream r4 = (com.appsamurai.storyly.exoplayer2.core.source.ClippingMediaPeriod.ClippingSampleStream) r4
            r3[r2] = r4
            if (r4 == 0) goto L_0x001c
            com.appsamurai.storyly.exoplayer2.core.source.SampleStream r11 = r4.childStream
        L_0x001c:
            r9[r2] = r11
            int r2 = r2 + 1
            goto L_0x000c
        L_0x0021:
            com.appsamurai.storyly.exoplayer2.core.source.MediaPeriod r2 = r0.mediaPeriod
            r3 = r13
            r4 = r14
            r5 = r9
            r6 = r16
            r7 = r17
            long r2 = r2.selectTracks(r3, r4, r5, r6, r7)
            boolean r4 = r12.isPendingInitialDiscontinuity()
            if (r4 == 0) goto L_0x0043
            long r4 = r0.startUs
            int r6 = (r17 > r4 ? 1 : (r17 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x0043
            r6 = r13
            boolean r4 = shouldKeepInitialDiscontinuity(r4, r13)
            if (r4 == 0) goto L_0x0043
            r4 = r2
            goto L_0x0048
        L_0x0043:
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x0048:
            r0.pendingInitialDiscontinuityPositionUs = r4
            int r4 = (r2 > r17 ? 1 : (r2 == r17 ? 0 : -1))
            if (r4 == 0) goto L_0x0063
            long r4 = r0.startUs
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 < 0) goto L_0x0061
            long r4 = r0.endUs
            r6 = -9223372036854775808
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 == 0) goto L_0x0063
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 > 0) goto L_0x0061
            goto L_0x0063
        L_0x0061:
            r4 = r10
            goto L_0x0064
        L_0x0063:
            r4 = 1
        L_0x0064:
            com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkState(r4)
        L_0x0067:
            int r4 = r1.length
            if (r10 >= r4) goto L_0x008d
            r4 = r9[r10]
            if (r4 != 0) goto L_0x0073
            com.appsamurai.storyly.exoplayer2.core.source.ClippingMediaPeriod$ClippingSampleStream[] r4 = r0.sampleStreams
            r4[r10] = r11
            goto L_0x0084
        L_0x0073:
            com.appsamurai.storyly.exoplayer2.core.source.ClippingMediaPeriod$ClippingSampleStream[] r5 = r0.sampleStreams
            r6 = r5[r10]
            if (r6 == 0) goto L_0x007d
            com.appsamurai.storyly.exoplayer2.core.source.SampleStream r6 = r6.childStream
            if (r6 == r4) goto L_0x0084
        L_0x007d:
            com.appsamurai.storyly.exoplayer2.core.source.ClippingMediaPeriod$ClippingSampleStream r6 = new com.appsamurai.storyly.exoplayer2.core.source.ClippingMediaPeriod$ClippingSampleStream
            r6.<init>(r4)
            r5[r10] = r6
        L_0x0084:
            com.appsamurai.storyly.exoplayer2.core.source.ClippingMediaPeriod$ClippingSampleStream[] r4 = r0.sampleStreams
            r4 = r4[r10]
            r1[r10] = r4
            int r10 = r10 + 1
            goto L_0x0067
        L_0x008d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.source.ClippingMediaPeriod.selectTracks(com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection[], boolean[], com.appsamurai.storyly.exoplayer2.core.source.SampleStream[], boolean[], long):long");
    }

    public void setClippingError(ClippingMediaSource.IllegalClippingException illegalClippingException) {
        this.clippingError = illegalClippingException;
    }

    public void updateClipping(long j2, long j3) {
        this.startUs = j2;
        this.endUs = j3;
    }

    public void onContinueLoadingRequested(MediaPeriod mediaPeriod2) {
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
    }
}
