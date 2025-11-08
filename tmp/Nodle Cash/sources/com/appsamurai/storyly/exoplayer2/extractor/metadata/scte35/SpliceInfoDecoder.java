package com.appsamurai.storyly.exoplayer2.extractor.metadata.scte35;

import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.TimestampAdjuster;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.MetadataInputBuffer;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.SimpleMetadataDecoder;
import java.nio.ByteBuffer;

public final class SpliceInfoDecoder extends SimpleMetadataDecoder {
    private static final int TYPE_PRIVATE_COMMAND = 255;
    private static final int TYPE_SPLICE_INSERT = 5;
    private static final int TYPE_SPLICE_NULL = 0;
    private static final int TYPE_SPLICE_SCHEDULE = 4;
    private static final int TYPE_TIME_SIGNAL = 6;
    private final ParsableByteArray sectionData = new ParsableByteArray();
    private final ParsableBitArray sectionHeader = new ParsableBitArray();
    private TimestampAdjuster timestampAdjuster;

    public Metadata decode(MetadataInputBuffer metadataInputBuffer, ByteBuffer byteBuffer) {
        TimestampAdjuster timestampAdjuster2 = this.timestampAdjuster;
        if (timestampAdjuster2 == null || metadataInputBuffer.subsampleOffsetUs != timestampAdjuster2.getTimestampOffsetUs()) {
            TimestampAdjuster timestampAdjuster3 = new TimestampAdjuster(metadataInputBuffer.timeUs);
            this.timestampAdjuster = timestampAdjuster3;
            timestampAdjuster3.adjustSampleTimestamp(metadataInputBuffer.timeUs - metadataInputBuffer.subsampleOffsetUs);
        }
        byte[] array = byteBuffer.array();
        int limit = byteBuffer.limit();
        this.sectionData.reset(array, limit);
        this.sectionHeader.reset(array, limit);
        this.sectionHeader.skipBits(39);
        long readBits = (((long) this.sectionHeader.readBits(1)) << 32) | ((long) this.sectionHeader.readBits(32));
        this.sectionHeader.skipBits(20);
        int readBits2 = this.sectionHeader.readBits(12);
        int readBits3 = this.sectionHeader.readBits(8);
        this.sectionData.skipBytes(14);
        Metadata.Entry parseFromSection = readBits3 != 0 ? readBits3 != 255 ? readBits3 != 4 ? readBits3 != 5 ? readBits3 != 6 ? null : TimeSignalCommand.parseFromSection(this.sectionData, readBits, this.timestampAdjuster) : SpliceInsertCommand.parseFromSection(this.sectionData, readBits, this.timestampAdjuster) : SpliceScheduleCommand.parseFromSection(this.sectionData) : PrivateCommand.parseFromSection(this.sectionData, readBits2, readBits) : new SpliceNullCommand();
        if (parseFromSection == null) {
            return new Metadata(new Metadata.Entry[0]);
        }
        return new Metadata(parseFromSection);
    }
}
