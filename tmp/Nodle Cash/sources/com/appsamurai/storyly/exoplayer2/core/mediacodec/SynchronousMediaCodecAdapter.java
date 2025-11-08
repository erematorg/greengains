package com.appsamurai.storyly.exoplayer2.core.mediacodec;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.Surface;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.TraceUtil;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter;
import com.appsamurai.storyly.exoplayer2.decoder.CryptoInfo;
import java.io.IOException;
import java.nio.ByteBuffer;

public final class SynchronousMediaCodecAdapter implements MediaCodecAdapter {
    private final MediaCodec codec;
    @Nullable
    private ByteBuffer[] inputByteBuffers;
    @Nullable
    private ByteBuffer[] outputByteBuffers;

    public static class Factory implements MediaCodecAdapter.Factory {
        /* JADX WARNING: Removed duplicated region for block: B:11:0x002f  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter createAdapter(com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter.Configuration r5) throws java.io.IOException {
            /*
                r4 = this;
                r0 = 0
                android.media.MediaCodec r4 = r4.createCodec(r5)     // Catch:{ IOException | RuntimeException -> 0x002c }
                java.lang.String r1 = "configureCodec"
                com.appsamurai.storyly.exoplayer2.common.util.TraceUtil.beginSection(r1)     // Catch:{ IOException | RuntimeException -> 0x0029 }
                android.media.MediaFormat r1 = r5.mediaFormat     // Catch:{ IOException | RuntimeException -> 0x0029 }
                android.view.Surface r2 = r5.surface     // Catch:{ IOException | RuntimeException -> 0x0029 }
                android.media.MediaCrypto r3 = r5.crypto     // Catch:{ IOException | RuntimeException -> 0x0029 }
                int r5 = r5.flags     // Catch:{ IOException | RuntimeException -> 0x0029 }
                r4.configure(r1, r2, r3, r5)     // Catch:{ IOException | RuntimeException -> 0x0029 }
                com.appsamurai.storyly.exoplayer2.common.util.TraceUtil.endSection()     // Catch:{ IOException | RuntimeException -> 0x0029 }
                java.lang.String r5 = "startCodec"
                com.appsamurai.storyly.exoplayer2.common.util.TraceUtil.beginSection(r5)     // Catch:{ IOException | RuntimeException -> 0x0029 }
                r4.start()     // Catch:{ IOException | RuntimeException -> 0x0029 }
                com.appsamurai.storyly.exoplayer2.common.util.TraceUtil.endSection()     // Catch:{ IOException | RuntimeException -> 0x0029 }
                com.appsamurai.storyly.exoplayer2.core.mediacodec.SynchronousMediaCodecAdapter r5 = new com.appsamurai.storyly.exoplayer2.core.mediacodec.SynchronousMediaCodecAdapter     // Catch:{ IOException | RuntimeException -> 0x0029 }
                r5.<init>(r4)     // Catch:{ IOException | RuntimeException -> 0x0029 }
                return r5
            L_0x0029:
                r5 = move-exception
                r0 = r4
                goto L_0x002d
            L_0x002c:
                r5 = move-exception
            L_0x002d:
                if (r0 == 0) goto L_0x0032
                r0.release()
            L_0x0032:
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.mediacodec.SynchronousMediaCodecAdapter.Factory.createAdapter(com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter$Configuration):com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter");
        }

        public MediaCodec createCodec(MediaCodecAdapter.Configuration configuration) throws IOException {
            Assertions.checkNotNull(configuration.codecInfo);
            String str = configuration.codecInfo.name;
            TraceUtil.beginSection("createCodec:" + str);
            MediaCodec createByCodecName = MediaCodec.createByCodecName(str);
            TraceUtil.endSection();
            return createByCodecName;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setOnFrameRenderedListener$0(MediaCodecAdapter.OnFrameRenderedListener onFrameRenderedListener, MediaCodec mediaCodec, long j2, long j3) {
        onFrameRenderedListener.onFrameRendered(this, j2, j3);
    }

    public int dequeueInputBufferIndex() {
        return this.codec.dequeueInputBuffer(0);
    }

    public int dequeueOutputBufferIndex(MediaCodec.BufferInfo bufferInfo) {
        int dequeueOutputBuffer;
        do {
            dequeueOutputBuffer = this.codec.dequeueOutputBuffer(bufferInfo, 0);
            if (dequeueOutputBuffer == -3 && Util.SDK_INT < 21) {
                this.outputByteBuffers = this.codec.getOutputBuffers();
                continue;
            }
        } while (dequeueOutputBuffer == -3);
        return dequeueOutputBuffer;
    }

    public void flush() {
        this.codec.flush();
    }

    @Nullable
    public ByteBuffer getInputBuffer(int i3) {
        return Util.SDK_INT >= 21 ? this.codec.getInputBuffer(i3) : ((ByteBuffer[]) Util.castNonNull(this.inputByteBuffers))[i3];
    }

    @RequiresApi(26)
    public PersistableBundle getMetrics() {
        return this.codec.getMetrics();
    }

    @Nullable
    public ByteBuffer getOutputBuffer(int i3) {
        return Util.SDK_INT >= 21 ? this.codec.getOutputBuffer(i3) : ((ByteBuffer[]) Util.castNonNull(this.outputByteBuffers))[i3];
    }

    public MediaFormat getOutputFormat() {
        return this.codec.getOutputFormat();
    }

    public boolean needsReconfiguration() {
        return false;
    }

    public void queueInputBuffer(int i3, int i4, int i5, long j2, int i6) {
        this.codec.queueInputBuffer(i3, i4, i5, j2, i6);
    }

    public void queueSecureInputBuffer(int i3, int i4, CryptoInfo cryptoInfo, long j2, int i5) {
        this.codec.queueSecureInputBuffer(i3, i4, cryptoInfo.getFrameworkCryptoInfo(), j2, i5);
    }

    public void release() {
        this.inputByteBuffers = null;
        this.outputByteBuffers = null;
        this.codec.release();
    }

    public void releaseOutputBuffer(int i3, boolean z2) {
        this.codec.releaseOutputBuffer(i3, z2);
    }

    @RequiresApi(23)
    public void setOnFrameRenderedListener(MediaCodecAdapter.OnFrameRenderedListener onFrameRenderedListener, Handler handler) {
        this.codec.setOnFrameRenderedListener(new a(this, onFrameRenderedListener, 1), handler);
    }

    @RequiresApi(23)
    public void setOutputSurface(Surface surface) {
        this.codec.setOutputSurface(surface);
    }

    @RequiresApi(19)
    public void setParameters(Bundle bundle) {
        this.codec.setParameters(bundle);
    }

    public void setVideoScalingMode(int i3) {
        this.codec.setVideoScalingMode(i3);
    }

    private SynchronousMediaCodecAdapter(MediaCodec mediaCodec) {
        this.codec = mediaCodec;
        if (Util.SDK_INT < 21) {
            this.inputByteBuffers = mediaCodec.getInputBuffers();
            this.outputByteBuffers = mediaCodec.getOutputBuffers();
        }
    }

    @RequiresApi(21)
    public void releaseOutputBuffer(int i3, long j2) {
        this.codec.releaseOutputBuffer(i3, j2);
    }
}
