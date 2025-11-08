package com.bumptech.glide.gifdecoder;

import android.graphics.Bitmap;
import android.util.Log;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.compose.ui.autofill.a;
import com.bumptech.glide.gifdecoder.GifDecoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;

public class StandardGifDecoder implements GifDecoder {
    private static final int BYTES_PER_INTEGER = 4;
    @ColorInt
    private static final int COLOR_TRANSPARENT_BLACK = 0;
    private static final int INITIAL_FRAME_POINTER = -1;
    private static final int MASK_INT_LOWEST_BYTE = 255;
    private static final int MAX_STACK_SIZE = 4096;
    private static final int NULL_CODE = -1;
    private static final String TAG = "StandardGifDecoder";
    @ColorInt
    private int[] act;
    @NonNull
    private Bitmap.Config bitmapConfig;
    private final GifDecoder.BitmapProvider bitmapProvider;
    private byte[] block;
    private int downsampledHeight;
    private int downsampledWidth;
    private int framePointer;
    private GifHeader header;
    @Nullable
    private Boolean isFirstFrameTransparent;
    private byte[] mainPixels;
    @ColorInt
    private int[] mainScratch;
    private GifHeaderParser parser;
    @ColorInt
    private final int[] pct;
    private byte[] pixelStack;
    private short[] prefix;
    private Bitmap previousImage;
    private ByteBuffer rawData;
    private int sampleSize;
    private boolean savePrevious;
    private int status;
    private byte[] suffix;

    public StandardGifDecoder(@NonNull GifDecoder.BitmapProvider bitmapProvider2, GifHeader gifHeader, ByteBuffer byteBuffer) {
        this(bitmapProvider2, gifHeader, byteBuffer, 1);
    }

    @ColorInt
    private int averageColorsNear(int i3, int i4, int i5) {
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        for (int i11 = i3; i11 < this.sampleSize + i3; i11++) {
            byte[] bArr = this.mainPixels;
            if (i11 >= bArr.length || i11 >= i4) {
                break;
            }
            int i12 = this.act[bArr[i11] & 255];
            if (i12 != 0) {
                i6 += (i12 >> 24) & 255;
                i7 += (i12 >> 16) & 255;
                i8 += (i12 >> 8) & 255;
                i9 += i12 & 255;
                i10++;
            }
        }
        int i13 = i3 + i5;
        for (int i14 = i13; i14 < this.sampleSize + i13; i14++) {
            byte[] bArr2 = this.mainPixels;
            if (i14 >= bArr2.length || i14 >= i4) {
                break;
            }
            int i15 = this.act[bArr2[i14] & 255];
            if (i15 != 0) {
                i6 += (i15 >> 24) & 255;
                i7 += (i15 >> 16) & 255;
                i8 += (i15 >> 8) & 255;
                i9 += i15 & 255;
                i10++;
            }
        }
        if (i10 == 0) {
            return 0;
        }
        return ((i6 / i10) << 24) | ((i7 / i10) << 16) | ((i8 / i10) << 8) | (i9 / i10);
    }

    private void copyCopyIntoScratchRobust(GifFrame gifFrame) {
        int i3;
        int i4;
        int i5;
        int i6;
        GifFrame gifFrame2 = gifFrame;
        int[] iArr = this.mainScratch;
        int i7 = gifFrame2.ih;
        int i8 = this.sampleSize;
        int i9 = i7 / i8;
        int i10 = gifFrame2.iy / i8;
        int i11 = gifFrame2.iw / i8;
        int i12 = gifFrame2.ix / i8;
        boolean z2 = this.framePointer == 0;
        int i13 = this.downsampledWidth;
        int i14 = this.downsampledHeight;
        byte[] bArr = this.mainPixels;
        int[] iArr2 = this.act;
        Boolean bool = this.isFirstFrameTransparent;
        int i15 = 8;
        int i16 = 0;
        int i17 = 0;
        int i18 = 1;
        while (i17 < i9) {
            Boolean bool2 = bool;
            if (gifFrame2.interlace) {
                if (i16 >= i9) {
                    int i19 = i18 + 1;
                    i3 = i9;
                    if (i19 == 2) {
                        i16 = 4;
                    } else if (i19 == 3) {
                        i15 = 4;
                        i18 = i19;
                        i16 = 2;
                    } else if (i19 == 4) {
                        i18 = i19;
                        i16 = 1;
                        i15 = 2;
                    }
                    i18 = i19;
                } else {
                    i3 = i9;
                }
                i4 = i16 + i15;
            } else {
                i3 = i9;
                i4 = i16;
                i16 = i17;
            }
            int i20 = i16 + i10;
            boolean z3 = i8 == 1;
            if (i20 < i14) {
                int i21 = i20 * i13;
                int i22 = i21 + i12;
                i5 = i4;
                int i23 = i22 + i11;
                int i24 = i21 + i13;
                if (i24 < i23) {
                    i23 = i24;
                }
                i6 = i10;
                int i25 = i17 * i8 * gifFrame2.iw;
                if (z3) {
                    while (i22 < i23) {
                        int i26 = iArr2[bArr[i25] & 255];
                        if (i26 != 0) {
                            iArr[i22] = i26;
                        } else if (z2 && bool2 == null) {
                            bool2 = Boolean.TRUE;
                        }
                        i25 += i8;
                        i22++;
                    }
                } else {
                    int c3 = a.c(i23, i22, i8, i25);
                    while (i22 < i23) {
                        int i27 = i23;
                        int averageColorsNear = averageColorsNear(i25, c3, gifFrame2.iw);
                        if (averageColorsNear != 0) {
                            iArr[i22] = averageColorsNear;
                        } else if (z2 && bool2 == null) {
                            bool2 = Boolean.TRUE;
                        }
                        i25 += i8;
                        i22++;
                        i23 = i27;
                    }
                }
            } else {
                i5 = i4;
                i6 = i10;
            }
            bool = bool2;
            i17++;
            i9 = i3;
            i16 = i5;
            i10 = i6;
        }
        Boolean bool3 = bool;
        if (this.isFirstFrameTransparent == null) {
            this.isFirstFrameTransparent = Boolean.valueOf(bool3 == null ? false : bool3.booleanValue());
        }
    }

    private void copyIntoScratchFast(GifFrame gifFrame) {
        GifFrame gifFrame2 = gifFrame;
        int[] iArr = this.mainScratch;
        int i3 = gifFrame2.ih;
        int i4 = gifFrame2.iy;
        int i5 = gifFrame2.iw;
        int i6 = gifFrame2.ix;
        boolean z2 = this.framePointer == 0;
        int i7 = this.downsampledWidth;
        byte[] bArr = this.mainPixels;
        int[] iArr2 = this.act;
        int i8 = 0;
        byte b3 = -1;
        while (i8 < i3) {
            int i9 = (i8 + i4) * i7;
            int i10 = i9 + i6;
            int i11 = i10 + i5;
            int i12 = i9 + i7;
            if (i12 < i11) {
                i11 = i12;
            }
            int i13 = gifFrame2.iw * i8;
            int i14 = i10;
            while (i14 < i11) {
                byte b4 = bArr[i13];
                int i15 = i3;
                byte b5 = b4 & 255;
                if (b5 != b3) {
                    int i16 = iArr2[b5];
                    if (i16 != 0) {
                        iArr[i14] = i16;
                    } else {
                        b3 = b4;
                    }
                }
                i13++;
                i14++;
                GifFrame gifFrame3 = gifFrame;
                i3 = i15;
            }
            int i17 = i3;
            i8++;
            gifFrame2 = gifFrame;
        }
        Boolean bool = this.isFirstFrameTransparent;
        this.isFirstFrameTransparent = Boolean.valueOf((bool != null && bool.booleanValue()) || (this.isFirstFrameTransparent == null && z2 && b3 != -1));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v14, resolved type: byte} */
    /* JADX WARNING: Incorrect type for immutable var: ssa=short, code=int, for r7v13, types: [short] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void decodeBitmapData(com.bumptech.glide.gifdecoder.GifFrame r29) {
        /*
            r28 = this;
            r0 = r28
            r1 = r29
            if (r1 == 0) goto L_0x000d
            java.nio.ByteBuffer r2 = r0.rawData
            int r3 = r1.bufferFrameStart
            r2.position(r3)
        L_0x000d:
            if (r1 != 0) goto L_0x0017
            com.bumptech.glide.gifdecoder.GifHeader r1 = r0.header
            int r2 = r1.width
            int r1 = r1.height
        L_0x0015:
            int r2 = r2 * r1
            goto L_0x001c
        L_0x0017:
            int r2 = r1.iw
            int r1 = r1.ih
            goto L_0x0015
        L_0x001c:
            byte[] r1 = r0.mainPixels
            if (r1 == 0) goto L_0x0023
            int r1 = r1.length
            if (r1 >= r2) goto L_0x002b
        L_0x0023:
            com.bumptech.glide.gifdecoder.GifDecoder$BitmapProvider r1 = r0.bitmapProvider
            byte[] r1 = r1.obtainByteArray(r2)
            r0.mainPixels = r1
        L_0x002b:
            byte[] r1 = r0.mainPixels
            short[] r3 = r0.prefix
            r4 = 4096(0x1000, float:5.74E-42)
            if (r3 != 0) goto L_0x0037
            short[] r3 = new short[r4]
            r0.prefix = r3
        L_0x0037:
            short[] r3 = r0.prefix
            byte[] r5 = r0.suffix
            if (r5 != 0) goto L_0x0041
            byte[] r5 = new byte[r4]
            r0.suffix = r5
        L_0x0041:
            byte[] r5 = r0.suffix
            byte[] r6 = r0.pixelStack
            if (r6 != 0) goto L_0x004d
            r6 = 4097(0x1001, float:5.741E-42)
            byte[] r6 = new byte[r6]
            r0.pixelStack = r6
        L_0x004d:
            byte[] r6 = r0.pixelStack
            int r7 = r28.readByte()
            r8 = 1
            int r9 = r8 << r7
            int r10 = r9 + 1
            int r11 = r9 + 2
            int r7 = r7 + r8
            int r12 = r8 << r7
            int r12 = r12 - r8
            r13 = 0
            r14 = r13
        L_0x0060:
            if (r14 >= r9) goto L_0x006a
            r3[r14] = r13
            byte r15 = (byte) r14
            r5[r14] = r15
            int r14 = r14 + 1
            goto L_0x0060
        L_0x006a:
            byte[] r14 = r0.block
            r15 = -1
            r23 = r7
            r21 = r11
            r22 = r12
            r16 = r13
            r17 = r16
            r18 = r17
            r19 = r18
            r20 = r19
            r25 = r20
            r26 = r25
            r24 = r15
        L_0x0083:
            if (r13 >= r2) goto L_0x0090
            if (r16 != 0) goto L_0x0097
            int r16 = r28.readBlock()
            if (r16 > 0) goto L_0x0095
            r3 = 3
            r0.status = r3
        L_0x0090:
            r13 = r20
            r0 = 0
            goto L_0x014f
        L_0x0095:
            r17 = 0
        L_0x0097:
            byte r4 = r14[r17]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << r18
            int r19 = r19 + r4
            int r18 = r18 + 8
            int r17 = r17 + 1
            int r16 = r16 + -1
            r4 = r18
            r8 = r21
            r15 = r23
            r0 = r24
            r23 = r7
            r7 = r25
        L_0x00b1:
            if (r4 < r15) goto L_0x0139
            r24 = r11
            r11 = r19 & r22
            int r19 = r19 >> r15
            int r4 = r4 - r15
            if (r11 != r9) goto L_0x00c5
            r22 = r12
            r15 = r23
            r8 = r24
            r11 = r8
            r0 = -1
            goto L_0x00b1
        L_0x00c5:
            if (r11 != r10) goto L_0x00dc
            r18 = r4
            r25 = r7
            r21 = r8
            r7 = r23
            r11 = r24
            r4 = 4096(0x1000, float:5.74E-42)
            r8 = 1
            r24 = r0
            r23 = r15
            r15 = -1
            r0 = r28
            goto L_0x0083
        L_0x00dc:
            r25 = r4
            r4 = -1
            if (r0 != r4) goto L_0x00f0
            byte r0 = r5[r11]
            r1[r20] = r0
            int r20 = r20 + 1
            int r13 = r13 + 1
            r0 = r11
            r7 = r0
            r11 = r24
            r4 = r25
            goto L_0x00b1
        L_0x00f0:
            if (r11 < r8) goto L_0x00f9
            byte r7 = (byte) r7
            r6[r26] = r7
            int r26 = r26 + 1
            r7 = r0
            goto L_0x00fa
        L_0x00f9:
            r7 = r11
        L_0x00fa:
            if (r7 < r9) goto L_0x0105
            byte r21 = r5[r7]
            r6[r26] = r21
            int r26 = r26 + 1
            short r7 = r3[r7]
            goto L_0x00fa
        L_0x0105:
            byte r7 = r5[r7]
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r4 = (byte) r7
            r1[r20] = r4
        L_0x010c:
            int r20 = r20 + 1
            int r13 = r13 + 1
            if (r26 <= 0) goto L_0x0119
            int r26 = r26 + -1
            byte r27 = r6[r26]
            r1[r20] = r27
            goto L_0x010c
        L_0x0119:
            r27 = r6
            r6 = 4096(0x1000, float:5.74E-42)
            if (r8 >= r6) goto L_0x0130
            short r0 = (short) r0
            r3[r8] = r0
            r5[r8] = r4
            int r8 = r8 + 1
            r0 = r8 & r22
            if (r0 != 0) goto L_0x0130
            if (r8 >= r6) goto L_0x0130
            int r15 = r15 + 1
            int r22 = r22 + r8
        L_0x0130:
            r0 = r11
            r11 = r24
            r4 = r25
            r6 = r27
            goto L_0x00b1
        L_0x0139:
            r25 = r4
            r24 = r0
            r21 = r8
            r18 = r25
            r4 = 4096(0x1000, float:5.74E-42)
            r8 = 1
            r0 = r28
            r25 = r7
            r7 = r23
            r23 = r15
            r15 = -1
            goto L_0x0083
        L_0x014f:
            java.util.Arrays.fill(r1, r13, r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.StandardGifDecoder.decodeBitmapData(com.bumptech.glide.gifdecoder.GifFrame):void");
    }

    @NonNull
    private GifHeaderParser getHeaderParser() {
        if (this.parser == null) {
            this.parser = new GifHeaderParser();
        }
        return this.parser;
    }

    private Bitmap getNextBitmap() {
        Boolean bool = this.isFirstFrameTransparent;
        Bitmap obtain = this.bitmapProvider.obtain(this.downsampledWidth, this.downsampledHeight, (bool == null || bool.booleanValue()) ? Bitmap.Config.ARGB_8888 : this.bitmapConfig);
        obtain.setHasAlpha(true);
        return obtain;
    }

    private int readBlock() {
        int readByte = readByte();
        if (readByte <= 0) {
            return readByte;
        }
        ByteBuffer byteBuffer = this.rawData;
        byteBuffer.get(this.block, 0, Math.min(readByte, byteBuffer.remaining()));
        return readByte;
    }

    private int readByte() {
        return this.rawData.get() & 255;
    }

    private Bitmap setPixels(GifFrame gifFrame, GifFrame gifFrame2) {
        int i3;
        int i4;
        Bitmap bitmap;
        int[] iArr = this.mainScratch;
        int i5 = 0;
        if (gifFrame2 == null) {
            Bitmap bitmap2 = this.previousImage;
            if (bitmap2 != null) {
                this.bitmapProvider.release(bitmap2);
            }
            this.previousImage = null;
            Arrays.fill(iArr, 0);
        }
        if (gifFrame2 != null && gifFrame2.dispose == 3 && this.previousImage == null) {
            Arrays.fill(iArr, 0);
        }
        if (gifFrame2 != null && (i4 = gifFrame2.dispose) > 0) {
            if (i4 == 2) {
                if (!gifFrame.transparency) {
                    GifHeader gifHeader = this.header;
                    int i6 = gifHeader.bgColor;
                    if (gifFrame.lct == null || gifHeader.bgIndex != gifFrame.transIndex) {
                        i5 = i6;
                    }
                }
                int i7 = gifFrame2.ih;
                int i8 = this.sampleSize;
                int i9 = i7 / i8;
                int i10 = gifFrame2.iy / i8;
                int i11 = gifFrame2.iw / i8;
                int i12 = gifFrame2.ix / i8;
                int i13 = this.downsampledWidth;
                int i14 = (i10 * i13) + i12;
                int i15 = (i9 * i13) + i14;
                while (i14 < i15) {
                    int i16 = i14 + i11;
                    for (int i17 = i14; i17 < i16; i17++) {
                        iArr[i17] = i5;
                    }
                    i14 += this.downsampledWidth;
                }
            } else if (i4 == 3 && (bitmap = this.previousImage) != null) {
                int i18 = this.downsampledWidth;
                bitmap.getPixels(iArr, 0, i18, 0, 0, i18, this.downsampledHeight);
            }
        }
        decodeBitmapData(gifFrame);
        if (gifFrame.interlace || this.sampleSize != 1) {
            copyCopyIntoScratchRobust(gifFrame);
        } else {
            copyIntoScratchFast(gifFrame);
        }
        if (this.savePrevious && ((i3 = gifFrame.dispose) == 0 || i3 == 1)) {
            if (this.previousImage == null) {
                this.previousImage = getNextBitmap();
            }
            Bitmap bitmap3 = this.previousImage;
            int i19 = this.downsampledWidth;
            bitmap3.setPixels(iArr, 0, i19, 0, 0, i19, this.downsampledHeight);
        }
        Bitmap nextBitmap = getNextBitmap();
        int i20 = this.downsampledWidth;
        nextBitmap.setPixels(iArr, 0, i20, 0, 0, i20, this.downsampledHeight);
        return nextBitmap;
    }

    public void advance() {
        this.framePointer = (this.framePointer + 1) % this.header.frameCount;
    }

    public void clear() {
        this.header = null;
        byte[] bArr = this.mainPixels;
        if (bArr != null) {
            this.bitmapProvider.release(bArr);
        }
        int[] iArr = this.mainScratch;
        if (iArr != null) {
            this.bitmapProvider.release(iArr);
        }
        Bitmap bitmap = this.previousImage;
        if (bitmap != null) {
            this.bitmapProvider.release(bitmap);
        }
        this.previousImage = null;
        this.rawData = null;
        this.isFirstFrameTransparent = null;
        byte[] bArr2 = this.block;
        if (bArr2 != null) {
            this.bitmapProvider.release(bArr2);
        }
    }

    public int getByteSize() {
        return (this.mainScratch.length * 4) + this.rawData.limit() + this.mainPixels.length;
    }

    public int getCurrentFrameIndex() {
        return this.framePointer;
    }

    @NonNull
    public ByteBuffer getData() {
        return this.rawData;
    }

    public int getDelay(int i3) {
        if (i3 >= 0) {
            GifHeader gifHeader = this.header;
            if (i3 < gifHeader.frameCount) {
                return gifHeader.frames.get(i3).delay;
            }
        }
        return -1;
    }

    public int getFrameCount() {
        return this.header.frameCount;
    }

    public int getHeight() {
        return this.header.height;
    }

    @Deprecated
    public int getLoopCount() {
        int i3 = this.header.loopCount;
        if (i3 == -1) {
            return 1;
        }
        return i3;
    }

    public int getNetscapeLoopCount() {
        return this.header.loopCount;
    }

    public int getNextDelay() {
        int i3;
        if (this.header.frameCount <= 0 || (i3 = this.framePointer) < 0) {
            return 0;
        }
        return getDelay(i3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00de, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0043 A[Catch:{ all -> 0x0014 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00cc A[Catch:{ all -> 0x0014 }] */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.graphics.Bitmap getNextFrame() {
        /*
            r9 = this;
            java.lang.String r0 = "Unable to decode frame, status="
            java.lang.String r1 = "No valid color table found for frame #"
            java.lang.String r2 = "Unable to decode frame, frameCount="
            monitor-enter(r9)
            com.bumptech.glide.gifdecoder.GifHeader r3 = r9.header     // Catch:{ all -> 0x0014 }
            int r3 = r3.frameCount     // Catch:{ all -> 0x0014 }
            r4 = 3
            r5 = 1
            if (r3 <= 0) goto L_0x0017
            int r3 = r9.framePointer     // Catch:{ all -> 0x0014 }
            if (r3 >= 0) goto L_0x003e
            goto L_0x0017
        L_0x0014:
            r0 = move-exception
            goto L_0x00df
        L_0x0017:
            java.lang.String r3 = TAG     // Catch:{ all -> 0x0014 }
            boolean r6 = android.util.Log.isLoggable(r3, r4)     // Catch:{ all -> 0x0014 }
            if (r6 == 0) goto L_0x003c
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0014 }
            r6.<init>(r2)     // Catch:{ all -> 0x0014 }
            com.bumptech.glide.gifdecoder.GifHeader r2 = r9.header     // Catch:{ all -> 0x0014 }
            int r2 = r2.frameCount     // Catch:{ all -> 0x0014 }
            r6.append(r2)     // Catch:{ all -> 0x0014 }
            java.lang.String r2 = ", framePointer="
            r6.append(r2)     // Catch:{ all -> 0x0014 }
            int r2 = r9.framePointer     // Catch:{ all -> 0x0014 }
            r6.append(r2)     // Catch:{ all -> 0x0014 }
            java.lang.String r2 = r6.toString()     // Catch:{ all -> 0x0014 }
            android.util.Log.d(r3, r2)     // Catch:{ all -> 0x0014 }
        L_0x003c:
            r9.status = r5     // Catch:{ all -> 0x0014 }
        L_0x003e:
            int r2 = r9.status     // Catch:{ all -> 0x0014 }
            r3 = 0
            if (r2 == r5) goto L_0x00c4
            r6 = 2
            if (r2 != r6) goto L_0x0048
            goto L_0x00c4
        L_0x0048:
            r0 = 0
            r9.status = r0     // Catch:{ all -> 0x0014 }
            byte[] r2 = r9.block     // Catch:{ all -> 0x0014 }
            if (r2 != 0) goto L_0x0059
            com.bumptech.glide.gifdecoder.GifDecoder$BitmapProvider r2 = r9.bitmapProvider     // Catch:{ all -> 0x0014 }
            r7 = 255(0xff, float:3.57E-43)
            byte[] r2 = r2.obtainByteArray(r7)     // Catch:{ all -> 0x0014 }
            r9.block = r2     // Catch:{ all -> 0x0014 }
        L_0x0059:
            com.bumptech.glide.gifdecoder.GifHeader r2 = r9.header     // Catch:{ all -> 0x0014 }
            java.util.List<com.bumptech.glide.gifdecoder.GifFrame> r2 = r2.frames     // Catch:{ all -> 0x0014 }
            int r7 = r9.framePointer     // Catch:{ all -> 0x0014 }
            java.lang.Object r2 = r2.get(r7)     // Catch:{ all -> 0x0014 }
            com.bumptech.glide.gifdecoder.GifFrame r2 = (com.bumptech.glide.gifdecoder.GifFrame) r2     // Catch:{ all -> 0x0014 }
            int r7 = r9.framePointer     // Catch:{ all -> 0x0014 }
            int r7 = r7 - r5
            if (r7 < 0) goto L_0x0075
            com.bumptech.glide.gifdecoder.GifHeader r8 = r9.header     // Catch:{ all -> 0x0014 }
            java.util.List<com.bumptech.glide.gifdecoder.GifFrame> r8 = r8.frames     // Catch:{ all -> 0x0014 }
            java.lang.Object r7 = r8.get(r7)     // Catch:{ all -> 0x0014 }
            com.bumptech.glide.gifdecoder.GifFrame r7 = (com.bumptech.glide.gifdecoder.GifFrame) r7     // Catch:{ all -> 0x0014 }
            goto L_0x0076
        L_0x0075:
            r7 = r3
        L_0x0076:
            int[] r8 = r2.lct     // Catch:{ all -> 0x0014 }
            if (r8 == 0) goto L_0x007b
            goto L_0x007f
        L_0x007b:
            com.bumptech.glide.gifdecoder.GifHeader r8 = r9.header     // Catch:{ all -> 0x0014 }
            int[] r8 = r8.gct     // Catch:{ all -> 0x0014 }
        L_0x007f:
            r9.act = r8     // Catch:{ all -> 0x0014 }
            if (r8 != 0) goto L_0x00a0
            java.lang.String r0 = TAG     // Catch:{ all -> 0x0014 }
            boolean r2 = android.util.Log.isLoggable(r0, r4)     // Catch:{ all -> 0x0014 }
            if (r2 == 0) goto L_0x009c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0014 }
            r2.<init>(r1)     // Catch:{ all -> 0x0014 }
            int r1 = r9.framePointer     // Catch:{ all -> 0x0014 }
            r2.append(r1)     // Catch:{ all -> 0x0014 }
            java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x0014 }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x0014 }
        L_0x009c:
            r9.status = r5     // Catch:{ all -> 0x0014 }
            monitor-exit(r9)
            return r3
        L_0x00a0:
            boolean r1 = r2.transparency     // Catch:{ all -> 0x0014 }
            if (r1 == 0) goto L_0x00be
            int[] r1 = r9.pct     // Catch:{ all -> 0x0014 }
            int r3 = r8.length     // Catch:{ all -> 0x0014 }
            java.lang.System.arraycopy(r8, r0, r1, r0, r3)     // Catch:{ all -> 0x0014 }
            int[] r1 = r9.pct     // Catch:{ all -> 0x0014 }
            r9.act = r1     // Catch:{ all -> 0x0014 }
            int r3 = r2.transIndex     // Catch:{ all -> 0x0014 }
            r1[r3] = r0     // Catch:{ all -> 0x0014 }
            int r0 = r2.dispose     // Catch:{ all -> 0x0014 }
            if (r0 != r6) goto L_0x00be
            int r0 = r9.framePointer     // Catch:{ all -> 0x0014 }
            if (r0 != 0) goto L_0x00be
            java.lang.Boolean r0 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0014 }
            r9.isFirstFrameTransparent = r0     // Catch:{ all -> 0x0014 }
        L_0x00be:
            android.graphics.Bitmap r0 = r9.setPixels(r2, r7)     // Catch:{ all -> 0x0014 }
            monitor-exit(r9)
            return r0
        L_0x00c4:
            java.lang.String r1 = TAG     // Catch:{ all -> 0x0014 }
            boolean r2 = android.util.Log.isLoggable(r1, r4)     // Catch:{ all -> 0x0014 }
            if (r2 == 0) goto L_0x00dd
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0014 }
            r2.<init>(r0)     // Catch:{ all -> 0x0014 }
            int r0 = r9.status     // Catch:{ all -> 0x0014 }
            r2.append(r0)     // Catch:{ all -> 0x0014 }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x0014 }
            android.util.Log.d(r1, r0)     // Catch:{ all -> 0x0014 }
        L_0x00dd:
            monitor-exit(r9)
            return r3
        L_0x00df:
            monitor-exit(r9)     // Catch:{ all -> 0x0014 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.StandardGifDecoder.getNextFrame():android.graphics.Bitmap");
    }

    public int getStatus() {
        return this.status;
    }

    public int getTotalIterationCount() {
        int i3 = this.header.loopCount;
        if (i3 == -1) {
            return 1;
        }
        if (i3 == 0) {
            return 0;
        }
        return i3 + 1;
    }

    public int getWidth() {
        return this.header.width;
    }

    public int read(@Nullable InputStream inputStream, int i3) {
        if (inputStream != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i3 > 0 ? i3 + 4096 : 16384);
                byte[] bArr = new byte[16384];
                while (true) {
                    int read = inputStream.read(bArr, 0, 16384);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.flush();
                read(byteArrayOutputStream.toByteArray());
            } catch (IOException e3) {
                Log.w(TAG, "Error reading data from stream", e3);
            }
        } else {
            this.status = 2;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e4) {
                Log.w(TAG, "Error closing stream", e4);
            }
        }
        return this.status;
    }

    public void resetFrameIndex() {
        this.framePointer = -1;
    }

    public synchronized void setData(@NonNull GifHeader gifHeader, @NonNull byte[] bArr) {
        setData(gifHeader, ByteBuffer.wrap(bArr));
    }

    public void setDefaultBitmapConfig(@NonNull Bitmap.Config config) {
        Bitmap.Config config2;
        Bitmap.Config config3 = Bitmap.Config.ARGB_8888;
        if (config == config3 || config == (config2 = Bitmap.Config.RGB_565)) {
            this.bitmapConfig = config;
            return;
        }
        throw new IllegalArgumentException("Unsupported format: " + config + ", must be one of " + config3 + " or " + config2);
    }

    public StandardGifDecoder(@NonNull GifDecoder.BitmapProvider bitmapProvider2, GifHeader gifHeader, ByteBuffer byteBuffer, int i3) {
        this(bitmapProvider2);
        setData(gifHeader, byteBuffer, i3);
    }

    public synchronized void setData(@NonNull GifHeader gifHeader, @NonNull ByteBuffer byteBuffer) {
        setData(gifHeader, byteBuffer, 1);
    }

    public StandardGifDecoder(@NonNull GifDecoder.BitmapProvider bitmapProvider2) {
        this.pct = new int[256];
        this.bitmapConfig = Bitmap.Config.ARGB_8888;
        this.bitmapProvider = bitmapProvider2;
        this.header = new GifHeader();
    }

    public synchronized void setData(@NonNull GifHeader gifHeader, @NonNull ByteBuffer byteBuffer, int i3) {
        if (i3 > 0) {
            try {
                int highestOneBit = Integer.highestOneBit(i3);
                this.status = 0;
                this.header = gifHeader;
                this.framePointer = -1;
                ByteBuffer asReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
                this.rawData = asReadOnlyBuffer;
                asReadOnlyBuffer.position(0);
                this.rawData.order(ByteOrder.LITTLE_ENDIAN);
                this.savePrevious = false;
                Iterator<GifFrame> it = gifHeader.frames.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().dispose == 3) {
                            this.savePrevious = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                this.sampleSize = highestOneBit;
                int i4 = gifHeader.width;
                this.downsampledWidth = i4 / highestOneBit;
                int i5 = gifHeader.height;
                this.downsampledHeight = i5 / highestOneBit;
                this.mainPixels = this.bitmapProvider.obtainByteArray(i4 * i5);
                this.mainScratch = this.bitmapProvider.obtainIntArray(this.downsampledWidth * this.downsampledHeight);
            } catch (Throwable th) {
                throw th;
            }
        } else {
            throw new IllegalArgumentException("Sample size must be >=0, not: " + i3);
        }
    }

    public synchronized int read(@Nullable byte[] bArr) {
        try {
            GifHeader parseHeader = getHeaderParser().setData(bArr).parseHeader();
            this.header = parseHeader;
            if (bArr != null) {
                setData(parseHeader, bArr);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this.status;
    }
}
