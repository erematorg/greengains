package com.appsamurai.storyly.exoplayer2.core.video;

import A0.C0201b;
import T1.a;
import android.os.Handler;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.common.video.VideoSize;
import com.appsamurai.storyly.exoplayer2.core.audio.c;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderCounters;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderReuseEvaluation;

public interface VideoRendererEventListener {

    public static final class EventDispatcher {
        @Nullable
        private final Handler handler;
        @Nullable
        private final VideoRendererEventListener listener;

        public EventDispatcher(@Nullable Handler handler2, @Nullable VideoRendererEventListener videoRendererEventListener) {
            this.handler = videoRendererEventListener != null ? (Handler) Assertions.checkNotNull(handler2) : null;
            this.listener = videoRendererEventListener;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$decoderInitialized$1(String str, long j2, long j3) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoDecoderInitialized(str, j2, j3);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$decoderReleased$7(String str) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoDecoderReleased(str);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$disabled$8(DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoDisabled(decoderCounters);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$droppedFrames$3(int i3, long j2) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onDroppedFrames(i3, j2);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$enabled$0(DecoderCounters decoderCounters) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoEnabled(decoderCounters);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$inputFormatChanged$2(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoInputFormatChanged(format);
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoInputFormatChanged(format, decoderReuseEvaluation);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$renderedFirstFrame$6(Object obj, long j2) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onRenderedFirstFrame(obj, j2);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$reportVideoFrameProcessingOffset$4(long j2, int i3) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoFrameProcessingOffset(j2, i3);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$videoCodecError$9(Exception exc) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoCodecError(exc);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$videoSizeChanged$5(VideoSize videoSize) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoSizeChanged(videoSize);
        }

        public void decoderInitialized(String str, long j2, long j3) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new c(this, str, j2, j3, 1));
            }
        }

        public void decoderReleased(String str) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new C0201b(this, str, 28));
            }
        }

        public void disabled(DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new a(this, decoderCounters, 1));
            }
        }

        public void droppedFrames(int i3, long j2) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new b(this, i3, j2));
            }
        }

        public void enabled(DecoderCounters decoderCounters) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new a(this, decoderCounters, 0));
            }
        }

        public void inputFormatChanged(Format format, @Nullable DecoderReuseEvaluation decoderReuseEvaluation) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new a(this, 14, format, decoderReuseEvaluation));
            }
        }

        public void renderedFirstFrame(Object obj) {
            if (this.handler != null) {
                this.handler.post(new c(this, obj, SystemClock.elapsedRealtime()));
            }
        }

        public void reportVideoFrameProcessingOffset(long j2, int i3) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new b(this, j2, i3));
            }
        }

        public void videoCodecError(Exception exc) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new C0201b(this, exc, 27));
            }
        }

        public void videoSizeChanged(VideoSize videoSize) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new C0201b(this, videoSize, 29));
            }
        }
    }

    void onDroppedFrames(int i3, long j2) {
    }

    void onRenderedFirstFrame(Object obj, long j2) {
    }

    void onVideoCodecError(Exception exc) {
    }

    void onVideoDecoderInitialized(String str, long j2, long j3) {
    }

    void onVideoDecoderReleased(String str) {
    }

    void onVideoDisabled(DecoderCounters decoderCounters) {
    }

    void onVideoEnabled(DecoderCounters decoderCounters) {
    }

    void onVideoFrameProcessingOffset(long j2, int i3) {
    }

    @Deprecated
    void onVideoInputFormatChanged(Format format) {
    }

    void onVideoSizeChanged(VideoSize videoSize) {
    }

    void onVideoInputFormatChanged(Format format, @Nullable DecoderReuseEvaluation decoderReuseEvaluation) {
    }
}
