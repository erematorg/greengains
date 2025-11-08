package com.appsamurai.storyly.exoplayer2.core.video.spherical;

import android.opengl.GLES20;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.GlProgram;
import com.appsamurai.storyly.exoplayer2.common.util.GlUtil;
import com.appsamurai.storyly.exoplayer2.core.video.spherical.Projection;
import java.nio.FloatBuffer;

final class ProjectionRenderer {
    private static final String FRAGMENT_SHADER = "// This is required since the texture data is GL_TEXTURE_EXTERNAL_OES.\n#extension GL_OES_EGL_image_external : require\nprecision mediump float;\n// Standard texture rendering shader.\nuniform samplerExternalOES uTexture;\nvarying vec2 vTexCoords;\nvoid main() {\n  gl_FragColor = texture2D(uTexture, vTexCoords);\n}\n";
    private static final float[] TEX_MATRIX_BOTTOM = {1.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 1.0f, 1.0f};
    private static final float[] TEX_MATRIX_LEFT = {0.5f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 1.0f};
    private static final float[] TEX_MATRIX_RIGHT = {0.5f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.5f, 1.0f, 1.0f};
    private static final float[] TEX_MATRIX_TOP = {1.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.5f, 1.0f};
    private static final float[] TEX_MATRIX_WHOLE = {1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 1.0f};
    private static final String VERTEX_SHADER = "uniform mat4 uMvpMatrix;\nuniform mat3 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec2 aTexCoords;\nvarying vec2 vTexCoords;\n// Standard transformation.\nvoid main() {\n  gl_Position = uMvpMatrix * aPosition;\n  vTexCoords = (uTexMatrix * vec3(aTexCoords, 1)).xy;\n}\n";
    @Nullable
    private MeshData leftMeshData;
    private int mvpMatrixHandle;
    private int positionHandle;
    private GlProgram program;
    @Nullable
    private MeshData rightMeshData;
    private int stereoMode;
    private int texCoordsHandle;
    private int textureHandle;
    private int uTexMatrixHandle;

    public static class MeshData {
        /* access modifiers changed from: private */
        public final int drawMode;
        /* access modifiers changed from: private */
        public final FloatBuffer textureBuffer;
        /* access modifiers changed from: private */
        public final FloatBuffer vertexBuffer;
        /* access modifiers changed from: private */
        public final int vertexCount;

        public MeshData(Projection.SubMesh subMesh) {
            this.vertexCount = subMesh.getVertexCount();
            this.vertexBuffer = GlUtil.createBuffer(subMesh.vertices);
            this.textureBuffer = GlUtil.createBuffer(subMesh.textureCoords);
            int i3 = subMesh.mode;
            if (i3 == 1) {
                this.drawMode = 5;
            } else if (i3 != 2) {
                this.drawMode = 4;
            } else {
                this.drawMode = 6;
            }
        }
    }

    public static boolean isSupported(Projection projection) {
        Projection.Mesh mesh = projection.leftMesh;
        Projection.Mesh mesh2 = projection.rightMesh;
        return mesh.getSubMeshCount() == 1 && mesh.getSubMesh(0).textureId == 0 && mesh2.getSubMeshCount() == 1 && mesh2.getSubMesh(0).textureId == 0;
    }

    public void draw(int i3, float[] fArr, boolean z2) {
        MeshData meshData = z2 ? this.rightMeshData : this.leftMeshData;
        if (meshData != null) {
            int i4 = this.stereoMode;
            GLES20.glUniformMatrix3fv(this.uTexMatrixHandle, 1, false, i4 == 1 ? z2 ? TEX_MATRIX_BOTTOM : TEX_MATRIX_TOP : i4 == 2 ? z2 ? TEX_MATRIX_RIGHT : TEX_MATRIX_LEFT : TEX_MATRIX_WHOLE, 0);
            GLES20.glUniformMatrix4fv(this.mvpMatrixHandle, 1, false, fArr, 0);
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(36197, i3);
            GLES20.glUniform1i(this.textureHandle, 0);
            GlUtil.checkGlError();
            GLES20.glVertexAttribPointer(this.positionHandle, 3, 5126, false, 12, meshData.vertexBuffer);
            GlUtil.checkGlError();
            GLES20.glVertexAttribPointer(this.texCoordsHandle, 2, 5126, false, 8, meshData.textureBuffer);
            GlUtil.checkGlError();
            GLES20.glDrawArrays(meshData.drawMode, 0, meshData.vertexCount);
            GlUtil.checkGlError();
        }
    }

    public void init() {
        GlProgram glProgram = new GlProgram(VERTEX_SHADER, FRAGMENT_SHADER);
        this.program = glProgram;
        this.mvpMatrixHandle = glProgram.getUniformLocation("uMvpMatrix");
        this.uTexMatrixHandle = this.program.getUniformLocation("uTexMatrix");
        this.positionHandle = this.program.getAttributeArrayLocationAndEnable("aPosition");
        this.texCoordsHandle = this.program.getAttributeArrayLocationAndEnable("aTexCoords");
        this.textureHandle = this.program.getUniformLocation("uTexture");
    }

    public void setProjection(Projection projection) {
        if (isSupported(projection)) {
            this.stereoMode = projection.stereoMode;
            MeshData meshData = new MeshData(projection.leftMesh.getSubMesh(0));
            this.leftMeshData = meshData;
            if (!projection.singleMesh) {
                meshData = new MeshData(projection.rightMesh.getSubMesh(0));
            }
            this.rightMeshData = meshData;
        }
    }

    public void shutdown() {
        GlProgram glProgram = this.program;
        if (glProgram != null) {
            glProgram.delete();
        }
    }
}
