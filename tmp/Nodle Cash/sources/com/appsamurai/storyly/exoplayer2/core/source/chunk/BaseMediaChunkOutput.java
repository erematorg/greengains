package com.appsamurai.storyly.exoplayer2.core.source.chunk;

import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.core.source.SampleQueue;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.ChunkExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.DummyTrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;

public final class BaseMediaChunkOutput implements ChunkExtractor.TrackOutputProvider {
    private static final String TAG = "BaseMediaChunkOutput";
    private final SampleQueue[] sampleQueues;
    private final int[] trackTypes;

    public BaseMediaChunkOutput(int[] iArr, SampleQueue[] sampleQueueArr) {
        this.trackTypes = iArr;
        this.sampleQueues = sampleQueueArr;
    }

    public int[] getWriteIndices() {
        int[] iArr = new int[this.sampleQueues.length];
        int i3 = 0;
        while (true) {
            SampleQueue[] sampleQueueArr = this.sampleQueues;
            if (i3 >= sampleQueueArr.length) {
                return iArr;
            }
            iArr[i3] = sampleQueueArr[i3].getWriteIndex();
            i3++;
        }
    }

    public void setSampleOffsetUs(long j2) {
        for (SampleQueue sampleOffsetUs : this.sampleQueues) {
            sampleOffsetUs.setSampleOffsetUs(j2);
        }
    }

    public TrackOutput track(int i3, int i4) {
        int i5 = 0;
        while (true) {
            int[] iArr = this.trackTypes;
            if (i5 >= iArr.length) {
                Log.e(TAG, "Unmatched track of type: " + i4);
                return new DummyTrackOutput();
            } else if (i4 == iArr[i5]) {
                return this.sampleQueues[i5];
            } else {
                i5++;
            }
        }
    }
}
