package com.appsamurai.storyly.exoplayer2.extractor.extractor.avi;

import com.appsamurai.storyly.exoplayer2.common.upstream.DataReader;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekPoint;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import java.io.IOException;
import java.util.Arrays;

final class ChunkReader {
    private static final int CHUNK_TYPE_AUDIO = 1651965952;
    private static final int CHUNK_TYPE_VIDEO_COMPRESSED = 1667497984;
    private static final int CHUNK_TYPE_VIDEO_UNCOMPRESSED = 1650720768;
    private static final int INITIAL_INDEX_SIZE = 512;
    private final int alternativeChunkId;
    private int bytesRemainingInCurrentChunk;
    private final int chunkId;
    private int currentChunkIndex;
    private int currentChunkSize;
    private final long durationUs;
    private int indexChunkCount;
    private int indexSize;
    private int[] keyFrameIndices;
    private long[] keyFrameOffsets;
    private final int streamHeaderChunkCount;
    protected final TrackOutput trackOutput;

    public ChunkReader(int i3, int i4, long j2, int i5, TrackOutput trackOutput2) {
        boolean z2 = true;
        if (!(i4 == 1 || i4 == 2)) {
            z2 = false;
        }
        Assertions.checkArgument(z2);
        this.durationUs = j2;
        this.streamHeaderChunkCount = i5;
        this.trackOutput = trackOutput2;
        this.chunkId = getChunkIdFourCc(i3, i4 == 2 ? CHUNK_TYPE_VIDEO_COMPRESSED : CHUNK_TYPE_AUDIO);
        this.alternativeChunkId = i4 == 2 ? getChunkIdFourCc(i3, CHUNK_TYPE_VIDEO_UNCOMPRESSED) : -1;
        this.keyFrameOffsets = new long[512];
        this.keyFrameIndices = new int[512];
    }

    private static int getChunkIdFourCc(int i3, int i4) {
        return (((i3 % 10) + 48) << 8) | ((i3 / 10) + 48) | i4;
    }

    private long getChunkTimestampUs(int i3) {
        return (this.durationUs * ((long) i3)) / ((long) this.streamHeaderChunkCount);
    }

    private SeekPoint getSeekPoint(int i3) {
        return new SeekPoint(((long) this.keyFrameIndices[i3]) * getFrameDurationUs(), this.keyFrameOffsets[i3]);
    }

    public void advanceCurrentChunk() {
        this.currentChunkIndex++;
    }

    public void appendKeyFrameToIndex(long j2) {
        if (this.indexSize == this.keyFrameIndices.length) {
            long[] jArr = this.keyFrameOffsets;
            this.keyFrameOffsets = Arrays.copyOf(jArr, (jArr.length * 3) / 2);
            int[] iArr = this.keyFrameIndices;
            this.keyFrameIndices = Arrays.copyOf(iArr, (iArr.length * 3) / 2);
        }
        long[] jArr2 = this.keyFrameOffsets;
        int i3 = this.indexSize;
        jArr2[i3] = j2;
        this.keyFrameIndices[i3] = this.indexChunkCount;
        this.indexSize = i3 + 1;
    }

    public void compactIndex() {
        this.keyFrameOffsets = Arrays.copyOf(this.keyFrameOffsets, this.indexSize);
        this.keyFrameIndices = Arrays.copyOf(this.keyFrameIndices, this.indexSize);
    }

    public long getCurrentChunkTimestampUs() {
        return getChunkTimestampUs(this.currentChunkIndex);
    }

    public long getFrameDurationUs() {
        return getChunkTimestampUs(1);
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        int frameDurationUs = (int) (j2 / getFrameDurationUs());
        int binarySearchFloor = Util.binarySearchFloor(this.keyFrameIndices, frameDurationUs, true, true);
        if (this.keyFrameIndices[binarySearchFloor] == frameDurationUs) {
            return new SeekMap.SeekPoints(getSeekPoint(binarySearchFloor));
        }
        SeekPoint seekPoint = getSeekPoint(binarySearchFloor);
        int i3 = binarySearchFloor + 1;
        return i3 < this.keyFrameOffsets.length ? new SeekMap.SeekPoints(seekPoint, getSeekPoint(i3)) : new SeekMap.SeekPoints(seekPoint);
    }

    public boolean handlesChunkId(int i3) {
        return this.chunkId == i3 || this.alternativeChunkId == i3;
    }

    public void incrementIndexChunkCount() {
        this.indexChunkCount++;
    }

    public boolean isAudio() {
        return (this.chunkId & CHUNK_TYPE_AUDIO) == CHUNK_TYPE_AUDIO;
    }

    public boolean isCurrentFrameAKeyFrame() {
        return Arrays.binarySearch(this.keyFrameIndices, this.currentChunkIndex) >= 0;
    }

    public boolean isVideo() {
        return (this.chunkId & CHUNK_TYPE_VIDEO_COMPRESSED) == CHUNK_TYPE_VIDEO_COMPRESSED;
    }

    public boolean onChunkData(ExtractorInput extractorInput) throws IOException {
        int i3 = this.bytesRemainingInCurrentChunk;
        boolean z2 = false;
        int sampleData = i3 - this.trackOutput.sampleData((DataReader) extractorInput, i3, false);
        this.bytesRemainingInCurrentChunk = sampleData;
        if (sampleData == 0) {
            z2 = true;
        }
        if (z2) {
            if (this.currentChunkSize > 0) {
                TrackOutput trackOutput2 = this.trackOutput;
                long currentChunkTimestampUs = getCurrentChunkTimestampUs();
                boolean isCurrentFrameAKeyFrame = isCurrentFrameAKeyFrame();
                trackOutput2.sampleMetadata(currentChunkTimestampUs, isCurrentFrameAKeyFrame ? 1 : 0, this.currentChunkSize, 0, (TrackOutput.CryptoData) null);
            }
            advanceCurrentChunk();
        }
        return z2;
    }

    public void onChunkStart(int i3) {
        this.currentChunkSize = i3;
        this.bytesRemainingInCurrentChunk = i3;
    }

    public void seekToPosition(long j2) {
        if (this.indexSize == 0) {
            this.currentChunkIndex = 0;
            return;
        }
        this.currentChunkIndex = this.keyFrameIndices[Util.binarySearchFloor(this.keyFrameOffsets, j2, true, true)];
    }
}
