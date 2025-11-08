package com.appsamurai.storyly.exoplayer2.core.audio;

import A0.C0201b;
import T1.a;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.camera.camera2.interop.c;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderCounters;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderReuseEvaluation;

public interface AudioRendererEventListener {

    public static final class EventDispatcher {
        @Nullable
        private final Handler handler;
        @Nullable
        private final AudioRendererEventListener listener;

        public EventDispatcher(@Nullable Handler handler2, @Nullable AudioRendererEventListener audioRendererEventListener) {
            this.handler = audioRendererEventListener != null ? (Handler) Assertions.checkNotNull(handler2) : null;
            this.listener = audioRendererEventListener;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$audioCodecError$9(Exception exc) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioCodecError(exc);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$audioSinkError$8(Exception exc) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioSinkError(exc);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$decoderInitialized$1(String str, long j2, long j3) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioDecoderInitialized(str, j2, j3);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$decoderReleased$5(String str) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioDecoderReleased(str);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$disabled$6(DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioDisabled(decoderCounters);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$enabled$0(DecoderCounters decoderCounters) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioEnabled(decoderCounters);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$inputFormatChanged$2(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioInputFormatChanged(format);
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioInputFormatChanged(format, decoderReuseEvaluation);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$positionAdvancing$3(long j2) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioPositionAdvancing(j2);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$skipSilenceEnabledChanged$7(boolean z2) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onSkipSilenceEnabledChanged(z2);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$underrun$4(int i3, long j2, long j3) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioUnderrun(i3, j2, j3);
        }

        public void audioCodecError(Exception exc) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new b(this, exc, 0));
            }
        }

        public void audioSinkError(Exception exc) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new b(this, exc, 1));
            }
        }

        public void decoderInitialized(String str, long j2, long j3) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new c(this, str, j2, j3, 0));
            }
        }

        public void decoderReleased(String str) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new C0201b(this, str, 26));
            }
        }

        public void disabled(DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new d(this, decoderCounters, 0));
            }
        }

        public void enabled(DecoderCounters decoderCounters) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new d(this, decoderCounters, 1));
            }
        }

        public void inputFormatChanged(Format format, @Nullable DecoderReuseEvaluation decoderReuseEvaluation) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new a(this, 11, format, decoderReuseEvaluation));
            }
        }

        public void positionAdvancing(long j2) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new e(this, j2, 0));
            }
        }

        public void skipSilenceEnabledChanged(boolean z2) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new c(this, z2, 3));
            }
        }

        public void underrun(int i3, long j2, long j3) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new a(this, i3, j2, j3));
            }
        }
    }

    void onAudioCodecError(Exception exc) {
    }

    void onAudioDecoderInitialized(String str, long j2, long j3) {
    }

    void onAudioDecoderReleased(String str) {
    }

    void onAudioDisabled(DecoderCounters decoderCounters) {
    }

    void onAudioEnabled(DecoderCounters decoderCounters) {
    }

    @Deprecated
    void onAudioInputFormatChanged(Format format) {
    }

    void onAudioPositionAdvancing(long j2) {
    }

    void onAudioSinkError(Exception exc) {
    }

    void onAudioUnderrun(int i3, long j2, long j3) {
    }

    void onSkipSilenceEnabledChanged(boolean z2) {
    }

    void onAudioInputFormatChanged(Format format, @Nullable DecoderReuseEvaluation decoderReuseEvaluation) {
    }
}
