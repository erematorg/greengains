package com.google.maps.android.heatmaps;

import android.graphics.Bitmap;
import android.graphics.Color;
import androidx.collection.LongSparseArray;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileProvider;
import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.quadtree.PointQuadTree;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import jnr.ffi.provider.jffi.JNINativeInterface;

public class HeatmapTileProvider implements TileProvider {
    public static final Gradient DEFAULT_GRADIENT;
    private static final int[] DEFAULT_GRADIENT_COLORS;
    private static final float[] DEFAULT_GRADIENT_START_POINTS;
    private static final int DEFAULT_MAX_ZOOM = 11;
    private static final int DEFAULT_MIN_ZOOM = 5;
    public static final double DEFAULT_OPACITY = 0.7d;
    public static final int DEFAULT_RADIUS = 20;
    private static final int MAX_RADIUS = 50;
    private static final int MAX_ZOOM_LEVEL = 22;
    private static final int MIN_RADIUS = 10;
    private static final int SCREEN_SIZE = 1280;
    private static final int TILE_DIM = 512;
    static final double WORLD_WIDTH = 1.0d;
    private Bounds mBounds;
    private int[] mColorMap;
    private double mCustomMaxIntensity;
    private Collection<WeightedLatLng> mData;
    private Gradient mGradient;
    private double[] mKernel;
    private double[] mMaxIntensity;
    private double mOpacity;
    private int mRadius;
    private PointQuadTree<WeightedLatLng> mTree;

    public static class Builder {
        /* access modifiers changed from: private */
        public Collection<WeightedLatLng> data;
        /* access modifiers changed from: private */
        public Gradient gradient = HeatmapTileProvider.DEFAULT_GRADIENT;
        /* access modifiers changed from: private */
        public double intensity = 0.0d;
        /* access modifiers changed from: private */
        public double opacity = 0.7d;
        /* access modifiers changed from: private */
        public int radius = 20;

        public HeatmapTileProvider build() {
            if (this.data != null) {
                return new HeatmapTileProvider(this);
            }
            throw new IllegalStateException("No input data: you must use either .data or .weightedData before building");
        }

        public Builder data(Collection<LatLng> collection) {
            return weightedData(HeatmapTileProvider.wrapData(collection));
        }

        public Builder gradient(Gradient gradient2) {
            this.gradient = gradient2;
            return this;
        }

        public Builder maxIntensity(double d2) {
            this.intensity = d2;
            return this;
        }

        public Builder opacity(double d2) {
            this.opacity = d2;
            if (d2 >= 0.0d && d2 <= 1.0d) {
                return this;
            }
            throw new IllegalArgumentException("Opacity must be in range [0, 1]");
        }

        public Builder radius(int i3) {
            this.radius = i3;
            if (i3 >= 10 && i3 <= 50) {
                return this;
            }
            throw new IllegalArgumentException("Radius not within bounds.");
        }

        public Builder weightedData(Collection<WeightedLatLng> collection) {
            this.data = collection;
            if (!collection.isEmpty()) {
                return this;
            }
            throw new IllegalArgumentException("No input points.");
        }
    }

    static {
        int[] iArr = {Color.rgb(102, JNINativeInterface.ReleaseStringCritical, 0), Color.rgb(255, 0, 0)};
        DEFAULT_GRADIENT_COLORS = iArr;
        float[] fArr = {0.2f, 1.0f};
        DEFAULT_GRADIENT_START_POINTS = fArr;
        DEFAULT_GRADIENT = new Gradient(iArr, fArr);
    }

    public static Bitmap colorize(double[][] dArr, int[] iArr, double d2) {
        double[][] dArr2 = dArr;
        int[] iArr2 = iArr;
        int i3 = iArr2[iArr2.length - 1];
        double length = ((double) (iArr2.length - 1)) / d2;
        int length2 = dArr2.length;
        int[] iArr3 = new int[(length2 * length2)];
        for (int i4 = 0; i4 < length2; i4++) {
            for (int i5 = 0; i5 < length2; i5++) {
                double d3 = dArr2[i5][i4];
                int i6 = (i4 * length2) + i5;
                int i7 = (int) (d3 * length);
                if (d3 == 0.0d) {
                    iArr3[i6] = 0;
                } else if (i7 < iArr2.length) {
                    iArr3[i6] = iArr2[i7];
                } else {
                    iArr3[i6] = i3;
                }
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(length2, length2, Bitmap.Config.ARGB_8888);
        createBitmap.setPixels(iArr3, 0, length2, 0, 0, length2, length2);
        return createBitmap;
    }

    private static Tile convertBitmap(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return new Tile(512, 512, byteArrayOutputStream.toByteArray());
    }

    public static double[][] convolve(double[][] dArr, double[] dArr2) {
        double[][] dArr3 = dArr;
        double[] dArr4 = dArr2;
        int floor = (int) Math.floor(((double) dArr4.length) / 2.0d);
        int length = dArr3.length;
        int i3 = length - (floor * 2);
        int i4 = floor + i3;
        int i5 = i4 - 1;
        int[] iArr = new int[2];
        int i6 = 1;
        iArr[1] = length;
        int i7 = 0;
        iArr[0] = length;
        double[][] dArr5 = (double[][]) Array.newInstance(Double.TYPE, iArr);
        int i8 = 0;
        while (true) {
            double d2 = 0.0d;
            if (i8 >= length) {
                break;
            }
            int i9 = i7;
            while (i9 < length) {
                double d3 = dArr3[i8][i9];
                if (d3 != d2) {
                    int i10 = i8 + floor;
                    if (i5 < i10) {
                        i10 = i5;
                    }
                    int i11 = i10 + 1;
                    int i12 = i8 - floor;
                    for (int i13 = floor > i12 ? floor : i12; i13 < i11; i13++) {
                        double[] dArr6 = dArr5[i13];
                        dArr6[i9] = (dArr4[i13 - i12] * d3) + dArr6[i9];
                    }
                }
                i9++;
                d2 = 0.0d;
            }
            i8++;
            i7 = 0;
        }
        int[] iArr2 = new int[2];
        iArr2[1] = i3;
        int i14 = 0;
        iArr2[0] = i3;
        double[][] dArr7 = (double[][]) Array.newInstance(Double.TYPE, iArr2);
        int i15 = floor;
        while (i15 < i4) {
            int i16 = i14;
            while (i16 < length) {
                double d4 = dArr5[i15][i16];
                if (d4 != 0.0d) {
                    int i17 = i16 + floor;
                    if (i5 < i17) {
                        i17 = i5;
                    }
                    int i18 = i17 + i6;
                    int i19 = i16 - floor;
                    for (int i20 = floor > i19 ? floor : i19; i20 < i18; i20++) {
                        double[] dArr8 = dArr7[i15 - floor];
                        int i21 = i20 - floor;
                        dArr8[i21] = (dArr4[i20 - i19] * d4) + dArr8[i21];
                    }
                }
                i16++;
                i6 = 1;
            }
            i15++;
            i14 = 0;
            i6 = 1;
        }
        return dArr7;
    }

    public static double[] generateKernel(int i3, double d2) {
        double[] dArr = new double[((i3 * 2) + 1)];
        for (int i4 = -i3; i4 <= i3; i4++) {
            dArr[i4 + i3] = Math.exp(((double) ((-i4) * i4)) / ((2.0d * d2) * d2));
        }
        return dArr;
    }

    public static Bounds getBounds(Collection<WeightedLatLng> collection) {
        Iterator<WeightedLatLng> it = collection.iterator();
        WeightedLatLng next = it.next();
        double d2 = next.getPoint().f7188x;
        double d3 = next.getPoint().f7188x;
        double d4 = d2;
        double d5 = d3;
        double d6 = next.getPoint().f7189y;
        double d7 = next.getPoint().f7189y;
        while (it.hasNext()) {
            WeightedLatLng next2 = it.next();
            double d8 = next2.getPoint().f7188x;
            double d9 = next2.getPoint().f7189y;
            if (d8 < d4) {
                d4 = d8;
            }
            if (d8 > d5) {
                d5 = d8;
            }
            if (d9 < d6) {
                d6 = d9;
            }
            if (d9 > d7) {
                d7 = d9;
            }
        }
        return new Bounds(d4, d5, d6, d7);
    }

    private double[] getMaxIntensities(int i3) {
        int i4;
        double[] dArr = new double[22];
        if (this.mCustomMaxIntensity != 0.0d) {
            for (int i5 = 0; i5 < 22; i5++) {
                dArr[i5] = this.mCustomMaxIntensity;
            }
            return dArr;
        }
        int i6 = 5;
        while (true) {
            if (i6 >= 11) {
                break;
            }
            dArr[i6] = getMaxValue(this.mData, this.mBounds, i3, (int) (Math.pow(2.0d, (double) (i6 - 3)) * 1280.0d));
            if (i6 == 5) {
                for (int i7 = 0; i7 < i6; i7++) {
                    dArr[i7] = dArr[i6];
                }
            }
            i6++;
        }
        for (i4 = 11; i4 < 22; i4++) {
            dArr[i4] = dArr[10];
        }
        return dArr;
    }

    public static double getMaxValue(Collection<WeightedLatLng> collection, Bounds bounds, int i3, int i4) {
        Bounds bounds2 = bounds;
        double d2 = bounds2.minX;
        double d3 = bounds2.maxX;
        double d4 = bounds2.minY;
        double d5 = d3 - d2;
        double d6 = bounds2.maxY - d4;
        if (d5 <= d6) {
            d5 = d6;
        }
        double d7 = ((double) ((int) (((double) (i4 / (i3 * 2))) + 0.5d))) / d5;
        LongSparseArray longSparseArray = new LongSparseArray();
        double d8 = 0.0d;
        for (WeightedLatLng next : collection) {
            double d9 = next.getPoint().f7188x;
            int i5 = (int) ((next.getPoint().f7189y - d4) * d7);
            long j2 = (long) ((int) ((d9 - d2) * d7));
            LongSparseArray longSparseArray2 = (LongSparseArray) longSparseArray.get(j2);
            if (longSparseArray2 == null) {
                longSparseArray2 = new LongSparseArray();
                longSparseArray.put(j2, longSparseArray2);
            }
            long j3 = (long) i5;
            Double d10 = (Double) longSparseArray2.get(j3);
            if (d10 == null) {
                d10 = Double.valueOf(0.0d);
            }
            double intensity = next.getIntensity() + d10.doubleValue();
            longSparseArray2.put(j3, Double.valueOf(intensity));
            if (intensity > d8) {
                d8 = intensity;
            }
        }
        return d8;
    }

    /* access modifiers changed from: private */
    public static Collection<WeightedLatLng> wrapData(Collection<LatLng> collection) {
        ArrayList arrayList = new ArrayList();
        for (LatLng weightedLatLng : collection) {
            arrayList.add(new WeightedLatLng(weightedLatLng));
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.maps.model.Tile getTile(int r37, int r38, int r39) {
        /*
            r36 = this;
            r0 = r36
            r1 = r37
            r2 = r38
            r3 = r39
            double r4 = (double) r3
            r6 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r4 = java.lang.Math.pow(r6, r4)
            r8 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r4 = r8 / r4
            int r10 = r0.mRadius
            double r11 = (double) r10
            double r11 = r11 * r4
            r13 = 4647714815446351872(0x4080000000000000, double:512.0)
            double r11 = r11 / r13
            double r6 = r6 * r11
            double r6 = r6 + r4
            r13 = 2
            int r10 = r10 * r13
            int r10 = r10 + 512
            double r14 = (double) r10
            double r6 = r6 / r14
            double r14 = (double) r1
            double r14 = r14 * r4
            double r14 = r14 - r11
            r10 = 1
            int r1 = r1 + r10
            double r8 = (double) r1
            double r8 = r8 * r4
            double r19 = r8 + r11
            double r8 = (double) r2
            double r8 = r8 * r4
            double r8 = r8 - r11
            int r1 = r2 + 1
            double r1 = (double) r1
            double r1 = r1 * r4
            double r1 = r1 + r11
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r21 = 0
            int r5 = (r14 > r21 ? 1 : (r14 == r21 ? 0 : -1))
            if (r5 >= 0) goto L_0x005a
            com.google.maps.android.geometry.Bounds r4 = new com.google.maps.android.geometry.Bounds
            r16 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r22 = r14 + r16
            r24 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r21 = r4
            r26 = r8
            r28 = r1
            r21.<init>(r22, r24, r26, r28)
            com.google.maps.android.quadtree.PointQuadTree<com.google.maps.android.heatmaps.WeightedLatLng> r5 = r0.mTree
            java.util.Collection r4 = r5.search(r4)
            r16 = -4616189618054758400(0xbff0000000000000, double:-1.0)
        L_0x0057:
            r25 = r16
            goto L_0x0078
        L_0x005a:
            r16 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r5 = (r19 > r16 ? 1 : (r19 == r16 ? 0 : -1))
            if (r5 <= 0) goto L_0x0076
            com.google.maps.android.geometry.Bounds r4 = new com.google.maps.android.geometry.Bounds
            r22 = 0
            double r24 = r19 - r16
            r21 = r4
            r26 = r8
            r28 = r1
            r21.<init>(r22, r24, r26, r28)
            com.google.maps.android.quadtree.PointQuadTree<com.google.maps.android.heatmaps.WeightedLatLng> r5 = r0.mTree
            java.util.Collection r4 = r5.search(r4)
            goto L_0x0057
        L_0x0076:
            r25 = r21
        L_0x0078:
            com.google.maps.android.geometry.Bounds r5 = new com.google.maps.android.geometry.Bounds
            r16 = r5
            r17 = r14
            r21 = r8
            r23 = r1
            r16.<init>(r17, r19, r21, r23)
            com.google.maps.android.geometry.Bounds r1 = new com.google.maps.android.geometry.Bounds
            com.google.maps.android.geometry.Bounds r2 = r0.mBounds
            double r13 = r2.minX
            double r28 = r13 - r11
            double r13 = r2.maxX
            double r30 = r13 + r11
            double r13 = r2.minY
            double r32 = r13 - r11
            double r13 = r2.maxY
            double r34 = r13 + r11
            r27 = r1
            r27.<init>(r28, r30, r32, r34)
            boolean r1 = r5.intersects(r1)
            if (r1 != 0) goto L_0x00a7
            com.google.android.gms.maps.model.Tile r0 = com.google.android.gms.maps.model.TileProvider.NO_TILE
            return r0
        L_0x00a7:
            com.google.maps.android.quadtree.PointQuadTree<com.google.maps.android.heatmaps.WeightedLatLng> r1 = r0.mTree
            java.util.Collection r1 = r1.search(r5)
            boolean r2 = r1.isEmpty()
            if (r2 == 0) goto L_0x00b6
            com.google.android.gms.maps.model.Tile r0 = com.google.android.gms.maps.model.TileProvider.NO_TILE
            return r0
        L_0x00b6:
            int r2 = r0.mRadius
            int r5 = r2 * 2
            int r5 = r5 + 512
            r11 = 2
            int r2 = r2 * r11
            int r2 = r2 + 512
            int[] r11 = new int[r11]
            r11[r10] = r2
            r2 = 0
            r11[r2] = r5
            java.lang.Class r2 = java.lang.Double.TYPE
            java.lang.Object r2 = java.lang.reflect.Array.newInstance(r2, r11)
            double[][] r2 = (double[][]) r2
            java.util.Iterator r1 = r1.iterator()
        L_0x00d3:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L_0x00fa
            java.lang.Object r5 = r1.next()
            com.google.maps.android.heatmaps.WeightedLatLng r5 = (com.google.maps.android.heatmaps.WeightedLatLng) r5
            com.google.maps.android.geometry.Point r10 = r5.getPoint()
            double r11 = r10.f7188x
            double r11 = r11 - r17
            double r11 = r11 / r6
            int r11 = (int) r11
            double r12 = r10.f7189y
            double r12 = r12 - r8
            double r12 = r12 / r6
            int r10 = (int) r12
            r11 = r2[r11]
            r12 = r11[r10]
            double r14 = r5.getIntensity()
            double r14 = r14 + r12
            r11[r10] = r14
            goto L_0x00d3
        L_0x00fa:
            java.util.Iterator r1 = r4.iterator()
        L_0x00fe:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x0127
            java.lang.Object r4 = r1.next()
            com.google.maps.android.heatmaps.WeightedLatLng r4 = (com.google.maps.android.heatmaps.WeightedLatLng) r4
            com.google.maps.android.geometry.Point r5 = r4.getPoint()
            double r10 = r5.f7188x
            double r10 = r10 + r25
            double r10 = r10 - r17
            double r10 = r10 / r6
            int r10 = (int) r10
            double r11 = r5.f7189y
            double r11 = r11 - r8
            double r11 = r11 / r6
            int r5 = (int) r11
            r10 = r2[r10]
            r11 = r10[r5]
            double r13 = r4.getIntensity()
            double r13 = r13 + r11
            r10[r5] = r13
            goto L_0x00fe
        L_0x0127:
            double[] r1 = r0.mKernel
            double[][] r1 = convolve(r2, r1)
            int[] r2 = r0.mColorMap
            double[] r0 = r0.mMaxIntensity
            r3 = r0[r3]
            android.graphics.Bitmap r0 = colorize(r1, r2, r3)
            com.google.android.gms.maps.model.Tile r0 = convertBitmap(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.heatmaps.HeatmapTileProvider.getTile(int, int, int):com.google.android.gms.maps.model.Tile");
    }

    public void setData(Collection<LatLng> collection) {
        setWeightedData(wrapData(collection));
    }

    public void setGradient(Gradient gradient) {
        this.mGradient = gradient;
        this.mColorMap = gradient.generateColorMap(this.mOpacity);
    }

    public void setMaxIntensity(double d2) {
        this.mCustomMaxIntensity = d2;
        setWeightedData(this.mData);
    }

    public void setOpacity(double d2) {
        this.mOpacity = d2;
        setGradient(this.mGradient);
    }

    public void setRadius(int i3) {
        this.mRadius = i3;
        this.mKernel = generateKernel(i3, ((double) i3) / 3.0d);
        this.mMaxIntensity = getMaxIntensities(this.mRadius);
    }

    public void setWeightedData(Collection<WeightedLatLng> collection) {
        this.mData = collection;
        if (!collection.isEmpty()) {
            Bounds bounds = getBounds(this.mData);
            this.mBounds = bounds;
            this.mTree = new PointQuadTree<>(bounds);
            for (WeightedLatLng add : this.mData) {
                this.mTree.add(add);
            }
            this.mMaxIntensity = getMaxIntensities(this.mRadius);
            return;
        }
        throw new IllegalArgumentException("No input points.");
    }

    private HeatmapTileProvider(Builder builder) {
        this.mData = builder.data;
        this.mRadius = builder.radius;
        this.mGradient = builder.gradient;
        this.mOpacity = builder.opacity;
        this.mCustomMaxIntensity = builder.intensity;
        int i3 = this.mRadius;
        this.mKernel = generateKernel(i3, ((double) i3) / 3.0d);
        setGradient(this.mGradient);
        setWeightedData(this.mData);
    }
}
