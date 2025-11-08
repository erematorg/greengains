package com.appsamurai.storyly.exoplayer2.core.audio;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.audio.AudioProcessor;
import com.appsamurai.storyly.exoplayer2.extractor.audio.WavUtil;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class TeeAudioProcessor extends BaseAudioProcessor {
    private final AudioBufferSink audioBufferSink;

    public interface AudioBufferSink {
        void flush(int i3, int i4, int i5);

        void handleBuffer(ByteBuffer byteBuffer);
    }

    public static final class WavFileAudioBufferSink implements AudioBufferSink {
        private static final int FILE_SIZE_MINUS_44_OFFSET = 40;
        private static final int FILE_SIZE_MINUS_8_OFFSET = 4;
        private static final int HEADER_LENGTH = 44;
        private static final String TAG = "WaveFileAudioBufferSink";
        private int bytesWritten;
        private int channelCount;
        private int counter;
        private int encoding;
        private final String outputFileNamePrefix;
        @Nullable
        private RandomAccessFile randomAccessFile;
        private int sampleRateHz;
        private final byte[] scratchBuffer;
        private final ByteBuffer scratchByteBuffer;

        public WavFileAudioBufferSink(String str) {
            this.outputFileNamePrefix = str;
            byte[] bArr = new byte[1024];
            this.scratchBuffer = bArr;
            this.scratchByteBuffer = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        }

        private String getNextOutputFileName() {
            String str = this.outputFileNamePrefix;
            int i3 = this.counter;
            this.counter = i3 + 1;
            return Util.formatInvariant("%s-%04d.wav", str, Integer.valueOf(i3));
        }

        private void maybePrepareFile() throws IOException {
            if (this.randomAccessFile == null) {
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(getNextOutputFileName(), "rw");
                writeFileHeader(randomAccessFile2);
                this.randomAccessFile = randomAccessFile2;
                this.bytesWritten = 44;
            }
        }

        private void reset() throws IOException {
            RandomAccessFile randomAccessFile2 = this.randomAccessFile;
            if (randomAccessFile2 != null) {
                try {
                    this.scratchByteBuffer.clear();
                    this.scratchByteBuffer.putInt(this.bytesWritten - 8);
                    randomAccessFile2.seek(4);
                    randomAccessFile2.write(this.scratchBuffer, 0, 4);
                    this.scratchByteBuffer.clear();
                    this.scratchByteBuffer.putInt(this.bytesWritten - 44);
                    randomAccessFile2.seek(40);
                    randomAccessFile2.write(this.scratchBuffer, 0, 4);
                } catch (IOException e3) {
                    Log.w(TAG, "Error updating file size", e3);
                }
                try {
                    randomAccessFile2.close();
                } finally {
                    this.randomAccessFile = null;
                }
            }
        }

        private void writeBuffer(ByteBuffer byteBuffer) throws IOException {
            RandomAccessFile randomAccessFile2 = (RandomAccessFile) Assertions.checkNotNull(this.randomAccessFile);
            while (byteBuffer.hasRemaining()) {
                int min = Math.min(byteBuffer.remaining(), this.scratchBuffer.length);
                byteBuffer.get(this.scratchBuffer, 0, min);
                randomAccessFile2.write(this.scratchBuffer, 0, min);
                this.bytesWritten += min;
            }
        }

        private void writeFileHeader(RandomAccessFile randomAccessFile2) throws IOException {
            randomAccessFile2.writeInt(WavUtil.RIFF_FOURCC);
            randomAccessFile2.writeInt(-1);
            randomAccessFile2.writeInt(WavUtil.WAVE_FOURCC);
            randomAccessFile2.writeInt(WavUtil.FMT_FOURCC);
            this.scratchByteBuffer.clear();
            this.scratchByteBuffer.putInt(16);
            this.scratchByteBuffer.putShort((short) WavUtil.getTypeForPcmEncoding(this.encoding));
            this.scratchByteBuffer.putShort((short) this.channelCount);
            this.scratchByteBuffer.putInt(this.sampleRateHz);
            int pcmFrameSize = Util.getPcmFrameSize(this.encoding, this.channelCount);
            this.scratchByteBuffer.putInt(this.sampleRateHz * pcmFrameSize);
            this.scratchByteBuffer.putShort((short) pcmFrameSize);
            this.scratchByteBuffer.putShort((short) ((pcmFrameSize * 8) / this.channelCount));
            randomAccessFile2.write(this.scratchBuffer, 0, this.scratchByteBuffer.position());
            randomAccessFile2.writeInt(1684108385);
            randomAccessFile2.writeInt(-1);
        }

        public void flush(int i3, int i4, int i5) {
            try {
                reset();
            } catch (IOException e3) {
                Log.e(TAG, "Error resetting", e3);
            }
            this.sampleRateHz = i3;
            this.channelCount = i4;
            this.encoding = i5;
        }

        public void handleBuffer(ByteBuffer byteBuffer) {
            try {
                maybePrepareFile();
                writeBuffer(byteBuffer);
            } catch (IOException e3) {
                Log.e(TAG, "Error writing data", e3);
            }
        }
    }

    public TeeAudioProcessor(AudioBufferSink audioBufferSink2) {
        this.audioBufferSink = (AudioBufferSink) Assertions.checkNotNull(audioBufferSink2);
    }

    private void flushSinkIfActive() {
        if (isActive()) {
            AudioBufferSink audioBufferSink2 = this.audioBufferSink;
            AudioProcessor.AudioFormat audioFormat = this.inputAudioFormat;
            audioBufferSink2.flush(audioFormat.sampleRate, audioFormat.channelCount, audioFormat.encoding);
        }
    }

    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat audioFormat) {
        return audioFormat;
    }

    public void onFlush() {
        flushSinkIfActive();
    }

    public void onQueueEndOfStream() {
        flushSinkIfActive();
    }

    public void onReset() {
        flushSinkIfActive();
    }

    public void queueInput(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        if (remaining != 0) {
            this.audioBufferSink.handleBuffer(byteBuffer.asReadOnlyBuffer());
            replaceOutputBuffer(remaining).put(byteBuffer).flip();
        }
    }
}
