package com.appsamurai.storyly.exoplayer2.common.util;

import android.content.Context;
import android.opengl.GLES20;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

public final class GlProgram {
    private static final int GL_SAMPLER_EXTERNAL_2D_Y2Y_EXT = 35815;
    private final Map<String, Attribute> attributeByName;
    private final Attribute[] attributes;
    private final int programId;
    private final Map<String, Uniform> uniformByName;
    private final Uniform[] uniforms;

    public static final class Attribute {
        @Nullable
        private Buffer buffer;
        private final int index;
        private final int location;
        public final String name;
        private int size;

        private Attribute(String str, int i3, int i4) {
            this.name = str;
            this.index = i3;
            this.location = i4;
        }

        public static Attribute create(int i3, int i4) {
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(i3, 35722, iArr, 0);
            int i5 = iArr[0];
            byte[] bArr = new byte[i5];
            GLES20.glGetActiveAttrib(i3, i4, i5, new int[1], 0, new int[1], 0, new int[1], 0, bArr, 0);
            String str = new String(bArr, 0, GlProgram.getCStringLength(bArr));
            return new Attribute(str, i4, GlProgram.getAttributeLocation(i3, str));
        }

        public void bind() {
            GLES20.glBindBuffer(34962, 0);
            GLES20.glVertexAttribPointer(this.location, this.size, 5126, false, 0, (Buffer) Assertions.checkNotNull(this.buffer, "call setBuffer before bind"));
            GLES20.glEnableVertexAttribArray(this.index);
            GlUtil.checkGlError();
        }

        public void setBuffer(float[] fArr, int i3) {
            this.buffer = GlUtil.createBuffer(fArr);
            this.size = i3;
        }
    }

    public static final class Uniform {
        private final int location;
        public final String name;
        private int texId;
        private int texUnitIndex;
        private final int type;
        private final float[] value = new float[16];

        private Uniform(String str, int i3, int i4) {
            this.name = str;
            this.location = i3;
            this.type = i4;
        }

        public static Uniform create(int i3, int i4) {
            int i5 = i3;
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(i3, 35719, iArr, 0);
            int[] iArr2 = new int[1];
            int i6 = iArr[0];
            byte[] bArr = new byte[i6];
            GLES20.glGetActiveUniform(i3, i4, i6, new int[1], 0, new int[1], 0, iArr2, 0, bArr, 0);
            String str = new String(bArr, 0, GlProgram.getCStringLength(bArr));
            return new Uniform(str, GlProgram.getUniformLocation(i3, str), iArr2[0]);
        }

        public void bind() {
            switch (this.type) {
                case 5126:
                    GLES20.glUniform1fv(this.location, 1, this.value, 0);
                    GlUtil.checkGlError();
                    return;
                case 35664:
                    GLES20.glUniform2fv(this.location, 1, this.value, 0);
                    GlUtil.checkGlError();
                    return;
                case 35665:
                    GLES20.glUniform3fv(this.location, 1, this.value, 0);
                    GlUtil.checkGlError();
                    return;
                case 35675:
                    GLES20.glUniformMatrix3fv(this.location, 1, false, this.value, 0);
                    GlUtil.checkGlError();
                    return;
                case 35676:
                    GLES20.glUniformMatrix4fv(this.location, 1, false, this.value, 0);
                    GlUtil.checkGlError();
                    return;
                case 35678:
                case GlProgram.GL_SAMPLER_EXTERNAL_2D_Y2Y_EXT /*35815*/:
                case 36198:
                    if (this.texId != 0) {
                        GLES20.glActiveTexture(this.texUnitIndex + 33984);
                        GlUtil.checkGlError();
                        GlUtil.bindTexture(this.type == 35678 ? 3553 : 36197, this.texId);
                        GLES20.glUniform1i(this.location, this.texUnitIndex);
                        GlUtil.checkGlError();
                        return;
                    }
                    throw new IllegalStateException("No call to setSamplerTexId() before bind.");
                default:
                    throw new IllegalStateException("Unexpected uniform type: " + this.type);
            }
        }

        public void setFloat(float f2) {
            this.value[0] = f2;
        }

        public void setFloats(float[] fArr) {
            System.arraycopy(fArr, 0, this.value, 0, fArr.length);
        }

        public void setSamplerTexId(int i3, int i4) {
            this.texId = i3;
            this.texUnitIndex = i4;
        }
    }

    public GlProgram(Context context, String str, String str2) throws IOException {
        this(GlUtil.loadAsset(context, str), GlUtil.loadAsset(context, str2));
    }

    private static void addShader(int i3, int i4, String str) {
        int glCreateShader = GLES20.glCreateShader(i4);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = {0};
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 1) {
            GlUtil.throwGlException(GLES20.glGetShaderInfoLog(glCreateShader) + ", source: " + str);
        }
        GLES20.glAttachShader(i3, glCreateShader);
        GLES20.glDeleteShader(glCreateShader);
        GlUtil.checkGlError();
    }

    /* access modifiers changed from: private */
    public static int getAttributeLocation(int i3, String str) {
        return GLES20.glGetAttribLocation(i3, str);
    }

    /* access modifiers changed from: private */
    public static int getCStringLength(byte[] bArr) {
        for (int i3 = 0; i3 < bArr.length; i3++) {
            if (bArr[i3] == 0) {
                return i3;
            }
        }
        return bArr.length;
    }

    /* access modifiers changed from: private */
    public static int getUniformLocation(int i3, String str) {
        return GLES20.glGetUniformLocation(i3, str);
    }

    public void bindAttributesAndUniforms() {
        for (Attribute bind : this.attributes) {
            bind.bind();
        }
        for (Uniform bind2 : this.uniforms) {
            bind2.bind();
        }
    }

    public void delete() {
        GLES20.glDeleteProgram(this.programId);
        GlUtil.checkGlError();
    }

    public int getAttributeArrayLocationAndEnable(String str) {
        int attributeLocation = getAttributeLocation(str);
        GLES20.glEnableVertexAttribArray(attributeLocation);
        GlUtil.checkGlError();
        return attributeLocation;
    }

    public void setBufferAttribute(String str, float[] fArr, int i3) {
        ((Attribute) Assertions.checkNotNull(this.attributeByName.get(str))).setBuffer(fArr, i3);
    }

    public void setFloatUniform(String str, float f2) {
        ((Uniform) Assertions.checkNotNull(this.uniformByName.get(str))).setFloat(f2);
    }

    public void setFloatsUniform(String str, float[] fArr) {
        ((Uniform) Assertions.checkNotNull(this.uniformByName.get(str))).setFloats(fArr);
    }

    public void setSamplerTexIdUniform(String str, int i3, int i4) {
        ((Uniform) Assertions.checkNotNull(this.uniformByName.get(str))).setSamplerTexId(i3, i4);
    }

    public void use() {
        GLES20.glUseProgram(this.programId);
        GlUtil.checkGlError();
    }

    private int getAttributeLocation(String str) {
        return getAttributeLocation(this.programId, str);
    }

    public int getUniformLocation(String str) {
        return getUniformLocation(this.programId, str);
    }

    public GlProgram(String str, String str2) {
        int glCreateProgram = GLES20.glCreateProgram();
        this.programId = glCreateProgram;
        GlUtil.checkGlError();
        addShader(glCreateProgram, 35633, str);
        addShader(glCreateProgram, 35632, str2);
        GLES20.glLinkProgram(glCreateProgram);
        int[] iArr = {0};
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] != 1) {
            GlUtil.throwGlException("Unable to link shader program: \n" + GLES20.glGetProgramInfoLog(glCreateProgram));
        }
        GLES20.glUseProgram(glCreateProgram);
        this.attributeByName = new HashMap();
        int[] iArr2 = new int[1];
        GLES20.glGetProgramiv(glCreateProgram, 35721, iArr2, 0);
        this.attributes = new Attribute[iArr2[0]];
        for (int i3 = 0; i3 < iArr2[0]; i3++) {
            Attribute create = Attribute.create(this.programId, i3);
            this.attributes[i3] = create;
            this.attributeByName.put(create.name, create);
        }
        this.uniformByName = new HashMap();
        int[] iArr3 = new int[1];
        GLES20.glGetProgramiv(this.programId, 35718, iArr3, 0);
        this.uniforms = new Uniform[iArr3[0]];
        for (int i4 = 0; i4 < iArr3[0]; i4++) {
            Uniform create2 = Uniform.create(this.programId, i4);
            this.uniforms[i4] = create2;
            this.uniformByName.put(create2.name, create2);
        }
        GlUtil.checkGlError();
    }
}
