package com.appsamurai.storyly.exoplayer2.core.text;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.extractor.text.CueDecoder;
import com.appsamurai.storyly.exoplayer2.extractor.text.Subtitle;
import com.appsamurai.storyly.exoplayer2.extractor.text.SubtitleDecoder;
import com.appsamurai.storyly.exoplayer2.extractor.text.SubtitleDecoderException;
import com.appsamurai.storyly.exoplayer2.extractor.text.SubtitleInputBuffer;
import com.appsamurai.storyly.exoplayer2.extractor.text.SubtitleOutputBuffer;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public final class ExoplayerCuesDecoder implements SubtitleDecoder {
    private static final int INPUT_BUFFER_AVAILABLE = 0;
    private static final int INPUT_BUFFER_DEQUEUED = 1;
    private static final int INPUT_BUFFER_QUEUED = 2;
    private static final int OUTPUT_BUFFERS_COUNT = 2;
    private final Deque<SubtitleOutputBuffer> availableOutputBuffers = new ArrayDeque();
    private final CueDecoder cueDecoder = new CueDecoder();
    private final SubtitleInputBuffer inputBuffer = new SubtitleInputBuffer();
    private int inputBufferState;
    private boolean released;

    public static final class SingleEventSubtitle implements Subtitle {
        private final ImmutableList<Cue> cues;
        private final long timeUs;

        public SingleEventSubtitle(long j2, ImmutableList<Cue> immutableList) {
            this.timeUs = j2;
            this.cues = immutableList;
        }

        public List<Cue> getCues(long j2) {
            return j2 >= this.timeUs ? this.cues : ImmutableList.of();
        }

        public long getEventTime(int i3) {
            Assertions.checkArgument(i3 == 0);
            return this.timeUs;
        }

        public int getEventTimeCount() {
            return 1;
        }

        public int getNextEventTimeIndex(long j2) {
            return this.timeUs > j2 ? 0 : -1;
        }
    }

    public ExoplayerCuesDecoder() {
        for (int i3 = 0; i3 < 2; i3++) {
            this.availableOutputBuffers.addFirst(new SubtitleOutputBuffer() {
                public void release() {
                    ExoplayerCuesDecoder.this.releaseOutputBuffer(this);
                }
            });
        }
        this.inputBufferState = 0;
    }

    /* access modifiers changed from: private */
    public void releaseOutputBuffer(SubtitleOutputBuffer subtitleOutputBuffer) {
        Assertions.checkState(this.availableOutputBuffers.size() < 2);
        Assertions.checkArgument(!this.availableOutputBuffers.contains(subtitleOutputBuffer));
        subtitleOutputBuffer.clear();
        this.availableOutputBuffers.addFirst(subtitleOutputBuffer);
    }

    public void flush() {
        Assertions.checkState(!this.released);
        this.inputBuffer.clear();
        this.inputBufferState = 0;
    }

    public String getName() {
        return "ExoplayerCuesDecoder";
    }

    public void release() {
        this.released = true;
    }

    public void setPositionUs(long j2) {
    }

    @Nullable
    public SubtitleInputBuffer dequeueInputBuffer() throws SubtitleDecoderException {
        Assertions.checkState(!this.released);
        if (this.inputBufferState != 0) {
            return null;
        }
        this.inputBufferState = 1;
        return this.inputBuffer;
    }

    @Nullable
    public SubtitleOutputBuffer dequeueOutputBuffer() throws SubtitleDecoderException {
        Assertions.checkState(!this.released);
        if (this.inputBufferState != 2 || this.availableOutputBuffers.isEmpty()) {
            return null;
        }
        SubtitleOutputBuffer removeFirst = this.availableOutputBuffers.removeFirst();
        if (this.inputBuffer.isEndOfStream()) {
            removeFirst.addFlag(4);
        } else {
            SubtitleInputBuffer subtitleInputBuffer = this.inputBuffer;
            SingleEventSubtitle singleEventSubtitle = new SingleEventSubtitle(subtitleInputBuffer.timeUs, this.cueDecoder.decode(((ByteBuffer) Assertions.checkNotNull(subtitleInputBuffer.data)).array()));
            removeFirst.setContent(this.inputBuffer.timeUs, singleEventSubtitle, 0);
        }
        this.inputBuffer.clear();
        this.inputBufferState = 0;
        return removeFirst;
    }

    public void queueInputBuffer(SubtitleInputBuffer subtitleInputBuffer) throws SubtitleDecoderException {
        boolean z2 = true;
        Assertions.checkState(!this.released);
        Assertions.checkState(this.inputBufferState == 1);
        if (this.inputBuffer != subtitleInputBuffer) {
            z2 = false;
        }
        Assertions.checkArgument(z2);
        this.inputBufferState = 2;
    }
}
