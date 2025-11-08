package com.appsamurai.storyly.exoplayer2.core;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId;
import com.appsamurai.storyly.exoplayer2.core.source.SampleStream;
import com.appsamurai.storyly.exoplayer2.core.util.MediaClock;
import java.io.IOException;

public abstract class NoSampleRenderer implements Renderer, RendererCapabilities {
    private RendererConfiguration configuration;
    private int index;
    private int state;
    @Nullable
    private SampleStream stream;
    private boolean streamIsFinal;

    public final void disable() {
        boolean z2 = true;
        if (this.state != 1) {
            z2 = false;
        }
        Assertions.checkState(z2);
        this.state = 0;
        this.stream = null;
        this.streamIsFinal = false;
        onDisabled();
    }

    public final void enable(RendererConfiguration rendererConfiguration, Format[] formatArr, SampleStream sampleStream, long j2, boolean z2, boolean z3, long j3, long j4) throws ExoPlaybackException {
        boolean z4 = z2;
        Assertions.checkState(this.state == 0);
        this.configuration = rendererConfiguration;
        this.state = 1;
        onEnabled(z2);
        replaceStream(formatArr, sampleStream, j3, j4);
        long j5 = j2;
        onPositionReset(j2, z2);
    }

    public final RendererCapabilities getCapabilities() {
        return this;
    }

    @Nullable
    public final RendererConfiguration getConfiguration() {
        return this.configuration;
    }

    public final int getIndex() {
        return this.index;
    }

    @Nullable
    public MediaClock getMediaClock() {
        return null;
    }

    public long getReadingPositionUs() {
        return Long.MIN_VALUE;
    }

    public final int getState() {
        return this.state;
    }

    @Nullable
    public final SampleStream getStream() {
        return this.stream;
    }

    public final int getTrackType() {
        return -2;
    }

    public void handleMessage(int i3, @Nullable Object obj) throws ExoPlaybackException {
    }

    public final boolean hasReadStreamToEnd() {
        return true;
    }

    public final void init(int i3, PlayerId playerId) {
        this.index = i3;
    }

    public final boolean isCurrentStreamFinal() {
        return this.streamIsFinal;
    }

    public boolean isEnded() {
        return true;
    }

    public boolean isReady() {
        return true;
    }

    public final void maybeThrowStreamError() throws IOException {
    }

    public void onDisabled() {
    }

    public void onEnabled(boolean z2) throws ExoPlaybackException {
    }

    public void onPositionReset(long j2, boolean z2) throws ExoPlaybackException {
    }

    public void onRendererOffsetChanged(long j2) throws ExoPlaybackException {
    }

    public void onReset() {
    }

    public void onStarted() throws ExoPlaybackException {
    }

    public void onStopped() {
    }

    public final void replaceStream(Format[] formatArr, SampleStream sampleStream, long j2, long j3) throws ExoPlaybackException {
        Assertions.checkState(!this.streamIsFinal);
        this.stream = sampleStream;
        onRendererOffsetChanged(j3);
    }

    public final void reset() {
        Assertions.checkState(this.state == 0);
        onReset();
    }

    public final void resetPosition(long j2) throws ExoPlaybackException {
        this.streamIsFinal = false;
        onPositionReset(j2, false);
    }

    public final void setCurrentStreamFinal() {
        this.streamIsFinal = true;
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

    public int supportsFormat(Format format) throws ExoPlaybackException {
        return RendererCapabilities.create(0);
    }

    public int supportsMixedMimeTypeAdaptation() throws ExoPlaybackException {
        return 0;
    }
}
