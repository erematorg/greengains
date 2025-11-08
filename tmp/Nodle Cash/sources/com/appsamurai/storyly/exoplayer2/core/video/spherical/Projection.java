package com.appsamurai.storyly.exoplayer2.core.video.spherical;

import androidx.constraintlayout.core.state.b;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

final class Projection {
    public static final int DRAW_MODE_TRIANGLES = 0;
    public static final int DRAW_MODE_TRIANGLES_FAN = 2;
    public static final int DRAW_MODE_TRIANGLES_STRIP = 1;
    public static final int POSITION_COORDS_PER_VERTEX = 3;
    public static final int TEXTURE_COORDS_PER_VERTEX = 2;
    public final Mesh leftMesh;
    public final Mesh rightMesh;
    public final boolean singleMesh;
    public final int stereoMode;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DrawMode {
    }

    public static final class Mesh {
        private final SubMesh[] subMeshes;

        public Mesh(SubMesh... subMeshArr) {
            this.subMeshes = subMeshArr;
        }

        public SubMesh getSubMesh(int i3) {
            return this.subMeshes[i3];
        }

        public int getSubMeshCount() {
            return this.subMeshes.length;
        }
    }

    public static final class SubMesh {
        public static final int VIDEO_TEXTURE_ID = 0;
        public final int mode;
        public final float[] textureCoords;
        public final int textureId;
        public final float[] vertices;

        public SubMesh(int i3, float[] fArr, float[] fArr2, int i4) {
            this.textureId = i3;
            Assertions.checkArgument(((long) fArr.length) * 2 == ((long) fArr2.length) * 3);
            this.vertices = fArr;
            this.textureCoords = fArr2;
            this.mode = i4;
        }

        public int getVertexCount() {
            return this.vertices.length / 3;
        }
    }

    public Projection(Mesh mesh, int i3) {
        this(mesh, mesh, i3);
    }

    public static Projection createEquirectangular(int i3) {
        return createEquirectangular(50.0f, 36, 72, 180.0f, 360.0f, i3);
    }

    public Projection(Mesh mesh, Mesh mesh2, int i3) {
        this.leftMesh = mesh;
        this.rightMesh = mesh2;
        this.stereoMode = i3;
        this.singleMesh = mesh == mesh2;
    }

    public static Projection createEquirectangular(float f2, int i3, int i4, float f3, float f4, int i5) {
        int i6;
        float f5;
        float[] fArr;
        int i7;
        int i8;
        int i9;
        int i10;
        float f6 = f2;
        int i11 = i3;
        int i12 = i4;
        float f7 = f3;
        float f8 = f4;
        Assertions.checkArgument(f6 > 0.0f);
        Assertions.checkArgument(i11 >= 1);
        Assertions.checkArgument(i12 >= 1);
        Assertions.checkArgument(f7 > 0.0f && f7 <= 180.0f);
        Assertions.checkArgument(f8 > 0.0f && f8 <= 360.0f);
        float radians = (float) Math.toRadians((double) f7);
        float radians2 = (float) Math.toRadians((double) f8);
        float f9 = radians / ((float) i11);
        float f10 = radians2 / ((float) i12);
        int i13 = i12 + 1;
        int A2 = b.A(i13, 2, 2, i11);
        float[] fArr2 = new float[(A2 * 3)];
        float[] fArr3 = new float[(A2 * 2)];
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        while (i14 < i11) {
            float f11 = radians / 2.0f;
            float f12 = (((float) i14) * f9) - f11;
            int i17 = i14 + 1;
            float f13 = (((float) i17) * f9) - f11;
            int i18 = 0;
            while (i18 < i13) {
                float f14 = f12;
                int i19 = i17;
                int i20 = 2;
                int i21 = 0;
                while (i21 < i20) {
                    if (i21 == 0) {
                        f5 = f14;
                        i6 = i13;
                    } else {
                        i6 = i13;
                        f5 = f13;
                    }
                    float f15 = ((float) i18) * f10;
                    float f16 = f10;
                    int i22 = i18;
                    double d2 = (double) f6;
                    float f17 = f9;
                    double d3 = (double) ((f15 + 3.1415927f) - (radians2 / 2.0f));
                    int i23 = i21;
                    double d4 = (double) f5;
                    float[] fArr4 = fArr3;
                    float f18 = f13;
                    fArr2[i15] = -((float) (Math.cos(d4) * Math.sin(d3) * d2));
                    float f19 = radians;
                    float f20 = radians2;
                    fArr2[i15 + 1] = (float) (Math.sin(d4) * d2);
                    int i24 = i15 + 3;
                    fArr2[i15 + 2] = (float) (Math.cos(d4) * Math.cos(d3) * d2);
                    fArr4[i16] = f15 / f20;
                    int i25 = i16 + 2;
                    fArr4[i16 + 1] = (((float) (i14 + i23)) * f17) / f19;
                    if (i22 == 0 && i23 == 0) {
                        i10 = i4;
                        i9 = i22;
                        i7 = i23;
                    } else {
                        i10 = i4;
                        i9 = i22;
                        i7 = i23;
                        if (!(i9 == i10 && i7 == 1)) {
                            fArr = fArr4;
                            i8 = 2;
                            i16 = i25;
                            i15 = i24;
                            i21 = i7 + 1;
                            i12 = i10;
                            i18 = i9;
                            fArr3 = fArr;
                            radians = f19;
                            i13 = i6;
                            f10 = f16;
                            radians2 = f20;
                            f13 = f18;
                            i20 = i8;
                            f9 = f17;
                        }
                    }
                    System.arraycopy(fArr2, i15, fArr2, i24, 3);
                    i15 += 6;
                    fArr = fArr4;
                    i8 = 2;
                    System.arraycopy(fArr, i16, fArr, i25, 2);
                    i16 += 4;
                    i21 = i7 + 1;
                    i12 = i10;
                    i18 = i9;
                    fArr3 = fArr;
                    radians = f19;
                    i13 = i6;
                    f10 = f16;
                    radians2 = f20;
                    f13 = f18;
                    i20 = i8;
                    f9 = f17;
                }
                float f21 = radians2;
                float f22 = f9;
                float f23 = f10;
                int i26 = i20;
                int i27 = i13;
                float[] fArr5 = fArr3;
                float f24 = f13;
                int i28 = i18;
                int i29 = i12;
                float f25 = radians;
                int i30 = i28 + 1;
                i17 = i19;
                f9 = f22;
                radians2 = f21;
                f13 = f24;
                f12 = f14;
                int i31 = i30;
                i12 = i29;
                i18 = i31;
            }
            i11 = i3;
            i14 = i17;
        }
        return new Projection(new Mesh(new SubMesh(0, fArr2, fArr3, 1)), i5);
    }
}
