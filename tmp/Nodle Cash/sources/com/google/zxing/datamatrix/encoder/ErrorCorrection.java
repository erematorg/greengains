package com.google.zxing.datamatrix.encoder;

import A.a;
import androidx.recyclerview.widget.ItemTouchHelper;
import jnr.ffi.provider.jffi.JNINativeInterface;

public final class ErrorCorrection {
    private static final int[] ALOG = new int[255];
    private static final int[][] FACTORS = {new int[]{JNINativeInterface.ExceptionCheck, 48, 15, 111, 62}, new int[]{23, 68, 144, 134, 240, 92, 254}, new int[]{28, 24, 185, 166, JNINativeInterface.ReleasePrimitiveArrayCritical, 248, 116, 255, 110, 61}, new int[]{175, 138, 205, 12, 194, 168, 39, 245, 60, 97, 120}, new int[]{41, 153, 158, 91, 61, 42, 142, JNINativeInterface.SetFloatArrayRegion, 97, 178, 100, 242}, new int[]{156, 97, 192, 252, 95, 9, 157, 119, 138, 45, 18, 186, 83, 185}, new int[]{83, 195, 100, 39, 188, 75, 66, 61, 241, JNINativeInterface.SetFloatArrayRegion, 109, 129, 94, 254, JNINativeInterface.ReleaseStringCritical, 48, 90, 188}, new int[]{15, 195, 244, 9, 233, 71, 168, 2, 188, 160, 153, 145, 253, 79, 108, 82, 27, 174, 186, 172}, new int[]{52, 190, 88, 205, 109, 39, 176, 21, 155, 197, 251, JNINativeInterface.ReleasePrimitiveArrayCritical, 155, 21, 5, 172, 254, 124, 12, 181, 184, 96, 50, 193}, new int[]{211, JNINativeInterface.GetDirectBufferCapacity, 43, 97, 71, 96, 103, 174, 37, 151, 170, 53, 75, 34, 249, 121, 17, 138, 110, JNINativeInterface.SetFloatArrayRegion, 141, 136, 120, 151, 233, 168, 93, 255}, new int[]{245, 127, 242, JNINativeInterface.MonitorExit, 130, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 162, 181, 102, 120, 84, 179, JNINativeInterface.GetStringRegion, 251, 80, 182, JNINativeInterface.NewDirectByteBuffer, 18, 2, 4, 68, 33, 101, 137, 95, 119, 115, 44, 175, 184, 59, 25, JNINativeInterface.ReleaseStringCritical, 98, 81, 112}, new int[]{77, 193, 137, 31, 19, 38, 22, 153, 247, 105, 122, 2, 245, 133, 242, 8, 175, 95, 100, 9, 167, 105, JNINativeInterface.SetDoubleArrayRegion, 111, 57, 121, 21, 1, 253, 57, 54, 101, 248, 202, 69, 50, 150, 177, JNINativeInterface.NewWeakGlobalRef, 5, 9, 5}, new int[]{245, 132, 172, JNINativeInterface.ReleasePrimitiveArrayCritical, 96, 32, 117, 22, 238, 133, 238, JNINativeInterface.GetDirectBufferCapacity, 205, 188, 237, 87, 191, 106, 16, 147, 118, 23, 37, 90, 170, 205, 131, 88, 120, 100, 66, 138, 186, 240, 82, 44, 176, 87, 187, 147, 160, 175, 69, JNINativeInterface.SetFloatArrayRegion, 92, 253, JNINativeInterface.ReleaseStringCritical, 19}, new int[]{175, 9, JNINativeInterface.ReleasePrimitiveArrayCritical, 238, 12, 17, JNINativeInterface.GetStringRegion, JNINativeInterface.SetByteArrayRegion, 100, 29, 175, 170, JNINativeInterface.GetDirectBufferAddress, 192, JNINativeInterface.RegisterNatives, 235, 150, 159, 36, JNINativeInterface.ReleasePrimitiveArrayCritical, 38, 200, 132, 54, JNINativeInterface.ExceptionCheck, 146, JNINativeInterface.MonitorExit, 234, 117, 203, 29, JNINativeInterface.GetObjectRefType, 144, 238, 22, 150, 201, 117, 62, 207, 164, 13, 137, 245, 127, 67, 247, 28, 155, 43, 203, 107, 233, 53, 143, 46}, new int[]{242, 93, 169, 50, 144, JNINativeInterface.SetShortArrayRegion, 39, 118, 202, 188, 201, 189, 143, 108, 196, 37, 185, 112, 134, JNINativeInterface.GetDirectBufferAddress, 245, 63, 197, 190, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 106, 185, JNINativeInterface.GetStringUTFRegion, 175, 64, 114, 71, 161, 44, 147, 6, 27, JNINativeInterface.MonitorExit, 51, 63, 87, 10, 40, 130, 188, 17, 163, 31, 176, 170, 4, 107, JNINativeInterface.GetObjectRefType, 7, 94, 166, 224, 124, 86, 47, 11, 204}, new int[]{JNINativeInterface.GetStringRegion, JNINativeInterface.ExceptionCheck, 173, 89, 251, 149, 159, 56, 89, 33, 147, 244, 154, 36, 73, 127, JNINativeInterface.SetFloatArrayRegion, 136, 248, 180, 234, 197, 158, 177, 68, 122, 93, JNINativeInterface.SetFloatArrayRegion, 15, 160, JNINativeInterface.DeleteWeakGlobalRef, 236, 66, 139, 153, 185, 202, 167, 179, 25, JNINativeInterface.GetStringRegion, JNINativeInterface.GetObjectRefType, 96, JNINativeInterface.SetShortArrayRegion, JNINativeInterface.GetDirectBufferCapacity, 136, JNINativeInterface.ReleasePrimitiveArrayCritical, 239, 181, 241, 59, 52, 172, 25, 49, JNINativeInterface.GetObjectRefType, 211, 189, 64, 54, 108, 153, 132, 63, 96, 103, 82, 186}};
    private static final int[] FACTOR_SETS = {5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 36, 42, 48, 56, 62, 68};
    private static final int[] LOG = new int[256];
    private static final int MODULO_VALUE = 301;

    static {
        int i3 = 1;
        for (int i4 = 0; i4 < 255; i4++) {
            ALOG[i4] = i3;
            LOG[i3] = i4;
            i3 *= 2;
            if (i3 >= 256) {
                i3 ^= 301;
            }
        }
    }

    private ErrorCorrection() {
    }

    private static String createECCBlock(CharSequence charSequence, int i3) {
        int i4;
        int i5;
        int i6 = 0;
        while (true) {
            int[] iArr = FACTOR_SETS;
            if (i6 >= iArr.length) {
                i6 = -1;
                break;
            } else if (iArr[i6] == i3) {
                break;
            } else {
                i6++;
            }
        }
        if (i6 >= 0) {
            int[] iArr2 = FACTORS[i6];
            char[] cArr = new char[i3];
            for (int i7 = 0; i7 < i3; i7++) {
                cArr[i7] = 0;
            }
            for (int i8 = 0; i8 < charSequence.length(); i8++) {
                int i9 = i3 - 1;
                char charAt = cArr[i9] ^ charSequence.charAt(i8);
                while (i9 > 0) {
                    if (charAt == 0 || (i5 = iArr2[i9]) == 0) {
                        cArr[i9] = cArr[i9 - 1];
                    } else {
                        char c3 = cArr[i9 - 1];
                        int[] iArr3 = ALOG;
                        int[] iArr4 = LOG;
                        cArr[i9] = (char) (iArr3[(iArr4[charAt] + iArr4[i5]) % 255] ^ c3);
                    }
                    i9--;
                }
                if (charAt == 0 || (i4 = iArr2[0]) == 0) {
                    cArr[0] = 0;
                } else {
                    int[] iArr5 = ALOG;
                    int[] iArr6 = LOG;
                    cArr[0] = (char) iArr5[(iArr6[charAt] + iArr6[i4]) % 255];
                }
            }
            char[] cArr2 = new char[i3];
            for (int i10 = 0; i10 < i3; i10++) {
                cArr2[i10] = cArr[(i3 - i10) - 1];
            }
            return String.valueOf(cArr2);
        }
        throw new IllegalArgumentException(a.k("Illegal number of error correction codewords specified: ", i3));
    }

    public static String encodeECC200(String str, SymbolInfo symbolInfo) {
        if (str.length() == symbolInfo.getDataCapacity()) {
            StringBuilder sb = new StringBuilder(symbolInfo.getErrorCodewords() + symbolInfo.getDataCapacity());
            sb.append(str);
            int interleavedBlockCount = symbolInfo.getInterleavedBlockCount();
            if (interleavedBlockCount == 1) {
                sb.append(createECCBlock(str, symbolInfo.getErrorCodewords()));
            } else {
                sb.setLength(sb.capacity());
                int[] iArr = new int[interleavedBlockCount];
                int[] iArr2 = new int[interleavedBlockCount];
                int i3 = 0;
                while (i3 < interleavedBlockCount) {
                    int i4 = i3 + 1;
                    iArr[i3] = symbolInfo.getDataLengthForInterleavedBlock(i4);
                    iArr2[i3] = symbolInfo.getErrorLengthForInterleavedBlock(i4);
                    i3 = i4;
                }
                for (int i5 = 0; i5 < interleavedBlockCount; i5++) {
                    StringBuilder sb2 = new StringBuilder(iArr[i5]);
                    for (int i6 = i5; i6 < symbolInfo.getDataCapacity(); i6 += interleavedBlockCount) {
                        sb2.append(str.charAt(i6));
                    }
                    String createECCBlock = createECCBlock(sb2.toString(), iArr2[i5]);
                    int i7 = 0;
                    int i8 = i5;
                    while (i8 < iArr2[i5] * interleavedBlockCount) {
                        sb.setCharAt(symbolInfo.getDataCapacity() + i8, createECCBlock.charAt(i7));
                        i8 += interleavedBlockCount;
                        i7++;
                    }
                }
            }
            return sb.toString();
        }
        throw new IllegalArgumentException("The number of codewords does not match the selected symbol");
    }
}
