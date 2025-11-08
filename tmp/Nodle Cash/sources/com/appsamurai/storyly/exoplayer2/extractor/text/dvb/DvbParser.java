package com.appsamurai.storyly.exoplayer2.extractor.text.dvb;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.trackselection.AdaptiveTrackSelection;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.msgpack.core.MessagePack;

final class DvbParser {
    private static final int DATA_TYPE_24_TABLE_DATA = 32;
    private static final int DATA_TYPE_28_TABLE_DATA = 33;
    private static final int DATA_TYPE_2BP_CODE_STRING = 16;
    private static final int DATA_TYPE_48_TABLE_DATA = 34;
    private static final int DATA_TYPE_4BP_CODE_STRING = 17;
    private static final int DATA_TYPE_8BP_CODE_STRING = 18;
    private static final int DATA_TYPE_END_LINE = 240;
    private static final int OBJECT_CODING_PIXELS = 0;
    private static final int OBJECT_CODING_STRING = 1;
    private static final int PAGE_STATE_NORMAL = 0;
    private static final int REGION_DEPTH_4_BIT = 2;
    private static final int REGION_DEPTH_8_BIT = 3;
    private static final int SEGMENT_TYPE_CLUT_DEFINITION = 18;
    private static final int SEGMENT_TYPE_DISPLAY_DEFINITION = 20;
    private static final int SEGMENT_TYPE_OBJECT_DATA = 19;
    private static final int SEGMENT_TYPE_PAGE_COMPOSITION = 16;
    private static final int SEGMENT_TYPE_REGION_COMPOSITION = 17;
    private static final String TAG = "DvbParser";
    private static final byte[] defaultMap2To4 = {0, 7, 8, Ascii.SI};
    private static final byte[] defaultMap2To8 = {0, 119, -120, -1};
    private static final byte[] defaultMap4To8 = {0, 17, 34, 51, 68, 85, 102, 119, -120, -103, -86, ByteSourceJsonBootstrapper.UTF8_BOM_2, MessagePack.Code.UINT8, MessagePack.Code.ARRAY32, -18, -1};
    private Bitmap bitmap;
    private final Canvas canvas = new Canvas();
    private final ClutDefinition defaultClutDefinition = new ClutDefinition(0, generateDefault2BitClutEntries(), generateDefault4BitClutEntries(), generateDefault8BitClutEntries());
    private final DisplayDefinition defaultDisplayDefinition = new DisplayDefinition(AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD, 575, 0, AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD, 0, 575);
    private final Paint defaultPaint;
    private final Paint fillRegionPaint;
    private final SubtitleService subtitleService;

    public static final class ClutDefinition {
        public final int[] clutEntries2Bit;
        public final int[] clutEntries4Bit;
        public final int[] clutEntries8Bit;
        public final int id;

        public ClutDefinition(int i3, int[] iArr, int[] iArr2, int[] iArr3) {
            this.id = i3;
            this.clutEntries2Bit = iArr;
            this.clutEntries4Bit = iArr2;
            this.clutEntries8Bit = iArr3;
        }
    }

    public static final class DisplayDefinition {
        public final int height;
        public final int horizontalPositionMaximum;
        public final int horizontalPositionMinimum;
        public final int verticalPositionMaximum;
        public final int verticalPositionMinimum;
        public final int width;

        public DisplayDefinition(int i3, int i4, int i5, int i6, int i7, int i8) {
            this.width = i3;
            this.height = i4;
            this.horizontalPositionMinimum = i5;
            this.horizontalPositionMaximum = i6;
            this.verticalPositionMinimum = i7;
            this.verticalPositionMaximum = i8;
        }
    }

    public static final class ObjectData {
        public final byte[] bottomFieldData;
        public final int id;
        public final boolean nonModifyingColorFlag;
        public final byte[] topFieldData;

        public ObjectData(int i3, boolean z2, byte[] bArr, byte[] bArr2) {
            this.id = i3;
            this.nonModifyingColorFlag = z2;
            this.topFieldData = bArr;
            this.bottomFieldData = bArr2;
        }
    }

    public static final class PageComposition {
        public final SparseArray<PageRegion> regions;
        public final int state;
        public final int timeOutSecs;
        public final int version;

        public PageComposition(int i3, int i4, int i5, SparseArray<PageRegion> sparseArray) {
            this.timeOutSecs = i3;
            this.version = i4;
            this.state = i5;
            this.regions = sparseArray;
        }
    }

    public static final class PageRegion {
        public final int horizontalAddress;
        public final int verticalAddress;

        public PageRegion(int i3, int i4) {
            this.horizontalAddress = i3;
            this.verticalAddress = i4;
        }
    }

    public static final class RegionComposition {
        public final int clutId;
        public final int depth;
        public final boolean fillFlag;
        public final int height;
        public final int id;
        public final int levelOfCompatibility;
        public final int pixelCode2Bit;
        public final int pixelCode4Bit;
        public final int pixelCode8Bit;
        public final SparseArray<RegionObject> regionObjects;
        public final int width;

        public RegionComposition(int i3, boolean z2, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, SparseArray<RegionObject> sparseArray) {
            this.id = i3;
            this.fillFlag = z2;
            this.width = i4;
            this.height = i5;
            this.levelOfCompatibility = i6;
            this.depth = i7;
            this.clutId = i8;
            this.pixelCode8Bit = i9;
            this.pixelCode4Bit = i10;
            this.pixelCode2Bit = i11;
            this.regionObjects = sparseArray;
        }

        public void mergeFrom(RegionComposition regionComposition) {
            SparseArray<RegionObject> sparseArray = regionComposition.regionObjects;
            for (int i3 = 0; i3 < sparseArray.size(); i3++) {
                this.regionObjects.put(sparseArray.keyAt(i3), sparseArray.valueAt(i3));
            }
        }
    }

    public static final class RegionObject {
        public final int backgroundPixelCode;
        public final int foregroundPixelCode;
        public final int horizontalPosition;
        public final int provider;
        public final int type;
        public final int verticalPosition;

        public RegionObject(int i3, int i4, int i5, int i6, int i7, int i8) {
            this.type = i3;
            this.provider = i4;
            this.horizontalPosition = i5;
            this.verticalPosition = i6;
            this.foregroundPixelCode = i7;
            this.backgroundPixelCode = i8;
        }
    }

    public static final class SubtitleService {
        public final SparseArray<ClutDefinition> ancillaryCluts = new SparseArray<>();
        public final SparseArray<ObjectData> ancillaryObjects = new SparseArray<>();
        public final int ancillaryPageId;
        public final SparseArray<ClutDefinition> cluts = new SparseArray<>();
        @Nullable
        public DisplayDefinition displayDefinition;
        public final SparseArray<ObjectData> objects = new SparseArray<>();
        @Nullable
        public PageComposition pageComposition;
        public final SparseArray<RegionComposition> regions = new SparseArray<>();
        public final int subtitlePageId;

        public SubtitleService(int i3, int i4) {
            this.subtitlePageId = i3;
            this.ancillaryPageId = i4;
        }

        public void reset() {
            this.regions.clear();
            this.cluts.clear();
            this.objects.clear();
            this.ancillaryCluts.clear();
            this.ancillaryObjects.clear();
            this.displayDefinition = null;
            this.pageComposition = null;
        }
    }

    public DvbParser(int i3, int i4) {
        Paint paint = new Paint();
        this.defaultPaint = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        paint.setPathEffect((PathEffect) null);
        Paint paint2 = new Paint();
        this.fillRegionPaint = paint2;
        paint2.setStyle(Paint.Style.FILL);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        paint2.setPathEffect((PathEffect) null);
        this.subtitleService = new SubtitleService(i3, i4);
    }

    private static byte[] buildClutMapTable(int i3, int i4, ParsableBitArray parsableBitArray) {
        byte[] bArr = new byte[i3];
        for (int i5 = 0; i5 < i3; i5++) {
            bArr[i5] = (byte) parsableBitArray.readBits(i4);
        }
        return bArr;
    }

    private static int[] generateDefault2BitClutEntries() {
        return new int[]{0, -1, ViewCompat.MEASURED_STATE_MASK, -8421505};
    }

    private static int[] generateDefault4BitClutEntries() {
        int[] iArr = new int[16];
        iArr[0] = 0;
        for (int i3 = 1; i3 < 16; i3++) {
            if (i3 < 8) {
                iArr[i3] = getColor(255, (i3 & 1) != 0 ? 255 : 0, (i3 & 2) != 0 ? 255 : 0, (i3 & 4) != 0 ? 255 : 0);
            } else {
                int i4 = 127;
                int i5 = (i3 & 1) != 0 ? 127 : 0;
                int i6 = (i3 & 2) != 0 ? 127 : 0;
                if ((i3 & 4) == 0) {
                    i4 = 0;
                }
                iArr[i3] = getColor(255, i5, i6, i4);
            }
        }
        return iArr;
    }

    private static int[] generateDefault8BitClutEntries() {
        int[] iArr = new int[256];
        iArr[0] = 0;
        for (int i3 = 0; i3 < 256; i3++) {
            int i4 = 255;
            if (i3 < 8) {
                int i5 = (i3 & 1) != 0 ? 255 : 0;
                int i6 = (i3 & 2) != 0 ? 255 : 0;
                if ((i3 & 4) == 0) {
                    i4 = 0;
                }
                iArr[i3] = getColor(63, i5, i6, i4);
            } else {
                int i7 = i3 & 136;
                int i8 = 170;
                int i9 = 85;
                if (i7 == 0) {
                    int i10 = ((i3 & 1) != 0 ? 85 : 0) + ((i3 & 16) != 0 ? 170 : 0);
                    int i11 = ((i3 & 2) != 0 ? 85 : 0) + ((i3 & 32) != 0 ? 170 : 0);
                    if ((i3 & 4) == 0) {
                        i9 = 0;
                    }
                    if ((i3 & 64) == 0) {
                        i8 = 0;
                    }
                    iArr[i3] = getColor(255, i10, i11, i9 + i8);
                } else if (i7 != 8) {
                    int i12 = 43;
                    if (i7 == 128) {
                        int i13 = ((i3 & 1) != 0 ? 43 : 0) + 127 + ((i3 & 16) != 0 ? 85 : 0);
                        int i14 = ((i3 & 2) != 0 ? 43 : 0) + 127 + ((i3 & 32) != 0 ? 85 : 0);
                        if ((i3 & 4) == 0) {
                            i12 = 0;
                        }
                        int i15 = i12 + 127;
                        if ((i3 & 64) == 0) {
                            i9 = 0;
                        }
                        iArr[i3] = getColor(255, i13, i14, i15 + i9);
                    } else if (i7 == 136) {
                        int i16 = ((i3 & 1) != 0 ? 43 : 0) + ((i3 & 16) != 0 ? 85 : 0);
                        int i17 = ((i3 & 2) != 0 ? 43 : 0) + ((i3 & 32) != 0 ? 85 : 0);
                        if ((i3 & 4) == 0) {
                            i12 = 0;
                        }
                        if ((i3 & 64) == 0) {
                            i9 = 0;
                        }
                        iArr[i3] = getColor(255, i16, i17, i12 + i9);
                    }
                } else {
                    int i18 = ((i3 & 1) != 0 ? 85 : 0) + ((i3 & 16) != 0 ? 170 : 0);
                    int i19 = ((i3 & 2) != 0 ? 85 : 0) + ((i3 & 32) != 0 ? 170 : 0);
                    if ((i3 & 4) == 0) {
                        i9 = 0;
                    }
                    if ((i3 & 64) == 0) {
                        i8 = 0;
                    }
                    iArr[i3] = getColor(127, i18, i19, i9 + i8);
                }
            }
        }
        return iArr;
    }

    private static int getColor(int i3, int i4, int i5, int i6) {
        return (i3 << 24) | (i4 << 16) | (i5 << 8) | i6;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v20, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int paint2BitPixelCodeString(com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray r13, int[] r14, @androidx.annotation.Nullable byte[] r15, int r16, int r17, @androidx.annotation.Nullable android.graphics.Paint r18, android.graphics.Canvas r19) {
        /*
            r0 = r13
            r1 = r17
            r8 = r18
            r9 = 0
            r10 = r16
            r2 = r9
        L_0x0009:
            r3 = 2
            int r4 = r13.readBits(r3)
            r5 = 1
            if (r4 == 0) goto L_0x0014
            r11 = r2
            r12 = r5
            goto L_0x0060
        L_0x0014:
            boolean r4 = r13.readBit()
            r6 = 3
            if (r4 == 0) goto L_0x0028
            int r4 = r13.readBits(r6)
            int r4 = r4 + r6
            int r3 = r13.readBits(r3)
        L_0x0024:
            r11 = r2
            r12 = r4
            r4 = r3
            goto L_0x0060
        L_0x0028:
            boolean r4 = r13.readBit()
            if (r4 == 0) goto L_0x0032
            r11 = r2
            r12 = r5
        L_0x0030:
            r4 = r9
            goto L_0x0060
        L_0x0032:
            int r4 = r13.readBits(r3)
            if (r4 == 0) goto L_0x005e
            if (r4 == r5) goto L_0x005b
            if (r4 == r3) goto L_0x004f
            if (r4 == r6) goto L_0x0042
            r11 = r2
        L_0x003f:
            r4 = r9
            r12 = r4
            goto L_0x0060
        L_0x0042:
            r4 = 8
            int r4 = r13.readBits(r4)
            int r4 = r4 + 29
            int r3 = r13.readBits(r3)
            goto L_0x0024
        L_0x004f:
            r4 = 4
            int r4 = r13.readBits(r4)
            int r4 = r4 + 12
            int r3 = r13.readBits(r3)
            goto L_0x0024
        L_0x005b:
            r11 = r2
            r12 = r3
            goto L_0x0030
        L_0x005e:
            r11 = r5
            goto L_0x003f
        L_0x0060:
            if (r12 == 0) goto L_0x007e
            if (r8 == 0) goto L_0x007e
            if (r15 == 0) goto L_0x0068
            byte r4 = r15[r4]
        L_0x0068:
            r2 = r14[r4]
            r8.setColor(r2)
            float r3 = (float) r10
            float r4 = (float) r1
            int r2 = r10 + r12
            float r6 = (float) r2
            int r2 = r1 + 1
            float r7 = (float) r2
            r2 = r19
            r5 = r6
            r6 = r7
            r7 = r18
            r2.drawRect(r3, r4, r5, r6, r7)
        L_0x007e:
            int r10 = r10 + r12
            if (r11 == 0) goto L_0x0082
            return r10
        L_0x0082:
            r2 = r11
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.text.dvb.DvbParser.paint2BitPixelCodeString(com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray, int[], byte[], int, int, android.graphics.Paint, android.graphics.Canvas):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v19, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int paint4BitPixelCodeString(com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray r13, int[] r14, @androidx.annotation.Nullable byte[] r15, int r16, int r17, @androidx.annotation.Nullable android.graphics.Paint r18, android.graphics.Canvas r19) {
        /*
            r0 = r13
            r1 = r17
            r8 = r18
            r9 = 0
            r10 = r16
            r2 = r9
        L_0x0009:
            r3 = 4
            int r4 = r13.readBits(r3)
            r5 = 1
            if (r4 == 0) goto L_0x0015
            r11 = r2
            r12 = r5
            goto L_0x006c
        L_0x0015:
            boolean r4 = r13.readBit()
            r6 = 3
            if (r4 != 0) goto L_0x002c
            int r3 = r13.readBits(r6)
            if (r3 == 0) goto L_0x0028
            int r3 = r3 + 2
            r11 = r2
            r12 = r3
        L_0x0026:
            r4 = r9
            goto L_0x006c
        L_0x0028:
            r11 = r5
        L_0x0029:
            r4 = r9
            r12 = r4
            goto L_0x006c
        L_0x002c:
            boolean r4 = r13.readBit()
            r7 = 2
            if (r4 != 0) goto L_0x0040
            int r4 = r13.readBits(r7)
            int r4 = r4 + r3
            int r3 = r13.readBits(r3)
        L_0x003c:
            r11 = r2
            r12 = r4
            r4 = r3
            goto L_0x006c
        L_0x0040:
            int r4 = r13.readBits(r7)
            if (r4 == 0) goto L_0x0069
            if (r4 == r5) goto L_0x0066
            if (r4 == r7) goto L_0x005b
            if (r4 == r6) goto L_0x004e
            r11 = r2
            goto L_0x0029
        L_0x004e:
            r4 = 8
            int r4 = r13.readBits(r4)
            int r4 = r4 + 25
            int r3 = r13.readBits(r3)
            goto L_0x003c
        L_0x005b:
            int r4 = r13.readBits(r3)
            int r4 = r4 + 9
            int r3 = r13.readBits(r3)
            goto L_0x003c
        L_0x0066:
            r11 = r2
            r12 = r7
            goto L_0x0026
        L_0x0069:
            r11 = r2
            r12 = r5
            goto L_0x0026
        L_0x006c:
            if (r12 == 0) goto L_0x008a
            if (r8 == 0) goto L_0x008a
            if (r15 == 0) goto L_0x0074
            byte r4 = r15[r4]
        L_0x0074:
            r2 = r14[r4]
            r8.setColor(r2)
            float r3 = (float) r10
            float r4 = (float) r1
            int r2 = r10 + r12
            float r6 = (float) r2
            int r2 = r1 + 1
            float r7 = (float) r2
            r2 = r19
            r5 = r6
            r6 = r7
            r7 = r18
            r2.drawRect(r3, r4, r5, r6, r7)
        L_0x008a:
            int r10 = r10 + r12
            if (r11 == 0) goto L_0x008e
            return r10
        L_0x008e:
            r2 = r11
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.text.dvb.DvbParser.paint4BitPixelCodeString(com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray, int[], byte[], int, int, android.graphics.Paint, android.graphics.Canvas):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int paint8BitPixelCodeString(com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray r13, int[] r14, @androidx.annotation.Nullable byte[] r15, int r16, int r17, @androidx.annotation.Nullable android.graphics.Paint r18, android.graphics.Canvas r19) {
        /*
            r0 = r13
            r1 = r17
            r8 = r18
            r9 = 0
            r10 = r16
            r2 = r9
        L_0x0009:
            r3 = 8
            int r4 = r13.readBits(r3)
            r5 = 1
            if (r4 == 0) goto L_0x0015
            r11 = r2
            r12 = r5
            goto L_0x0035
        L_0x0015:
            boolean r4 = r13.readBit()
            r6 = 7
            if (r4 != 0) goto L_0x002a
            int r3 = r13.readBits(r6)
            if (r3 == 0) goto L_0x0026
            r11 = r2
            r12 = r3
            r4 = r9
            goto L_0x0035
        L_0x0026:
            r11 = r5
            r4 = r9
            r12 = r4
            goto L_0x0035
        L_0x002a:
            int r4 = r13.readBits(r6)
            int r3 = r13.readBits(r3)
            r11 = r2
            r12 = r4
            r4 = r3
        L_0x0035:
            if (r12 == 0) goto L_0x0053
            if (r8 == 0) goto L_0x0053
            if (r15 == 0) goto L_0x003d
            byte r4 = r15[r4]
        L_0x003d:
            r2 = r14[r4]
            r8.setColor(r2)
            float r3 = (float) r10
            float r4 = (float) r1
            int r2 = r10 + r12
            float r6 = (float) r2
            int r2 = r1 + 1
            float r7 = (float) r2
            r2 = r19
            r5 = r6
            r6 = r7
            r7 = r18
            r2.drawRect(r3, r4, r5, r6, r7)
        L_0x0053:
            int r10 = r10 + r12
            if (r11 == 0) goto L_0x0057
            return r10
        L_0x0057:
            r2 = r11
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.text.dvb.DvbParser.paint8BitPixelCodeString(com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray, int[], byte[], int, int, android.graphics.Paint, android.graphics.Canvas):int");
    }

    private static void paintPixelDataSubBlock(byte[] bArr, int[] iArr, int i3, int i4, int i5, @Nullable Paint paint, Canvas canvas2) {
        byte[] bArr2;
        byte[] bArr3;
        byte[] bArr4;
        int i6 = i3;
        byte[] bArr5 = bArr;
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr);
        int i7 = i4;
        int i8 = i5;
        byte[] bArr6 = null;
        byte[] bArr7 = null;
        byte[] bArr8 = null;
        while (parsableBitArray.bitsLeft() != 0) {
            int readBits = parsableBitArray.readBits(8);
            if (readBits != 240) {
                switch (readBits) {
                    case 16:
                        if (i6 != 3) {
                            if (i6 != 2) {
                                bArr2 = null;
                                i7 = paint2BitPixelCodeString(parsableBitArray, iArr, bArr2, i7, i8, paint, canvas2);
                                parsableBitArray.byteAlign();
                                break;
                            } else {
                                bArr3 = bArr8 == null ? defaultMap2To4 : bArr8;
                            }
                        } else {
                            bArr3 = bArr6 == null ? defaultMap2To8 : bArr6;
                        }
                        bArr2 = bArr3;
                        i7 = paint2BitPixelCodeString(parsableBitArray, iArr, bArr2, i7, i8, paint, canvas2);
                        parsableBitArray.byteAlign();
                    case 17:
                        if (i6 == 3) {
                            bArr4 = bArr7 == null ? defaultMap4To8 : bArr7;
                        } else {
                            bArr4 = null;
                        }
                        i7 = paint4BitPixelCodeString(parsableBitArray, iArr, bArr4, i7, i8, paint, canvas2);
                        parsableBitArray.byteAlign();
                        break;
                    case 18:
                        i7 = paint8BitPixelCodeString(parsableBitArray, iArr, (byte[]) null, i7, i8, paint, canvas2);
                        break;
                    default:
                        switch (readBits) {
                            case 32:
                                bArr8 = buildClutMapTable(4, 4, parsableBitArray);
                                break;
                            case 33:
                                bArr6 = buildClutMapTable(4, 8, parsableBitArray);
                                break;
                            case 34:
                                bArr7 = buildClutMapTable(16, 8, parsableBitArray);
                                break;
                        }
                }
            } else {
                i8 += 2;
                i7 = i4;
            }
        }
    }

    private static void paintPixelDataSubBlocks(ObjectData objectData, ClutDefinition clutDefinition, int i3, int i4, int i5, @Nullable Paint paint, Canvas canvas2) {
        int[] iArr = i3 == 3 ? clutDefinition.clutEntries8Bit : i3 == 2 ? clutDefinition.clutEntries4Bit : clutDefinition.clutEntries2Bit;
        int i6 = i3;
        int i7 = i4;
        Paint paint2 = paint;
        Canvas canvas3 = canvas2;
        paintPixelDataSubBlock(objectData.topFieldData, iArr, i6, i7, i5, paint2, canvas3);
        paintPixelDataSubBlock(objectData.bottomFieldData, iArr, i6, i7, i5 + 1, paint2, canvas3);
    }

    private static ClutDefinition parseClutDefinition(ParsableBitArray parsableBitArray, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        ParsableBitArray parsableBitArray2 = parsableBitArray;
        int i9 = 8;
        int readBits = parsableBitArray2.readBits(8);
        parsableBitArray2.skipBits(8);
        int i10 = 2;
        int i11 = i3 - 2;
        int[] generateDefault2BitClutEntries = generateDefault2BitClutEntries();
        int[] generateDefault4BitClutEntries = generateDefault4BitClutEntries();
        int[] generateDefault8BitClutEntries = generateDefault8BitClutEntries();
        while (i11 > 0) {
            int readBits2 = parsableBitArray2.readBits(i9);
            int readBits3 = parsableBitArray2.readBits(i9);
            int[] iArr = (readBits3 & 128) != 0 ? generateDefault2BitClutEntries : (readBits3 & 64) != 0 ? generateDefault4BitClutEntries : generateDefault8BitClutEntries;
            if ((readBits3 & 1) != 0) {
                i7 = parsableBitArray2.readBits(i9);
                i6 = parsableBitArray2.readBits(i9);
                i5 = parsableBitArray2.readBits(i9);
                i4 = parsableBitArray2.readBits(i9);
                i8 = i11 - 6;
            } else {
                i5 = parsableBitArray2.readBits(4) << 4;
                i8 = i11 - 4;
                int readBits4 = parsableBitArray2.readBits(4) << 4;
                i4 = parsableBitArray2.readBits(i10) << 6;
                i7 = parsableBitArray2.readBits(6) << i10;
                i6 = readBits4;
            }
            if (i7 == 0) {
                i4 = 255;
                i6 = 0;
                i5 = 0;
            }
            double d2 = (double) i7;
            double d3 = (double) (i6 - 128);
            double d4 = (double) (i5 - 128);
            iArr[readBits2] = getColor((byte) (255 - (i4 & 255)), Util.constrainValue((int) ((1.402d * d3) + d2), 0, 255), Util.constrainValue((int) ((d2 - (0.34414d * d4)) - (d3 * 0.71414d)), 0, 255), Util.constrainValue((int) ((d4 * 1.772d) + d2), 0, 255));
            i11 = i8;
            readBits = readBits;
            i9 = 8;
            i10 = 2;
        }
        return new ClutDefinition(readBits, generateDefault2BitClutEntries, generateDefault4BitClutEntries, generateDefault8BitClutEntries);
    }

    private static DisplayDefinition parseDisplayDefinition(ParsableBitArray parsableBitArray) {
        int i3;
        int i4;
        int i5;
        int i6;
        parsableBitArray.skipBits(4);
        boolean readBit = parsableBitArray.readBit();
        parsableBitArray.skipBits(3);
        int readBits = parsableBitArray.readBits(16);
        int readBits2 = parsableBitArray.readBits(16);
        if (readBit) {
            int readBits3 = parsableBitArray.readBits(16);
            int readBits4 = parsableBitArray.readBits(16);
            int readBits5 = parsableBitArray.readBits(16);
            i3 = parsableBitArray.readBits(16);
            i5 = readBits4;
            i4 = readBits5;
            i6 = readBits3;
        } else {
            i6 = 0;
            i4 = 0;
            i5 = readBits;
            i3 = readBits2;
        }
        return new DisplayDefinition(readBits, readBits2, i6, i5, i4, i3);
    }

    private static ObjectData parseObjectData(ParsableBitArray parsableBitArray) {
        byte[] bArr;
        int readBits = parsableBitArray.readBits(16);
        parsableBitArray.skipBits(4);
        int readBits2 = parsableBitArray.readBits(2);
        boolean readBit = parsableBitArray.readBit();
        parsableBitArray.skipBits(1);
        byte[] bArr2 = Util.EMPTY_BYTE_ARRAY;
        if (readBits2 == 1) {
            parsableBitArray.skipBits(parsableBitArray.readBits(8) * 16);
        } else if (readBits2 == 0) {
            int readBits3 = parsableBitArray.readBits(16);
            int readBits4 = parsableBitArray.readBits(16);
            if (readBits3 > 0) {
                bArr2 = new byte[readBits3];
                parsableBitArray.readBytes(bArr2, 0, readBits3);
            }
            if (readBits4 > 0) {
                bArr = new byte[readBits4];
                parsableBitArray.readBytes(bArr, 0, readBits4);
                return new ObjectData(readBits, readBit, bArr2, bArr);
            }
        }
        bArr = bArr2;
        return new ObjectData(readBits, readBit, bArr2, bArr);
    }

    private static PageComposition parsePageComposition(ParsableBitArray parsableBitArray, int i3) {
        int readBits = parsableBitArray.readBits(8);
        int readBits2 = parsableBitArray.readBits(4);
        int readBits3 = parsableBitArray.readBits(2);
        parsableBitArray.skipBits(2);
        int i4 = i3 - 2;
        SparseArray sparseArray = new SparseArray();
        while (i4 > 0) {
            int readBits4 = parsableBitArray.readBits(8);
            parsableBitArray.skipBits(8);
            i4 -= 6;
            sparseArray.put(readBits4, new PageRegion(parsableBitArray.readBits(16), parsableBitArray.readBits(16)));
        }
        return new PageComposition(readBits, readBits2, readBits3, sparseArray);
    }

    private static RegionComposition parseRegionComposition(ParsableBitArray parsableBitArray, int i3) {
        int i4;
        int i5;
        int i6;
        ParsableBitArray parsableBitArray2 = parsableBitArray;
        int readBits = parsableBitArray2.readBits(8);
        parsableBitArray2.skipBits(4);
        boolean readBit = parsableBitArray.readBit();
        parsableBitArray2.skipBits(3);
        int i7 = 16;
        int readBits2 = parsableBitArray2.readBits(16);
        int readBits3 = parsableBitArray2.readBits(16);
        int readBits4 = parsableBitArray2.readBits(3);
        int readBits5 = parsableBitArray2.readBits(3);
        int i8 = 2;
        parsableBitArray2.skipBits(2);
        int readBits6 = parsableBitArray2.readBits(8);
        int readBits7 = parsableBitArray2.readBits(8);
        int readBits8 = parsableBitArray2.readBits(4);
        int readBits9 = parsableBitArray2.readBits(2);
        parsableBitArray2.skipBits(2);
        int i9 = i3 - 10;
        SparseArray sparseArray = new SparseArray();
        while (i9 > 0) {
            int readBits10 = parsableBitArray2.readBits(i7);
            int readBits11 = parsableBitArray2.readBits(i8);
            int readBits12 = parsableBitArray2.readBits(i8);
            int readBits13 = parsableBitArray2.readBits(12);
            int i10 = readBits9;
            parsableBitArray2.skipBits(4);
            int readBits14 = parsableBitArray2.readBits(12);
            int i11 = i9 - 6;
            if (readBits11 != 1) {
                i6 = 2;
                if (readBits11 != 2) {
                    i5 = 0;
                    i4 = 0;
                    i9 = i11;
                    sparseArray.put(readBits10, new RegionObject(readBits11, readBits12, readBits13, readBits14, i5, i4));
                    i8 = i6;
                    readBits9 = i10;
                    i7 = 16;
                }
            } else {
                i6 = 2;
            }
            i9 -= 8;
            i5 = parsableBitArray2.readBits(8);
            i4 = parsableBitArray2.readBits(8);
            sparseArray.put(readBits10, new RegionObject(readBits11, readBits12, readBits13, readBits14, i5, i4));
            i8 = i6;
            readBits9 = i10;
            i7 = 16;
        }
        return new RegionComposition(readBits, readBit, readBits2, readBits3, readBits4, readBits5, readBits6, readBits7, readBits8, readBits9, sparseArray);
    }

    private static void parseSubtitlingSegment(ParsableBitArray parsableBitArray, SubtitleService subtitleService2) {
        RegionComposition regionComposition;
        int readBits = parsableBitArray.readBits(8);
        int readBits2 = parsableBitArray.readBits(16);
        int readBits3 = parsableBitArray.readBits(16);
        int bytePosition = parsableBitArray.getBytePosition() + readBits3;
        if (readBits3 * 8 > parsableBitArray.bitsLeft()) {
            Log.w(TAG, "Data field length exceeds limit");
            parsableBitArray.skipBits(parsableBitArray.bitsLeft());
            return;
        }
        switch (readBits) {
            case 16:
                if (readBits2 == subtitleService2.subtitlePageId) {
                    PageComposition pageComposition = subtitleService2.pageComposition;
                    PageComposition parsePageComposition = parsePageComposition(parsableBitArray, readBits3);
                    if (parsePageComposition.state == 0) {
                        if (!(pageComposition == null || pageComposition.version == parsePageComposition.version)) {
                            subtitleService2.pageComposition = parsePageComposition;
                            break;
                        }
                    } else {
                        subtitleService2.pageComposition = parsePageComposition;
                        subtitleService2.regions.clear();
                        subtitleService2.cluts.clear();
                        subtitleService2.objects.clear();
                        break;
                    }
                }
                break;
            case 17:
                PageComposition pageComposition2 = subtitleService2.pageComposition;
                if (readBits2 == subtitleService2.subtitlePageId && pageComposition2 != null) {
                    RegionComposition parseRegionComposition = parseRegionComposition(parsableBitArray, readBits3);
                    if (pageComposition2.state == 0 && (regionComposition = subtitleService2.regions.get(parseRegionComposition.id)) != null) {
                        parseRegionComposition.mergeFrom(regionComposition);
                    }
                    subtitleService2.regions.put(parseRegionComposition.id, parseRegionComposition);
                    break;
                }
            case 18:
                if (readBits2 != subtitleService2.subtitlePageId) {
                    if (readBits2 == subtitleService2.ancillaryPageId) {
                        ClutDefinition parseClutDefinition = parseClutDefinition(parsableBitArray, readBits3);
                        subtitleService2.ancillaryCluts.put(parseClutDefinition.id, parseClutDefinition);
                        break;
                    }
                } else {
                    ClutDefinition parseClutDefinition2 = parseClutDefinition(parsableBitArray, readBits3);
                    subtitleService2.cluts.put(parseClutDefinition2.id, parseClutDefinition2);
                    break;
                }
                break;
            case 19:
                if (readBits2 != subtitleService2.subtitlePageId) {
                    if (readBits2 == subtitleService2.ancillaryPageId) {
                        ObjectData parseObjectData = parseObjectData(parsableBitArray);
                        subtitleService2.ancillaryObjects.put(parseObjectData.id, parseObjectData);
                        break;
                    }
                } else {
                    ObjectData parseObjectData2 = parseObjectData(parsableBitArray);
                    subtitleService2.objects.put(parseObjectData2.id, parseObjectData2);
                    break;
                }
                break;
            case 20:
                if (readBits2 == subtitleService2.subtitlePageId) {
                    subtitleService2.displayDefinition = parseDisplayDefinition(parsableBitArray);
                    break;
                }
                break;
        }
        parsableBitArray.skipBytes(bytePosition - parsableBitArray.getBytePosition());
    }

    public List<Cue> decode(byte[] bArr, int i3) {
        int i4;
        SparseArray<RegionObject> sparseArray;
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr, i3);
        while (parsableBitArray.bitsLeft() >= 48 && parsableBitArray.readBits(8) == 15) {
            parseSubtitlingSegment(parsableBitArray, this.subtitleService);
        }
        SubtitleService subtitleService2 = this.subtitleService;
        PageComposition pageComposition = subtitleService2.pageComposition;
        if (pageComposition == null) {
            return Collections.emptyList();
        }
        DisplayDefinition displayDefinition = subtitleService2.displayDefinition;
        if (displayDefinition == null) {
            displayDefinition = this.defaultDisplayDefinition;
        }
        Bitmap bitmap2 = this.bitmap;
        if (!(bitmap2 != null && displayDefinition.width + 1 == bitmap2.getWidth() && displayDefinition.height + 1 == this.bitmap.getHeight())) {
            Bitmap createBitmap = Bitmap.createBitmap(displayDefinition.width + 1, displayDefinition.height + 1, Bitmap.Config.ARGB_8888);
            this.bitmap = createBitmap;
            this.canvas.setBitmap(createBitmap);
        }
        ArrayList arrayList = new ArrayList();
        SparseArray<PageRegion> sparseArray2 = pageComposition.regions;
        for (int i5 = 0; i5 < sparseArray2.size(); i5++) {
            this.canvas.save();
            PageRegion valueAt = sparseArray2.valueAt(i5);
            RegionComposition regionComposition = this.subtitleService.regions.get(sparseArray2.keyAt(i5));
            int i6 = valueAt.horizontalAddress + displayDefinition.horizontalPositionMinimum;
            int i7 = valueAt.verticalAddress + displayDefinition.verticalPositionMinimum;
            this.canvas.clipRect(i6, i7, Math.min(regionComposition.width + i6, displayDefinition.horizontalPositionMaximum), Math.min(regionComposition.height + i7, displayDefinition.verticalPositionMaximum));
            ClutDefinition clutDefinition = this.subtitleService.cluts.get(regionComposition.clutId);
            if (clutDefinition == null && (clutDefinition = this.subtitleService.ancillaryCluts.get(regionComposition.clutId)) == null) {
                clutDefinition = this.defaultClutDefinition;
            }
            SparseArray<RegionObject> sparseArray3 = regionComposition.regionObjects;
            int i8 = 0;
            while (i8 < sparseArray3.size()) {
                int keyAt = sparseArray3.keyAt(i8);
                RegionObject valueAt2 = sparseArray3.valueAt(i8);
                ObjectData objectData = this.subtitleService.objects.get(keyAt);
                ObjectData objectData2 = objectData == null ? this.subtitleService.ancillaryObjects.get(keyAt) : objectData;
                if (objectData2 != null) {
                    i4 = i8;
                    sparseArray = sparseArray3;
                    paintPixelDataSubBlocks(objectData2, clutDefinition, regionComposition.depth, valueAt2.horizontalPosition + i6, i7 + valueAt2.verticalPosition, objectData2.nonModifyingColorFlag ? null : this.defaultPaint, this.canvas);
                } else {
                    i4 = i8;
                    sparseArray = sparseArray3;
                }
                i8 = i4 + 1;
                sparseArray3 = sparseArray;
            }
            if (regionComposition.fillFlag) {
                int i9 = regionComposition.depth;
                this.fillRegionPaint.setColor(i9 == 3 ? clutDefinition.clutEntries8Bit[regionComposition.pixelCode8Bit] : i9 == 2 ? clutDefinition.clutEntries4Bit[regionComposition.pixelCode4Bit] : clutDefinition.clutEntries2Bit[regionComposition.pixelCode2Bit]);
                this.canvas.drawRect((float) i6, (float) i7, (float) (regionComposition.width + i6), (float) (regionComposition.height + i7), this.fillRegionPaint);
            }
            arrayList.add(new Cue.Builder().setBitmap(Bitmap.createBitmap(this.bitmap, i6, i7, regionComposition.width, regionComposition.height)).setPosition(((float) i6) / ((float) displayDefinition.width)).setPositionAnchor(0).setLine(((float) i7) / ((float) displayDefinition.height), 0).setLineAnchor(0).setSize(((float) regionComposition.width) / ((float) displayDefinition.width)).setBitmapHeight(((float) regionComposition.height) / ((float) displayDefinition.height)).build());
            this.canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            this.canvas.restore();
        }
        return Collections.unmodifiableList(arrayList);
    }

    public void reset() {
        this.subtitleService.reset();
    }
}
