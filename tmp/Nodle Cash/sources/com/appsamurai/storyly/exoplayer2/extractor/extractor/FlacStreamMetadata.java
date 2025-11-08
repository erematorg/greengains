package com.appsamurai.storyly.exoplayer2.extractor.extractor;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.audio.AacUtil;
import com.appsamurai.storyly.exoplayer2.extractor.audio.DtsUtil;
import com.appsamurai.storyly.exoplayer2.extractor.audio.OpusUtil;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.flac.PictureFrame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class FlacStreamMetadata {
    public static final int NOT_IN_LOOKUP_TABLE = -1;
    private static final String TAG = "FlacStreamMetadata";
    public final int bitsPerSample;
    public final int bitsPerSampleLookupKey;
    public final int channels;
    public final int maxBlockSizeSamples;
    public final int maxFrameSize;
    @Nullable
    private final Metadata metadata;
    public final int minBlockSizeSamples;
    public final int minFrameSize;
    public final int sampleRate;
    public final int sampleRateLookupKey;
    @Nullable
    public final SeekTable seekTable;
    public final long totalSamples;

    public static class SeekTable {
        public final long[] pointOffsets;
        public final long[] pointSampleNumbers;

        public SeekTable(long[] jArr, long[] jArr2) {
            this.pointSampleNumbers = jArr;
            this.pointOffsets = jArr2;
        }
    }

    public FlacStreamMetadata(byte[] bArr, int i3) {
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr);
        parsableBitArray.setPosition(i3 * 8);
        this.minBlockSizeSamples = parsableBitArray.readBits(16);
        this.maxBlockSizeSamples = parsableBitArray.readBits(16);
        this.minFrameSize = parsableBitArray.readBits(24);
        this.maxFrameSize = parsableBitArray.readBits(24);
        int readBits = parsableBitArray.readBits(20);
        this.sampleRate = readBits;
        this.sampleRateLookupKey = getSampleRateLookupKey(readBits);
        this.channels = parsableBitArray.readBits(3) + 1;
        int readBits2 = parsableBitArray.readBits(5) + 1;
        this.bitsPerSample = readBits2;
        this.bitsPerSampleLookupKey = getBitsPerSampleLookupKey(readBits2);
        this.totalSamples = parsableBitArray.readBitsToLong(36);
        this.seekTable = null;
        this.metadata = null;
    }

    @Nullable
    private static Metadata concatenateVorbisMetadata(List<String> list, List<PictureFrame> list2) {
        Metadata parseVorbisComments = VorbisUtil.parseVorbisComments(list);
        if (parseVorbisComments != null || !list2.isEmpty()) {
            return new Metadata((List<? extends Metadata.Entry>) list2).copyWithAppendedEntriesFrom(parseVorbisComments);
        }
        return null;
    }

    private static int getBitsPerSampleLookupKey(int i3) {
        if (i3 == 8) {
            return 1;
        }
        if (i3 == 12) {
            return 2;
        }
        if (i3 == 16) {
            return 4;
        }
        if (i3 != 20) {
            return i3 != 24 ? -1 : 6;
        }
        return 5;
    }

    private static int getSampleRateLookupKey(int i3) {
        switch (i3) {
            case 8000:
                return 4;
            case AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND:
                return 5;
            case 22050:
                return 6;
            case 24000:
                return 7;
            case 32000:
                return 8;
            case 44100:
                return 9;
            case OpusUtil.SAMPLE_RATE:
                return 10;
            case 88200:
                return 1;
            case 96000:
                return 11;
            case 176400:
                return 2;
            case DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND:
                return 3;
            default:
                return -1;
        }
    }

    public FlacStreamMetadata copyWithPictureFrames(List<PictureFrame> list) {
        return new FlacStreamMetadata(this.minBlockSizeSamples, this.maxBlockSizeSamples, this.minFrameSize, this.maxFrameSize, this.sampleRate, this.channels, this.bitsPerSample, this.totalSamples, this.seekTable, getMetadataCopyWithAppendedEntriesFrom(new Metadata((List<? extends Metadata.Entry>) list)));
    }

    public FlacStreamMetadata copyWithSeekTable(@Nullable SeekTable seekTable2) {
        return new FlacStreamMetadata(this.minBlockSizeSamples, this.maxBlockSizeSamples, this.minFrameSize, this.maxFrameSize, this.sampleRate, this.channels, this.bitsPerSample, this.totalSamples, seekTable2, this.metadata);
    }

    public FlacStreamMetadata copyWithVorbisComments(List<String> list) {
        return new FlacStreamMetadata(this.minBlockSizeSamples, this.maxBlockSizeSamples, this.minFrameSize, this.maxFrameSize, this.sampleRate, this.channels, this.bitsPerSample, this.totalSamples, this.seekTable, getMetadataCopyWithAppendedEntriesFrom(VorbisUtil.parseVorbisComments(list)));
    }

    public long getApproxBytesPerFrame() {
        long j2;
        long j3;
        int i3 = this.maxFrameSize;
        if (i3 > 0) {
            j2 = (((long) i3) + ((long) this.minFrameSize)) / 2;
            j3 = 1;
        } else {
            int i4 = this.minBlockSizeSamples;
            j2 = ((((i4 != this.maxBlockSizeSamples || i4 <= 0) ? PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM : (long) i4) * ((long) this.channels)) * ((long) this.bitsPerSample)) / 8;
            j3 = 64;
        }
        return j2 + j3;
    }

    public int getDecodedBitrate() {
        return this.bitsPerSample * this.sampleRate * this.channels;
    }

    public long getDurationUs() {
        long j2 = this.totalSamples;
        return j2 == 0 ? C.TIME_UNSET : (j2 * 1000000) / ((long) this.sampleRate);
    }

    public Format getFormat(byte[] bArr, @Nullable Metadata metadata2) {
        bArr[4] = Byte.MIN_VALUE;
        int i3 = this.maxFrameSize;
        if (i3 <= 0) {
            i3 = -1;
        }
        return new Format.Builder().setSampleMimeType(MimeTypes.AUDIO_FLAC).setMaxInputSize(i3).setChannelCount(this.channels).setSampleRate(this.sampleRate).setInitializationData(Collections.singletonList(bArr)).setMetadata(getMetadataCopyWithAppendedEntriesFrom(metadata2)).build();
    }

    public int getMaxDecodedFrameSize() {
        return (this.bitsPerSample / 8) * this.maxBlockSizeSamples * this.channels;
    }

    @Nullable
    public Metadata getMetadataCopyWithAppendedEntriesFrom(@Nullable Metadata metadata2) {
        Metadata metadata3 = this.metadata;
        return metadata3 == null ? metadata2 : metadata3.copyWithAppendedEntriesFrom(metadata2);
    }

    public long getSampleNumber(long j2) {
        return Util.constrainValue((j2 * ((long) this.sampleRate)) / 1000000, 0, this.totalSamples - 1);
    }

    public FlacStreamMetadata(int i3, int i4, int i5, int i6, int i7, int i8, int i9, long j2, ArrayList<String> arrayList, ArrayList<PictureFrame> arrayList2) {
        this(i3, i4, i5, i6, i7, i8, i9, j2, (SeekTable) null, concatenateVorbisMetadata(arrayList, arrayList2));
    }

    private FlacStreamMetadata(int i3, int i4, int i5, int i6, int i7, int i8, int i9, long j2, @Nullable SeekTable seekTable2, @Nullable Metadata metadata2) {
        this.minBlockSizeSamples = i3;
        this.maxBlockSizeSamples = i4;
        this.minFrameSize = i5;
        this.maxFrameSize = i6;
        this.sampleRate = i7;
        this.sampleRateLookupKey = getSampleRateLookupKey(i7);
        this.channels = i8;
        this.bitsPerSample = i9;
        this.bitsPerSampleLookupKey = getBitsPerSampleLookupKey(i9);
        this.totalSamples = j2;
        this.seekTable = seekTable2;
        this.metadata = metadata2;
    }
}
