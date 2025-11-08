package com.appsamurai.storyly.exoplayer2.core.video.spherical;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.BaseRenderer;
import com.appsamurai.storyly.exoplayer2.core.ExoPlaybackException;
import com.appsamurai.storyly.exoplayer2.core.RendererCapabilities;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer;
import java.nio.ByteBuffer;

public final class CameraMotionRenderer extends BaseRenderer {
    private static final int SAMPLE_WINDOW_DURATION_US = 100000;
    private static final String TAG = "CameraMotionRenderer";
    private final DecoderInputBuffer buffer = new DecoderInputBuffer(1);
    private long lastTimestampUs;
    @Nullable
    private CameraMotionListener listener;
    private long offsetUs;
    private final ParsableByteArray scratch = new ParsableByteArray();

    public CameraMotionRenderer() {
        super(6);
    }

    @Nullable
    private float[] parseMetadata(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() != 16) {
            return null;
        }
        this.scratch.reset(byteBuffer.array(), byteBuffer.limit());
        this.scratch.setPosition(byteBuffer.arrayOffset() + 4);
        float[] fArr = new float[3];
        for (int i3 = 0; i3 < 3; i3++) {
            fArr[i3] = Float.intBitsToFloat(this.scratch.readLittleEndianInt());
        }
        return fArr;
    }

    private void resetListener() {
        CameraMotionListener cameraMotionListener = this.listener;
        if (cameraMotionListener != null) {
            cameraMotionListener.onCameraMotionReset();
        }
    }

    public String getName() {
        return TAG;
    }

    public void handleMessage(int i3, @Nullable Object obj) throws ExoPlaybackException {
        if (i3 == 8) {
            this.listener = (CameraMotionListener) obj;
        } else {
            super.handleMessage(i3, obj);
        }
    }

    public boolean isEnded() {
        return hasReadStreamToEnd();
    }

    public boolean isReady() {
        return true;
    }

    public void onDisabled() {
        resetListener();
    }

    public void onPositionReset(long j2, boolean z2) {
        this.lastTimestampUs = Long.MIN_VALUE;
        resetListener();
    }

    public void onStreamChanged(Format[] formatArr, long j2, long j3) {
        this.offsetUs = j3;
    }

    public void render(long j2, long j3) {
        while (!hasReadStreamToEnd() && this.lastTimestampUs < 100000 + j2) {
            this.buffer.clear();
            if (readSource(getFormatHolder(), this.buffer, 0) == -4 && !this.buffer.isEndOfStream()) {
                DecoderInputBuffer decoderInputBuffer = this.buffer;
                this.lastTimestampUs = decoderInputBuffer.timeUs;
                if (this.listener != null && !decoderInputBuffer.isDecodeOnly()) {
                    this.buffer.flip();
                    float[] parseMetadata = parseMetadata((ByteBuffer) Util.castNonNull(this.buffer.data));
                    if (parseMetadata != null) {
                        ((CameraMotionListener) Util.castNonNull(this.listener)).onCameraMotion(this.lastTimestampUs - this.offsetUs, parseMetadata);
                    }
                }
            } else {
                return;
            }
        }
    }

    public int supportsFormat(Format format) {
        return MimeTypes.APPLICATION_CAMERA_MOTION.equals(format.sampleMimeType) ? RendererCapabilities.create(4) : RendererCapabilities.create(0);
    }
}
