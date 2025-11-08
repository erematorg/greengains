package com.appsamurai.storyly.exoplayer2.core.mediacodec;

import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer;
import com.appsamurai.storyly.exoplayer2.extractor.audio.MpegAudioUtil;
import java.nio.ByteBuffer;

final class C2Mp3TimestampTracker {
    private static final long DECODER_DELAY_FRAMES = 529;
    private static final String TAG = "C2Mp3TimestampTracker";
    private long anchorTimestampUs;
    private long processedFrames;
    private boolean seenInvalidMpegAudioHeader;

    private long getBufferTimestampUs(long j2) {
        return Math.max(0, ((this.processedFrames - DECODER_DELAY_FRAMES) * 1000000) / j2) + this.anchorTimestampUs;
    }

    public long getLastOutputBufferPresentationTimeUs(Format format) {
        return getBufferTimestampUs((long) format.sampleRate);
    }

    public void reset() {
        this.anchorTimestampUs = 0;
        this.processedFrames = 0;
        this.seenInvalidMpegAudioHeader = false;
    }

    public long updateAndGetPresentationTimeUs(Format format, DecoderInputBuffer decoderInputBuffer) {
        if (this.processedFrames == 0) {
            this.anchorTimestampUs = decoderInputBuffer.timeUs;
        }
        if (this.seenInvalidMpegAudioHeader) {
            return decoderInputBuffer.timeUs;
        }
        ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(decoderInputBuffer.data);
        byte b3 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            b3 = (b3 << 8) | (byteBuffer.get(i3) & 255);
        }
        int parseMpegAudioFrameSampleCount = MpegAudioUtil.parseMpegAudioFrameSampleCount(b3);
        if (parseMpegAudioFrameSampleCount == -1) {
            this.seenInvalidMpegAudioHeader = true;
            this.processedFrames = 0;
            this.anchorTimestampUs = decoderInputBuffer.timeUs;
            Log.w(TAG, "MPEG audio header is invalid.");
            return decoderInputBuffer.timeUs;
        }
        long bufferTimestampUs = getBufferTimestampUs((long) format.sampleRate);
        this.processedFrames += (long) parseMpegAudioFrameSampleCount;
        return bufferTimestampUs;
    }
}
