package com.appsamurai.storyly.exoplayer2.core;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.source.TrackGroupArray;
import com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection;
import com.appsamurai.storyly.exoplayer2.core.upstream.Allocator;
import com.appsamurai.storyly.exoplayer2.core.upstream.DefaultAllocator;
import org.apache.xerces.impl.xs.SchemaSymbols;

public class DefaultLoadControl implements LoadControl {
    public static final int DEFAULT_AUDIO_BUFFER_SIZE = 13107200;
    public static final int DEFAULT_BACK_BUFFER_DURATION_MS = 0;
    public static final int DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS = 5000;
    public static final int DEFAULT_BUFFER_FOR_PLAYBACK_MS = 2500;
    public static final int DEFAULT_CAMERA_MOTION_BUFFER_SIZE = 131072;
    public static final int DEFAULT_IMAGE_BUFFER_SIZE = 131072;
    public static final int DEFAULT_MAX_BUFFER_MS = 50000;
    public static final int DEFAULT_METADATA_BUFFER_SIZE = 131072;
    public static final int DEFAULT_MIN_BUFFER_MS = 50000;
    public static final int DEFAULT_MIN_BUFFER_SIZE = 13107200;
    public static final int DEFAULT_MUXED_BUFFER_SIZE = 144310272;
    public static final boolean DEFAULT_PRIORITIZE_TIME_OVER_SIZE_THRESHOLDS = false;
    public static final boolean DEFAULT_RETAIN_BACK_BUFFER_FROM_KEYFRAME = false;
    public static final int DEFAULT_TARGET_BUFFER_BYTES = -1;
    public static final int DEFAULT_TEXT_BUFFER_SIZE = 131072;
    public static final int DEFAULT_VIDEO_BUFFER_SIZE = 131072000;
    private final DefaultAllocator allocator;
    private final long backBufferDurationUs;
    private final long bufferForPlaybackAfterRebufferUs;
    private final long bufferForPlaybackUs;
    private boolean isLoading;
    private final long maxBufferUs;
    private final long minBufferUs;
    private final boolean prioritizeTimeOverSizeThresholds;
    private final boolean retainBackBufferFromKeyframe;
    private int targetBufferBytes;
    private final int targetBufferBytesOverwrite;

    public static final class Builder {
        @Nullable
        private DefaultAllocator allocator;
        private int backBufferDurationMs = 0;
        private int bufferForPlaybackAfterRebufferMs = DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS;
        private int bufferForPlaybackMs = 2500;
        private boolean buildCalled;
        private int maxBufferMs = 50000;
        private int minBufferMs = 50000;
        private boolean prioritizeTimeOverSizeThresholds = false;
        private boolean retainBackBufferFromKeyframe = false;
        private int targetBufferBytes = -1;

        public DefaultLoadControl build() {
            Assertions.checkState(!this.buildCalled);
            this.buildCalled = true;
            if (this.allocator == null) {
                this.allocator = new DefaultAllocator(true, 65536);
            }
            return new DefaultLoadControl(this.allocator, this.minBufferMs, this.maxBufferMs, this.bufferForPlaybackMs, this.bufferForPlaybackAfterRebufferMs, this.targetBufferBytes, this.prioritizeTimeOverSizeThresholds, this.backBufferDurationMs, this.retainBackBufferFromKeyframe);
        }

        @Deprecated
        public DefaultLoadControl createDefaultLoadControl() {
            return build();
        }

        public Builder setAllocator(DefaultAllocator defaultAllocator) {
            Assertions.checkState(!this.buildCalled);
            this.allocator = defaultAllocator;
            return this;
        }

        public Builder setBackBuffer(int i3, boolean z2) {
            Assertions.checkState(!this.buildCalled);
            DefaultLoadControl.assertGreaterOrEqual(i3, 0, "backBufferDurationMs", SchemaSymbols.ATTVAL_FALSE_0);
            this.backBufferDurationMs = i3;
            this.retainBackBufferFromKeyframe = z2;
            return this;
        }

        public Builder setBufferDurationsMs(int i3, int i4, int i5, int i6) {
            Assertions.checkState(!this.buildCalled);
            DefaultLoadControl.assertGreaterOrEqual(i5, 0, "bufferForPlaybackMs", SchemaSymbols.ATTVAL_FALSE_0);
            DefaultLoadControl.assertGreaterOrEqual(i6, 0, "bufferForPlaybackAfterRebufferMs", SchemaSymbols.ATTVAL_FALSE_0);
            DefaultLoadControl.assertGreaterOrEqual(i3, i5, "minBufferMs", "bufferForPlaybackMs");
            DefaultLoadControl.assertGreaterOrEqual(i3, i6, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
            DefaultLoadControl.assertGreaterOrEqual(i4, i3, "maxBufferMs", "minBufferMs");
            this.minBufferMs = i3;
            this.maxBufferMs = i4;
            this.bufferForPlaybackMs = i5;
            this.bufferForPlaybackAfterRebufferMs = i6;
            return this;
        }

        public Builder setPrioritizeTimeOverSizeThresholds(boolean z2) {
            Assertions.checkState(!this.buildCalled);
            this.prioritizeTimeOverSizeThresholds = z2;
            return this;
        }

        public Builder setTargetBufferBytes(int i3) {
            Assertions.checkState(!this.buildCalled);
            this.targetBufferBytes = i3;
            return this;
        }
    }

    public DefaultLoadControl() {
        this(new DefaultAllocator(true, 65536), 50000, 50000, 2500, DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS, -1, false, 0, false);
    }

    /* access modifiers changed from: private */
    public static void assertGreaterOrEqual(int i3, int i4, String str, String str2) {
        boolean z2 = i3 >= i4;
        Assertions.checkArgument(z2, str + " cannot be less than " + str2);
    }

    private static int getDefaultBufferSize(int i3) {
        switch (i3) {
            case -2:
                return 0;
            case 0:
                return DEFAULT_MUXED_BUFFER_SIZE;
            case 1:
                return 13107200;
            case 2:
                return DEFAULT_VIDEO_BUFFER_SIZE;
            case 3:
            case 4:
            case 5:
            case 6:
                return 131072;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void reset(boolean z2) {
        int i3 = this.targetBufferBytesOverwrite;
        if (i3 == -1) {
            i3 = 13107200;
        }
        this.targetBufferBytes = i3;
        this.isLoading = false;
        if (z2) {
            this.allocator.reset();
        }
    }

    public int calculateTargetBufferBytes(Renderer[] rendererArr, ExoTrackSelection[] exoTrackSelectionArr) {
        int i3 = 0;
        for (int i4 = 0; i4 < rendererArr.length; i4++) {
            if (exoTrackSelectionArr[i4] != null) {
                i3 += getDefaultBufferSize(rendererArr[i4].getTrackType());
            }
        }
        return Math.max(13107200, i3);
    }

    public Allocator getAllocator() {
        return this.allocator;
    }

    public long getBackBufferDurationUs() {
        return this.backBufferDurationUs;
    }

    public void onPrepared() {
        reset(false);
    }

    public void onReleased() {
        reset(true);
    }

    public void onStopped() {
        reset(true);
    }

    public void onTracksSelected(Renderer[] rendererArr, TrackGroupArray trackGroupArray, ExoTrackSelection[] exoTrackSelectionArr) {
        int i3 = this.targetBufferBytesOverwrite;
        if (i3 == -1) {
            i3 = calculateTargetBufferBytes(rendererArr, exoTrackSelectionArr);
        }
        this.targetBufferBytes = i3;
        this.allocator.setTargetBufferSize(i3);
    }

    public boolean retainBackBufferFromKeyframe() {
        return this.retainBackBufferFromKeyframe;
    }

    public boolean shouldContinueLoading(long j2, long j3, float f2) {
        boolean z2 = true;
        boolean z3 = this.allocator.getTotalBytesAllocated() >= this.targetBufferBytes;
        long j4 = this.minBufferUs;
        if (f2 > 1.0f) {
            j4 = Math.min(Util.getMediaDurationForPlayoutDuration(j4, f2), this.maxBufferUs);
        }
        if (j3 < Math.max(j4, 500000)) {
            if (!this.prioritizeTimeOverSizeThresholds && z3) {
                z2 = false;
            }
            this.isLoading = z2;
            if (!z2 && j3 < 500000) {
                Log.w("DefaultLoadControl", "Target buffer size reached with less than 500ms of buffered media data.");
            }
        } else if (j3 >= this.maxBufferUs || z3) {
            this.isLoading = false;
        }
        return this.isLoading;
    }

    public boolean shouldStartPlayback(long j2, float f2, boolean z2, long j3) {
        long playoutDurationForMediaDuration = Util.getPlayoutDurationForMediaDuration(j2, f2);
        long j4 = z2 ? this.bufferForPlaybackAfterRebufferUs : this.bufferForPlaybackUs;
        if (j3 != C.TIME_UNSET) {
            j4 = Math.min(j3 / 2, j4);
        }
        return j4 <= 0 || playoutDurationForMediaDuration >= j4 || (!this.prioritizeTimeOverSizeThresholds && this.allocator.getTotalBytesAllocated() >= this.targetBufferBytes);
    }

    public DefaultLoadControl(DefaultAllocator defaultAllocator, int i3, int i4, int i5, int i6, int i7, boolean z2, int i8, boolean z3) {
        assertGreaterOrEqual(i5, 0, "bufferForPlaybackMs", SchemaSymbols.ATTVAL_FALSE_0);
        assertGreaterOrEqual(i6, 0, "bufferForPlaybackAfterRebufferMs", SchemaSymbols.ATTVAL_FALSE_0);
        assertGreaterOrEqual(i3, i5, "minBufferMs", "bufferForPlaybackMs");
        assertGreaterOrEqual(i3, i6, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
        assertGreaterOrEqual(i4, i3, "maxBufferMs", "minBufferMs");
        assertGreaterOrEqual(i8, 0, "backBufferDurationMs", SchemaSymbols.ATTVAL_FALSE_0);
        this.allocator = defaultAllocator;
        this.minBufferUs = Util.msToUs((long) i3);
        this.maxBufferUs = Util.msToUs((long) i4);
        this.bufferForPlaybackUs = Util.msToUs((long) i5);
        this.bufferForPlaybackAfterRebufferUs = Util.msToUs((long) i6);
        this.targetBufferBytesOverwrite = i7;
        this.targetBufferBytes = i7 == -1 ? 13107200 : i7;
        this.prioritizeTimeOverSizeThresholds = z2;
        this.backBufferDurationUs = Util.msToUs((long) i8);
        this.retainBackBufferFromKeyframe = z3;
    }
}
