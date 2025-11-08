package com.appsamurai.storyly.exoplayer2.core.source;

import android.net.Uri;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.source.TrackGroup;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.FormatHolder;
import com.appsamurai.storyly.exoplayer2.core.SeekParameters;
import com.appsamurai.storyly.exoplayer2.core.source.MediaPeriod;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.upstream.Allocator;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.TransferListener;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer;
import java.util.ArrayList;

public final class SilenceMediaSource extends BaseMediaSource {
    private static final int CHANNEL_COUNT = 2;
    /* access modifiers changed from: private */
    public static final Format FORMAT;
    public static final String MEDIA_ID = "SilenceMediaSource";
    /* access modifiers changed from: private */
    public static final MediaItem MEDIA_ITEM;
    private static final int PCM_ENCODING = 2;
    private static final int SAMPLE_RATE_HZ = 44100;
    /* access modifiers changed from: private */
    public static final byte[] SILENCE_SAMPLE = new byte[(Util.getPcmFrameSize(2, 2) * 1024)];
    private final long durationUs;
    private final MediaItem mediaItem;

    public static final class Factory {
        private long durationUs;
        @Nullable
        private Object tag;

        public SilenceMediaSource createMediaSource() {
            Assertions.checkState(this.durationUs > 0);
            return new SilenceMediaSource(this.durationUs, SilenceMediaSource.MEDIA_ITEM.buildUpon().setTag(this.tag).build());
        }

        public Factory setDurationUs(@IntRange(from = 1) long j2) {
            this.durationUs = j2;
            return this;
        }

        public Factory setTag(@Nullable Object obj) {
            this.tag = obj;
            return this;
        }
    }

    public static final class SilenceMediaPeriod implements MediaPeriod {
        private static final TrackGroupArray TRACKS = new TrackGroupArray(new TrackGroup(SilenceMediaSource.FORMAT));
        private final long durationUs;
        private final ArrayList<SampleStream> sampleStreams = new ArrayList<>();

        public SilenceMediaPeriod(long j2) {
            this.durationUs = j2;
        }

        private long constrainSeekPosition(long j2) {
            return Util.constrainValue(j2, 0, this.durationUs);
        }

        public boolean continueLoading(long j2) {
            return false;
        }

        public void discardBuffer(long j2, boolean z2) {
        }

        public long getAdjustedSeekPositionUs(long j2, SeekParameters seekParameters) {
            return constrainSeekPosition(j2);
        }

        public long getBufferedPositionUs() {
            return Long.MIN_VALUE;
        }

        public long getNextLoadPositionUs() {
            return Long.MIN_VALUE;
        }

        public TrackGroupArray getTrackGroups() {
            return TRACKS;
        }

        public boolean isLoading() {
            return false;
        }

        public void maybeThrowPrepareError() {
        }

        public void prepare(MediaPeriod.Callback callback, long j2) {
            callback.onPrepared(this);
        }

        public long readDiscontinuity() {
            return C.TIME_UNSET;
        }

        public void reevaluateBuffer(long j2) {
        }

        public long seekToUs(long j2) {
            long constrainSeekPosition = constrainSeekPosition(j2);
            for (int i3 = 0; i3 < this.sampleStreams.size(); i3++) {
                ((SilenceSampleStream) this.sampleStreams.get(i3)).seekTo(constrainSeekPosition);
            }
            return constrainSeekPosition;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Object[]} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object[]} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long selectTracks(com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection[] r5, boolean[] r6, com.appsamurai.storyly.exoplayer2.core.source.SampleStream[] r7, boolean[] r8, long r9) {
            /*
                r4 = this;
                long r9 = r4.constrainSeekPosition(r9)
                r0 = 0
            L_0x0005:
                int r1 = r5.length
                if (r0 >= r1) goto L_0x003b
                r1 = r7[r0]
                if (r1 == 0) goto L_0x001c
                r2 = r5[r0]
                if (r2 == 0) goto L_0x0014
                boolean r2 = r6[r0]
                if (r2 != 0) goto L_0x001c
            L_0x0014:
                java.util.ArrayList<com.appsamurai.storyly.exoplayer2.core.source.SampleStream> r2 = r4.sampleStreams
                r2.remove(r1)
                r1 = 0
                r7[r0] = r1
            L_0x001c:
                r1 = r7[r0]
                if (r1 != 0) goto L_0x0038
                r1 = r5[r0]
                if (r1 == 0) goto L_0x0038
                com.appsamurai.storyly.exoplayer2.core.source.SilenceMediaSource$SilenceSampleStream r1 = new com.appsamurai.storyly.exoplayer2.core.source.SilenceMediaSource$SilenceSampleStream
                long r2 = r4.durationUs
                r1.<init>(r2)
                r1.seekTo(r9)
                java.util.ArrayList<com.appsamurai.storyly.exoplayer2.core.source.SampleStream> r2 = r4.sampleStreams
                r2.add(r1)
                r7[r0] = r1
                r1 = 1
                r8[r0] = r1
            L_0x0038:
                int r0 = r0 + 1
                goto L_0x0005
            L_0x003b:
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.source.SilenceMediaSource.SilenceMediaPeriod.selectTracks(com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection[], boolean[], com.appsamurai.storyly.exoplayer2.core.source.SampleStream[], boolean[], long):long");
        }
    }

    public static final class SilenceSampleStream implements SampleStream {
        private final long durationBytes;
        private long positionBytes;
        private boolean sentFormat;

        public SilenceSampleStream(long j2) {
            this.durationBytes = SilenceMediaSource.getAudioByteCount(j2);
            seekTo(0);
        }

        public boolean isReady() {
            return true;
        }

        public void maybeThrowError() {
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i3) {
            if (!this.sentFormat || (i3 & 2) != 0) {
                formatHolder.format = SilenceMediaSource.FORMAT;
                this.sentFormat = true;
                return -5;
            }
            long j2 = this.durationBytes;
            long j3 = this.positionBytes;
            long j4 = j2 - j3;
            if (j4 == 0) {
                decoderInputBuffer.addFlag(4);
                return -4;
            }
            decoderInputBuffer.timeUs = SilenceMediaSource.getAudioPositionUs(j3);
            decoderInputBuffer.addFlag(1);
            int min = (int) Math.min((long) SilenceMediaSource.SILENCE_SAMPLE.length, j4);
            if ((i3 & 4) == 0) {
                decoderInputBuffer.ensureSpaceForWrite(min);
                decoderInputBuffer.data.put(SilenceMediaSource.SILENCE_SAMPLE, 0, min);
            }
            if ((i3 & 1) == 0) {
                this.positionBytes += (long) min;
            }
            return -4;
        }

        public void seekTo(long j2) {
            this.positionBytes = Util.constrainValue(SilenceMediaSource.getAudioByteCount(j2), 0, this.durationBytes);
        }

        public int skipData(long j2) {
            long j3 = this.positionBytes;
            seekTo(j2);
            return (int) ((this.positionBytes - j3) / ((long) SilenceMediaSource.SILENCE_SAMPLE.length));
        }
    }

    static {
        Format build = new Format.Builder().setSampleMimeType(MimeTypes.AUDIO_RAW).setChannelCount(2).setSampleRate(SAMPLE_RATE_HZ).setPcmEncoding(2).build();
        FORMAT = build;
        MEDIA_ITEM = new MediaItem.Builder().setMediaId(MEDIA_ID).setUri(Uri.EMPTY).setMimeType(build.sampleMimeType).build();
    }

    /* access modifiers changed from: private */
    public static long getAudioByteCount(long j2) {
        return ((long) Util.getPcmFrameSize(2, 2)) * ((j2 * 44100) / 1000000);
    }

    /* access modifiers changed from: private */
    public static long getAudioPositionUs(long j2) {
        return ((j2 / ((long) Util.getPcmFrameSize(2, 2))) * 1000000) / 44100;
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j2) {
        return new SilenceMediaPeriod(this.durationUs);
    }

    public MediaItem getMediaItem() {
        return this.mediaItem;
    }

    public void maybeThrowSourceInfoRefreshError() {
    }

    public void prepareSourceInternal(@Nullable TransferListener transferListener) {
        refreshSourceInfo(new SinglePeriodTimeline(this.durationUs, true, false, false, (Object) null, this.mediaItem));
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
    }

    public void releaseSourceInternal() {
    }

    public SilenceMediaSource(long j2) {
        this(j2, MEDIA_ITEM);
    }

    private SilenceMediaSource(long j2, MediaItem mediaItem2) {
        Assertions.checkArgument(j2 >= 0);
        this.durationUs = j2;
        this.mediaItem = mediaItem2;
    }
}
