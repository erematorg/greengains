package com.appsamurai.storyly.exoplayer2.core;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.core.audio.AudioCapabilities;
import com.appsamurai.storyly.exoplayer2.core.audio.AudioRendererEventListener;
import com.appsamurai.storyly.exoplayer2.core.audio.AudioSink;
import com.appsamurai.storyly.exoplayer2.core.audio.DefaultAudioSink;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.DefaultMediaCodecAdapterFactory;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecSelector;
import com.appsamurai.storyly.exoplayer2.core.metadata.MetadataOutput;
import com.appsamurai.storyly.exoplayer2.core.metadata.MetadataRenderer;
import com.appsamurai.storyly.exoplayer2.core.text.TextOutput;
import com.appsamurai.storyly.exoplayer2.core.text.TextRenderer;
import com.appsamurai.storyly.exoplayer2.core.video.VideoRendererEventListener;
import com.appsamurai.storyly.exoplayer2.core.video.spherical.CameraMotionRenderer;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;

public class DefaultRenderersFactory implements RenderersFactory {
    public static final long DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS = 5000;
    public static final int EXTENSION_RENDERER_MODE_OFF = 0;
    public static final int EXTENSION_RENDERER_MODE_ON = 1;
    public static final int EXTENSION_RENDERER_MODE_PREFER = 2;
    public static final int MAX_DROPPED_VIDEO_FRAME_COUNT_TO_NOTIFY = 50;
    private static final String TAG = "DefaultRenderersFactory";
    private long allowedVideoJoiningTimeMs = 5000;
    private final DefaultMediaCodecAdapterFactory codecAdapterFactory = new DefaultMediaCodecAdapterFactory();
    private final Context context;
    private boolean enableAudioTrackPlaybackParams;
    private boolean enableDecoderFallback;
    private boolean enableFloatOutput;
    private boolean enableOffload;
    private int extensionRendererMode = 0;
    private MediaCodecSelector mediaCodecSelector = MediaCodecSelector.DEFAULT;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ExtensionRendererMode {
    }

    public DefaultRenderersFactory(Context context2) {
        this.context = context2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0055, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0057, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0060, code lost:
        throw new java.lang.RuntimeException("Error instantiating MIDI extension", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x008a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x008e, code lost:
        r1 = r21;
        r3 = r22;
        r4 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x009b, code lost:
        throw new java.lang.RuntimeException("Error instantiating Opus extension", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00c0, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00c2, code lost:
        r5 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00cb, code lost:
        throw new java.lang.RuntimeException("Error instantiating FLAC extension", r0);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0055 A[ExcHandler: Exception (r0v23 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:7:0x003b] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008a A[ExcHandler: Exception (r0v17 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:20:0x0064] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00c0 A[ExcHandler: Exception (r0v11 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:38:0x009f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void buildAudioRenderers(android.content.Context r17, int r18, com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecSelector r19, boolean r20, com.appsamurai.storyly.exoplayer2.core.audio.AudioSink r21, android.os.Handler r22, com.appsamurai.storyly.exoplayer2.core.audio.AudioRendererEventListener r23, java.util.ArrayList<com.appsamurai.storyly.exoplayer2.core.Renderer> r24) {
        /*
            r16 = this;
            r0 = r18
            r9 = r21
            r10 = r22
            r11 = r23
            r12 = r24
            java.lang.Class<com.appsamurai.storyly.exoplayer2.core.audio.AudioSink> r13 = com.appsamurai.storyly.exoplayer2.core.audio.AudioSink.class
            java.lang.Class<com.appsamurai.storyly.exoplayer2.core.audio.AudioRendererEventListener> r14 = com.appsamurai.storyly.exoplayer2.core.audio.AudioRendererEventListener.class
            java.lang.Class<android.os.Handler> r15 = android.os.Handler.class
            java.lang.String r8 = "DefaultRenderersFactory"
            com.appsamurai.storyly.exoplayer2.core.audio.MediaCodecAudioRenderer r7 = new com.appsamurai.storyly.exoplayer2.core.audio.MediaCodecAudioRenderer
            com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter$Factory r3 = r16.getCodecAdapterFactory()
            r1 = r7
            r2 = r17
            r4 = r19
            r5 = r20
            r6 = r22
            r9 = r7
            r7 = r23
            r10 = r8
            r8 = r21
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            r12.add(r9)
            if (r0 != 0) goto L_0x0030
            return
        L_0x0030:
            int r1 = r24.size()
            r2 = 2
            if (r0 != r2) goto L_0x0039
            int r1 = r1 + -1
        L_0x0039:
            java.lang.String r0 = "com.google.android.exoplayer2.decoder.midi.MidiRenderer"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x0061, Exception -> 0x0055 }
            r2 = 0
            java.lang.reflect.Constructor r0 = r0.getConstructor(r2)     // Catch:{ ClassNotFoundException -> 0x0061, Exception -> 0x0055 }
            java.lang.Object r0 = r0.newInstance(r2)     // Catch:{ ClassNotFoundException -> 0x0061, Exception -> 0x0055 }
            com.appsamurai.storyly.exoplayer2.core.Renderer r0 = (com.appsamurai.storyly.exoplayer2.core.Renderer) r0     // Catch:{ ClassNotFoundException -> 0x0061, Exception -> 0x0055 }
            int r2 = r1 + 1
            r12.add(r1, r0)     // Catch:{ ClassNotFoundException -> 0x0057, Exception -> 0x0055 }
            java.lang.String r0 = "Loaded MidiRenderer."
            com.appsamurai.storyly.exoplayer2.common.util.Log.i(r10, r0)     // Catch:{ ClassNotFoundException -> 0x0057, Exception -> 0x0055 }
            goto L_0x0062
        L_0x0055:
            r0 = move-exception
            goto L_0x0059
        L_0x0057:
            r1 = r2
            goto L_0x0061
        L_0x0059:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "Error instantiating MIDI extension"
            r1.<init>(r2, r0)
            throw r1
        L_0x0061:
            r2 = r1
        L_0x0062:
            java.lang.String r0 = "com.google.android.exoplayer2.ext.opus.LibopusAudioRenderer"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x008e, Exception -> 0x008a }
            java.lang.Class[] r1 = new java.lang.Class[]{r15, r14, r13}     // Catch:{ ClassNotFoundException -> 0x008e, Exception -> 0x008a }
            java.lang.reflect.Constructor r0 = r0.getConstructor(r1)     // Catch:{ ClassNotFoundException -> 0x008e, Exception -> 0x008a }
            r1 = r21
            r3 = r22
            r4 = r10
            java.lang.Object[] r5 = new java.lang.Object[]{r3, r11, r1}     // Catch:{ ClassNotFoundException -> 0x009c, Exception -> 0x008a }
            java.lang.Object r0 = r0.newInstance(r5)     // Catch:{ ClassNotFoundException -> 0x009c, Exception -> 0x008a }
            com.appsamurai.storyly.exoplayer2.core.Renderer r0 = (com.appsamurai.storyly.exoplayer2.core.Renderer) r0     // Catch:{ ClassNotFoundException -> 0x009c, Exception -> 0x008a }
            int r5 = r2 + 1
            r12.add(r2, r0)     // Catch:{ ClassNotFoundException -> 0x008c, Exception -> 0x008a }
            java.lang.String r0 = "Loaded LibopusAudioRenderer."
            com.appsamurai.storyly.exoplayer2.common.util.Log.i(r4, r0)     // Catch:{ ClassNotFoundException -> 0x008c, Exception -> 0x008a }
            goto L_0x009d
        L_0x008a:
            r0 = move-exception
            goto L_0x0094
        L_0x008c:
            r2 = r5
            goto L_0x009c
        L_0x008e:
            r1 = r21
            r3 = r22
            r4 = r10
            goto L_0x009c
        L_0x0094:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "Error instantiating Opus extension"
            r1.<init>(r2, r0)
            throw r1
        L_0x009c:
            r5 = r2
        L_0x009d:
            java.lang.String r0 = "com.google.android.exoplayer2.ext.flac.LibflacAudioRenderer"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x00cc, Exception -> 0x00c0 }
            java.lang.Class[] r2 = new java.lang.Class[]{r15, r14, r13}     // Catch:{ ClassNotFoundException -> 0x00cc, Exception -> 0x00c0 }
            java.lang.reflect.Constructor r0 = r0.getConstructor(r2)     // Catch:{ ClassNotFoundException -> 0x00cc, Exception -> 0x00c0 }
            java.lang.Object[] r2 = new java.lang.Object[]{r3, r11, r1}     // Catch:{ ClassNotFoundException -> 0x00cc, Exception -> 0x00c0 }
            java.lang.Object r0 = r0.newInstance(r2)     // Catch:{ ClassNotFoundException -> 0x00cc, Exception -> 0x00c0 }
            com.appsamurai.storyly.exoplayer2.core.Renderer r0 = (com.appsamurai.storyly.exoplayer2.core.Renderer) r0     // Catch:{ ClassNotFoundException -> 0x00cc, Exception -> 0x00c0 }
            int r2 = r5 + 1
            r12.add(r5, r0)     // Catch:{ ClassNotFoundException -> 0x00c2, Exception -> 0x00c0 }
            java.lang.String r0 = "Loaded LibflacAudioRenderer."
            com.appsamurai.storyly.exoplayer2.common.util.Log.i(r4, r0)     // Catch:{ ClassNotFoundException -> 0x00c2, Exception -> 0x00c0 }
            goto L_0x00cd
        L_0x00c0:
            r0 = move-exception
            goto L_0x00c4
        L_0x00c2:
            r5 = r2
            goto L_0x00cc
        L_0x00c4:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "Error instantiating FLAC extension"
            r1.<init>(r2, r0)
            throw r1
        L_0x00cc:
            r2 = r5
        L_0x00cd:
            java.lang.String r0 = "com.google.android.exoplayer2.ext.ffmpeg.FfmpegAudioRenderer"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x00f7, Exception -> 0x00ee }
            java.lang.Class[] r5 = new java.lang.Class[]{r15, r14, r13}     // Catch:{ ClassNotFoundException -> 0x00f7, Exception -> 0x00ee }
            java.lang.reflect.Constructor r0 = r0.getConstructor(r5)     // Catch:{ ClassNotFoundException -> 0x00f7, Exception -> 0x00ee }
            java.lang.Object[] r1 = new java.lang.Object[]{r3, r11, r1}     // Catch:{ ClassNotFoundException -> 0x00f7, Exception -> 0x00ee }
            java.lang.Object r0 = r0.newInstance(r1)     // Catch:{ ClassNotFoundException -> 0x00f7, Exception -> 0x00ee }
            com.appsamurai.storyly.exoplayer2.core.Renderer r0 = (com.appsamurai.storyly.exoplayer2.core.Renderer) r0     // Catch:{ ClassNotFoundException -> 0x00f7, Exception -> 0x00ee }
            r12.add(r2, r0)     // Catch:{ ClassNotFoundException -> 0x00f7, Exception -> 0x00ee }
            java.lang.String r0 = "Loaded FfmpegAudioRenderer."
            com.appsamurai.storyly.exoplayer2.common.util.Log.i(r4, r0)     // Catch:{ ClassNotFoundException -> 0x00f7, Exception -> 0x00ee }
            goto L_0x00f7
        L_0x00ee:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "Error instantiating FFmpeg extension"
            r1.<init>(r2, r0)
            throw r1
        L_0x00f7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.DefaultRenderersFactory.buildAudioRenderers(android.content.Context, int, com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecSelector, boolean, com.appsamurai.storyly.exoplayer2.core.audio.AudioSink, android.os.Handler, com.appsamurai.storyly.exoplayer2.core.audio.AudioRendererEventListener, java.util.ArrayList):void");
    }

    @Nullable
    public AudioSink buildAudioSink(Context context2, boolean z2, boolean z3, boolean z4) {
        return new DefaultAudioSink.Builder().setAudioCapabilities(AudioCapabilities.getCapabilities(context2)).setEnableFloatOutput(z2).setEnableAudioTrackPlaybackParams(z3).setOffloadMode(z4 ? 1 : 0).build();
    }

    public void buildCameraMotionRenderers(Context context2, int i3, ArrayList<Renderer> arrayList) {
        arrayList.add(new CameraMotionRenderer());
    }

    public void buildMetadataRenderers(Context context2, MetadataOutput metadataOutput, Looper looper, int i3, ArrayList<Renderer> arrayList) {
        arrayList.add(new MetadataRenderer(metadataOutput, looper));
    }

    public void buildMiscellaneousRenderers(Context context2, Handler handler, int i3, ArrayList<Renderer> arrayList) {
    }

    public void buildTextRenderers(Context context2, TextOutput textOutput, Looper looper, int i3, ArrayList<Renderer> arrayList) {
        arrayList.add(new TextRenderer(textOutput, looper));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0071, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0075, code lost:
        r2 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0078, code lost:
        r2 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007b, code lost:
        r5 = r23;
        r6 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0086, code lost:
        throw new java.lang.RuntimeException("Error instantiating VP9 extension", r0);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0071 A[ExcHandler: Exception (r0v8 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:7:0x003f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void buildVideoRenderers(android.content.Context r19, int r20, com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecSelector r21, boolean r22, android.os.Handler r23, com.appsamurai.storyly.exoplayer2.core.video.VideoRendererEventListener r24, long r25, java.util.ArrayList<com.appsamurai.storyly.exoplayer2.core.Renderer> r27) {
        /*
            r18 = this;
            r0 = r20
            r11 = r23
            r12 = r24
            r13 = r27
            java.lang.String r14 = "DefaultRenderersFactory"
            java.lang.Class<com.appsamurai.storyly.exoplayer2.core.video.VideoRendererEventListener> r15 = com.appsamurai.storyly.exoplayer2.core.video.VideoRendererEventListener.class
            java.lang.Class<android.os.Handler> r10 = android.os.Handler.class
            com.appsamurai.storyly.exoplayer2.core.video.MediaCodecVideoRenderer r9 = new com.appsamurai.storyly.exoplayer2.core.video.MediaCodecVideoRenderer
            com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter$Factory r3 = r18.getCodecAdapterFactory()
            r16 = 50
            r1 = r9
            r2 = r19
            r4 = r21
            r5 = r25
            r7 = r22
            r8 = r23
            r17 = r14
            r14 = r9
            r9 = r24
            r11 = r10
            r10 = r16
            r1.<init>(r2, r3, r4, r5, r7, r8, r9, r10)
            r13.add(r14)
            if (r0 != 0) goto L_0x0032
            return
        L_0x0032:
            int r1 = r27.size()
            r2 = 2
            if (r0 != r2) goto L_0x003b
            int r1 = r1 + -1
        L_0x003b:
            r0 = 50
            java.lang.String r2 = "com.google.android.exoplayer2.ext.vp9.LibvpxVideoRenderer"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ ClassNotFoundException -> 0x007b, Exception -> 0x0071 }
            java.lang.Class r3 = java.lang.Long.TYPE     // Catch:{ ClassNotFoundException -> 0x007b, Exception -> 0x0071 }
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ ClassNotFoundException -> 0x007b, Exception -> 0x0071 }
            java.lang.Class[] r3 = new java.lang.Class[]{r3, r11, r15, r4}     // Catch:{ ClassNotFoundException -> 0x007b, Exception -> 0x0071 }
            java.lang.reflect.Constructor r2 = r2.getConstructor(r3)     // Catch:{ ClassNotFoundException -> 0x007b, Exception -> 0x0071 }
            java.lang.Long r3 = java.lang.Long.valueOf(r25)     // Catch:{ ClassNotFoundException -> 0x007b, Exception -> 0x0071 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)     // Catch:{ ClassNotFoundException -> 0x007b, Exception -> 0x0071 }
            r5 = r23
            r6 = r11
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r5, r12, r4}     // Catch:{ ClassNotFoundException -> 0x0078, Exception -> 0x0071 }
            java.lang.Object r2 = r2.newInstance(r3)     // Catch:{ ClassNotFoundException -> 0x0078, Exception -> 0x0071 }
            com.appsamurai.storyly.exoplayer2.core.Renderer r2 = (com.appsamurai.storyly.exoplayer2.core.Renderer) r2     // Catch:{ ClassNotFoundException -> 0x0078, Exception -> 0x0071 }
            int r3 = r1 + 1
            r13.add(r1, r2)     // Catch:{ ClassNotFoundException -> 0x0075, Exception -> 0x0071 }
            java.lang.String r1 = "Loaded LibvpxVideoRenderer."
            r2 = r17
            com.appsamurai.storyly.exoplayer2.common.util.Log.i(r2, r1)     // Catch:{ ClassNotFoundException -> 0x0073, Exception -> 0x0071 }
            goto L_0x0088
        L_0x0071:
            r0 = move-exception
            goto L_0x007f
        L_0x0073:
            r1 = r3
            goto L_0x0087
        L_0x0075:
            r2 = r17
            goto L_0x0073
        L_0x0078:
            r2 = r17
            goto L_0x0087
        L_0x007b:
            r5 = r23
            r6 = r11
            goto L_0x0078
        L_0x007f:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "Error instantiating VP9 extension"
            r1.<init>(r2, r0)
            throw r1
        L_0x0087:
            r3 = r1
        L_0x0088:
            java.lang.String r1 = "com.google.android.exoplayer2.ext.av1.Libgav1VideoRenderer"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x00be, Exception -> 0x00b5 }
            java.lang.Class r4 = java.lang.Long.TYPE     // Catch:{ ClassNotFoundException -> 0x00be, Exception -> 0x00b5 }
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch:{ ClassNotFoundException -> 0x00be, Exception -> 0x00b5 }
            java.lang.Class[] r4 = new java.lang.Class[]{r4, r6, r15, r7}     // Catch:{ ClassNotFoundException -> 0x00be, Exception -> 0x00b5 }
            java.lang.reflect.Constructor r1 = r1.getConstructor(r4)     // Catch:{ ClassNotFoundException -> 0x00be, Exception -> 0x00b5 }
            java.lang.Long r4 = java.lang.Long.valueOf(r25)     // Catch:{ ClassNotFoundException -> 0x00be, Exception -> 0x00b5 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ ClassNotFoundException -> 0x00be, Exception -> 0x00b5 }
            java.lang.Object[] r0 = new java.lang.Object[]{r4, r5, r12, r0}     // Catch:{ ClassNotFoundException -> 0x00be, Exception -> 0x00b5 }
            java.lang.Object r0 = r1.newInstance(r0)     // Catch:{ ClassNotFoundException -> 0x00be, Exception -> 0x00b5 }
            com.appsamurai.storyly.exoplayer2.core.Renderer r0 = (com.appsamurai.storyly.exoplayer2.core.Renderer) r0     // Catch:{ ClassNotFoundException -> 0x00be, Exception -> 0x00b5 }
            r13.add(r3, r0)     // Catch:{ ClassNotFoundException -> 0x00be, Exception -> 0x00b5 }
            java.lang.String r0 = "Loaded Libgav1VideoRenderer."
            com.appsamurai.storyly.exoplayer2.common.util.Log.i(r2, r0)     // Catch:{ ClassNotFoundException -> 0x00be, Exception -> 0x00b5 }
            goto L_0x00be
        L_0x00b5:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "Error instantiating AV1 extension"
            r1.<init>(r2, r0)
            throw r1
        L_0x00be:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.DefaultRenderersFactory.buildVideoRenderers(android.content.Context, int, com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecSelector, boolean, android.os.Handler, com.appsamurai.storyly.exoplayer2.core.video.VideoRendererEventListener, long, java.util.ArrayList):void");
    }

    public Renderer[] createRenderers(Handler handler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextOutput textOutput, MetadataOutput metadataOutput) {
        ArrayList arrayList = new ArrayList();
        buildVideoRenderers(this.context, this.extensionRendererMode, this.mediaCodecSelector, this.enableDecoderFallback, handler, videoRendererEventListener, this.allowedVideoJoiningTimeMs, arrayList);
        AudioSink buildAudioSink = buildAudioSink(this.context, this.enableFloatOutput, this.enableAudioTrackPlaybackParams, this.enableOffload);
        if (buildAudioSink != null) {
            buildAudioRenderers(this.context, this.extensionRendererMode, this.mediaCodecSelector, this.enableDecoderFallback, buildAudioSink, handler, audioRendererEventListener, arrayList);
        }
        ArrayList arrayList2 = arrayList;
        buildTextRenderers(this.context, textOutput, handler.getLooper(), this.extensionRendererMode, arrayList2);
        buildMetadataRenderers(this.context, metadataOutput, handler.getLooper(), this.extensionRendererMode, arrayList2);
        buildCameraMotionRenderers(this.context, this.extensionRendererMode, arrayList);
        Handler handler2 = handler;
        buildMiscellaneousRenderers(this.context, handler, this.extensionRendererMode, arrayList);
        return (Renderer[]) arrayList.toArray(new Renderer[0]);
    }

    public DefaultRenderersFactory experimentalSetSynchronizeCodecInteractionsWithQueueingEnabled(boolean z2) {
        this.codecAdapterFactory.experimentalSetSynchronizeCodecInteractionsWithQueueingEnabled(z2);
        return this;
    }

    public DefaultRenderersFactory forceDisableMediaCodecAsynchronousQueueing() {
        this.codecAdapterFactory.forceDisableAsynchronous();
        return this;
    }

    public DefaultRenderersFactory forceEnableMediaCodecAsynchronousQueueing() {
        this.codecAdapterFactory.forceEnableAsynchronous();
        return this;
    }

    public MediaCodecAdapter.Factory getCodecAdapterFactory() {
        return this.codecAdapterFactory;
    }

    public DefaultRenderersFactory setAllowedVideoJoiningTimeMs(long j2) {
        this.allowedVideoJoiningTimeMs = j2;
        return this;
    }

    public DefaultRenderersFactory setEnableAudioFloatOutput(boolean z2) {
        this.enableFloatOutput = z2;
        return this;
    }

    public DefaultRenderersFactory setEnableAudioOffload(boolean z2) {
        this.enableOffload = z2;
        return this;
    }

    public DefaultRenderersFactory setEnableAudioTrackPlaybackParams(boolean z2) {
        this.enableAudioTrackPlaybackParams = z2;
        return this;
    }

    public DefaultRenderersFactory setEnableDecoderFallback(boolean z2) {
        this.enableDecoderFallback = z2;
        return this;
    }

    public DefaultRenderersFactory setExtensionRendererMode(int i3) {
        this.extensionRendererMode = i3;
        return this;
    }

    public DefaultRenderersFactory setMediaCodecSelector(MediaCodecSelector mediaCodecSelector2) {
        this.mediaCodecSelector = mediaCodecSelector2;
        return this;
    }
}
