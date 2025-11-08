package com.appsamurai.storyly.exoplayer2.core.audio;

import android.annotation.SuppressLint;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.PlaybackParams;
import android.media.metrics.LogSessionId;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Pair;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.compose.animation.core.a;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.PlaybackParameters;
import com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes;
import com.appsamurai.storyly.exoplayer2.common.audio.AuxEffectInfo;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId;
import com.appsamurai.storyly.exoplayer2.core.audio.AudioProcessor;
import com.appsamurai.storyly.exoplayer2.core.audio.AudioSink;
import com.appsamurai.storyly.exoplayer2.core.audio.AudioTrackPositionTracker;
import com.appsamurai.storyly.exoplayer2.core.audio.DefaultAudioTrackBufferSizeProvider;
import com.appsamurai.storyly.exoplayer2.extractor.audio.Ac3Util;
import com.appsamurai.storyly.exoplayer2.extractor.audio.Ac4Util;
import com.appsamurai.storyly.exoplayer2.extractor.audio.DtsUtil;
import com.appsamurai.storyly.exoplayer2.extractor.audio.MpegAudioUtil;
import com.google.common.base.MoreObjects;
import com.google.errorprone.annotations.InlineMe;
import com.google.errorprone.annotations.InlineMeValidationDisabled;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class DefaultAudioSink implements AudioSink {
    private static final int AUDIO_TRACK_RETRY_DURATION_MS = 100;
    private static final int AUDIO_TRACK_SMALLER_BUFFER_RETRY_SIZE = 1000000;
    public static final float DEFAULT_PLAYBACK_SPEED = 1.0f;
    private static final boolean DEFAULT_SKIP_SILENCE = false;
    private static final int ERROR_NATIVE_DEAD_OBJECT = -32;
    public static final float MAX_PITCH = 8.0f;
    public static final float MAX_PLAYBACK_SPEED = 8.0f;
    public static final float MIN_PITCH = 0.1f;
    public static final float MIN_PLAYBACK_SPEED = 0.1f;
    public static final int OFFLOAD_MODE_DISABLED = 0;
    public static final int OFFLOAD_MODE_ENABLED_GAPLESS_DISABLED = 3;
    public static final int OFFLOAD_MODE_ENABLED_GAPLESS_NOT_REQUIRED = 2;
    public static final int OFFLOAD_MODE_ENABLED_GAPLESS_REQUIRED = 1;
    public static final int OUTPUT_MODE_OFFLOAD = 1;
    public static final int OUTPUT_MODE_PASSTHROUGH = 2;
    public static final int OUTPUT_MODE_PCM = 0;
    private static final String TAG = "DefaultAudioSink";
    public static boolean failOnSpuriousAudioTimestamp = false;
    private AudioProcessor[] activeAudioProcessors;
    @Nullable
    private MediaPositionParameters afterDrainParameters;
    private AudioAttributes audioAttributes;
    private final AudioCapabilities audioCapabilities;
    private final AudioProcessorChain audioProcessorChain;
    private int audioSessionId;
    /* access modifiers changed from: private */
    @Nullable
    public AudioTrack audioTrack;
    private final AudioTrackBufferSizeProvider audioTrackBufferSizeProvider;
    private PlaybackParameters audioTrackPlaybackParameters;
    private final AudioTrackPositionTracker audioTrackPositionTracker;
    private AuxEffectInfo auxEffectInfo;
    @Nullable
    private ByteBuffer avSyncHeader;
    private int bytesUntilNextAvSync;
    private final ChannelMappingAudioProcessor channelMappingAudioProcessor;
    private Configuration configuration;
    private int drainingAudioProcessorIndex;
    private final boolean enableAudioTrackPlaybackParams;
    private final boolean enableFloatOutput;
    private boolean externalAudioSessionIdProvided;
    private int framesPerEncodedSample;
    private boolean handledEndOfStream;
    private final PendingExceptionHolder<AudioSink.InitializationException> initializationExceptionPendingExceptionHolder;
    @Nullable
    private ByteBuffer inputBuffer;
    private int inputBufferAccessUnitCount;
    private boolean isWaitingForOffloadEndOfStreamHandled;
    /* access modifiers changed from: private */
    public long lastFeedElapsedRealtimeMs;
    /* access modifiers changed from: private */
    @Nullable
    public AudioSink.Listener listener;
    private MediaPositionParameters mediaPositionParameters;
    private final ArrayDeque<MediaPositionParameters> mediaPositionParametersCheckpoints;
    private boolean offloadDisabledUntilNextConfiguration;
    private final int offloadMode;
    private StreamEventCallbackV29 offloadStreamEventCallbackV29;
    @Nullable
    private ByteBuffer outputBuffer;
    private ByteBuffer[] outputBuffers;
    @Nullable
    private Configuration pendingConfiguration;
    @Nullable
    private PlayerId playerId;
    /* access modifiers changed from: private */
    public boolean playing;
    private byte[] preV21OutputBuffer;
    private int preV21OutputBufferOffset;
    /* access modifiers changed from: private */
    public final ConditionVariable releasingConditionVariable;
    private long startMediaTimeUs;
    private boolean startMediaTimeUsNeedsInit;
    private boolean startMediaTimeUsNeedsSync;
    private boolean stoppedAudioTrack;
    private long submittedEncodedFrames;
    private long submittedPcmBytes;
    private final AudioProcessor[] toFloatPcmAvailableAudioProcessors;
    private final AudioProcessor[] toIntPcmAvailableAudioProcessors;
    private final TrimmingAudioProcessor trimmingAudioProcessor;
    private boolean tunneling;
    private float volume;
    private final PendingExceptionHolder<AudioSink.WriteException> writeExceptionPendingExceptionHolder;
    private long writtenEncodedFrames;
    private long writtenPcmBytes;

    @RequiresApi(31)
    public static final class Api31 {
        private Api31() {
        }

        @DoNotInline
        public static void setLogSessionIdOnAudioTrack(AudioTrack audioTrack, PlayerId playerId) {
            LogSessionId logSessionId = playerId.getLogSessionId();
            if (!logSessionId.equals(LogSessionId.LOG_SESSION_ID_NONE)) {
                audioTrack.setLogSessionId(logSessionId);
            }
        }
    }

    public interface AudioProcessorChain {
        PlaybackParameters applyPlaybackParameters(PlaybackParameters playbackParameters);

        boolean applySkipSilenceEnabled(boolean z2);

        AudioProcessor[] getAudioProcessors();

        long getMediaDuration(long j2);

        long getSkippedOutputFrameCount();
    }

    public interface AudioTrackBufferSizeProvider {
        public static final AudioTrackBufferSizeProvider DEFAULT = new DefaultAudioTrackBufferSizeProvider.Builder().build();

        int getBufferSizeInBytes(int i3, int i4, int i5, int i6, int i7, double d2);
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public AudioCapabilities audioCapabilities = AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES;
        /* access modifiers changed from: private */
        @Nullable
        public AudioProcessorChain audioProcessorChain;
        AudioTrackBufferSizeProvider audioTrackBufferSizeProvider = AudioTrackBufferSizeProvider.DEFAULT;
        /* access modifiers changed from: private */
        public boolean enableAudioTrackPlaybackParams;
        /* access modifiers changed from: private */
        public boolean enableFloatOutput;
        /* access modifiers changed from: private */
        public int offloadMode = 0;

        public DefaultAudioSink build() {
            if (this.audioProcessorChain == null) {
                this.audioProcessorChain = new DefaultAudioProcessorChain(new AudioProcessor[0]);
            }
            return new DefaultAudioSink(this);
        }

        public Builder setAudioCapabilities(AudioCapabilities audioCapabilities2) {
            Assertions.checkNotNull(audioCapabilities2);
            this.audioCapabilities = audioCapabilities2;
            return this;
        }

        public Builder setAudioProcessorChain(AudioProcessorChain audioProcessorChain2) {
            Assertions.checkNotNull(audioProcessorChain2);
            this.audioProcessorChain = audioProcessorChain2;
            return this;
        }

        public Builder setAudioProcessors(AudioProcessor[] audioProcessorArr) {
            Assertions.checkNotNull(audioProcessorArr);
            return setAudioProcessorChain(new DefaultAudioProcessorChain(audioProcessorArr));
        }

        public Builder setAudioTrackBufferSizeProvider(AudioTrackBufferSizeProvider audioTrackBufferSizeProvider2) {
            this.audioTrackBufferSizeProvider = audioTrackBufferSizeProvider2;
            return this;
        }

        public Builder setEnableAudioTrackPlaybackParams(boolean z2) {
            this.enableAudioTrackPlaybackParams = z2;
            return this;
        }

        public Builder setEnableFloatOutput(boolean z2) {
            this.enableFloatOutput = z2;
            return this;
        }

        public Builder setOffloadMode(int i3) {
            this.offloadMode = i3;
            return this;
        }
    }

    public static final class Configuration {
        public final AudioProcessor[] availableAudioProcessors;
        public final int bufferSize;
        public final Format inputFormat;
        public final int inputPcmFrameSize;
        public final int outputChannelConfig;
        public final int outputEncoding;
        public final int outputMode;
        public final int outputPcmFrameSize;
        public final int outputSampleRate;

        public Configuration(Format format, int i3, int i4, int i5, int i6, int i7, int i8, int i9, AudioProcessor[] audioProcessorArr) {
            this.inputFormat = format;
            this.inputPcmFrameSize = i3;
            this.outputMode = i4;
            this.outputPcmFrameSize = i5;
            this.outputSampleRate = i6;
            this.outputChannelConfig = i7;
            this.outputEncoding = i8;
            this.bufferSize = i9;
            this.availableAudioProcessors = audioProcessorArr;
        }

        private AudioTrack createAudioTrack(boolean z2, AudioAttributes audioAttributes, int i3) {
            int i4 = Util.SDK_INT;
            return i4 >= 29 ? createAudioTrackV29(z2, audioAttributes, i3) : i4 >= 21 ? createAudioTrackV21(z2, audioAttributes, i3) : createAudioTrackV9(audioAttributes, i3);
        }

        @RequiresApi(21)
        private AudioTrack createAudioTrackV21(boolean z2, AudioAttributes audioAttributes, int i3) {
            return new AudioTrack(getAudioTrackAttributesV21(audioAttributes, z2), DefaultAudioSink.getAudioFormat(this.outputSampleRate, this.outputChannelConfig, this.outputEncoding), this.bufferSize, 1, i3);
        }

        @RequiresApi(29)
        private AudioTrack createAudioTrackV29(boolean z2, AudioAttributes audioAttributes, int i3) {
            AudioFormat access$1600 = DefaultAudioSink.getAudioFormat(this.outputSampleRate, this.outputChannelConfig, this.outputEncoding);
            android.media.AudioAttributes audioTrackAttributesV21 = getAudioTrackAttributesV21(audioAttributes, z2);
            boolean z3 = true;
            AudioTrack.Builder sessionId = new AudioTrack.Builder().setAudioAttributes(audioTrackAttributesV21).setAudioFormat(access$1600).setTransferMode(1).setBufferSizeInBytes(this.bufferSize).setSessionId(i3);
            if (this.outputMode != 1) {
                z3 = false;
            }
            return sessionId.setOffloadedPlayback(z3).build();
        }

        private AudioTrack createAudioTrackV9(AudioAttributes audioAttributes, int i3) {
            int streamTypeForAudioUsage = Util.getStreamTypeForAudioUsage(audioAttributes.usage);
            return i3 == 0 ? new AudioTrack(streamTypeForAudioUsage, this.outputSampleRate, this.outputChannelConfig, this.outputEncoding, this.bufferSize, 1) : new AudioTrack(streamTypeForAudioUsage, this.outputSampleRate, this.outputChannelConfig, this.outputEncoding, this.bufferSize, 1, i3);
        }

        @RequiresApi(21)
        private static android.media.AudioAttributes getAudioTrackAttributesV21(AudioAttributes audioAttributes, boolean z2) {
            return z2 ? getAudioTrackTunnelingAttributesV21() : audioAttributes.getAudioAttributesV21().audioAttributes;
        }

        @RequiresApi(21)
        private static android.media.AudioAttributes getAudioTrackTunnelingAttributesV21() {
            return new AudioAttributes.Builder().setContentType(3).setFlags(16).setUsage(1).build();
        }

        public AudioTrack buildAudioTrack(boolean z2, com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes audioAttributes, int i3) throws AudioSink.InitializationException {
            try {
                AudioTrack createAudioTrack = createAudioTrack(z2, audioAttributes, i3);
                int state = createAudioTrack.getState();
                if (state == 1) {
                    return createAudioTrack;
                }
                try {
                    createAudioTrack.release();
                } catch (Exception unused) {
                }
                throw new AudioSink.InitializationException(state, this.outputSampleRate, this.outputChannelConfig, this.bufferSize, this.inputFormat, outputModeIsOffload(), (Exception) null);
            } catch (IllegalArgumentException | UnsupportedOperationException e3) {
                throw new AudioSink.InitializationException(0, this.outputSampleRate, this.outputChannelConfig, this.bufferSize, this.inputFormat, outputModeIsOffload(), e3);
            }
        }

        public boolean canReuseAudioTrack(Configuration configuration) {
            return configuration.outputMode == this.outputMode && configuration.outputEncoding == this.outputEncoding && configuration.outputSampleRate == this.outputSampleRate && configuration.outputChannelConfig == this.outputChannelConfig && configuration.outputPcmFrameSize == this.outputPcmFrameSize;
        }

        public Configuration copyWithBufferSize(int i3) {
            return new Configuration(this.inputFormat, this.inputPcmFrameSize, this.outputMode, this.outputPcmFrameSize, this.outputSampleRate, this.outputChannelConfig, this.outputEncoding, i3, this.availableAudioProcessors);
        }

        public long framesToDurationUs(long j2) {
            return (j2 * 1000000) / ((long) this.outputSampleRate);
        }

        public long inputFramesToDurationUs(long j2) {
            return (j2 * 1000000) / ((long) this.inputFormat.sampleRate);
        }

        public boolean outputModeIsOffload() {
            return this.outputMode == 1;
        }
    }

    public static class DefaultAudioProcessorChain implements AudioProcessorChain {
        private final AudioProcessor[] audioProcessors;
        private final SilenceSkippingAudioProcessor silenceSkippingAudioProcessor;
        private final SonicAudioProcessor sonicAudioProcessor;

        public DefaultAudioProcessorChain(AudioProcessor... audioProcessorArr) {
            this(audioProcessorArr, new SilenceSkippingAudioProcessor(), new SonicAudioProcessor());
        }

        public PlaybackParameters applyPlaybackParameters(PlaybackParameters playbackParameters) {
            this.sonicAudioProcessor.setSpeed(playbackParameters.speed);
            this.sonicAudioProcessor.setPitch(playbackParameters.pitch);
            return playbackParameters;
        }

        public boolean applySkipSilenceEnabled(boolean z2) {
            this.silenceSkippingAudioProcessor.setEnabled(z2);
            return z2;
        }

        public AudioProcessor[] getAudioProcessors() {
            return this.audioProcessors;
        }

        public long getMediaDuration(long j2) {
            return this.sonicAudioProcessor.getMediaDuration(j2);
        }

        public long getSkippedOutputFrameCount() {
            return this.silenceSkippingAudioProcessor.getSkippedFrames();
        }

        public DefaultAudioProcessorChain(AudioProcessor[] audioProcessorArr, SilenceSkippingAudioProcessor silenceSkippingAudioProcessor2, SonicAudioProcessor sonicAudioProcessor2) {
            AudioProcessor[] audioProcessorArr2 = new AudioProcessor[(audioProcessorArr.length + 2)];
            this.audioProcessors = audioProcessorArr2;
            System.arraycopy(audioProcessorArr, 0, audioProcessorArr2, 0, audioProcessorArr.length);
            this.silenceSkippingAudioProcessor = silenceSkippingAudioProcessor2;
            this.sonicAudioProcessor = sonicAudioProcessor2;
            audioProcessorArr2[audioProcessorArr.length] = silenceSkippingAudioProcessor2;
            audioProcessorArr2[audioProcessorArr.length + 1] = sonicAudioProcessor2;
        }
    }

    public static final class InvalidAudioTrackTimestampException extends RuntimeException {
        private InvalidAudioTrackTimestampException(String str) {
            super(str);
        }
    }

    public static final class MediaPositionParameters {
        public final long audioTrackPositionUs;
        public final long mediaTimeUs;
        public final PlaybackParameters playbackParameters;
        public final boolean skipSilence;

        private MediaPositionParameters(PlaybackParameters playbackParameters2, boolean z2, long j2, long j3) {
            this.playbackParameters = playbackParameters2;
            this.skipSilence = z2;
            this.mediaTimeUs = j2;
            this.audioTrackPositionUs = j3;
        }
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OffloadMode {
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OutputMode {
    }

    public static final class PendingExceptionHolder<T extends Exception> {
        @Nullable
        private T pendingException;
        private long throwDeadlineMs;
        private final long throwDelayMs;

        public PendingExceptionHolder(long j2) {
            this.throwDelayMs = j2;
        }

        public void clear() {
            this.pendingException = null;
        }

        public void throwExceptionIfDeadlineIsReached(T t2) throws Exception {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (this.pendingException == null) {
                this.pendingException = t2;
                this.throwDeadlineMs = this.throwDelayMs + elapsedRealtime;
            }
            if (elapsedRealtime >= this.throwDeadlineMs) {
                T t3 = this.pendingException;
                if (t3 != t2) {
                    t3.addSuppressed(t2);
                }
                T t4 = this.pendingException;
                clear();
                throw t4;
            }
        }
    }

    public final class PositionTrackerListener implements AudioTrackPositionTracker.Listener {
        private PositionTrackerListener() {
        }

        public void onInvalidLatency(long j2) {
            Log.w(DefaultAudioSink.TAG, "Ignoring impossibly large audio latency: " + j2);
        }

        public void onPositionAdvancing(long j2) {
            if (DefaultAudioSink.this.listener != null) {
                DefaultAudioSink.this.listener.onPositionAdvancing(j2);
            }
        }

        public void onPositionFramesMismatch(long j2, long j3, long j4, long j5) {
            StringBuilder u3 = a.u("Spurious audio timestamp (frame position mismatch): ", j2, ", ");
            u3.append(j3);
            u3.append(", ");
            u3.append(j4);
            u3.append(", ");
            u3.append(j5);
            u3.append(", ");
            u3.append(DefaultAudioSink.this.getSubmittedFrames());
            u3.append(", ");
            u3.append(DefaultAudioSink.this.getWrittenFrames());
            String sb = u3.toString();
            if (!DefaultAudioSink.failOnSpuriousAudioTimestamp) {
                Log.w(DefaultAudioSink.TAG, sb);
                return;
            }
            throw new InvalidAudioTrackTimestampException(sb);
        }

        public void onSystemTimeUsMismatch(long j2, long j3, long j4, long j5) {
            StringBuilder u3 = a.u("Spurious audio timestamp (system clock mismatch): ", j2, ", ");
            u3.append(j3);
            u3.append(", ");
            u3.append(j4);
            u3.append(", ");
            u3.append(j5);
            u3.append(", ");
            u3.append(DefaultAudioSink.this.getSubmittedFrames());
            u3.append(", ");
            u3.append(DefaultAudioSink.this.getWrittenFrames());
            String sb = u3.toString();
            if (!DefaultAudioSink.failOnSpuriousAudioTimestamp) {
                Log.w(DefaultAudioSink.TAG, sb);
                return;
            }
            throw new InvalidAudioTrackTimestampException(sb);
        }

        public void onUnderrun(int i3, long j2) {
            if (DefaultAudioSink.this.listener != null) {
                DefaultAudioSink.this.listener.onUnderrun(i3, j2, SystemClock.elapsedRealtime() - DefaultAudioSink.this.lastFeedElapsedRealtimeMs);
            }
        }
    }

    @RequiresApi(29)
    public final class StreamEventCallbackV29 {
        private final AudioTrack.StreamEventCallback callback;
        private final Handler handler = new Handler();

        public StreamEventCallbackV29() {
            this.callback = new AudioTrack.StreamEventCallback(DefaultAudioSink.this) {
                public void onDataRequest(AudioTrack audioTrack, int i3) {
                    Assertions.checkState(audioTrack == DefaultAudioSink.this.audioTrack);
                    if (DefaultAudioSink.this.listener != null && DefaultAudioSink.this.playing) {
                        DefaultAudioSink.this.listener.onOffloadBufferEmptying();
                    }
                }

                public void onTearDown(AudioTrack audioTrack) {
                    Assertions.checkState(audioTrack == DefaultAudioSink.this.audioTrack);
                    if (DefaultAudioSink.this.listener != null && DefaultAudioSink.this.playing) {
                        DefaultAudioSink.this.listener.onOffloadBufferEmptying();
                    }
                }
            };
        }

        public void register(AudioTrack audioTrack) {
            Handler handler2 = this.handler;
            Objects.requireNonNull(handler2);
            audioTrack.registerStreamEventCallback(new androidx.emoji2.text.a(handler2), this.callback);
        }

        public void unregister(AudioTrack audioTrack) {
            audioTrack.unregisterStreamEventCallback(this.callback);
            this.handler.removeCallbacksAndMessages((Object) null);
        }
    }

    private void applyAudioProcessorPlaybackParametersAndSkipSilence(long j2) {
        PlaybackParameters applyPlaybackParameters = shouldApplyAudioProcessorPlaybackParameters() ? this.audioProcessorChain.applyPlaybackParameters(getAudioProcessorPlaybackParameters()) : PlaybackParameters.DEFAULT;
        boolean applySkipSilenceEnabled = shouldApplyAudioProcessorPlaybackParameters() ? this.audioProcessorChain.applySkipSilenceEnabled(getSkipSilenceEnabled()) : false;
        this.mediaPositionParametersCheckpoints.add(new MediaPositionParameters(applyPlaybackParameters, applySkipSilenceEnabled, Math.max(0, j2), this.configuration.framesToDurationUs(getWrittenFrames())));
        setupAudioProcessors();
        AudioSink.Listener listener2 = this.listener;
        if (listener2 != null) {
            listener2.onSkipSilenceEnabledChanged(applySkipSilenceEnabled);
        }
    }

    private long applyMediaPositionParameters(long j2) {
        while (!this.mediaPositionParametersCheckpoints.isEmpty() && j2 >= this.mediaPositionParametersCheckpoints.getFirst().audioTrackPositionUs) {
            this.mediaPositionParameters = this.mediaPositionParametersCheckpoints.remove();
        }
        MediaPositionParameters mediaPositionParameters2 = this.mediaPositionParameters;
        long j3 = j2 - mediaPositionParameters2.audioTrackPositionUs;
        if (mediaPositionParameters2.playbackParameters.equals(PlaybackParameters.DEFAULT)) {
            return this.mediaPositionParameters.mediaTimeUs + j3;
        }
        if (this.mediaPositionParametersCheckpoints.isEmpty()) {
            return this.mediaPositionParameters.mediaTimeUs + this.audioProcessorChain.getMediaDuration(j3);
        }
        MediaPositionParameters first = this.mediaPositionParametersCheckpoints.getFirst();
        return first.mediaTimeUs - Util.getMediaDurationForPlayoutDuration(first.audioTrackPositionUs - j2, this.mediaPositionParameters.playbackParameters.speed);
    }

    private long applySkipping(long j2) {
        return j2 + this.configuration.framesToDurationUs(this.audioProcessorChain.getSkippedOutputFrameCount());
    }

    private AudioTrack buildAudioTrack(Configuration configuration2) throws AudioSink.InitializationException {
        try {
            return configuration2.buildAudioTrack(this.tunneling, this.audioAttributes, this.audioSessionId);
        } catch (AudioSink.InitializationException e3) {
            AudioSink.Listener listener2 = this.listener;
            if (listener2 != null) {
                listener2.onAudioSinkError(e3);
            }
            throw e3;
        }
    }

    private AudioTrack buildAudioTrackWithRetry() throws AudioSink.InitializationException {
        try {
            return buildAudioTrack((Configuration) Assertions.checkNotNull(this.configuration));
        } catch (AudioSink.InitializationException e3) {
            Configuration configuration2 = this.configuration;
            if (configuration2.bufferSize > 1000000) {
                Configuration copyWithBufferSize = configuration2.copyWithBufferSize(1000000);
                try {
                    AudioTrack buildAudioTrack = buildAudioTrack(copyWithBufferSize);
                    this.configuration = copyWithBufferSize;
                    return buildAudioTrack;
                } catch (AudioSink.InitializationException e4) {
                    e3.addSuppressed(e4);
                    maybeDisableOffload();
                    throw e3;
                }
            }
            maybeDisableOffload();
            throw e3;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0018  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean drainToEndOfStream() throws com.appsamurai.storyly.exoplayer2.core.audio.AudioSink.WriteException {
        /*
            r9 = this;
            int r0 = r9.drainingAudioProcessorIndex
            r1 = 1
            r2 = 0
            r3 = -1
            if (r0 != r3) goto L_0x000b
            r9.drainingAudioProcessorIndex = r2
        L_0x0009:
            r0 = r1
            goto L_0x000c
        L_0x000b:
            r0 = r2
        L_0x000c:
            int r4 = r9.drainingAudioProcessorIndex
            com.appsamurai.storyly.exoplayer2.core.audio.AudioProcessor[] r5 = r9.activeAudioProcessors
            int r6 = r5.length
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r4 >= r6) goto L_0x002f
            r4 = r5[r4]
            if (r0 == 0) goto L_0x001f
            r4.queueEndOfStream()
        L_0x001f:
            r9.processBuffers(r7)
            boolean r0 = r4.isEnded()
            if (r0 != 0) goto L_0x0029
            return r2
        L_0x0029:
            int r0 = r9.drainingAudioProcessorIndex
            int r0 = r0 + r1
            r9.drainingAudioProcessorIndex = r0
            goto L_0x0009
        L_0x002f:
            java.nio.ByteBuffer r0 = r9.outputBuffer
            if (r0 == 0) goto L_0x003b
            r9.writeBuffer(r0, r7)
            java.nio.ByteBuffer r0 = r9.outputBuffer
            if (r0 == 0) goto L_0x003b
            return r2
        L_0x003b:
            r9.drainingAudioProcessorIndex = r3
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.audio.DefaultAudioSink.drainToEndOfStream():boolean");
    }

    private void flushAudioProcessors() {
        int i3 = 0;
        while (true) {
            AudioProcessor[] audioProcessorArr = this.activeAudioProcessors;
            if (i3 < audioProcessorArr.length) {
                AudioProcessor audioProcessor = audioProcessorArr[i3];
                audioProcessor.flush();
                this.outputBuffers[i3] = audioProcessor.getOutput();
                i3++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    @RequiresApi(21)
    public static AudioFormat getAudioFormat(int i3, int i4, int i5) {
        return new AudioFormat.Builder().setSampleRate(i3).setChannelMask(i4).setEncoding(i5).build();
    }

    private PlaybackParameters getAudioProcessorPlaybackParameters() {
        return getMediaPositionParameters().playbackParameters;
    }

    private static int getAudioTrackMinBufferSize(int i3, int i4, int i5) {
        int minBufferSize = AudioTrack.getMinBufferSize(i3, i4, i5);
        Assertions.checkState(minBufferSize != -2);
        return minBufferSize;
    }

    private static int getFramesPerEncodedSample(int i3, ByteBuffer byteBuffer) {
        switch (i3) {
            case 5:
            case 6:
            case 18:
                return Ac3Util.parseAc3SyncframeAudioSampleCount(byteBuffer);
            case 7:
            case 8:
                return DtsUtil.parseDtsAudioSampleCount(byteBuffer);
            case 9:
                int parseMpegAudioFrameSampleCount = MpegAudioUtil.parseMpegAudioFrameSampleCount(Util.getBigEndianInt(byteBuffer, byteBuffer.position()));
                if (parseMpegAudioFrameSampleCount != -1) {
                    return parseMpegAudioFrameSampleCount;
                }
                throw new IllegalArgumentException();
            case 10:
                return 1024;
            case 11:
            case 12:
                return 2048;
            case 14:
                int findTrueHdSyncframeOffset = Ac3Util.findTrueHdSyncframeOffset(byteBuffer);
                if (findTrueHdSyncframeOffset == -1) {
                    return 0;
                }
                return Ac3Util.parseTrueHdSyncframeAudioSampleCount(byteBuffer, findTrueHdSyncframeOffset) * 16;
            case 15:
                return 512;
            case 16:
                return 1024;
            case 17:
                return Ac4Util.parseAc4SyncframeAudioSampleCount(byteBuffer);
            default:
                throw new IllegalStateException(A.a.k("Unexpected audio encoding: ", i3));
        }
    }

    private MediaPositionParameters getMediaPositionParameters() {
        MediaPositionParameters mediaPositionParameters2 = this.afterDrainParameters;
        return mediaPositionParameters2 != null ? mediaPositionParameters2 : !this.mediaPositionParametersCheckpoints.isEmpty() ? this.mediaPositionParametersCheckpoints.getLast() : this.mediaPositionParameters;
    }

    @RequiresApi(29)
    @SuppressLint({"InlinedApi"})
    private int getOffloadedPlaybackSupport(AudioFormat audioFormat, android.media.AudioAttributes audioAttributes2) {
        int i3 = Util.SDK_INT;
        if (i3 >= 31) {
            return AudioManager.getPlaybackOffloadSupport(audioFormat, audioAttributes2);
        }
        if (!AudioManager.isOffloadedPlaybackSupported(audioFormat, audioAttributes2)) {
            return 0;
        }
        return (i3 != 30 || !Util.MODEL.startsWith("Pixel")) ? 1 : 2;
    }

    /* access modifiers changed from: private */
    public long getSubmittedFrames() {
        Configuration configuration2 = this.configuration;
        return configuration2.outputMode == 0 ? this.submittedPcmBytes / ((long) configuration2.inputPcmFrameSize) : this.submittedEncodedFrames;
    }

    /* access modifiers changed from: private */
    public long getWrittenFrames() {
        Configuration configuration2 = this.configuration;
        return configuration2.outputMode == 0 ? this.writtenPcmBytes / ((long) configuration2.outputPcmFrameSize) : this.writtenEncodedFrames;
    }

    private void initializeAudioTrack() throws AudioSink.InitializationException {
        PlayerId playerId2;
        this.releasingConditionVariable.block();
        AudioTrack buildAudioTrackWithRetry = buildAudioTrackWithRetry();
        this.audioTrack = buildAudioTrackWithRetry;
        if (isOffloadedPlayback(buildAudioTrackWithRetry)) {
            registerStreamEventCallbackV29(this.audioTrack);
            if (this.offloadMode != 3) {
                AudioTrack audioTrack2 = this.audioTrack;
                Format format = this.configuration.inputFormat;
                audioTrack2.setOffloadDelayPadding(format.encoderDelay, format.encoderPadding);
            }
        }
        if (Util.SDK_INT >= 31 && (playerId2 = this.playerId) != null) {
            Api31.setLogSessionIdOnAudioTrack(this.audioTrack, playerId2);
        }
        this.audioSessionId = this.audioTrack.getAudioSessionId();
        AudioTrackPositionTracker audioTrackPositionTracker2 = this.audioTrackPositionTracker;
        AudioTrack audioTrack3 = this.audioTrack;
        Configuration configuration2 = this.configuration;
        audioTrackPositionTracker2.setAudioTrack(audioTrack3, configuration2.outputMode == 2, configuration2.outputEncoding, configuration2.outputPcmFrameSize, configuration2.bufferSize);
        setVolumeInternal();
        int i3 = this.auxEffectInfo.effectId;
        if (i3 != 0) {
            this.audioTrack.attachAuxEffect(i3);
            this.audioTrack.setAuxEffectSendLevel(this.auxEffectInfo.sendLevel);
        }
        this.startMediaTimeUsNeedsInit = true;
    }

    private static boolean isAudioTrackDeadObject(int i3) {
        return (Util.SDK_INT >= 24 && i3 == -6) || i3 == ERROR_NATIVE_DEAD_OBJECT;
    }

    private boolean isAudioTrackInitialized() {
        return this.audioTrack != null;
    }

    private static boolean isOffloadedPlayback(AudioTrack audioTrack2) {
        return Util.SDK_INT >= 29 && audioTrack2.isOffloadedPlayback();
    }

    private void maybeDisableOffload() {
        if (this.configuration.outputModeIsOffload()) {
            this.offloadDisabledUntilNextConfiguration = true;
        }
    }

    private void playPendingData() {
        if (!this.stoppedAudioTrack) {
            this.stoppedAudioTrack = true;
            this.audioTrackPositionTracker.handleEndOfStream(getWrittenFrames());
            this.audioTrack.stop();
            this.bytesUntilNextAvSync = 0;
        }
    }

    private void processBuffers(long j2) throws AudioSink.WriteException {
        ByteBuffer byteBuffer;
        int length = this.activeAudioProcessors.length;
        int i3 = length;
        while (i3 >= 0) {
            if (i3 > 0) {
                byteBuffer = this.outputBuffers[i3 - 1];
            } else {
                byteBuffer = this.inputBuffer;
                if (byteBuffer == null) {
                    byteBuffer = AudioProcessor.EMPTY_BUFFER;
                }
            }
            if (i3 == length) {
                writeBuffer(byteBuffer, j2);
            } else {
                AudioProcessor audioProcessor = this.activeAudioProcessors[i3];
                if (i3 > this.drainingAudioProcessorIndex) {
                    audioProcessor.queueInput(byteBuffer);
                }
                ByteBuffer output = audioProcessor.getOutput();
                this.outputBuffers[i3] = output;
                if (output.hasRemaining()) {
                    i3++;
                }
            }
            if (!byteBuffer.hasRemaining()) {
                i3--;
            } else {
                return;
            }
        }
    }

    @RequiresApi(29)
    private void registerStreamEventCallbackV29(AudioTrack audioTrack2) {
        if (this.offloadStreamEventCallbackV29 == null) {
            this.offloadStreamEventCallbackV29 = new StreamEventCallbackV29();
        }
        this.offloadStreamEventCallbackV29.register(audioTrack2);
    }

    private void resetSinkStateForFlush() {
        this.submittedPcmBytes = 0;
        this.submittedEncodedFrames = 0;
        this.writtenPcmBytes = 0;
        this.writtenEncodedFrames = 0;
        this.isWaitingForOffloadEndOfStreamHandled = false;
        this.framesPerEncodedSample = 0;
        this.mediaPositionParameters = new MediaPositionParameters(getAudioProcessorPlaybackParameters(), getSkipSilenceEnabled(), 0, 0);
        this.startMediaTimeUs = 0;
        this.afterDrainParameters = null;
        this.mediaPositionParametersCheckpoints.clear();
        this.inputBuffer = null;
        this.inputBufferAccessUnitCount = 0;
        this.outputBuffer = null;
        this.stoppedAudioTrack = false;
        this.handledEndOfStream = false;
        this.drainingAudioProcessorIndex = -1;
        this.avSyncHeader = null;
        this.bytesUntilNextAvSync = 0;
        this.trimmingAudioProcessor.resetTrimmedFrameCount();
        flushAudioProcessors();
    }

    private void setAudioProcessorPlaybackParametersAndSkipSilence(PlaybackParameters playbackParameters, boolean z2) {
        MediaPositionParameters mediaPositionParameters2 = getMediaPositionParameters();
        if (!playbackParameters.equals(mediaPositionParameters2.playbackParameters) || z2 != mediaPositionParameters2.skipSilence) {
            MediaPositionParameters mediaPositionParameters3 = new MediaPositionParameters(playbackParameters, z2, C.TIME_UNSET, C.TIME_UNSET);
            if (isAudioTrackInitialized()) {
                this.afterDrainParameters = mediaPositionParameters3;
            } else {
                this.mediaPositionParameters = mediaPositionParameters3;
            }
        }
    }

    @RequiresApi(23)
    private void setAudioTrackPlaybackParametersV23(PlaybackParameters playbackParameters) {
        if (isAudioTrackInitialized()) {
            try {
                this.audioTrack.setPlaybackParams(new PlaybackParams().allowDefaults().setSpeed(playbackParameters.speed).setPitch(playbackParameters.pitch).setAudioFallbackMode(2));
            } catch (IllegalArgumentException e3) {
                Log.w(TAG, "Failed to set playback params", e3);
            }
            playbackParameters = new PlaybackParameters(this.audioTrack.getPlaybackParams().getSpeed(), this.audioTrack.getPlaybackParams().getPitch());
            this.audioTrackPositionTracker.setAudioTrackPlaybackSpeed(playbackParameters.speed);
        }
        this.audioTrackPlaybackParameters = playbackParameters;
    }

    private void setVolumeInternal() {
        if (isAudioTrackInitialized()) {
            if (Util.SDK_INT >= 21) {
                setVolumeInternalV21(this.audioTrack, this.volume);
            } else {
                setVolumeInternalV3(this.audioTrack, this.volume);
            }
        }
    }

    @RequiresApi(21)
    private static void setVolumeInternalV21(AudioTrack audioTrack2, float f2) {
        audioTrack2.setVolume(f2);
    }

    private static void setVolumeInternalV3(AudioTrack audioTrack2, float f2) {
        audioTrack2.setStereoVolume(f2, f2);
    }

    private void setupAudioProcessors() {
        AudioProcessor[] audioProcessorArr = this.configuration.availableAudioProcessors;
        ArrayList arrayList = new ArrayList();
        for (AudioProcessor audioProcessor : audioProcessorArr) {
            if (audioProcessor.isActive()) {
                arrayList.add(audioProcessor);
            } else {
                audioProcessor.flush();
            }
        }
        int size = arrayList.size();
        this.activeAudioProcessors = (AudioProcessor[]) arrayList.toArray(new AudioProcessor[size]);
        this.outputBuffers = new ByteBuffer[size];
        flushAudioProcessors();
    }

    private boolean shouldApplyAudioProcessorPlaybackParameters() {
        return !this.tunneling && MimeTypes.AUDIO_RAW.equals(this.configuration.inputFormat.sampleMimeType) && !shouldUseFloatOutput(this.configuration.inputFormat.pcmEncoding);
    }

    private boolean shouldUseFloatOutput(int i3) {
        return this.enableFloatOutput && Util.isEncodingHighResolutionPcm(i3);
    }

    private boolean useOffloadedPlayback(Format format, com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes audioAttributes2) {
        int encoding;
        int audioTrackChannelConfig;
        int offloadedPlaybackSupport;
        if (Util.SDK_INT < 29 || this.offloadMode == 0 || (encoding = MimeTypes.getEncoding((String) Assertions.checkNotNull(format.sampleMimeType), format.codecs)) == 0 || (audioTrackChannelConfig = Util.getAudioTrackChannelConfig(format.channelCount)) == 0 || (offloadedPlaybackSupport = getOffloadedPlaybackSupport(getAudioFormat(format.sampleRate, audioTrackChannelConfig, encoding), audioAttributes2.getAudioAttributesV21().audioAttributes)) == 0) {
            return false;
        }
        if (offloadedPlaybackSupport == 1) {
            return !(format.encoderDelay != 0 || format.encoderPadding != 0) || !(this.offloadMode == 1);
        } else if (offloadedPlaybackSupport == 2) {
            return true;
        } else {
            throw new IllegalStateException();
        }
    }

    private void writeBuffer(ByteBuffer byteBuffer, long j2) throws AudioSink.WriteException {
        int i3;
        AudioSink.Listener listener2;
        if (byteBuffer.hasRemaining()) {
            ByteBuffer byteBuffer2 = this.outputBuffer;
            boolean z2 = true;
            if (byteBuffer2 != null) {
                Assertions.checkArgument(byteBuffer2 == byteBuffer);
            } else {
                this.outputBuffer = byteBuffer;
                if (Util.SDK_INT < 21) {
                    int remaining = byteBuffer.remaining();
                    byte[] bArr = this.preV21OutputBuffer;
                    if (bArr == null || bArr.length < remaining) {
                        this.preV21OutputBuffer = new byte[remaining];
                    }
                    int position = byteBuffer.position();
                    byteBuffer.get(this.preV21OutputBuffer, 0, remaining);
                    byteBuffer.position(position);
                    this.preV21OutputBufferOffset = 0;
                }
            }
            int remaining2 = byteBuffer.remaining();
            if (Util.SDK_INT < 21) {
                int availableBufferSize = this.audioTrackPositionTracker.getAvailableBufferSize(this.writtenPcmBytes);
                if (availableBufferSize > 0) {
                    i3 = this.audioTrack.write(this.preV21OutputBuffer, this.preV21OutputBufferOffset, Math.min(remaining2, availableBufferSize));
                    if (i3 > 0) {
                        this.preV21OutputBufferOffset += i3;
                        byteBuffer.position(byteBuffer.position() + i3);
                    }
                } else {
                    i3 = 0;
                }
            } else if (this.tunneling) {
                Assertions.checkState(j2 != C.TIME_UNSET);
                i3 = writeNonBlockingWithAvSyncV21(this.audioTrack, byteBuffer, remaining2, j2);
            } else {
                i3 = writeNonBlockingV21(this.audioTrack, byteBuffer, remaining2);
            }
            this.lastFeedElapsedRealtimeMs = SystemClock.elapsedRealtime();
            if (i3 < 0) {
                boolean isAudioTrackDeadObject = isAudioTrackDeadObject(i3);
                if (isAudioTrackDeadObject) {
                    maybeDisableOffload();
                }
                AudioSink.WriteException writeException = new AudioSink.WriteException(i3, this.configuration.inputFormat, isAudioTrackDeadObject);
                AudioSink.Listener listener3 = this.listener;
                if (listener3 != null) {
                    listener3.onAudioSinkError(writeException);
                }
                if (!writeException.isRecoverable) {
                    this.writeExceptionPendingExceptionHolder.throwExceptionIfDeadlineIsReached(writeException);
                    return;
                }
                throw writeException;
            }
            this.writeExceptionPendingExceptionHolder.clear();
            if (isOffloadedPlayback(this.audioTrack)) {
                if (this.writtenEncodedFrames > 0) {
                    this.isWaitingForOffloadEndOfStreamHandled = false;
                }
                if (this.playing && (listener2 = this.listener) != null && i3 < remaining2 && !this.isWaitingForOffloadEndOfStreamHandled) {
                    listener2.onOffloadBufferFull();
                }
            }
            int i4 = this.configuration.outputMode;
            if (i4 == 0) {
                this.writtenPcmBytes += (long) i3;
            }
            if (i3 == remaining2) {
                if (i4 != 0) {
                    if (byteBuffer != this.inputBuffer) {
                        z2 = false;
                    }
                    Assertions.checkState(z2);
                    this.writtenEncodedFrames = (((long) this.framesPerEncodedSample) * ((long) this.inputBufferAccessUnitCount)) + this.writtenEncodedFrames;
                }
                this.outputBuffer = null;
            }
        }
    }

    @RequiresApi(21)
    private static int writeNonBlockingV21(AudioTrack audioTrack2, ByteBuffer byteBuffer, int i3) {
        return audioTrack2.write(byteBuffer, i3, 1);
    }

    @RequiresApi(21)
    private int writeNonBlockingWithAvSyncV21(AudioTrack audioTrack2, ByteBuffer byteBuffer, int i3, long j2) {
        if (Util.SDK_INT >= 26) {
            return audioTrack2.write(byteBuffer, i3, 1, j2 * 1000);
        }
        if (this.avSyncHeader == null) {
            ByteBuffer allocate = ByteBuffer.allocate(16);
            this.avSyncHeader = allocate;
            allocate.order(ByteOrder.BIG_ENDIAN);
            this.avSyncHeader.putInt(1431633921);
        }
        if (this.bytesUntilNextAvSync == 0) {
            this.avSyncHeader.putInt(4, i3);
            this.avSyncHeader.putLong(8, j2 * 1000);
            this.avSyncHeader.position(0);
            this.bytesUntilNextAvSync = i3;
        }
        int remaining = this.avSyncHeader.remaining();
        if (remaining > 0) {
            int write = audioTrack2.write(this.avSyncHeader, remaining, 1);
            if (write < 0) {
                this.bytesUntilNextAvSync = 0;
                return write;
            } else if (write < remaining) {
                return 0;
            }
        }
        int writeNonBlockingV21 = writeNonBlockingV21(audioTrack2, byteBuffer, i3);
        if (writeNonBlockingV21 < 0) {
            this.bytesUntilNextAvSync = 0;
            return writeNonBlockingV21;
        }
        this.bytesUntilNextAvSync -= writeNonBlockingV21;
        return writeNonBlockingV21;
    }

    public void configure(Format format, int i3, @Nullable int[] iArr) throws AudioSink.ConfigurationException {
        AudioProcessor[] audioProcessorArr;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int[] iArr2;
        Format format2 = format;
        if (MimeTypes.AUDIO_RAW.equals(format2.sampleMimeType)) {
            Assertions.checkArgument(Util.isEncodingLinearPcm(format2.pcmEncoding));
            int pcmFrameSize = Util.getPcmFrameSize(format2.pcmEncoding, format2.channelCount);
            AudioProcessor[] audioProcessorArr2 = shouldUseFloatOutput(format2.pcmEncoding) ? this.toFloatPcmAvailableAudioProcessors : this.toIntPcmAvailableAudioProcessors;
            this.trimmingAudioProcessor.setTrimFrameCount(format2.encoderDelay, format2.encoderPadding);
            if (Util.SDK_INT < 21 && format2.channelCount == 8 && iArr == null) {
                iArr2 = new int[6];
                for (int i13 = 0; i13 < 6; i13++) {
                    iArr2[i13] = i13;
                }
            } else {
                iArr2 = iArr;
            }
            this.channelMappingAudioProcessor.setChannelMap(iArr2);
            AudioProcessor.AudioFormat audioFormat = new AudioProcessor.AudioFormat(format2.sampleRate, format2.channelCount, format2.pcmEncoding);
            int length = audioProcessorArr2.length;
            int i14 = 0;
            while (i14 < length) {
                AudioProcessor audioProcessor = audioProcessorArr2[i14];
                try {
                    AudioProcessor.AudioFormat configure = audioProcessor.configure(audioFormat);
                    if (audioProcessor.isActive()) {
                        audioFormat = configure;
                    }
                    i14++;
                } catch (AudioProcessor.UnhandledAudioFormatException e3) {
                    throw new AudioSink.ConfigurationException((Throwable) e3, format2);
                }
            }
            int i15 = audioFormat.encoding;
            int i16 = audioFormat.sampleRate;
            int audioTrackChannelConfig = Util.getAudioTrackChannelConfig(audioFormat.channelCount);
            i7 = Util.getPcmFrameSize(i15, audioFormat.channelCount);
            audioProcessorArr = audioProcessorArr2;
            i5 = i15;
            i4 = i16;
            i6 = pcmFrameSize;
            i8 = 0;
            i9 = audioTrackChannelConfig;
        } else {
            AudioProcessor[] audioProcessorArr3 = new AudioProcessor[0];
            int i17 = format2.sampleRate;
            i6 = -1;
            if (useOffloadedPlayback(format2, this.audioAttributes)) {
                i12 = 1;
                audioProcessorArr = audioProcessorArr3;
                i4 = i17;
                i5 = MimeTypes.getEncoding((String) Assertions.checkNotNull(format2.sampleMimeType), format2.codecs);
                i7 = -1;
                i9 = Util.getAudioTrackChannelConfig(format2.channelCount);
            } else {
                Pair<Integer, Integer> encodingAndChannelConfigForPassthrough = this.audioCapabilities.getEncodingAndChannelConfigForPassthrough(format2);
                if (encodingAndChannelConfigForPassthrough != null) {
                    int intValue = ((Integer) encodingAndChannelConfigForPassthrough.first).intValue();
                    i12 = 2;
                    audioProcessorArr = audioProcessorArr3;
                    i4 = i17;
                    i9 = ((Integer) encodingAndChannelConfigForPassthrough.second).intValue();
                    i7 = -1;
                    i5 = intValue;
                } else {
                    throw new AudioSink.ConfigurationException("Unable to configure passthrough for: " + format2, format2);
                }
            }
            i8 = i12;
        }
        if (i3 != 0) {
            i11 = i3;
            i10 = i5;
        } else {
            i10 = i5;
            i11 = this.audioTrackBufferSizeProvider.getBufferSizeInBytes(getAudioTrackMinBufferSize(i4, i9, i5), i5, i8, i7, i4, this.enableAudioTrackPlaybackParams ? 8.0d : 1.0d);
        }
        if (i10 == 0) {
            throw new AudioSink.ConfigurationException("Invalid output encoding (mode=" + i8 + ") for: " + format2, format2);
        } else if (i9 != 0) {
            this.offloadDisabledUntilNextConfiguration = false;
            Configuration configuration2 = new Configuration(format, i6, i8, i7, i4, i9, i10, i11, audioProcessorArr);
            if (isAudioTrackInitialized()) {
                this.pendingConfiguration = configuration2;
            } else {
                this.configuration = configuration2;
            }
        } else {
            throw new AudioSink.ConfigurationException("Invalid output channel config (mode=" + i8 + ") for: " + format2, format2);
        }
    }

    public void disableTunneling() {
        if (this.tunneling) {
            this.tunneling = false;
            flush();
        }
    }

    public void enableTunnelingV21() {
        Assertions.checkState(Util.SDK_INT >= 21);
        Assertions.checkState(this.externalAudioSessionIdProvided);
        if (!this.tunneling) {
            this.tunneling = true;
            flush();
        }
    }

    public void experimentalFlushWithoutAudioTrackRelease() {
        if (Util.SDK_INT < 25) {
            flush();
            return;
        }
        this.writeExceptionPendingExceptionHolder.clear();
        this.initializationExceptionPendingExceptionHolder.clear();
        if (isAudioTrackInitialized()) {
            resetSinkStateForFlush();
            if (this.audioTrackPositionTracker.isPlaying()) {
                this.audioTrack.pause();
            }
            this.audioTrack.flush();
            this.audioTrackPositionTracker.reset();
            AudioTrackPositionTracker audioTrackPositionTracker2 = this.audioTrackPositionTracker;
            AudioTrack audioTrack2 = this.audioTrack;
            Configuration configuration2 = this.configuration;
            audioTrackPositionTracker2.setAudioTrack(audioTrack2, configuration2.outputMode == 2, configuration2.outputEncoding, configuration2.outputPcmFrameSize, configuration2.bufferSize);
            this.startMediaTimeUsNeedsInit = true;
        }
    }

    public void flush() {
        if (isAudioTrackInitialized()) {
            resetSinkStateForFlush();
            if (this.audioTrackPositionTracker.isPlaying()) {
                this.audioTrack.pause();
            }
            if (isOffloadedPlayback(this.audioTrack)) {
                ((StreamEventCallbackV29) Assertions.checkNotNull(this.offloadStreamEventCallbackV29)).unregister(this.audioTrack);
            }
            final AudioTrack audioTrack2 = this.audioTrack;
            this.audioTrack = null;
            if (Util.SDK_INT < 21 && !this.externalAudioSessionIdProvided) {
                this.audioSessionId = 0;
            }
            Configuration configuration2 = this.pendingConfiguration;
            if (configuration2 != null) {
                this.configuration = configuration2;
                this.pendingConfiguration = null;
            }
            this.audioTrackPositionTracker.reset();
            this.releasingConditionVariable.close();
            new Thread("ExoPlayer:AudioTrackReleaseThread") {
                public void run() {
                    try {
                        audioTrack2.flush();
                        audioTrack2.release();
                    } finally {
                        DefaultAudioSink.this.releasingConditionVariable.open();
                    }
                }
            }.start();
        }
        this.writeExceptionPendingExceptionHolder.clear();
        this.initializationExceptionPendingExceptionHolder.clear();
    }

    public com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes getAudioAttributes() {
        return this.audioAttributes;
    }

    public long getCurrentPositionUs(boolean z2) {
        if (!isAudioTrackInitialized() || this.startMediaTimeUsNeedsInit) {
            return Long.MIN_VALUE;
        }
        return applySkipping(applyMediaPositionParameters(Math.min(this.audioTrackPositionTracker.getCurrentPositionUs(z2), this.configuration.framesToDurationUs(getWrittenFrames()))));
    }

    public int getFormatSupport(Format format) {
        if (!MimeTypes.AUDIO_RAW.equals(format.sampleMimeType)) {
            return ((this.offloadDisabledUntilNextConfiguration || !useOffloadedPlayback(format, this.audioAttributes)) && !this.audioCapabilities.isPassthroughPlaybackSupported(format)) ? 0 : 2;
        }
        if (!Util.isEncodingLinearPcm(format.pcmEncoding)) {
            Log.w(TAG, "Invalid PCM encoding: " + format.pcmEncoding);
            return 0;
        }
        int i3 = format.pcmEncoding;
        return (i3 == 2 || (this.enableFloatOutput && i3 == 4)) ? 2 : 1;
    }

    public PlaybackParameters getPlaybackParameters() {
        return this.enableAudioTrackPlaybackParams ? this.audioTrackPlaybackParameters : getAudioProcessorPlaybackParameters();
    }

    public boolean getSkipSilenceEnabled() {
        return getMediaPositionParameters().skipSilence;
    }

    public boolean handleBuffer(ByteBuffer byteBuffer, long j2, int i3) throws AudioSink.InitializationException, AudioSink.WriteException {
        ByteBuffer byteBuffer2 = byteBuffer;
        long j3 = j2;
        int i4 = i3;
        ByteBuffer byteBuffer3 = this.inputBuffer;
        Assertions.checkArgument(byteBuffer3 == null || byteBuffer2 == byteBuffer3);
        if (this.pendingConfiguration != null) {
            if (!drainToEndOfStream()) {
                return false;
            }
            if (!this.pendingConfiguration.canReuseAudioTrack(this.configuration)) {
                playPendingData();
                if (hasPendingData()) {
                    return false;
                }
                flush();
            } else {
                this.configuration = this.pendingConfiguration;
                this.pendingConfiguration = null;
                if (isOffloadedPlayback(this.audioTrack) && this.offloadMode != 3) {
                    if (this.audioTrack.getPlayState() == 3) {
                        this.audioTrack.setOffloadEndOfStream();
                    }
                    AudioTrack audioTrack2 = this.audioTrack;
                    Format format = this.configuration.inputFormat;
                    audioTrack2.setOffloadDelayPadding(format.encoderDelay, format.encoderPadding);
                    this.isWaitingForOffloadEndOfStreamHandled = true;
                }
            }
            applyAudioProcessorPlaybackParametersAndSkipSilence(j3);
        }
        if (!isAudioTrackInitialized()) {
            try {
                initializeAudioTrack();
            } catch (AudioSink.InitializationException e3) {
                AudioSink.InitializationException initializationException = e3;
                if (!initializationException.isRecoverable) {
                    this.initializationExceptionPendingExceptionHolder.throwExceptionIfDeadlineIsReached(initializationException);
                    return false;
                }
                throw initializationException;
            }
        }
        this.initializationExceptionPendingExceptionHolder.clear();
        if (this.startMediaTimeUsNeedsInit) {
            this.startMediaTimeUs = Math.max(0, j3);
            this.startMediaTimeUsNeedsSync = false;
            this.startMediaTimeUsNeedsInit = false;
            if (this.enableAudioTrackPlaybackParams && Util.SDK_INT >= 23) {
                setAudioTrackPlaybackParametersV23(this.audioTrackPlaybackParameters);
            }
            applyAudioProcessorPlaybackParametersAndSkipSilence(j3);
            if (this.playing) {
                play();
            }
        }
        if (!this.audioTrackPositionTracker.mayHandleBuffer(getWrittenFrames())) {
            return false;
        }
        if (this.inputBuffer == null) {
            Assertions.checkArgument(byteBuffer.order() == ByteOrder.LITTLE_ENDIAN);
            if (!byteBuffer.hasRemaining()) {
                return true;
            }
            Configuration configuration2 = this.configuration;
            if (configuration2.outputMode != 0 && this.framesPerEncodedSample == 0) {
                int framesPerEncodedSample2 = getFramesPerEncodedSample(configuration2.outputEncoding, byteBuffer2);
                this.framesPerEncodedSample = framesPerEncodedSample2;
                if (framesPerEncodedSample2 == 0) {
                    return true;
                }
            }
            if (this.afterDrainParameters != null) {
                if (!drainToEndOfStream()) {
                    return false;
                }
                applyAudioProcessorPlaybackParametersAndSkipSilence(j3);
                this.afterDrainParameters = null;
            }
            long inputFramesToDurationUs = this.startMediaTimeUs + this.configuration.inputFramesToDurationUs(getSubmittedFrames() - this.trimmingAudioProcessor.getTrimmedFrameCount());
            if (!this.startMediaTimeUsNeedsSync && Math.abs(inputFramesToDurationUs - j3) > 200000) {
                this.listener.onAudioSinkError(new AudioSink.UnexpectedDiscontinuityException(j3, inputFramesToDurationUs));
                this.startMediaTimeUsNeedsSync = true;
            }
            if (this.startMediaTimeUsNeedsSync) {
                if (!drainToEndOfStream()) {
                    return false;
                }
                long j4 = j3 - inputFramesToDurationUs;
                this.startMediaTimeUs += j4;
                this.startMediaTimeUsNeedsSync = false;
                applyAudioProcessorPlaybackParametersAndSkipSilence(j3);
                AudioSink.Listener listener2 = this.listener;
                if (!(listener2 == null || j4 == 0)) {
                    listener2.onPositionDiscontinuity();
                }
            }
            if (this.configuration.outputMode == 0) {
                this.submittedPcmBytes += (long) byteBuffer.remaining();
            } else {
                this.submittedEncodedFrames = (((long) this.framesPerEncodedSample) * ((long) i4)) + this.submittedEncodedFrames;
            }
            this.inputBuffer = byteBuffer2;
            this.inputBufferAccessUnitCount = i4;
        }
        processBuffers(j3);
        if (!this.inputBuffer.hasRemaining()) {
            this.inputBuffer = null;
            this.inputBufferAccessUnitCount = 0;
            return true;
        } else if (!this.audioTrackPositionTracker.isStalled(getWrittenFrames())) {
            return false;
        } else {
            Log.w(TAG, "Resetting stalled audio track");
            flush();
            return true;
        }
    }

    public void handleDiscontinuity() {
        this.startMediaTimeUsNeedsSync = true;
    }

    public boolean hasPendingData() {
        return isAudioTrackInitialized() && this.audioTrackPositionTracker.hasPendingData(getWrittenFrames());
    }

    public boolean isEnded() {
        return !isAudioTrackInitialized() || (this.handledEndOfStream && !hasPendingData());
    }

    public void pause() {
        this.playing = false;
        if (isAudioTrackInitialized() && this.audioTrackPositionTracker.pause()) {
            this.audioTrack.pause();
        }
    }

    public void play() {
        this.playing = true;
        if (isAudioTrackInitialized()) {
            this.audioTrackPositionTracker.start();
            this.audioTrack.play();
        }
    }

    public void playToEndOfStream() throws AudioSink.WriteException {
        if (!this.handledEndOfStream && isAudioTrackInitialized() && drainToEndOfStream()) {
            playPendingData();
            this.handledEndOfStream = true;
        }
    }

    public void reset() {
        flush();
        for (AudioProcessor reset : this.toIntPcmAvailableAudioProcessors) {
            reset.reset();
        }
        for (AudioProcessor reset2 : this.toFloatPcmAvailableAudioProcessors) {
            reset2.reset();
        }
        this.playing = false;
        this.offloadDisabledUntilNextConfiguration = false;
    }

    public void setAudioAttributes(com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes audioAttributes2) {
        if (!this.audioAttributes.equals(audioAttributes2)) {
            this.audioAttributes = audioAttributes2;
            if (!this.tunneling) {
                flush();
            }
        }
    }

    public void setAudioSessionId(int i3) {
        if (this.audioSessionId != i3) {
            this.audioSessionId = i3;
            this.externalAudioSessionIdProvided = i3 != 0;
            flush();
        }
    }

    public void setAuxEffectInfo(AuxEffectInfo auxEffectInfo2) {
        if (!this.auxEffectInfo.equals(auxEffectInfo2)) {
            int i3 = auxEffectInfo2.effectId;
            float f2 = auxEffectInfo2.sendLevel;
            AudioTrack audioTrack2 = this.audioTrack;
            if (audioTrack2 != null) {
                if (this.auxEffectInfo.effectId != i3) {
                    audioTrack2.attachAuxEffect(i3);
                }
                if (i3 != 0) {
                    this.audioTrack.setAuxEffectSendLevel(f2);
                }
            }
            this.auxEffectInfo = auxEffectInfo2;
        }
    }

    public void setListener(AudioSink.Listener listener2) {
        this.listener = listener2;
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        PlaybackParameters playbackParameters2 = new PlaybackParameters(Util.constrainValue(playbackParameters.speed, 0.1f, 8.0f), Util.constrainValue(playbackParameters.pitch, 0.1f, 8.0f));
        if (!this.enableAudioTrackPlaybackParams || Util.SDK_INT < 23) {
            setAudioProcessorPlaybackParametersAndSkipSilence(playbackParameters2, getSkipSilenceEnabled());
        } else {
            setAudioTrackPlaybackParametersV23(playbackParameters2);
        }
    }

    public void setPlayerId(@Nullable PlayerId playerId2) {
        this.playerId = playerId2;
    }

    public void setSkipSilenceEnabled(boolean z2) {
        setAudioProcessorPlaybackParametersAndSkipSilence(getAudioProcessorPlaybackParameters(), z2);
    }

    public void setVolume(float f2) {
        if (this.volume != f2) {
            this.volume = f2;
            setVolumeInternal();
        }
    }

    public boolean supportsFormat(Format format) {
        return getFormatSupport(format) != 0;
    }

    @InlineMe(imports = {"com.google.android.exoplayer2.audio.DefaultAudioSink"}, replacement = "new DefaultAudioSink.Builder().setAudioCapabilities(audioCapabilities).setAudioProcessors(audioProcessors).build()")
    @InlineMeValidationDisabled("Migrate constructor to Builder")
    @Deprecated
    public DefaultAudioSink(@Nullable AudioCapabilities audioCapabilities2, AudioProcessor[] audioProcessorArr) {
        this(new Builder().setAudioCapabilities((AudioCapabilities) MoreObjects.firstNonNull(audioCapabilities2, AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES)).setAudioProcessors(audioProcessorArr));
    }

    @InlineMe(imports = {"com.google.android.exoplayer2.audio.DefaultAudioSink"}, replacement = "new DefaultAudioSink.Builder().setAudioCapabilities(audioCapabilities).setAudioProcessors(audioProcessors).setEnableFloatOutput(enableFloatOutput).build()")
    @InlineMeValidationDisabled("Migrate constructor to Builder")
    @Deprecated
    public DefaultAudioSink(@Nullable AudioCapabilities audioCapabilities2, AudioProcessor[] audioProcessorArr, boolean z2) {
        this(new Builder().setAudioCapabilities((AudioCapabilities) MoreObjects.firstNonNull(audioCapabilities2, AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES)).setAudioProcessors(audioProcessorArr).setEnableFloatOutput(z2));
    }

    @InlineMe(imports = {"com.google.android.exoplayer2.audio.DefaultAudioSink"}, replacement = "new DefaultAudioSink.Builder().setAudioCapabilities(audioCapabilities).setAudioProcessorChain(audioProcessorChain).setEnableFloatOutput(enableFloatOutput).setEnableAudioTrackPlaybackParams(enableAudioTrackPlaybackParams).setOffloadMode(offloadMode).build()")
    @InlineMeValidationDisabled("Migrate constructor to Builder")
    @Deprecated
    public DefaultAudioSink(@Nullable AudioCapabilities audioCapabilities2, AudioProcessorChain audioProcessorChain2, boolean z2, boolean z3, int i3) {
        this(new Builder().setAudioCapabilities((AudioCapabilities) MoreObjects.firstNonNull(audioCapabilities2, AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES)).setAudioProcessorChain(audioProcessorChain2).setEnableFloatOutput(z2).setEnableAudioTrackPlaybackParams(z3).setOffloadMode(i3));
    }

    @RequiresNonNull({"#1.audioProcessorChain"})
    private DefaultAudioSink(Builder builder) {
        this.audioCapabilities = builder.audioCapabilities;
        AudioProcessorChain access$200 = builder.audioProcessorChain;
        this.audioProcessorChain = access$200;
        int i3 = Util.SDK_INT;
        this.enableFloatOutput = i3 >= 21 && builder.enableFloatOutput;
        this.enableAudioTrackPlaybackParams = i3 >= 23 && builder.enableAudioTrackPlaybackParams;
        this.offloadMode = i3 >= 29 ? builder.offloadMode : 0;
        this.audioTrackBufferSizeProvider = builder.audioTrackBufferSizeProvider;
        this.releasingConditionVariable = new ConditionVariable(true);
        this.audioTrackPositionTracker = new AudioTrackPositionTracker(new PositionTrackerListener());
        ChannelMappingAudioProcessor channelMappingAudioProcessor2 = new ChannelMappingAudioProcessor();
        this.channelMappingAudioProcessor = channelMappingAudioProcessor2;
        TrimmingAudioProcessor trimmingAudioProcessor2 = new TrimmingAudioProcessor();
        this.trimmingAudioProcessor = trimmingAudioProcessor2;
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, new BaseAudioProcessor[]{new ResamplingAudioProcessor(), channelMappingAudioProcessor2, trimmingAudioProcessor2});
        Collections.addAll(arrayList, access$200.getAudioProcessors());
        this.toIntPcmAvailableAudioProcessors = (AudioProcessor[]) arrayList.toArray(new AudioProcessor[0]);
        this.toFloatPcmAvailableAudioProcessors = new AudioProcessor[]{new FloatResamplingAudioProcessor()};
        this.volume = 1.0f;
        this.audioAttributes = com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes.DEFAULT;
        this.audioSessionId = 0;
        this.auxEffectInfo = new AuxEffectInfo(0, 0.0f);
        PlaybackParameters playbackParameters = PlaybackParameters.DEFAULT;
        this.mediaPositionParameters = new MediaPositionParameters(playbackParameters, false, 0, 0);
        this.audioTrackPlaybackParameters = playbackParameters;
        this.drainingAudioProcessorIndex = -1;
        this.activeAudioProcessors = new AudioProcessor[0];
        this.outputBuffers = new ByteBuffer[0];
        this.mediaPositionParametersCheckpoints = new ArrayDeque<>();
        this.initializationExceptionPendingExceptionHolder = new PendingExceptionHolder<>(100);
        this.writeExceptionPendingExceptionHolder = new PendingExceptionHolder<>(100);
    }
}
