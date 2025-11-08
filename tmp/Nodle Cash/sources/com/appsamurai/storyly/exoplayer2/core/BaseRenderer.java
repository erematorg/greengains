package com.appsamurai.storyly.exoplayer2.core;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId;
import com.appsamurai.storyly.exoplayer2.core.source.SampleStream;
import com.appsamurai.storyly.exoplayer2.core.util.MediaClock;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer;
import java.io.IOException;

public abstract class BaseRenderer implements Renderer, RendererCapabilities {
    @Nullable
    private RendererConfiguration configuration;
    private final FormatHolder formatHolder = new FormatHolder();
    private int index;
    private long lastResetPositionUs;
    private PlayerId playerId;
    private long readingPositionUs = Long.MIN_VALUE;
    private int state;
    @Nullable
    private SampleStream stream;
    @Nullable
    private Format[] streamFormats;
    private boolean streamIsFinal;
    private long streamOffsetUs;
    private boolean throwRendererExceptionIsExecuting;
    private final int trackType;

    public BaseRenderer(int i3) {
        this.trackType = i3;
    }

    public final ExoPlaybackException createRendererException(Throwable th, @Nullable Format format, int i3) {
        return createRendererException(th, format, false, i3);
    }

    public final void disable() {
        boolean z2 = true;
        if (this.state != 1) {
            z2 = false;
        }
        Assertions.checkState(z2);
        this.formatHolder.clear();
        this.state = 0;
        this.stream = null;
        this.streamFormats = null;
        this.streamIsFinal = false;
        onDisabled();
    }

    public final void enable(RendererConfiguration rendererConfiguration, Format[] formatArr, SampleStream sampleStream, long j2, boolean z2, boolean z3, long j3, long j4) throws ExoPlaybackException {
        boolean z4 = z2;
        Assertions.checkState(this.state == 0);
        this.configuration = rendererConfiguration;
        this.state = 1;
        onEnabled(z2, z3);
        replaceStream(formatArr, sampleStream, j3, j4);
        long j5 = j2;
        resetPosition(j2, z2);
    }

    public final RendererCapabilities getCapabilities() {
        return this;
    }

    public final RendererConfiguration getConfiguration() {
        return (RendererConfiguration) Assertions.checkNotNull(this.configuration);
    }

    public final FormatHolder getFormatHolder() {
        this.formatHolder.clear();
        return this.formatHolder;
    }

    public final int getIndex() {
        return this.index;
    }

    public final long getLastResetPositionUs() {
        return this.lastResetPositionUs;
    }

    @Nullable
    public MediaClock getMediaClock() {
        return null;
    }

    public final PlayerId getPlayerId() {
        return (PlayerId) Assertions.checkNotNull(this.playerId);
    }

    public final long getReadingPositionUs() {
        return this.readingPositionUs;
    }

    public final int getState() {
        return this.state;
    }

    @Nullable
    public final SampleStream getStream() {
        return this.stream;
    }

    public final Format[] getStreamFormats() {
        return (Format[]) Assertions.checkNotNull(this.streamFormats);
    }

    public final int getTrackType() {
        return this.trackType;
    }

    public void handleMessage(int i3, @Nullable Object obj) throws ExoPlaybackException {
    }

    public final boolean hasReadStreamToEnd() {
        return this.readingPositionUs == Long.MIN_VALUE;
    }

    public final void init(int i3, PlayerId playerId2) {
        this.index = i3;
        this.playerId = playerId2;
    }

    public final boolean isCurrentStreamFinal() {
        return this.streamIsFinal;
    }

    public final boolean isSourceReady() {
        return hasReadStreamToEnd() ? this.streamIsFinal : ((SampleStream) Assertions.checkNotNull(this.stream)).isReady();
    }

    public final void maybeThrowStreamError() throws IOException {
        ((SampleStream) Assertions.checkNotNull(this.stream)).maybeThrowError();
    }

    public void onDisabled() {
    }

    public void onEnabled(boolean z2, boolean z3) throws ExoPlaybackException {
    }

    public void onPositionReset(long j2, boolean z2) throws ExoPlaybackException {
    }

    public void onReset() {
    }

    public void onStarted() throws ExoPlaybackException {
    }

    public void onStopped() {
    }

    public void onStreamChanged(Format[] formatArr, long j2, long j3) throws ExoPlaybackException {
    }

    public final int readSource(FormatHolder formatHolder2, DecoderInputBuffer decoderInputBuffer, int i3) {
        int readData = ((SampleStream) Assertions.checkNotNull(this.stream)).readData(formatHolder2, decoderInputBuffer, i3);
        if (readData == -4) {
            if (decoderInputBuffer.isEndOfStream()) {
                this.readingPositionUs = Long.MIN_VALUE;
                return this.streamIsFinal ? -4 : -3;
            }
            long j2 = decoderInputBuffer.timeUs + this.streamOffsetUs;
            decoderInputBuffer.timeUs = j2;
            this.readingPositionUs = Math.max(this.readingPositionUs, j2);
        } else if (readData == -5) {
            Format format = (Format) Assertions.checkNotNull(formatHolder2.format);
            if (format.subsampleOffsetUs != Long.MAX_VALUE) {
                formatHolder2.format = format.buildUpon().setSubsampleOffsetUs(format.subsampleOffsetUs + this.streamOffsetUs).build();
            }
        }
        return readData;
    }

    public final void replaceStream(Format[] formatArr, SampleStream sampleStream, long j2, long j3) throws ExoPlaybackException {
        Assertions.checkState(!this.streamIsFinal);
        this.stream = sampleStream;
        if (this.readingPositionUs == Long.MIN_VALUE) {
            this.readingPositionUs = j2;
        }
        this.streamFormats = formatArr;
        this.streamOffsetUs = j3;
        onStreamChanged(formatArr, j2, j3);
    }

    public final void reset() {
        Assertions.checkState(this.state == 0);
        this.formatHolder.clear();
        onReset();
    }

    public final void resetPosition(long j2) throws ExoPlaybackException {
        resetPosition(j2, false);
    }

    public final void setCurrentStreamFinal() {
        this.streamIsFinal = true;
    }

    public int skipSource(long j2) {
        return ((SampleStream) Assertions.checkNotNull(this.stream)).skipData(j2 - this.streamOffsetUs);
    }

    public final void start() throws ExoPlaybackException {
        boolean z2 = true;
        if (this.state != 1) {
            z2 = false;
        }
        Assertions.checkState(z2);
        this.state = 2;
        onStarted();
    }

    public final void stop() {
        Assertions.checkState(this.state == 2);
        this.state = 1;
        onStopped();
    }

    public int supportsMixedMimeTypeAdaptation() throws ExoPlaybackException {
        return 0;
    }

    private void resetPosition(long j2, boolean z2) throws ExoPlaybackException {
        this.streamIsFinal = false;
        this.lastResetPositionUs = j2;
        this.readingPositionUs = j2;
        onPositionReset(j2, z2);
    }

    public final ExoPlaybackException createRendererException(Throwable th, @Nullable Format format, boolean z2, int i3) {
        int i4;
        if (format != null && !this.throwRendererExceptionIsExecuting) {
            this.throwRendererExceptionIsExecuting = true;
            try {
                i4 = RendererCapabilities.getFormatSupport(supportsFormat(format));
            } catch (ExoPlaybackException unused) {
            } finally {
                this.throwRendererExceptionIsExecuting = false;
            }
            return ExoPlaybackException.createForRenderer(th, getName(), getIndex(), format, i4, z2, i3);
        }
        i4 = 4;
        return ExoPlaybackException.createForRenderer(th, getName(), getIndex(), format, i4, z2, i3);
    }
}
