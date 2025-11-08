package com.appsamurai.storyly.exoplayer2.extractor.extractor.ogg;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.FlacFrameReader;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.FlacMetadataReader;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.FlacSeekTableSeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.FlacStreamMetadata;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ogg.StreamReader;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

final class FlacReader extends StreamReader {
    private static final byte AUDIO_PACKET_TYPE = -1;
    private static final int FRAME_HEADER_SAMPLE_NUMBER_OFFSET = 4;
    @Nullable
    private FlacOggSeeker flacOggSeeker;
    @Nullable
    private FlacStreamMetadata streamMetadata;

    public static final class FlacOggSeeker implements OggSeeker {
        private long firstFrameOffset = -1;
        private long pendingSeekGranule = -1;
        private FlacStreamMetadata.SeekTable seekTable;
        private FlacStreamMetadata streamMetadata;

        public FlacOggSeeker(FlacStreamMetadata flacStreamMetadata, FlacStreamMetadata.SeekTable seekTable2) {
            this.streamMetadata = flacStreamMetadata;
            this.seekTable = seekTable2;
        }

        public SeekMap createSeekMap() {
            Assertions.checkState(this.firstFrameOffset != -1);
            return new FlacSeekTableSeekMap(this.streamMetadata, this.firstFrameOffset);
        }

        public long read(ExtractorInput extractorInput) {
            long j2 = this.pendingSeekGranule;
            if (j2 < 0) {
                return -1;
            }
            long j3 = -(j2 + 2);
            this.pendingSeekGranule = -1;
            return j3;
        }

        public void setFirstFrameOffset(long j2) {
            this.firstFrameOffset = j2;
        }

        public void startSeek(long j2) {
            long[] jArr = this.seekTable.pointSampleNumbers;
            this.pendingSeekGranule = jArr[Util.binarySearchFloor(jArr, j2, true, true)];
        }
    }

    private int getFlacFrameBlockSize(ParsableByteArray parsableByteArray) {
        int i3 = (parsableByteArray.getData()[2] & 255) >> 4;
        if (i3 == 6 || i3 == 7) {
            parsableByteArray.skipBytes(4);
            parsableByteArray.readUtf8EncodedLong();
        }
        int readFrameBlockSizeSamplesFromKey = FlacFrameReader.readFrameBlockSizeSamplesFromKey(parsableByteArray, i3);
        parsableByteArray.setPosition(0);
        return readFrameBlockSizeSamplesFromKey;
    }

    private static boolean isAudioPacket(byte[] bArr) {
        return bArr[0] == -1;
    }

    public static boolean verifyBitstreamType(ParsableByteArray parsableByteArray) {
        return parsableByteArray.bytesLeft() >= 5 && parsableByteArray.readUnsignedByte() == 127 && parsableByteArray.readUnsignedInt() == 1179402563;
    }

    public long preparePayload(ParsableByteArray parsableByteArray) {
        if (!isAudioPacket(parsableByteArray.getData())) {
            return -1;
        }
        return (long) getFlacFrameBlockSize(parsableByteArray);
    }

    @EnsuresNonNullIf(expression = {"#3.format"}, result = false)
    public boolean readHeaders(ParsableByteArray parsableByteArray, long j2, StreamReader.SetupData setupData) {
        byte[] data = parsableByteArray.getData();
        FlacStreamMetadata flacStreamMetadata = this.streamMetadata;
        if (flacStreamMetadata == null) {
            FlacStreamMetadata flacStreamMetadata2 = new FlacStreamMetadata(data, 17);
            this.streamMetadata = flacStreamMetadata2;
            setupData.format = flacStreamMetadata2.getFormat(Arrays.copyOfRange(data, 9, parsableByteArray.limit()), (Metadata) null);
            return true;
        } else if ((data[0] & Byte.MAX_VALUE) == 3) {
            FlacStreamMetadata.SeekTable readSeekTableMetadataBlock = FlacMetadataReader.readSeekTableMetadataBlock(parsableByteArray);
            FlacStreamMetadata copyWithSeekTable = flacStreamMetadata.copyWithSeekTable(readSeekTableMetadataBlock);
            this.streamMetadata = copyWithSeekTable;
            this.flacOggSeeker = new FlacOggSeeker(copyWithSeekTable, readSeekTableMetadataBlock);
            return true;
        } else if (!isAudioPacket(data)) {
            return true;
        } else {
            FlacOggSeeker flacOggSeeker2 = this.flacOggSeeker;
            if (flacOggSeeker2 != null) {
                flacOggSeeker2.setFirstFrameOffset(j2);
                setupData.oggSeeker = this.flacOggSeeker;
            }
            Assertions.checkNotNull(setupData.format);
            return false;
        }
    }

    public void reset(boolean z2) {
        super.reset(z2);
        if (z2) {
            this.streamMetadata = null;
            this.flacOggSeeker = null;
        }
    }
}
