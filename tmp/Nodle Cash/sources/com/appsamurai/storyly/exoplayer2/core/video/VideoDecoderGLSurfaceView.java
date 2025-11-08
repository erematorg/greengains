package com.appsamurai.storyly.exoplayer2.core.video;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.GlProgram;
import com.appsamurai.storyly.exoplayer2.common.util.GlUtil;
import com.appsamurai.storyly.exoplayer2.decoder.VideoDecoderOutputBuffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.concurrent.atomic.AtomicReference;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class VideoDecoderGLSurfaceView extends GLSurfaceView implements VideoDecoderOutputBufferRenderer {
    private final Renderer renderer;

    public static final class Renderer implements GLSurfaceView.Renderer {
        private static final String FRAGMENT_SHADER = "precision mediump float;\nvarying vec2 interp_tc_y;\nvarying vec2 interp_tc_u;\nvarying vec2 interp_tc_v;\nuniform sampler2D y_tex;\nuniform sampler2D u_tex;\nuniform sampler2D v_tex;\nuniform mat3 mColorConversion;\nvoid main() {\n  vec3 yuv;\n  yuv.x = texture2D(y_tex, interp_tc_y).r - 0.0625;\n  yuv.y = texture2D(u_tex, interp_tc_u).r - 0.5;\n  yuv.z = texture2D(v_tex, interp_tc_v).r - 0.5;\n  gl_FragColor = vec4(mColorConversion * yuv, 1.0);\n}\n";
        private static final String[] TEXTURE_UNIFORMS = {"y_tex", "u_tex", "v_tex"};
        private static final FloatBuffer TEXTURE_VERTICES = GlUtil.createBuffer(new float[]{-1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f, -1.0f});
        private static final String VERTEX_SHADER = "varying vec2 interp_tc_y;\nvarying vec2 interp_tc_u;\nvarying vec2 interp_tc_v;\nattribute vec4 in_pos;\nattribute vec2 in_tc_y;\nattribute vec2 in_tc_u;\nattribute vec2 in_tc_v;\nvoid main() {\n  gl_Position = in_pos;\n  interp_tc_y = in_tc_y;\n  interp_tc_u = in_tc_u;\n  interp_tc_v = in_tc_v;\n}\n";
        private static final float[] kColorConversion2020 = {1.168f, 1.168f, 1.168f, 0.0f, -0.188f, 2.148f, 1.683f, -0.652f, 0.0f};
        private static final float[] kColorConversion601 = {1.164f, 1.164f, 1.164f, 0.0f, -0.392f, 2.017f, 1.596f, -0.813f, 0.0f};
        private static final float[] kColorConversion709 = {1.164f, 1.164f, 1.164f, 0.0f, -0.213f, 2.112f, 1.793f, -0.533f, 0.0f};
        private int colorMatrixLocation;
        private final AtomicReference<VideoDecoderOutputBuffer> pendingOutputBufferReference = new AtomicReference<>();
        private final int[] previousStrides = new int[3];
        private final int[] previousWidths = new int[3];
        private GlProgram program;
        private VideoDecoderOutputBuffer renderedOutputBuffer;
        private final GLSurfaceView surfaceView;
        private final int[] texLocations = new int[3];
        private final FloatBuffer[] textureCoords = new FloatBuffer[3];
        private final int[] yuvTextures = new int[3];

        public Renderer(GLSurfaceView gLSurfaceView) {
            this.surfaceView = gLSurfaceView;
            for (int i3 = 0; i3 < 3; i3++) {
                int[] iArr = this.previousWidths;
                this.previousStrides[i3] = -1;
                iArr[i3] = -1;
            }
        }

        @RequiresNonNull({"program"})
        private void setupTextures() {
            GLES20.glGenTextures(3, this.yuvTextures, 0);
            for (int i3 = 0; i3 < 3; i3++) {
                GLES20.glUniform1i(this.program.getUniformLocation(TEXTURE_UNIFORMS[i3]), i3);
                GLES20.glActiveTexture(33984 + i3);
                GlUtil.bindTexture(3553, this.yuvTextures[i3]);
            }
            GlUtil.checkGlError();
        }

        public void onDrawFrame(GL10 gl10) {
            VideoDecoderOutputBuffer andSet = this.pendingOutputBufferReference.getAndSet((Object) null);
            if (andSet != null || this.renderedOutputBuffer != null) {
                if (andSet != null) {
                    VideoDecoderOutputBuffer videoDecoderOutputBuffer = this.renderedOutputBuffer;
                    if (videoDecoderOutputBuffer != null) {
                        videoDecoderOutputBuffer.release();
                    }
                    this.renderedOutputBuffer = andSet;
                }
                VideoDecoderOutputBuffer videoDecoderOutputBuffer2 = (VideoDecoderOutputBuffer) Assertions.checkNotNull(this.renderedOutputBuffer);
                float[] fArr = kColorConversion709;
                int i3 = videoDecoderOutputBuffer2.colorspace;
                if (i3 == 1) {
                    fArr = kColorConversion601;
                } else if (i3 == 3) {
                    fArr = kColorConversion2020;
                }
                GLES20.glUniformMatrix3fv(this.colorMatrixLocation, 1, false, fArr, 0);
                int[] iArr = (int[]) Assertions.checkNotNull(videoDecoderOutputBuffer2.yuvStrides);
                ByteBuffer[] byteBufferArr = (ByteBuffer[]) Assertions.checkNotNull(videoDecoderOutputBuffer2.yuvPlanes);
                for (int i4 = 0; i4 < 3; i4++) {
                    int i5 = videoDecoderOutputBuffer2.height;
                    if (i4 != 0) {
                        i5 = (i5 + 1) / 2;
                    }
                    int i6 = i5;
                    GLES20.glActiveTexture(33984 + i4);
                    GLES20.glBindTexture(3553, this.yuvTextures[i4]);
                    GLES20.glPixelStorei(3317, 1);
                    GLES20.glTexImage2D(3553, 0, 6409, iArr[i4], i6, 0, 6409, 5121, byteBufferArr[i4]);
                }
                int[] iArr2 = new int[3];
                int i7 = videoDecoderOutputBuffer2.width;
                iArr2[0] = i7;
                int i8 = (i7 + 1) / 2;
                iArr2[2] = i8;
                iArr2[1] = i8;
                for (int i9 = 0; i9 < 3; i9++) {
                    if (this.previousWidths[i9] != iArr2[i9] || this.previousStrides[i9] != iArr[i9]) {
                        Assertions.checkState(iArr[i9] != 0);
                        float f2 = ((float) iArr2[i9]) / ((float) iArr[i9]);
                        this.textureCoords[i9] = GlUtil.createBuffer(new float[]{0.0f, 0.0f, 0.0f, 1.0f, f2, 0.0f, f2, 1.0f});
                        GLES20.glVertexAttribPointer(this.texLocations[i9], 2, 5126, false, 0, this.textureCoords[i9]);
                        this.previousWidths[i9] = iArr2[i9];
                        this.previousStrides[i9] = iArr[i9];
                    }
                }
                GLES20.glClear(16384);
                GLES20.glDrawArrays(5, 0, 4);
                GlUtil.checkGlError();
            }
        }

        public void onSurfaceChanged(GL10 gl10, int i3, int i4) {
            GLES20.glViewport(0, 0, i3, i4);
        }

        public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
            GlProgram glProgram = new GlProgram(VERTEX_SHADER, FRAGMENT_SHADER);
            this.program = glProgram;
            GLES20.glVertexAttribPointer(glProgram.getAttributeArrayLocationAndEnable("in_pos"), 2, 5126, false, 0, TEXTURE_VERTICES);
            this.texLocations[0] = this.program.getAttributeArrayLocationAndEnable("in_tc_y");
            this.texLocations[1] = this.program.getAttributeArrayLocationAndEnable("in_tc_u");
            this.texLocations[2] = this.program.getAttributeArrayLocationAndEnable("in_tc_v");
            this.colorMatrixLocation = this.program.getUniformLocation("mColorConversion");
            GlUtil.checkGlError();
            setupTextures();
            GlUtil.checkGlError();
        }

        public void setOutputBuffer(VideoDecoderOutputBuffer videoDecoderOutputBuffer) {
            VideoDecoderOutputBuffer andSet = this.pendingOutputBufferReference.getAndSet(videoDecoderOutputBuffer);
            if (andSet != null) {
                andSet.release();
            }
            this.surfaceView.requestRender();
        }
    }

    public VideoDecoderGLSurfaceView(Context context) {
        this(context, (AttributeSet) null);
    }

    @Deprecated
    public VideoDecoderOutputBufferRenderer getVideoDecoderOutputBufferRenderer() {
        return this;
    }

    public void setOutputBuffer(VideoDecoderOutputBuffer videoDecoderOutputBuffer) {
        this.renderer.setOutputBuffer(videoDecoderOutputBuffer);
    }

    public VideoDecoderGLSurfaceView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Renderer renderer2 = new Renderer(this);
        this.renderer = renderer2;
        setPreserveEGLContextOnPause(true);
        setEGLContextClientVersion(2);
        setRenderer(renderer2);
        setRenderMode(0);
    }
}
