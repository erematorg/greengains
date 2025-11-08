package com.appsamurai.storyly.exoplayer2.hls;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.TimestampAdjuster;
import com.appsamurai.storyly.exoplayer2.common.util.UriUtil;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.MediaChunk;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSourceUtil;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.DefaultExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.Id3Decoder;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.PrivFrame;
import com.appsamurai.storyly.exoplayer2.hls.HlsChunkSource;
import com.appsamurai.storyly.exoplayer2.hls.playlist.HlsMediaPlaylist;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import io.zksync.wrappers.ZkSyncContract;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

final class HlsMediaChunk extends MediaChunk {
    public static final String PRIV_TIMESTAMP_FRAME_OWNER = "com.apple.streaming.transportStreamTimestamp";
    private static final AtomicInteger uidSource = new AtomicInteger();
    public final int discontinuitySequenceNumber;
    @Nullable
    private final DrmInitData drmInitData;
    private HlsMediaChunkExtractor extractor;
    private final HlsExtractorFactory extractorFactory;
    private boolean extractorInvalidated;
    private final boolean hasGapTag;
    private final Id3Decoder id3Decoder;
    private boolean initDataLoadRequired;
    @Nullable
    private final DataSource initDataSource;
    @Nullable
    private final DataSpec initDataSpec;
    private final boolean initSegmentEncrypted;
    private final boolean isMasterTimestampSource;
    private boolean isPublished;
    private volatile boolean loadCanceled;
    private boolean loadCompleted;
    private final boolean mediaSegmentEncrypted;
    @Nullable
    private final List<Format> muxedCaptionFormats;
    private int nextLoadPosition;
    private HlsSampleStreamWrapper output;
    public final int partIndex;
    private final PlayerId playerId;
    public final Uri playlistUrl;
    @Nullable
    private final HlsMediaChunkExtractor previousExtractor;
    private ImmutableList<Integer> sampleQueueFirstSampleIndices;
    private final ParsableByteArray scratchId3Data;
    public final boolean shouldSpliceIn;
    private final TimestampAdjuster timestampAdjuster;
    public final int uid;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private HlsMediaChunk(HlsExtractorFactory hlsExtractorFactory, DataSource dataSource, DataSpec dataSpec, Format format, boolean z2, @Nullable DataSource dataSource2, @Nullable DataSpec dataSpec2, boolean z3, Uri uri, @Nullable List<Format> list, int i3, @Nullable Object obj, long j2, long j3, long j4, int i4, boolean z4, int i5, boolean z5, boolean z6, TimestampAdjuster timestampAdjuster2, @Nullable DrmInitData drmInitData2, @Nullable HlsMediaChunkExtractor hlsMediaChunkExtractor, Id3Decoder id3Decoder2, ParsableByteArray parsableByteArray, boolean z7, PlayerId playerId2) {
        super(dataSource, dataSpec, format, i3, obj, j2, j3, j4);
        DataSpec dataSpec3 = dataSpec2;
        this.mediaSegmentEncrypted = z2;
        this.partIndex = i4;
        this.isPublished = z4;
        this.discontinuitySequenceNumber = i5;
        this.initDataSpec = dataSpec3;
        this.initDataSource = dataSource2;
        this.initDataLoadRequired = dataSpec3 != null;
        this.initSegmentEncrypted = z3;
        this.playlistUrl = uri;
        this.isMasterTimestampSource = z6;
        this.timestampAdjuster = timestampAdjuster2;
        this.hasGapTag = z5;
        this.extractorFactory = hlsExtractorFactory;
        this.muxedCaptionFormats = list;
        this.drmInitData = drmInitData2;
        this.previousExtractor = hlsMediaChunkExtractor;
        this.id3Decoder = id3Decoder2;
        this.scratchId3Data = parsableByteArray;
        this.shouldSpliceIn = z7;
        this.playerId = playerId2;
        this.sampleQueueFirstSampleIndices = ImmutableList.of();
        this.uid = uidSource.getAndIncrement();
    }

    private static DataSource buildDataSource(DataSource dataSource, @Nullable byte[] bArr, @Nullable byte[] bArr2) {
        if (bArr == null) {
            return dataSource;
        }
        Assertions.checkNotNull(bArr2);
        return new Aes128DataSource(dataSource, bArr, bArr2);
    }

    public static HlsMediaChunk createInstance(HlsExtractorFactory hlsExtractorFactory, DataSource dataSource, Format format, long j2, HlsMediaPlaylist hlsMediaPlaylist, HlsChunkSource.SegmentBaseHolder segmentBaseHolder, Uri uri, @Nullable List<Format> list, int i3, @Nullable Object obj, boolean z2, TimestampAdjusterProvider timestampAdjusterProvider, @Nullable HlsMediaChunk hlsMediaChunk, @Nullable byte[] bArr, @Nullable byte[] bArr2, boolean z3, PlayerId playerId2) {
        boolean z4;
        boolean z5;
        DataSpec dataSpec;
        DataSource dataSource2;
        ParsableByteArray parsableByteArray;
        Id3Decoder id3Decoder2;
        HlsMediaChunkExtractor hlsMediaChunkExtractor;
        DataSource dataSource3 = dataSource;
        HlsMediaPlaylist hlsMediaPlaylist2 = hlsMediaPlaylist;
        HlsChunkSource.SegmentBaseHolder segmentBaseHolder2 = segmentBaseHolder;
        HlsMediaChunk hlsMediaChunk2 = hlsMediaChunk;
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        HlsMediaPlaylist.SegmentBase segmentBase = segmentBaseHolder2.segmentBase;
        DataSpec build = new DataSpec.Builder().setUri(UriUtil.resolveToUri(hlsMediaPlaylist2.baseUri, segmentBase.url)).setPosition(segmentBase.byteRangeOffset).setLength(segmentBase.byteRangeLength).setFlags(segmentBaseHolder2.isPreload ? 8 : 0).build();
        boolean z6 = bArr3 != null;
        DataSource buildDataSource = buildDataSource(dataSource3, bArr3, z6 ? getEncryptionIvArray((String) Assertions.checkNotNull(segmentBase.encryptionIV)) : null);
        HlsMediaPlaylist.Segment segment = segmentBase.initializationSegment;
        if (segment != null) {
            boolean z7 = bArr4 != null;
            byte[] encryptionIvArray = z7 ? getEncryptionIvArray((String) Assertions.checkNotNull(segment.encryptionIV)) : null;
            z4 = z6;
            dataSpec = new DataSpec(UriUtil.resolveToUri(hlsMediaPlaylist2.baseUri, segment.url), segment.byteRangeOffset, segment.byteRangeLength);
            dataSource2 = buildDataSource(dataSource3, bArr4, encryptionIvArray);
            z5 = z7;
        } else {
            z4 = z6;
            dataSource2 = null;
            dataSpec = null;
            z5 = false;
        }
        long j3 = j2 + segmentBase.relativeStartTimeUs;
        long j4 = j3 + segmentBase.durationUs;
        int i4 = hlsMediaPlaylist2.discontinuitySequence + segmentBase.relativeDiscontinuitySequence;
        if (hlsMediaChunk2 != null) {
            DataSpec dataSpec2 = hlsMediaChunk2.initDataSpec;
            boolean z8 = dataSpec == dataSpec2 || (dataSpec != null && dataSpec2 != null && dataSpec.uri.equals(dataSpec2.uri) && dataSpec.position == hlsMediaChunk2.initDataSpec.position);
            boolean z9 = uri.equals(hlsMediaChunk2.playlistUrl) && hlsMediaChunk2.loadCompleted;
            Id3Decoder id3Decoder3 = hlsMediaChunk2.id3Decoder;
            id3Decoder2 = id3Decoder3;
            parsableByteArray = hlsMediaChunk2.scratchId3Data;
            hlsMediaChunkExtractor = (!z8 || !z9 || hlsMediaChunk2.extractorInvalidated || hlsMediaChunk2.discontinuitySequenceNumber != i4) ? null : hlsMediaChunk2.extractor;
        } else {
            Uri uri2 = uri;
            id3Decoder2 = new Id3Decoder();
            parsableByteArray = new ParsableByteArray(10);
            hlsMediaChunkExtractor = null;
        }
        return new HlsMediaChunk(hlsExtractorFactory, buildDataSource, build, format, z4, dataSource2, dataSpec, z5, uri, list, i3, obj, j3, j4, segmentBaseHolder2.mediaSequence, segmentBaseHolder2.partIndex, !segmentBaseHolder2.isPreload, i4, segmentBase.hasGapTag, z2, timestampAdjusterProvider.getAdjuster(i4), segmentBase.drmInitData, hlsMediaChunkExtractor, id3Decoder2, parsableByteArray, z3, playerId2);
    }

    @RequiresNonNull({"output"})
    private void feedDataToExtractor(DataSource dataSource, DataSpec dataSpec, boolean z2, boolean z3) throws IOException {
        DataSpec dataSpec2;
        DefaultExtractorInput prepareExtraction;
        long j2;
        long j3;
        boolean z4 = false;
        if (z2) {
            if (this.nextLoadPosition != 0) {
                z4 = true;
            }
            dataSpec2 = dataSpec;
        } else {
            dataSpec2 = dataSpec.subrange((long) this.nextLoadPosition);
        }
        try {
            prepareExtraction = prepareExtraction(dataSource, dataSpec2, z3);
            if (z4) {
                prepareExtraction.skipFully(this.nextLoadPosition);
            }
            do {
                if (this.loadCanceled || !this.extractor.read(prepareExtraction)) {
                    break;
                }
                break;
                break;
            } while (!this.extractor.read(prepareExtraction));
            break;
            j2 = prepareExtraction.getPosition();
            j3 = dataSpec.position;
        } catch (EOFException e3) {
            if ((this.trackFormat.roleFlags & 16384) != 0) {
                this.extractor.onTruncatedSegmentParsed();
                j2 = prepareExtraction.getPosition();
                j3 = dataSpec.position;
            } else {
                throw e3;
            }
        } catch (Throwable th) {
            DataSourceUtil.closeQuietly(dataSource);
            throw th;
        }
        this.nextLoadPosition = (int) (j2 - j3);
        DataSourceUtil.closeQuietly(dataSource);
    }

    private static byte[] getEncryptionIvArray(String str) {
        if (Ascii.toLowerCase(str).startsWith(ZkSyncContract.BINARY)) {
            str = str.substring(2);
        }
        byte[] byteArray = new BigInteger(str, 16).toByteArray();
        byte[] bArr = new byte[16];
        int length = byteArray.length > 16 ? byteArray.length - 16 : 0;
        System.arraycopy(byteArray, length, bArr, (16 - byteArray.length) + length, byteArray.length - length);
        return bArr;
    }

    private static boolean isIndependent(HlsChunkSource.SegmentBaseHolder segmentBaseHolder, HlsMediaPlaylist hlsMediaPlaylist) {
        HlsMediaPlaylist.SegmentBase segmentBase = segmentBaseHolder.segmentBase;
        return segmentBase instanceof HlsMediaPlaylist.Part ? ((HlsMediaPlaylist.Part) segmentBase).isIndependent || (segmentBaseHolder.partIndex == 0 && hlsMediaPlaylist.hasIndependentSegments) : hlsMediaPlaylist.hasIndependentSegments;
    }

    @RequiresNonNull({"output"})
    private void loadMedia() throws IOException {
        feedDataToExtractor(this.dataSource, this.dataSpec, this.mediaSegmentEncrypted, true);
    }

    @RequiresNonNull({"output"})
    private void maybeLoadInitData() throws IOException {
        if (this.initDataLoadRequired) {
            Assertions.checkNotNull(this.initDataSource);
            Assertions.checkNotNull(this.initDataSpec);
            feedDataToExtractor(this.initDataSource, this.initDataSpec, this.initSegmentEncrypted, false);
            this.nextLoadPosition = 0;
            this.initDataLoadRequired = false;
        }
    }

    private long peekId3PrivTimestamp(ExtractorInput extractorInput) throws IOException {
        extractorInput.resetPeekPosition();
        try {
            this.scratchId3Data.reset(10);
            extractorInput.peekFully(this.scratchId3Data.getData(), 0, 10);
            if (this.scratchId3Data.readUnsignedInt24() != 4801587) {
                return C.TIME_UNSET;
            }
            this.scratchId3Data.skipBytes(3);
            int readSynchSafeInt = this.scratchId3Data.readSynchSafeInt();
            int i3 = readSynchSafeInt + 10;
            if (i3 > this.scratchId3Data.capacity()) {
                byte[] data = this.scratchId3Data.getData();
                this.scratchId3Data.reset(i3);
                System.arraycopy(data, 0, this.scratchId3Data.getData(), 0, 10);
            }
            extractorInput.peekFully(this.scratchId3Data.getData(), 10, readSynchSafeInt);
            Metadata decode = this.id3Decoder.decode(this.scratchId3Data.getData(), readSynchSafeInt);
            if (decode == null) {
                return C.TIME_UNSET;
            }
            int length = decode.length();
            for (int i4 = 0; i4 < length; i4++) {
                Metadata.Entry entry = decode.get(i4);
                if (entry instanceof PrivFrame) {
                    PrivFrame privFrame = (PrivFrame) entry;
                    if (PRIV_TIMESTAMP_FRAME_OWNER.equals(privFrame.owner)) {
                        System.arraycopy(privFrame.privateData, 0, this.scratchId3Data.getData(), 0, 8);
                        this.scratchId3Data.setPosition(0);
                        this.scratchId3Data.setLimit(8);
                        return this.scratchId3Data.readLong() & 8589934591L;
                    }
                }
            }
            return C.TIME_UNSET;
        } catch (EOFException unused) {
        }
    }

    @RequiresNonNull({"output"})
    @EnsuresNonNull({"extractor"})
    private DefaultExtractorInput prepareExtraction(DataSource dataSource, DataSpec dataSpec, boolean z2) throws IOException {
        long open = dataSource.open(dataSpec);
        if (z2) {
            try {
                this.timestampAdjuster.sharedInitializeOrWait(this.isMasterTimestampSource, this.startTimeUs);
            } catch (InterruptedException unused) {
                throw new InterruptedIOException();
            }
        }
        DefaultExtractorInput defaultExtractorInput = new DefaultExtractorInput(dataSource, dataSpec.position, open);
        if (this.extractor == null) {
            long peekId3PrivTimestamp = peekId3PrivTimestamp(defaultExtractorInput);
            defaultExtractorInput.resetPeekPosition();
            HlsMediaChunkExtractor hlsMediaChunkExtractor = this.previousExtractor;
            HlsMediaChunkExtractor recreate = hlsMediaChunkExtractor != null ? hlsMediaChunkExtractor.recreate() : this.extractorFactory.createExtractor(dataSpec.uri, this.trackFormat, this.muxedCaptionFormats, this.timestampAdjuster, dataSource.getResponseHeaders(), defaultExtractorInput, this.playerId);
            this.extractor = recreate;
            if (recreate.isPackedAudioExtractor()) {
                this.output.setSampleOffsetUs(peekId3PrivTimestamp != C.TIME_UNSET ? this.timestampAdjuster.adjustTsTimestamp(peekId3PrivTimestamp) : this.startTimeUs);
            } else {
                this.output.setSampleOffsetUs(0);
            }
            this.output.onNewExtractor();
            this.extractor.init(this.output);
        }
        this.output.setDrmInitData(this.drmInitData);
        return defaultExtractorInput;
    }

    public static boolean shouldSpliceIn(@Nullable HlsMediaChunk hlsMediaChunk, Uri uri, HlsMediaPlaylist hlsMediaPlaylist, HlsChunkSource.SegmentBaseHolder segmentBaseHolder, long j2) {
        if (hlsMediaChunk == null) {
            return false;
        }
        if (uri.equals(hlsMediaChunk.playlistUrl) && hlsMediaChunk.loadCompleted) {
            return false;
        }
        return !isIndependent(segmentBaseHolder, hlsMediaPlaylist) || j2 + segmentBaseHolder.segmentBase.relativeStartTimeUs < hlsMediaChunk.endTimeUs;
    }

    public void cancelLoad() {
        this.loadCanceled = true;
    }

    public int getFirstSampleIndex(int i3) {
        Assertions.checkState(!this.shouldSpliceIn);
        if (i3 >= this.sampleQueueFirstSampleIndices.size()) {
            return 0;
        }
        return this.sampleQueueFirstSampleIndices.get(i3).intValue();
    }

    public void init(HlsSampleStreamWrapper hlsSampleStreamWrapper, ImmutableList<Integer> immutableList) {
        this.output = hlsSampleStreamWrapper;
        this.sampleQueueFirstSampleIndices = immutableList;
    }

    public void invalidateExtractor() {
        this.extractorInvalidated = true;
    }

    public boolean isLoadCompleted() {
        return this.loadCompleted;
    }

    public boolean isPublished() {
        return this.isPublished;
    }

    public void load() throws IOException {
        HlsMediaChunkExtractor hlsMediaChunkExtractor;
        Assertions.checkNotNull(this.output);
        if (this.extractor == null && (hlsMediaChunkExtractor = this.previousExtractor) != null && hlsMediaChunkExtractor.isReusable()) {
            this.extractor = this.previousExtractor;
            this.initDataLoadRequired = false;
        }
        maybeLoadInitData();
        if (!this.loadCanceled) {
            if (!this.hasGapTag) {
                loadMedia();
            }
            this.loadCompleted = !this.loadCanceled;
        }
    }

    public void publish() {
        this.isPublished = true;
    }
}
