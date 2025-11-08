package com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import java.io.IOException;

final class TrackFragment {
    public long atomPosition;
    public long auxiliaryDataPosition;
    public long dataPosition;
    public boolean definesEncryptionData;
    public DefaultSampleValues header;
    public long nextFragmentDecodeTime;
    public boolean nextFragmentDecodeTimeIncludesMoov;
    public int sampleCount;
    public final ParsableByteArray sampleEncryptionData = new ParsableByteArray();
    public boolean sampleEncryptionDataNeedsFill;
    public boolean[] sampleHasSubsampleEncryptionTable = new boolean[0];
    public boolean[] sampleIsSyncFrameTable = new boolean[0];
    public long[] samplePresentationTimesUs = new long[0];
    public int[] sampleSizeTable = new int[0];
    @Nullable
    public TrackEncryptionBox trackEncryptionBox;
    public int trunCount;
    public long[] trunDataPosition = new long[0];
    public int[] trunLength = new int[0];

    public void fillEncryptionData(ExtractorInput extractorInput) throws IOException {
        extractorInput.readFully(this.sampleEncryptionData.getData(), 0, this.sampleEncryptionData.limit());
        this.sampleEncryptionData.setPosition(0);
        this.sampleEncryptionDataNeedsFill = false;
    }

    public long getSamplePresentationTimeUs(int i3) {
        return this.samplePresentationTimesUs[i3];
    }

    public void initEncryptionData(int i3) {
        this.sampleEncryptionData.reset(i3);
        this.definesEncryptionData = true;
        this.sampleEncryptionDataNeedsFill = true;
    }

    public void initTables(int i3, int i4) {
        this.trunCount = i3;
        this.sampleCount = i4;
        if (this.trunLength.length < i3) {
            this.trunDataPosition = new long[i3];
            this.trunLength = new int[i3];
        }
        if (this.sampleSizeTable.length < i4) {
            int i5 = (i4 * 125) / 100;
            this.sampleSizeTable = new int[i5];
            this.samplePresentationTimesUs = new long[i5];
            this.sampleIsSyncFrameTable = new boolean[i5];
            this.sampleHasSubsampleEncryptionTable = new boolean[i5];
        }
    }

    public void reset() {
        this.trunCount = 0;
        this.nextFragmentDecodeTime = 0;
        this.nextFragmentDecodeTimeIncludesMoov = false;
        this.definesEncryptionData = false;
        this.sampleEncryptionDataNeedsFill = false;
        this.trackEncryptionBox = null;
    }

    public boolean sampleHasSubsampleEncryptionTable(int i3) {
        return this.definesEncryptionData && this.sampleHasSubsampleEncryptionTable[i3];
    }

    public void fillEncryptionData(ParsableByteArray parsableByteArray) {
        parsableByteArray.readBytes(this.sampleEncryptionData.getData(), 0, this.sampleEncryptionData.limit());
        this.sampleEncryptionData.setPosition(0);
        this.sampleEncryptionDataNeedsFill = false;
    }
}
