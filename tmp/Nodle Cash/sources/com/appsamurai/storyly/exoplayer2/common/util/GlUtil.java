package com.appsamurai.storyly.exoplayer2.common.util;

import android.content.Context;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.opengl.GLU;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.work.Data;
import com.appsamurai.storyly.exoplayer2.common.a;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.List;

public final class GlUtil {
    private static final int[] EGL_CONFIG_ATTRIBUTES_RGBA_1010102 = {12352, 4, 12324, 10, 12323, 10, 12322, 10, 12321, 2, 12325, 0, 12326, 0, 12344};
    private static final int[] EGL_CONFIG_ATTRIBUTES_RGBA_8888 = {12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12326, 0, 12344};
    private static final int EGL_GL_COLORSPACE_BT2020_PQ_EXT = 13120;
    private static final int EGL_GL_COLORSPACE_KHR = 12445;
    private static final int[] EGL_WINDOW_SURFACE_ATTRIBUTES_BT2020_PQ = {12445, EGL_GL_COLORSPACE_BT2020_PQ_EXT, 12344};
    private static final int[] EGL_WINDOW_SURFACE_ATTRIBUTES_NONE = {12344};
    private static final String EXTENSION_PROTECTED_CONTENT = "EGL_EXT_protected_content";
    private static final String EXTENSION_SURFACELESS_CONTEXT = "EGL_KHR_surfaceless_context";
    public static final int HOMOGENEOUS_COORDINATE_VECTOR_SIZE = 4;
    public static final float LENGTH_NDC = 2.0f;
    private static final String TAG = "GlUtil";
    public static boolean glAssertionsEnabled = false;

    @RequiresApi(17)
    public static final class Api17 {
        private Api17() {
        }

        @DoNotInline
        public static EGLContext createEglContext(EGLDisplay eGLDisplay, int i3, int[] iArr) {
            EGLContext eglCreateContext = EGL14.eglCreateContext(eGLDisplay, getEglConfig(eGLDisplay, iArr), EGL14.EGL_NO_CONTEXT, new int[]{12440, i3, 12344}, 0);
            if (eglCreateContext == null) {
                EGL14.eglTerminate(eGLDisplay);
                GlUtil.throwGlException("eglCreateContext() failed to create a valid context. The device may not support EGL version " + i3);
            }
            GlUtil.checkGlError();
            return eglCreateContext;
        }

        @DoNotInline
        public static EGLDisplay createEglDisplay() {
            EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
            GlUtil.checkEglException(!eglGetDisplay.equals(EGL14.EGL_NO_DISPLAY), "No EGL display.");
            if (!EGL14.eglInitialize(eglGetDisplay, new int[1], 0, new int[1], 0)) {
                GlUtil.throwGlException("Error in eglInitialize.");
            }
            GlUtil.checkGlError();
            return eglGetDisplay;
        }

        @DoNotInline
        public static EGLSurface createEglPbufferSurface(EGLDisplay eGLDisplay, int[] iArr, int[] iArr2) {
            EGLSurface eglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(eGLDisplay, getEglConfig(eGLDisplay, iArr), iArr2, 0);
            GlUtil.checkEglException("Error creating surface");
            return eglCreatePbufferSurface;
        }

        @DoNotInline
        public static void destroyEglContext(@Nullable EGLDisplay eGLDisplay, @Nullable EGLContext eGLContext) {
            if (eGLDisplay != null) {
                EGLSurface eGLSurface = EGL14.EGL_NO_SURFACE;
                EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, EGL14.EGL_NO_CONTEXT);
                GlUtil.checkEglException("Error releasing context");
                if (eGLContext != null) {
                    EGL14.eglDestroyContext(eGLDisplay, eGLContext);
                    GlUtil.checkEglException("Error destroying context");
                }
                EGL14.eglReleaseThread();
                GlUtil.checkEglException("Error releasing thread");
                EGL14.eglTerminate(eGLDisplay);
                GlUtil.checkEglException("Error terminating display");
            }
        }

        @DoNotInline
        public static void focusRenderTarget(EGLDisplay eGLDisplay, EGLContext eGLContext, EGLSurface eGLSurface, int i3, int i4, int i5) {
            int[] iArr = new int[1];
            GLES20.glGetIntegerv(36006, iArr, 0);
            if (iArr[0] != i3) {
                GLES20.glBindFramebuffer(36160, i3);
            }
            GlUtil.checkGlError();
            EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, eGLContext);
            GlUtil.checkEglException("Error making context current");
            GLES20.glViewport(0, 0, i4, i5);
            GlUtil.checkGlError();
        }

        @DoNotInline
        private static EGLConfig getEglConfig(EGLDisplay eGLDisplay, int[] iArr) {
            EGLConfig[] eGLConfigArr = new EGLConfig[1];
            if (!EGL14.eglChooseConfig(eGLDisplay, iArr, 0, eGLConfigArr, 0, 1, new int[1], 0)) {
                GlUtil.throwGlException("eglChooseConfig failed.");
            }
            return eGLConfigArr[0];
        }

        @DoNotInline
        public static EGLSurface getEglSurface(EGLDisplay eGLDisplay, Object obj, int[] iArr, int[] iArr2) {
            EGLSurface eglCreateWindowSurface = EGL14.eglCreateWindowSurface(eGLDisplay, getEglConfig(eGLDisplay, iArr), obj, iArr2, 0);
            GlUtil.checkEglException("Error creating surface");
            return eglCreateWindowSurface;
        }
    }

    public static final class GlException extends RuntimeException {
        public GlException(String str) {
            super(str);
        }
    }

    private GlUtil() {
    }

    public static void assertValidTextureSize(int i3, int i4) {
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(3379, iArr, 0);
        int i5 = iArr[0];
        if (i3 < 0 || i4 < 0) {
            throwGlException("width or height is less than 0");
        }
        if (i3 > i5 || i4 > i5) {
            throwGlException("width or height is greater than GL_MAX_TEXTURE_SIZE " + i5);
        }
    }

    public static void bindTexture(int i3, int i4) {
        GLES20.glBindTexture(i3, i4);
        checkGlError();
        GLES20.glTexParameteri(i3, Data.MAX_DATA_BYTES, 9729);
        checkGlError();
        GLES20.glTexParameteri(i3, 10241, 9729);
        checkGlError();
        GLES20.glTexParameteri(i3, 10242, 33071);
        checkGlError();
        GLES20.glTexParameteri(i3, 10243, 33071);
        checkGlError();
    }

    /* access modifiers changed from: private */
    public static void checkEglException(boolean z2, String str) {
        if (!z2) {
            throwGlException(str);
        }
    }

    public static void checkGlError() {
        int i3 = 0;
        while (true) {
            int glGetError = GLES20.glGetError();
            if (glGetError == 0) {
                break;
            }
            Log.e(TAG, "glError: " + GLU.gluErrorString(glGetError));
            i3 = glGetError;
        }
        if (i3 != 0) {
            throwGlException("glError: " + GLU.gluErrorString(i3));
        }
    }

    public static FloatBuffer createBuffer(float[] fArr) {
        return (FloatBuffer) createBuffer(fArr.length).put(fArr).flip();
    }

    @RequiresApi(17)
    public static EGLContext createEglContext(EGLDisplay eGLDisplay) {
        return Api17.createEglContext(eGLDisplay, 2, EGL_CONFIG_ATTRIBUTES_RGBA_8888);
    }

    @RequiresApi(17)
    public static EGLContext createEglContextEs3Rgba1010102(EGLDisplay eGLDisplay) {
        return Api17.createEglContext(eGLDisplay, 3, EGL_CONFIG_ATTRIBUTES_RGBA_1010102);
    }

    @RequiresApi(17)
    public static EGLDisplay createEglDisplay() {
        return Api17.createEglDisplay();
    }

    public static int createExternalTexture() {
        int generateTexture = generateTexture();
        bindTexture(36197, generateTexture);
        return generateTexture;
    }

    public static int createFboForTexture(int i3) {
        checkEglException(!Util.areEqual(EGL14.eglGetCurrentContext(), EGL14.EGL_NO_CONTEXT), "No current context");
        int[] iArr = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        checkGlError();
        GLES20.glBindFramebuffer(36160, iArr[0]);
        checkGlError();
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, i3, 0);
        checkGlError();
        return iArr[0];
    }

    @RequiresApi(17)
    private static EGLSurface createPbufferSurface(EGLDisplay eGLDisplay, int i3, int i4) {
        return Api17.createEglPbufferSurface(eGLDisplay, EGL_CONFIG_ATTRIBUTES_RGBA_8888, new int[]{12375, i3, 12374, i4, 12344});
    }

    @RequiresApi(17)
    public static EGLSurface createPlaceholderEglSurface(EGLDisplay eGLDisplay) {
        return isSurfacelessContextExtensionSupported() ? EGL14.EGL_NO_SURFACE : createPbufferSurface(eGLDisplay, 1, 1);
    }

    public static int createTexture(int i3, int i4) {
        assertValidTextureSize(i3, i4);
        int generateTexture = generateTexture();
        bindTexture(3553, generateTexture);
        GLES20.glTexImage2D(3553, 0, 6408, i3, i4, 0, 6408, 5121, ByteBuffer.allocateDirect(i3 * i4 * 4));
        checkGlError();
        return generateTexture;
    }

    public static float[] createVertexBuffer(List<float[]> list) {
        float[] fArr = new float[(list.size() * 4)];
        for (int i3 = 0; i3 < list.size(); i3++) {
            System.arraycopy(list.get(i3), 0, fArr, i3 * 4, 4);
        }
        return fArr;
    }

    public static void deleteTexture(int i3) {
        GLES20.glDeleteTextures(1, new int[]{i3}, 0);
        checkGlError();
    }

    @RequiresApi(17)
    public static void destroyEglContext(@Nullable EGLDisplay eGLDisplay, @Nullable EGLContext eGLContext) {
        Api17.destroyEglContext(eGLDisplay, eGLContext);
    }

    @RequiresApi(17)
    public static void focusEglSurface(EGLDisplay eGLDisplay, EGLContext eGLContext, EGLSurface eGLSurface, int i3, int i4) {
        Api17.focusRenderTarget(eGLDisplay, eGLContext, eGLSurface, 0, i3, i4);
    }

    @RequiresApi(17)
    public static void focusFramebuffer(EGLDisplay eGLDisplay, EGLContext eGLContext, EGLSurface eGLSurface, int i3, int i4, int i5) {
        Api17.focusRenderTarget(eGLDisplay, eGLContext, eGLSurface, i3, i4, i5);
    }

    @RequiresApi(17)
    public static void focusPlaceholderEglSurface(EGLContext eGLContext, EGLDisplay eGLDisplay) {
        focusEglSurface(eGLDisplay, eGLContext, createPbufferSurface(eGLDisplay, 1, 1), 1, 1);
    }

    @RequiresApi(17)
    public static void focusPlaceholderEglSurfaceBt2020Pq(EGLContext eGLContext, EGLDisplay eGLDisplay) {
        focusEglSurface(eGLDisplay, eGLContext, Api17.createEglPbufferSurface(eGLDisplay, EGL_CONFIG_ATTRIBUTES_RGBA_1010102, new int[]{12375, 1, 12374, 1, 12445, EGL_GL_COLORSPACE_BT2020_PQ_EXT, 12344}), 1, 1);
    }

    private static int generateTexture() {
        checkEglException(!Util.areEqual(EGL14.eglGetCurrentContext(), EGL14.EGL_NO_CONTEXT), "No current context");
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        checkGlError();
        return iArr[0];
    }

    @RequiresApi(17)
    public static EGLSurface getEglSurface(EGLDisplay eGLDisplay, Object obj) {
        return Api17.getEglSurface(eGLDisplay, obj, EGL_CONFIG_ATTRIBUTES_RGBA_8888, EGL_WINDOW_SURFACE_ATTRIBUTES_NONE);
    }

    @RequiresApi(17)
    public static EGLSurface getEglSurfaceBt2020Pq(EGLDisplay eGLDisplay, Object obj) {
        return Api17.getEglSurface(eGLDisplay, obj, EGL_CONFIG_ATTRIBUTES_RGBA_1010102, EGL_WINDOW_SURFACE_ATTRIBUTES_BT2020_PQ);
    }

    public static float[] getNormalizedCoordinateBounds() {
        return new float[]{-1.0f, -1.0f, 0.0f, 1.0f, 1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f};
    }

    public static float[] getTextureCoordinateBounds() {
        return new float[]{0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f};
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        r5 = android.opengl.EGL14.eglQueryString(android.opengl.EGL14.eglGetDisplay(0), 12373);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isProtectedContentExtensionSupported(android.content.Context r5) {
        /*
            int r0 = com.appsamurai.storyly.exoplayer2.common.util.Util.SDK_INT
            r1 = 24
            r2 = 0
            if (r0 >= r1) goto L_0x0008
            return r2
        L_0x0008:
            r1 = 26
            if (r0 >= r1) goto L_0x0021
            java.lang.String r3 = "samsung"
            java.lang.String r4 = com.appsamurai.storyly.exoplayer2.common.util.Util.MANUFACTURER
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0020
            java.lang.String r3 = "XT1650"
            java.lang.String r4 = com.appsamurai.storyly.exoplayer2.common.util.Util.MODEL
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0021
        L_0x0020:
            return r2
        L_0x0021:
            if (r0 >= r1) goto L_0x0030
            android.content.pm.PackageManager r5 = r5.getPackageManager()
            java.lang.String r0 = "android.hardware.vr.high_performance"
            boolean r5 = r5.hasSystemFeature(r0)
            if (r5 != 0) goto L_0x0030
            return r2
        L_0x0030:
            android.opengl.EGLDisplay r5 = android.opengl.EGL14.eglGetDisplay(r2)
            r0 = 12373(0x3055, float:1.7338E-41)
            java.lang.String r5 = android.opengl.EGL14.eglQueryString(r5, r0)
            if (r5 == 0) goto L_0x0045
            java.lang.String r0 = "EGL_EXT_protected_content"
            boolean r5 = r5.contains(r0)
            if (r5 == 0) goto L_0x0045
            r2 = 1
        L_0x0045:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.common.util.GlUtil.isProtectedContentExtensionSupported(android.content.Context):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        r0 = android.opengl.EGL14.eglQueryString(android.opengl.EGL14.eglGetDisplay(0), 12373);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isSurfacelessContextExtensionSupported() {
        /*
            int r0 = com.appsamurai.storyly.exoplayer2.common.util.Util.SDK_INT
            r1 = 17
            r2 = 0
            if (r0 >= r1) goto L_0x0008
            return r2
        L_0x0008:
            android.opengl.EGLDisplay r0 = android.opengl.EGL14.eglGetDisplay(r2)
            r1 = 12373(0x3055, float:1.7338E-41)
            java.lang.String r0 = android.opengl.EGL14.eglQueryString(r0, r1)
            if (r0 == 0) goto L_0x001d
            java.lang.String r1 = "EGL_KHR_surfaceless_context"
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L_0x001d
            r2 = 1
        L_0x001d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.common.util.GlUtil.isSurfacelessContextExtensionSupported():boolean");
    }

    public static String loadAsset(Context context, String str) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(str);
            return Util.fromUtf8Bytes(Util.toByteArray(inputStream));
        } finally {
            Util.closeQuietly(inputStream);
        }
    }

    public static void throwGlException(String str) {
        if (!glAssertionsEnabled) {
            Log.e(TAG, str);
            return;
        }
        throw new GlException(str);
    }

    /* access modifiers changed from: private */
    public static void checkEglException(String str) {
        int eglGetError = EGL14.eglGetError();
        checkEglException(eglGetError == 12288, a.b(eglGetError, str, ", error code: "));
    }

    public static FloatBuffer createBuffer(int i3) {
        return ByteBuffer.allocateDirect(i3 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    }
}
