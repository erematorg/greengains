package com.appsamurai.storyly.exoplayer2.core.audio;

import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.audio.AudioProcessor;
import java.nio.ByteBuffer;

final class TrimmingAudioProcessor extends BaseAudioProcessor {
    private static final int OUTPUT_ENCODING = 2;
    private byte[] endBuffer = Util.EMPTY_BYTE_ARRAY;
    private int endBufferSize;
    private int pendingTrimStartBytes;
    private boolean reconfigurationPending;
    private int trimEndFrames;
    private int trimStartFrames;
    private long trimmedFrameCount;

    public ByteBuffer getOutput() {
        int i3;
        if (super.isEnded() && (i3 = this.endBufferSize) > 0) {
            replaceOutputBuffer(i3).put(this.endBuffer, 0, this.endBufferSize).flip();
            this.endBufferSize = 0;
        }
        return super.getOutput();
    }

    public long getTrimmedFrameCount() {
        return this.trimmedFrameCount;
    }

    public boolean isEnded() {
        return super.isEnded() && this.endBufferSize == 0;
    }

    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        if (audioFormat.encoding == 2) {
            this.reconfigurationPending = true;
            return (this.trimStartFrames == 0 && this.trimEndFrames == 0) ? AudioProcessor.AudioFormat.NOT_SET : audioFormat;
        }
        throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
    }

    public void onFlush() {
        if (this.reconfigurationPending) {
            this.reconfigurationPending = false;
            int i3 = this.trimEndFrames;
            int i4 = this.inputAudioFormat.bytesPerFrame;
            this.endBuffer = new byte[(i3 * i4)];
            this.pendingTrimStartBytes = this.trimStartFrames * i4;
        }
        this.endBufferSize = 0;
    }

    public void onQueueEndOfStream() {
        if (this.reconfigurationPending) {
            int i3 = this.endBufferSize;
            if (i3 > 0) {
                this.trimmedFrameCount += (long) (i3 / this.inputAudioFormat.bytesPerFrame);
            }
            this.endBufferSize = 0;
        }
    }

    public void onReset() {
        this.endBuffer = Util.EMPTY_BYTE_ARRAY;
    }

    public void queueInput(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int i3 = limit - position;
        if (i3 != 0) {
            int min = Math.min(i3, this.pendingTrimStartBytes);
            this.trimmedFrameCount += (long) (min / this.inputAudioFormat.bytesPerFrame);
            this.pendingTrimStartBytes -= min;
            byteBuffer.position(position + min);
            if (this.pendingTrimStartBytes <= 0) {
                int i4 = i3 - min;
                int length = (this.endBufferSize + i4) - this.endBuffer.length;
                ByteBuffer replaceOutputBuffer = replaceOutputBuffer(length);
                int constrainValue = Util.constrainValue(length, 0, this.endBufferSize);
                replaceOutputBuffer.put(this.endBuffer, 0, constrainValue);
                int constrainValue2 = Util.constrainValue(length - constrainValue, 0, i4);
                byteBuffer.limit(byteBuffer.position() + constrainValue2);
                replaceOutputBuffer.put(byteBuffer);
                byteBuffer.limit(limit);
                int i5 = i4 - constrainValue2;
                int i6 = this.endBufferSize - constrainValue;
                this.endBufferSize = i6;
                byte[] bArr = this.endBuffer;
                System.arraycopy(bArr, constrainValue, bArr, 0, i6);
                byteBuffer.get(this.endBuffer, this.endBufferSize, i5);
                this.endBufferSize += i5;
                replaceOutputBuffer.flip();
            }
        }
    }

    public void resetTrimmedFrameCount() {
        this.trimmedFrameCount = 0;
    }

    public void setTrimFrameCount(int i3, int i4) {
        this.trimStartFrames = i3;
        this.trimEndFrames = i4;
    }
}
