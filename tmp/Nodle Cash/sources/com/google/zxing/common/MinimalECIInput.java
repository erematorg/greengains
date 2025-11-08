package com.google.zxing.common;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MinimalECIInput implements ECIInput {
    private static final int COST_PER_ECI = 3;
    private final int[] bytes;
    private final int fnc1;

    public static final class InputEdge {
        /* access modifiers changed from: private */

        /* renamed from: c  reason: collision with root package name */
        public final char f7195c;
        /* access modifiers changed from: private */
        public final int cachedTotalSize;
        /* access modifiers changed from: private */
        public final int encoderIndex;
        /* access modifiers changed from: private */
        public final InputEdge previous;

        public boolean isFNC1() {
            return this.f7195c == 1000;
        }

        private InputEdge(char c3, ECIEncoderSet eCIEncoderSet, int i3, InputEdge inputEdge, int i4) {
            int i5;
            int i6;
            char c4 = c3 == i4 ? 1000 : c3;
            this.f7195c = c4;
            this.encoderIndex = i3;
            this.previous = inputEdge;
            if (c4 == 1000) {
                i5 = 1;
            } else {
                i5 = eCIEncoderSet.encode(c3, i3).length;
            }
            if (inputEdge == null) {
                i6 = 0;
            } else {
                i6 = inputEdge.encoderIndex;
            }
            i5 = i6 != i3 ? i5 + 3 : i5;
            this.cachedTotalSize = inputEdge != null ? i5 + inputEdge.cachedTotalSize : i5;
        }
    }

    public MinimalECIInput(String str, Charset charset, int i3) {
        this.fnc1 = i3;
        ECIEncoderSet eCIEncoderSet = new ECIEncoderSet(str, charset, i3);
        if (eCIEncoderSet.length() == 1) {
            this.bytes = new int[str.length()];
            for (int i4 = 0; i4 < this.bytes.length; i4++) {
                int charAt = str.charAt(i4);
                int[] iArr = this.bytes;
                if (charAt == i3) {
                    charAt = 1000;
                }
                iArr[i4] = charAt;
            }
            return;
        }
        this.bytes = encodeMinimally(str, eCIEncoderSet, i3);
    }

    public static void addEdge(InputEdge[][] inputEdgeArr, int i3, InputEdge inputEdge) {
        if (inputEdgeArr[i3][inputEdge.encoderIndex] == null || inputEdgeArr[i3][inputEdge.encoderIndex].cachedTotalSize > inputEdge.cachedTotalSize) {
            inputEdgeArr[i3][inputEdge.encoderIndex] = inputEdge;
        }
    }

    public static void addEdges(String str, ECIEncoderSet eCIEncoderSet, InputEdge[][] inputEdgeArr, int i3, InputEdge inputEdge, int i4) {
        int i5;
        int i6;
        ECIEncoderSet eCIEncoderSet2 = eCIEncoderSet;
        int i7 = i3;
        String str2 = str;
        int i8 = i4;
        char charAt = str.charAt(i7);
        int length = eCIEncoderSet.length();
        if (eCIEncoderSet.getPriorityEncoderIndex() < 0 || (charAt != i8 && !eCIEncoderSet2.canEncode(charAt, eCIEncoderSet.getPriorityEncoderIndex()))) {
            i5 = length;
            i6 = 0;
        } else {
            i6 = eCIEncoderSet.getPriorityEncoderIndex();
            i5 = i6 + 1;
        }
        for (int i9 = i6; i9 < i5; i9++) {
            if (charAt == i8 || eCIEncoderSet2.canEncode(charAt, i9)) {
                addEdge(inputEdgeArr, i7 + 1, new InputEdge(charAt, eCIEncoderSet, i9, inputEdge, i4));
            } else {
                InputEdge[][] inputEdgeArr2 = inputEdgeArr;
            }
        }
    }

    public static int[] encodeMinimally(String str, ECIEncoderSet eCIEncoderSet, int i3) {
        int length = str.length();
        int[] iArr = new int[2];
        iArr[1] = eCIEncoderSet.length();
        iArr[0] = length + 1;
        InputEdge[][] inputEdgeArr = (InputEdge[][]) Array.newInstance(InputEdge.class, iArr);
        addEdges(str, eCIEncoderSet, inputEdgeArr, 0, (InputEdge) null, i3);
        for (int i4 = 1; i4 <= length; i4++) {
            for (int i5 = 0; i5 < eCIEncoderSet.length(); i5++) {
                InputEdge inputEdge = inputEdgeArr[i4][i5];
                if (inputEdge != null && i4 < length) {
                    addEdges(str, eCIEncoderSet, inputEdgeArr, i4, inputEdge, i3);
                }
            }
            for (int i6 = 0; i6 < eCIEncoderSet.length(); i6++) {
                inputEdgeArr[i4 - 1][i6] = null;
            }
        }
        int i7 = -1;
        int i8 = Integer.MAX_VALUE;
        for (int i9 = 0; i9 < eCIEncoderSet.length(); i9++) {
            InputEdge inputEdge2 = inputEdgeArr[length][i9];
            if (inputEdge2 != null && inputEdge2.cachedTotalSize < i8) {
                i8 = inputEdge2.cachedTotalSize;
                i7 = i9;
            }
        }
        if (i7 >= 0) {
            ArrayList arrayList = new ArrayList();
            for (InputEdge inputEdge3 = inputEdgeArr[length][i7]; inputEdge3 != null; inputEdge3 = inputEdge3.previous) {
                if (inputEdge3.isFNC1()) {
                    arrayList.add(0, 1000);
                } else {
                    byte[] encode = eCIEncoderSet.encode(inputEdge3.f7195c, inputEdge3.encoderIndex);
                    for (int length2 = encode.length - 1; length2 >= 0; length2--) {
                        arrayList.add(0, Integer.valueOf(encode[length2] & 255));
                    }
                }
                if ((inputEdge3.previous == null ? 0 : inputEdge3.previous.encoderIndex) != inputEdge3.encoderIndex) {
                    arrayList.add(0, Integer.valueOf(eCIEncoderSet.getECIValue(inputEdge3.encoderIndex) + 256));
                }
            }
            int size = arrayList.size();
            int[] iArr2 = new int[size];
            for (int i10 = 0; i10 < size; i10++) {
                iArr2[i10] = ((Integer) arrayList.get(i10)).intValue();
            }
            return iArr2;
        }
        throw new IllegalStateException(a.l("Failed to encode \"", str, "\""));
    }

    public char charAt(int i3) {
        if (i3 < 0 || i3 >= length()) {
            throw new IndexOutOfBoundsException(a.k("", i3));
        } else if (!isECI(i3)) {
            return (char) (isFNC1(i3) ? this.fnc1 : this.bytes[i3]);
        } else {
            throw new IllegalArgumentException(C0118y.c(i3, "value at ", " is not a character but an ECI"));
        }
    }

    public int getECIValue(int i3) {
        if (i3 < 0 || i3 >= length()) {
            throw new IndexOutOfBoundsException(a.k("", i3));
        } else if (isECI(i3)) {
            return this.bytes[i3] - 256;
        } else {
            throw new IllegalArgumentException(C0118y.c(i3, "value at ", " is not an ECI but a character"));
        }
    }

    public int getFNC1Character() {
        return this.fnc1;
    }

    public boolean haveNCharacters(int i3, int i4) {
        if ((i3 + i4) - 1 >= this.bytes.length) {
            return false;
        }
        for (int i5 = 0; i5 < i4; i5++) {
            if (isECI(i3 + i5)) {
                return false;
            }
        }
        return true;
    }

    public boolean isECI(int i3) {
        if (i3 < 0 || i3 >= length()) {
            throw new IndexOutOfBoundsException(a.k("", i3));
        }
        int i4 = this.bytes[i3];
        return i4 > 255 && i4 <= 999;
    }

    public boolean isFNC1(int i3) {
        if (i3 >= 0 && i3 < length()) {
            return this.bytes[i3] == 1000;
        }
        throw new IndexOutOfBoundsException(a.k("", i3));
    }

    public int length() {
        return this.bytes.length;
    }

    public CharSequence subSequence(int i3, int i4) {
        if (i3 < 0 || i3 > i4 || i4 > length()) {
            throw new IndexOutOfBoundsException(a.k("", i3));
        }
        StringBuilder sb = new StringBuilder();
        while (i3 < i4) {
            if (!isECI(i3)) {
                sb.append(charAt(i3));
                i3++;
            } else {
                throw new IllegalArgumentException(C0118y.c(i3, "value at ", " is not a character but an ECI"));
            }
        }
        return sb;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < length(); i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            if (isECI(i3)) {
                sb.append("ECI(");
                sb.append(getECIValue(i3));
                sb.append(')');
            } else if (charAt(i3) < 128) {
                sb.append('\'');
                sb.append(charAt(i3));
                sb.append('\'');
            } else {
                sb.append(charAt(i3));
            }
        }
        return sb.toString();
    }
}
