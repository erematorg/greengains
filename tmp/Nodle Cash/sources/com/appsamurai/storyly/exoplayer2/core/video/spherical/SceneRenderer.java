package com.appsamurai.storyly.exoplayer2.core.video.spherical;

import android.graphics.SurfaceTexture;
import android.media.MediaFormat;
import android.opengl.GLES20;
import android.opengl.Matrix;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.GlUtil;
import com.appsamurai.storyly.exoplayer2.common.util.TimedValueQueue;
import com.appsamurai.storyly.exoplayer2.core.video.VideoFrameMetadataListener;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

final class SceneRenderer implements VideoFrameMetadataListener, CameraMotionListener {
    private volatile int defaultStereoMode = 0;
    private final AtomicBoolean frameAvailable = new AtomicBoolean();
    private final FrameRotationQueue frameRotationQueue = new FrameRotationQueue();
    @Nullable
    private byte[] lastProjectionData;
    private int lastStereoMode = -1;
    private final TimedValueQueue<Projection> projectionQueue = new TimedValueQueue<>();
    private final ProjectionRenderer projectionRenderer = new ProjectionRenderer();
    private final AtomicBoolean resetRotationAtNextFrame = new AtomicBoolean(true);
    private final float[] rotationMatrix = new float[16];
    private final TimedValueQueue<Long> sampleTimestampQueue = new TimedValueQueue<>();
    private SurfaceTexture surfaceTexture;
    private final float[] tempMatrix = new float[16];
    private int textureId;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init$0(SurfaceTexture surfaceTexture2) {
        this.frameAvailable.set(true);
    }

    private void setProjection(@Nullable byte[] bArr, int i3, long j2) {
        byte[] bArr2 = this.lastProjectionData;
        int i4 = this.lastStereoMode;
        this.lastProjectionData = bArr;
        if (i3 == -1) {
            i3 = this.defaultStereoMode;
        }
        this.lastStereoMode = i3;
        if (i4 != i3 || !Arrays.equals(bArr2, this.lastProjectionData)) {
            byte[] bArr3 = this.lastProjectionData;
            Projection decode = bArr3 != null ? ProjectionDecoder.decode(bArr3, this.lastStereoMode) : null;
            if (decode == null || !ProjectionRenderer.isSupported(decode)) {
                decode = Projection.createEquirectangular(this.lastStereoMode);
            }
            this.projectionQueue.add(j2, decode);
        }
    }

    public void drawFrame(float[] fArr, boolean z2) {
        GLES20.glClear(16384);
        GlUtil.checkGlError();
        if (this.frameAvailable.compareAndSet(true, false)) {
            ((SurfaceTexture) Assertions.checkNotNull(this.surfaceTexture)).updateTexImage();
            GlUtil.checkGlError();
            if (this.resetRotationAtNextFrame.compareAndSet(true, false)) {
                Matrix.setIdentityM(this.rotationMatrix, 0);
            }
            long timestamp = this.surfaceTexture.getTimestamp();
            Long poll = this.sampleTimestampQueue.poll(timestamp);
            if (poll != null) {
                this.frameRotationQueue.pollRotationMatrix(this.rotationMatrix, poll.longValue());
            }
            Projection pollFloor = this.projectionQueue.pollFloor(timestamp);
            if (pollFloor != null) {
                this.projectionRenderer.setProjection(pollFloor);
            }
        }
        Matrix.multiplyMM(this.tempMatrix, 0, fArr, 0, this.rotationMatrix, 0);
        this.projectionRenderer.draw(this.textureId, this.tempMatrix, z2);
    }

    public SurfaceTexture init() {
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        GlUtil.checkGlError();
        this.projectionRenderer.init();
        GlUtil.checkGlError();
        this.textureId = GlUtil.createExternalTexture();
        SurfaceTexture surfaceTexture2 = new SurfaceTexture(this.textureId);
        this.surfaceTexture = surfaceTexture2;
        surfaceTexture2.setOnFrameAvailableListener(new a(this));
        return this.surfaceTexture;
    }

    public void onCameraMotion(long j2, float[] fArr) {
        this.frameRotationQueue.setRotation(j2, fArr);
    }

    public void onCameraMotionReset() {
        this.sampleTimestampQueue.clear();
        this.frameRotationQueue.reset();
        this.resetRotationAtNextFrame.set(true);
    }

    public void onVideoFrameAboutToBeRendered(long j2, long j3, Format format, @Nullable MediaFormat mediaFormat) {
        this.sampleTimestampQueue.add(j3, Long.valueOf(j2));
        setProjection(format.projectionData, format.stereoMode, j3);
    }

    public void setDefaultStereoMode(int i3) {
        this.defaultStereoMode = i3;
    }

    public void shutdown() {
        this.projectionRenderer.shutdown();
    }
}
